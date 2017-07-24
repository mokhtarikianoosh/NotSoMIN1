package persistenceLayer;

/*********************************************************************************
 * This class defines configuration parameters for accessing a database using JDBC.
 */
public abstract class DbAccessConfig
{
    /** The fully qualified name of the JDBC driver.
     */
    static final String DB_DRIVE_NAME  = "com.mysql.jdbc.Driver";
    
    /** The database name
     */
    static final String DB_NAME        = "termProject";
    
    /** The database server name for the connection pool
     */
    static final String DB_SERVER_NAME = "localhost";

    /** The JDBC connection string/URL.
     */
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost/termProject";

    /** The database user name.
     */
    static  String DB_CONNECTION_USERNAME = "root";

    /** The password for the database user.
     */
    static  String DB_CONNECTION_PWD = "Jc20586442";

}
