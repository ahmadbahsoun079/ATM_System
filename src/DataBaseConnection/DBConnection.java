//solve done
package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static Connection conn = null;
    private DBConnection(){};
    
    public static Connection makeConnection() throws SQLException, ClassNotFoundException{
        if(conn == null){
            try {
             /*   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;databaseName=ATM_Project";
                String user = "sa";
                String pass = "123456789"; */
                 String  connectionURL = "jdbc:sqlserver://localhost;databaseName=ATM_Project;user=se;password=123456789;encrypt=true;trustServerCertificate=true";
                 conn = DriverManager.getConnection(connectionURL);
               
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        }
        else
            System.out.println("Already connected to database...");
        return conn;
    }
    
    public static void DBDisconnect() throws SQLException {
        try{
            if(conn != null  && !conn.isClosed())
                conn.close();
        }
        catch(SQLException e){
            throw e;
        }
    }
}