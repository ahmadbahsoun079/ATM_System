package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperationsModel {
    
    private static int acc_number, acc_pass, cust_ID;
    private static String txt_username, txt_greeting;
    
    public static void setUserTXT(String username){
        OperationsModel.txt_username = username;
    }
    public static void setGreetingTXT(String greeting){
        OperationsModel.txt_greeting = greeting;
    }
    public static void setAccNumber(int acc_number){
        OperationsModel.acc_number = acc_number;
    }
    public static void setAccPassword(int acc_pass){
        OperationsModel.acc_pass = acc_pass;
    }
    public static void setCustomerID(int cust_ID){
        OperationsModel.cust_ID = cust_ID;
    }
    public static int getAccNumber(){
        return OperationsModel.acc_number;
    }
    public static int getAccPassword(){
        return OperationsModel.acc_pass;
    }
    public static int getCustID(){
        return OperationsModel.cust_ID;
    }
    public static String getUserTXT(){
        return OperationsModel.txt_username;
    }
    public static String getGreetingTXT(){
        return OperationsModel.txt_greeting;
    }
    
    public int getBalance() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sql = "SELECT acc_balance FROM account WHERE acc_number=?";
            PreparedStatement stmt = Main.Main.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,OperationsModel.getAccNumber());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OperationsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
