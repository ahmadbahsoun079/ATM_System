//solve done
package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    //these is a singelton dp so that not eery time i want to open new connection to database
    private static Connection conn = null;
    private DBConnection(){};
    
    public static Connection makeConnection() throws SQLException, ClassNotFoundException{
        //here we check if were are connected before if yes return the connection if no make a new connection and return it
        if(conn == null){
            try {
                  //here is the conection string it different btw pc and pc according to server username and passwork
                 String  connectionURL = "jdbc:sqlserver://localhost;databaseName=ATM_Project;user=se;password=123456789;encrypt=true;trustServerCertificate=true";
                 conn = DriverManager.getConnection(connectionURL);
               
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        }
        
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