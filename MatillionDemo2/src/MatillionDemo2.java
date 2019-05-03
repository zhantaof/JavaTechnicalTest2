/**
 * An arbitrary class for Matillion Java Technical Test 2.
 * @author Zhantao Fang
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class MatillionDemo2
{
    // ----------------------------------------------------------------------
    // Class variables
    // ----------------------------------------------------------------------
    static Connection connection = null;
    static PreparedStatement statement = null;

    // ----------------------------------------------------------------------
    // Public methods
    // ----------------------------------------------------------------------
 
    /**
     * A method to establish connection with the database.
     * 
     * @return a flag indicating whether the connection was established successfully or not.
     */
    public Boolean makeDBConnection()
    {
        String hostname = "jdbc:mysql://mysql-technical-test.cq5i4y35n9gg.eu-west-1.rds.amazonaws.com:3306/foodmart";
        String username = "technical_test";
        String password = "HopefullyProspectiveDevsDontBreakMe";
       
        try
        {
            connection = DriverManager.getConnection( hostname, username, password );
            if ( connection != null )
            {
                System.out.println( "Successfully connected to \"" + hostname + "\"" );
                return true;
            } // if
            else
                throw new SQLException();
            
        } // try
        catch ( Exception exception )
        {
            System.out.println( "Failed connecting to \"" + hostname + "\"" );
            System.out.println( "Exception message: " + exception.getMessage() );
            System.err.println( exception );
            return false;
        } // catch
    } // makeDBConnection
    
    /**
     * A method to get data from the database
     * 
     * @param department description of the user.
     * @param pay_type of the user.
     * @param educational_level of the user.
     * @return a flag indicating success or failure.
     */
    public Boolean getDataFromDB( String department, String pay_type, String education_level )
    {
        try
        {
            if ( department.isEmpty() || pay_type.isEmpty() || education_level.isEmpty() )
                throw new NullPointerException();
            
            // print graphic for table headings
            System.out.println( "+=============+===========================+===============+===========================+============+============+=========+======================+========+==========+" );
            System.out.println( "| Employee ID |         Full Name         | Department ID |   Department Description  | Birth Date | Hire Date  | Salary  |   Education Level    | Gender | Pay Type |" );
            System.out.println( "+=============+===========================+===============+===========================+============+============+=========+======================+========+==========+" );
            
            // generate a query statement
            String query = "SELECT e.employee_id, e.full_name, d.department_id, d.department_description, "
                + "e.birth_date, e.hire_date, e.salary, e.education_level, e.gender, p.pay_type "
                + "FROM department d JOIN employee e ON d.department_id = e.department_id "
                + "AND d.department_description = '" + department + "' "
                + "JOIN position p ON p.position_id = e.position_id "
                + "AND p.pay_type = '" + pay_type + "' AND e.education_level = '"+ education_level + "' "
                + "ORDER BY e.employee_id"; 
 
            // prepare statement based on query
            statement = connection.prepareStatement( query );
            // execute the query, return java result set
            ResultSet rs = statement.executeQuery();
            // get the metadata from java result set
            ResultSetMetaData rsmd = rs.getMetaData();
            int numOfColumns = rsmd.getColumnCount();
            
            if ( !rs.next() )
            {
                System.out.println( "|         N/A |                       N/A |           N/A |                       N/A |        N/A |       N/A  |    N/A  |                  N/A |    N/A |      N/A |" );
                System.out.println( "+=============+===========================+===============+===========================+============+============+=========+======================+========+==========+" );
                System.out.println( "Result Set is empty, data does not exist in database." );
                return false;
            } // if
            else
            {
                do
                {
                    // numOfColumns starts at 1
                    for ( int i = 1; i <= numOfColumns; i++ )
                    {
                        if ( i == 1 )
                            System.out.print( "| " );
                    
                        // get the value of the table column
                        String columnValue = rs.getString( i );
                    
                        // formatting the graphic with padding, spacing and column info
                        if ( ( rsmd.getColumnName( i ) ).equals( "hire_date" ) )
                            System.out.printf( "%.10s | ", columnValue );
                        else if ( ( rsmd.getColumnName( i ) ).equals( "employee_id" ) )
                            System.out.printf( "%11.11s | ", columnValue );
                        else if ( ( rsmd.getColumnName( i ) ).equals( "full_name" ) )
                            System.out.printf( "%25.30s | ", columnValue );
                        else if ( ( rsmd.getColumnName( i ) ).equals( "department_id" ) )
                            System.out.printf( "%13.11s | ", columnValue );
                        else if ( ( rsmd.getColumnName( i ) ).equals( "department_description" ) )
                            System.out.printf( "%25.25s | ", columnValue );
                        else if ( ( rsmd.getColumnName( i ) ).equals( "salary" ) )
                            System.out.printf( "%2.7s | ", columnValue );
                        else if ( ( rsmd.getColumnName( i ) ).equals( "education_level" ) )
                            System.out.printf( "%20.20s | ", columnValue );
                        else if ( ( rsmd.getColumnName( i ) ).equals( "gender" ) )
                            System.out.printf( "%6.3s | ", columnValue );
                        else if ( ( rsmd.getColumnName( i ) ).equals( "pay_type" ) )
                            System.out.printf( "%8.10s | ", columnValue );
                        else 
                            System.out.printf( "%.11s | ", columnValue );
                    
                    } // for
                    System.out.println( "" );
                } while ( rs.next() );
            } // else
            // print bottom border
            System.out.println( "+=============+===========================+===============+===========================+============+============+=========+======================+========+==========+" );
            return true;
            
        } // try
        catch ( NullPointerException exception )
        {
            System.out.println( "Please make sure all of the three strings are not null or empty." );
            System.out.println( "Exception message: " + exception.getMessage() );
            System.err.println( exception );
            return false;
        } // catch
        catch ( Exception exception ) 
        {
            System.out.println( "Exception message: " + exception.getMessage() );
            System.err.println( exception );
            return false;
        } // catch
        
    } // getDataFromDB
    
    /**
     * A method to close database connection
     * 
     * @return a flag indicating success or failure
     */
    public Boolean closeDBConnection()
    {
        try
        {
            if ( !statement.isClosed() )
                statement.close();
            if ( !connection.isClosed() )
                connection.close();
            
            if ( connection.isClosed() && statement.isClosed() )
            {
                System.out.println( "Successfully closed database connection." );
                return true;
            }
            else
                throw new SQLException();
        }
        catch ( Exception exception )
        {
            System.out.println( "Exception message: " + exception.getMessage() );
            System.err.println( exception );
            return false;
        }
        
    } // closeDBConnection
    
    
    // ----------------------------------------------------------------------
    // Protected methods
    // ----------------------------------------------------------------------
 
    // ----------------------------------------------------------------------
    // Private methods
    // ----------------------------------------------------------------------
 
    
    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------
    
    public static void main( String[] args )
    {
        try
        {
            System.out.println( "Hello, World!" );
            
            // create new class object
            MatillionDemo2 tester = new MatillionDemo2();
            tester.makeDBConnection();
            
            System.out.println( "Enter your department description (e.g. temp stockers): " );
            Scanner scanner = new Scanner(System.in);
            String department = scanner.nextLine();
            
            System.out.println( "Enter your pay type (e.g. hourly): " );
            String pay_type = scanner.nextLine();

            System.out.println( "Enter your education level (e.g. graduate degree): " );
            String education_level = scanner.nextLine();
            
            tester.getDataFromDB( department, pay_type, education_level );
            
            // closing connections and releasing resources
            scanner.close();
            tester.closeDBConnection();
            
        } // try
        catch ( Exception exception )
        {
            System.out.println( "Exception message: " + exception.getMessage() );
            System.err.println( exception );
        } // catch
        
    } // main
} // MatillionDemo2