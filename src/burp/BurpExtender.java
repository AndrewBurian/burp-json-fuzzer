/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burp;

import java.util.List;
import java.io.PrintWriter;
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
        
        stdout.println(NAME + " initialized.");
    }

    /**
     * Called by Burp when a scan is going to be initiated on a given base
     * request.
     * @param baseRequestResponse
     * @return 
     */
    @Override
    public List<IScannerInsertionPoint> getInsertionPoints(IHttpRequestResponse baseRequestResponse) {
        
        // retrieve the data parameter
        IParameter dataParameter = helpers.getRequestParameter(baseRequestResponse.getRequest(), "data");
        
        // If this request doesn't have data, we don't have anything for it
        if (dataParameter == null){
            return null;
        }
        
        // Get the data in string format
        String data = dataParameter.getValue();
        
        // Check if this is JSON
        int start = data.indexOf("{");
        int end = data.lastIndexOf("}");
        
        if (start == -1 || end == -1 || end < start){
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
            return null;
        }
        
        // Master JSON object now retreived, now look for nested objects
        // encoded into string values
        
        // Get the list of all keys in this object
        String keys[] = JSONObject.getNames(json);
        
        // Loop through the keys looking for strings
        for (String key : keys){
            
            String value;
            
            // see if there's a string value associated with this key
            try{
                value = json.getString(key);
            }
            catch(JSONException ex){
                continue;
            }
            
            // attempt to unstring
            value = value.replaceAll("\\\"", "\"");
            
            stdout.print(value);
            
        }
        
        return null;
        
    }
    
    /**
     * 
     */
    private class InsertionPoint implements IScannerInsertionPoint
    {

        @Override
        public String getInsertionPointName() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getBaseValue() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public byte[] buildRequest(byte[] payload) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int[] getPayloadOffsets(byte[] payload) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public byte getInsertionPointType() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
