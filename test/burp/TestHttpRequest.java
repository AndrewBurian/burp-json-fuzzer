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
public class TestHttpRequest implements IHttpRequestResponse {

    private final String request = "POST /messages?_dc=1433200424520 HTTP/1.1\n"
            + "Host: lb-macell2-cpqa1-van.dev-globalrelay.net\n"
            + "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0\n"
            + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n"
            + "Accept-Language: en-US,en;q=0.5\n"
            + "Accept-Encoding: gzip, deflate\n"
            + "csrftoken: e0da71af-ed6b-4ecb-ae2e-c7becec6ada2\n"
            + "Content-Type: application/json; charset=UTF-8\n"
            + "X-Requested-With: XMLHttpRequest\n"
            + "Referer: https://lb-macell2-cpqa1-van.dev-globalrelay.net/\n"
            + "Content-Length: 227\n"
            + "Cookie: CELL_REDIRECT_ARCHIVE_ID=f5d46078-1ae2-4155-a3a4-41e78d7fe121:177c31182550a13139a007b61f57a68b; JSESSIONID=ea7fd078-a0a9-4577-8f47-eb719494c3fa; grsh=fe2\n"
            + "Connection: keep-alive\n"
            + "Pragma: no-cache\n"
            + "Cache-Control: no-cache\n"
            + "\n"
            + "{\"selectionRequest\":\"null\",\"criteria\":\"{\\\"haswords\\\":\\\"hey-hey goodbye\\\""
            + ",\\\"start\\\":0,\\\"end\\\":32990191199999,\\\"queryId\\\":1433200424439,\\\"searchTime\\\":1433200424439}\",\"page\":1,\"start\":0,\"limit\":200,\"sortBy\":\"dateMs\",\"dir\":\"DESC\"}";

    @Override
    public byte[] getRequest() {

        return request.getBytes();
    }
    
    @Override
    public String toString(){
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
