package jumbo.com.smalldemoaf.utils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * WebClient Tester.
 *
 * @author jumbo chu
 * @version 1.0
 * @since <pre>11/05/2015</pre>
 */
public class WebClientTest extends TestCase {
    public WebClientTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: shouldOverrideUrlLoading(WebView view, String url)
     */
    public void testShouldOverrideUrlLoading() throws Exception {
//TODO: Test goes here... 
    }


    public static Test suite() {
        return new TestSuite(WebClientTest.class);
    }
} 
