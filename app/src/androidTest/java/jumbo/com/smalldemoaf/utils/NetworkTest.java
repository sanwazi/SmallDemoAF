package jumbo.com.smalldemoaf.utils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Network Tester.
 *
 * @author jumbo chu
 * @version 1.0
 * @since <pre>11/05/2015</pre>
 */
public class NetworkTest extends TestCase {
    public NetworkTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: isAvailiable(Context ctx)
     */
    public void testIsAvailiable() throws Exception {
//TODO: Test goes here... 
    }


    public static Test suite() {
        return new TestSuite(NetworkTest.class);
    }
} 
