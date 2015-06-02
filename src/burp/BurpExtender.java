/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burp;

import java.util.List;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;
import org.json.JSONObject;
import org.json.JSONException;

/**
 * A Burp Suite extension that targets nested JSON requests such as those used
 * in extJS.
 * Burp already targets JSON values, however, some applications like extJS
 * encode nested JSON objects as strings, which Burp does not get.
 * @author andrew.burian
 */
public class BurpExtender implements IBurpExtender, IScannerInsertionPointProvider{
    
    private static final String NAME = "Nested JSON Fuzzer";
    private static final String VERSION = "0.1.1";
    
    private IExtensionHelpers helpers;
    private PrintWriter stdout;
    private PrintWriter stderr;

    /**
     * Initializes the extension and registers it as providing Insertion Points
     * @param callbacks 
     */
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        
        // Get any helpers provided by burpsuite
        helpers = callbacks.getHelpers();
        
        // Get outputs
        stdout = new PrintWriter(callbacks.getStdout(), true);
        stderr = new PrintWriter(callbacks.getStderr(), true);
        
        // Set the extension name
        callbacks.setExtensionName(NAME);
        
        // Register this as an insertion point provider
        callbacks.registerScannerInsertionPointProvider(this);
        
        stdout.println(NAME + " v" + VERSION + " initialized.");
    }

    /**
     * Called by Burp when a scan is going to be initiated on a given base
     * request.
     * @param baseRequestResponse
     * @return 
     */
    @Override
    public List<IScannerInsertionPoint> getInsertionPoints(IHttpRequestResponse baseRequestResponse) {
        
        stdout.println("Insertion points requested");
        
        // retrieve the data parameter
        IParameter dataParameter = helpers.getRequestParameter(baseRequestResponse.getRequest(), "data");
        
        // If this request doesn't have data, we don't have anything for it
        if (dataParameter == null){
            stderr.println("No data param");
            return null;
        }
        
        // Get the data in string format
        String data = dataParameter.getValue();
        
        // Check if this is JSON
        int start = data.indexOf("{");
        int end = data.lastIndexOf("}") + 1;
        
        if (start == -1 || end == -1 || end < start){
            stderr.println("No JSON bracketing");
            return null;
        }
        
        // Get the JSON String
        String jsonString = data.substring(start, end);
        
        JSONObject json;
        // Check to see if it's valid
        try{
            json = new JSONObject(jsonString);
        }
        catch(JSONException ex){
            // not valid json
            stderr.println("Not valid JSON");
            return null;
        }
        
        // Master JSON object now retreived, now look for nested objects
        // encoded into string values
        stdout.println("Valid JSON payload");
        
        List<IScannerInsertionPoint> points = new ArrayList<>();
        
        // Load up the regex to find them
        Pattern stringJsonRegex = 
                Pattern.compile("((\\\\\\\"[^\\\\\\\"]+\\\\\\\")(:\\\\\\\"[^\\\\\\\"]*\\\\\\\"|:-?\\d+(\\.\\d+)?([eE]-?\\d+)?))");
        
        Matcher matches = stringJsonRegex.matcher(baseRequestResponse.toString());
        
        while(matches.find()){
            stdout.format("Found the object <%s> between %d:%d\n", matches.group(), matches.start(), matches.end());
            points.add(new InsertionPoint(baseRequestResponse.getRequest(), matches.start(), matches.end()));
        }
        
        if(points.isEmpty()){
            return null;
        }
        
        return points;
        
    }
    
    /**
     * 
     */
    private class InsertionPoint implements IScannerInsertionPoint
    {
        private final String insertionPointPrefix;
        private final String insertionPointSuffix;
        private final String baseValue;
        private final String name;
        private final int offset;
        
        InsertionPoint(byte[] baseRequest, int startIndex, int endIndex) {
            
            // Convert the entire base request into a string
            String baseRequestStr = new String(baseRequest);
            
            // grab the section identified by the regex
            String baseValueTmp = baseRequestStr.substring(startIndex, endIndex);
            
            // find the seperator
            int midIndex = baseValueTmp.indexOf(":") + 1;
            
            // use the key as the name of this insertion point
            name = baseValueTmp.substring(0, midIndex - 1).replaceAll("\\\"", "");
            
            // move the start index to grab the value
            startIndex += midIndex;
            baseValue = baseRequestStr.substring(startIndex, endIndex);
            offset = startIndex;
            
            // take before and after the base value and store them seperatly
            insertionPointPrefix = baseRequestStr.substring(0, startIndex);
            insertionPointSuffix = baseRequestStr.substring(endIndex);
            
        }

        @Override
        public String getInsertionPointName() {
            return name;
        }

        @Override
        public String getBaseValue() {
            return baseValue;
        }

        @Override
        public byte[] buildRequest(byte[] payload) {
            return (insertionPointPrefix + "\\\"" + (new String(payload)).replaceAll("\"", "\\\"") + "\\\"" + insertionPointSuffix).getBytes();
        }

        @Override
        public int[] getPayloadOffsets(byte[] payload) {
            int offsets[] = { offset, offset + payload.length };
            return offsets;
        }

        @Override
        public byte getInsertionPointType() {
            return INS_EXTENSION_PROVIDED;
        }
        
    }
    
}
