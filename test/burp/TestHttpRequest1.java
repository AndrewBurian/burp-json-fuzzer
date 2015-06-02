/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burp;

/**
 *
 * @author andrew.burian
 */
public class TestHttpRequest1 implements IHttpRequestResponse {

    private final String request = "POST /messages?_dc=1433269816411 HTTP/1.1\n"
            + "Host: lb-macell2-cpqa1-van.dev-globalrelay.net\n"
            + "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0\n"
            + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n"
            + "Accept-Language: en-US,en;q=0.5\n"
            + "Accept-Encoding: gzip, deflate\n"
            + "csrftoken: abbac963-d03e-4fed-a12b-f932d092d3be\n"
            + "Content-Type: application/json; charset=UTF-8\n"
            + "X-Requested-With: XMLHttpRequest\n"
            + "Referer: https://lb-macell2-cpqa1-van.dev-globalrelay.net/\n"
            + "Content-Length: 653\n"
            + "Cookie: JSESSIONID=c66d1f7b-69b0-4c7f-a16b-81b9378eecc5; grsh=fe2\n"
            + "Connection: keep-alive\n"
            + "Pragma: no-cache\n"
            + "Cache-Control: no-cache\n"
            + "\n"
            + "{\"selectionRequest\":\"null\",\"criteria\":\"{\\\"haswords\\\":\\\"these are keywords\\\",\\\"senderRecipient\\\""
            + ":{\\\"fromtoOper\\\":\\\"or\\\",\\\"items\\\":[{\\\"subOperator\\\":\\\"\\\",\\\"items\\\":[{\\\"type\\\":\\\"anySenders\\\""
            + ",\\\"fields\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":\\\"matt\\\"}]}]}]},\\\"subject\\\":\\\"subjectline\\\",\\\"start\\\""
            + ":0,\\\"end\\\":32990191199999,\\\"tags\\\":[{\\\"tagId\\\":\\\"570c47d7-7970-4eb6-a932-2e231b215837\\\",\\\"description\\\""
            + ":\\\"\\\"},{\\\"tagId\\\":\\\"9d1c73c0-4385-4c37-8dc7-45a16b41f606\\\",\\\"description\\\""
            + ":\\\"aaa@aa.com&quot;&gt;&lt;script&gt;alert(document.cookie)&lt;/script&gt;\\\"}],\\\"queryId\\\":1433269816299,"
            + "\\\"searchTime\\\":1433269816407}\",\"page\":1,\"start\":0,\"limit\":200,\"sortBy\":\"dateMs\",\"dir\":\"DESC\"}";

    @Override
    public byte[] getRequest() {

        return request.getBytes();
    }

    @Override
    public String toString() {
        return request;
    }

    @Override
    public void setRequest(byte[] message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getResponse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setResponse(byte[] message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getComment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setComment(String comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getHighlight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHighlight(String color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IHttpService getHttpService() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHttpService(IHttpService httpService) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
