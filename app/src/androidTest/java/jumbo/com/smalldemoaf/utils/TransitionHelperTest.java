package jumbo.com.smalldemoaf.utils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * TransitionHelper Tester.
 *
 * @author jumbo chu
 * @version 1.0
 * @since <pre>11/05/2015</pre>
 */
public class TransitionHelperTest extends TestCase {
    public TransitionHelperTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: createSafeTransitionParticipants(@NonNull Activity activity, boolean includeStatusBar, @Nullable Pair... otherParticipants)
     */
    public void testCreateSafeTransitionParticipants() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: addNonNullViewToTransitionParticipants(View view, List<Pair> participants)
     */
    public void testAddNonNullViewToTransitionParticipants() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = TransitionHelper.getClass().getMethod("addNonNullViewToTransitionParticipants", View.class, List<Pair>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }


    public static Test suite() {
        return new TestSuite(TransitionHelperTest.class);
    }
} 
