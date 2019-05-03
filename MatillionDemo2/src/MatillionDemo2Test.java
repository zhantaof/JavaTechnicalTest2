/**
 * An arbitrary JUnit test class for Matillion Java Technical Test 2.
 * @author Zhantao Fang
 *
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatillionDemo2Test
{

    @Test
    void testMatillionDemo2()
    {
        // create new class object
        MatillionDemo2 tester = new MatillionDemo2();
        
        assertEquals( true, tester.makeDBConnection(), "Must be true" );
        
        assertEquals( true, tester.getDataFromDB( "temp stockers", "hourly", "graduate degree" ), "Must be true" );
        assertEquals( true, tester.getDataFromDB( "Temp Stockers", "Hourly", "Graduate Degree" ), "Must be true" );
        assertEquals( true, tester.getDataFromDB( "HQ Information Systems", "Monthly", "Bachelors Degree" ), "Must be true" );
        assertEquals( true, tester.getDataFromDB( "HQ Marketing", "Monthly", "Bachelors Degree" ), "Must be true" );
        assertEquals( true, tester.getDataFromDB( "HQ Finance and Accounting", "Monthly", "Partial College" ), "Must be true" );
        assertEquals( true, tester.getDataFromDB( "Store Management", "Monthly", "High School Degree" ), "Must be true" );
        assertEquals( true, tester.getDataFromDB( "HQ Human Resources", "Monthly", "Partial College" ), "Must be true" );
        assertEquals( true, tester.getDataFromDB( "Store Permanent butchers", "Monthly", "bachelors Degree" ), "Must be true" );
        assertEquals( true, tester.getDataFromDB( "Store information Systems", "Monthly", "partial college" ), "Must be true" );
        assertEquals( true, tester.getDataFromDB( "Temp checkers", "Hourly", "high school degree" ), "Must be true" );
        assertEquals( true, tester.getDataFromDB( "permanent Stockers", "monthly", "partial college" ), "Must be true" );
        assertEquals( true, tester.getDataFromDB( "HQ General Management", "Monthly", "Graduate Degree" ), "Must be true" );
        
        assertEquals( false, tester.getDataFromDB( "HQ General Management", "Hourly", "Graduate Degree" ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( "HQ Marketing", "Hourly", "Graduate Degree" ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( "HQ Human Resources", "Hourly", "Graduate Degree" ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( "HQ Store ", "Monthly", "Graduate Degree" ), "Must be false" );
        
        assertEquals( false, tester.getDataFromDB( " HQ General Management", "Monthly", "Graduate Degree" ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( "", "", "" ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( "3423423", "Monthly", "Graduate Degree" ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( "fsdf", "dfsdf", "gfdg" ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( " ", " ", " " ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( "", "", "" ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( "fsdfds", "", null ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( null, null, null ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( null, " ", " " ), "Must be false" );
        assertEquals( false, tester.getDataFromDB( " ", null, " " ), "Must be false" );
        
        assertEquals( true, tester.closeDBConnection(), "Must be true" );
        
    } // testMatillionDemo2

} // MatillionDemo2Test
