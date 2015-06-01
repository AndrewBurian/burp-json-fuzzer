/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burp;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andrew.burian
 */
public class BurpExtenderTest {
    
    BurpExtender instance;
    IBurpExtenderCallbacks callbacks;
    
    public BurpExtenderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        callbacks = new TestCallbacks();
        instance = new BurpExtender();
        instance.registerExtenderCallbacks(callbacks);
    }
    
    @After
    public void tearDown() {
        instance = null;
        callbacks = null;
    }

    /**
     * Test of getInsertionPoints method, of class BurpExtender.
     */
    @Test
    public void testGetInsertionPoints() {
        System.out.println("getInsertionPoints");
        IHttpRequestResponse baseRequestResponse = new TestHttpRequest();
        List<IScannerInsertionPoint> result = instance.getInsertionPoints(baseRequestResponse);
        // check that the right number of insertion points were returned
        assertEquals("Insertion points count mismatch", result.size(), 5);
    }
    
}
