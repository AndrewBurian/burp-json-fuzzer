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
public class TestParameter implements IParameter {

    @Override
    public byte getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValue() {
        // I'll just parse this too
        // oh wait
        // no I won't
        return "\n"
                + "{\"selectionRequest\":\"null\",\"criteria\":\"{\\\"haswords\\\":\\\"hey-hey goodbye\\\""
                + ",\\\"start\\\":0,\\\"end\\\":32990191199999,\\\"queryId\\\":1433200424439,\\\"searchTime\\\":1433200424439}\",\"page\":1,\"start\":0,\"limit\":200,\"sortBy\":\"dateMs\",\"dir\":\"DESC\"}";
    }

    @Override
    public int getNameStart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNameEnd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getValueStart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getValueEnd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
