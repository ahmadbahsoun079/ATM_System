package Models;

import Main.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterModel {
    
    private PreparedStatement customer = null, account = null;
    private ResultSet rs;
    
    public RegisterModel(){}
    
    public boolean checkInformation(String firstname, String lastname, int phoneNumber) {
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sql = "SELECT * FROM customer WHERE firstname=? AND lastname=? AND phone_number=?";
            customer = Main.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            customer.setString(1, firstname);
            customer.setString(2, lastname);
            customer.setInt(3, phoneNumber);
            rs = customer.executeQuery();
            if(rs.next())
                return false;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegisterModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public Boolean createAccount(String firstname, String lastname, int phoneNumber,
            String address, String BDay, String gender,String acctype) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String sql = "INSERT INTO "
                + "customer(firstname, lastname, phone_number, address, date_of_birth, gender) "
                + "VALUES(?,?,?,?,?,?);";
        customer = Main.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        customer.setString(1, firstname);
        customer.setString(2, lastname);
        customer.setInt(3, phoneNumber);
        customer.setString(4, address);
        customer.setString(5, BDay);
        customer.setString(6, gender);
        
        //customer.setString(7, acctype);
        if(customer.executeUpdate() == 1){
            System.out.println("Data insert successfully");
            rs = customer.getGeneratedKeys();
            if(rs.next()){
                int cust_ID = rs.getInt(1);
                String sql2 = "INSERT INTO account(acc_pass, acc_balance, cust_number,acc_type) VALUES(?,?,?,?);";
                account = Main.con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                int pass = (int) (Math.random() * 10000);
                account.setInt(1, pass);
                account.setInt(2, 0);
                account.setInt(3, cust_ID);
                account.setString(4, acctype);
                if(account.executeUpdate() == 1){
                    System.out.println("Account created successfully");
                    rs = account.getGeneratedKeys();
                    if(rs.next()){
                        int acc_number = rs.getInt(1);
                        Account.setCustomerID(cust_ID);
                        Account.setAccNumber(acc_number);
                        Account.setAccPassword(pass);
                        Account.setUserTXT("Username: " +acc_number);
                        Account.setGreetingTXT("Hello " +firstname);
                        rs.close();
                        customer.close();
                        account.close();
                    }
                }
            }
        }
        else{
            return false;
        }
        return true;
    }
    
    public boolean is_8Digit(int nb) {
        int count = 0;
        while(nb!=0) {
            nb /= 10;
            count++;
        }
        return count == 8;
    }
}