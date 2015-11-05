package jumbo.com.smalldemoaf.database_manager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * DatabaseHelper Tester.
 *
 * @author jumbo chu
 * @version 1.0
 * @since <pre>11/05/2015</pre>
 */
public class DatabaseHelperTest extends TestCase {
    public DatabaseHelperTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: onCreate(SQLiteDatabase db)
     */
    public void testOnCreate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
     */
    public void testOnUpgrade() throws Exception {
//TODO: Test goes here... 
    }


    public static Test suite() {
        return new TestSuite(DatabaseHelperTest.class);
    }
} 
