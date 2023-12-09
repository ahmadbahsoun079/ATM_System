package Models;

import Generics.NormalAccount;
import Generics.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginModel {
    
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public LoginModel(){}
    
    public Boolean LoggingAccount(String username, String password){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sql = "SELECT cust_number, acc_type "
                    + "FROM account "
                    + "WHERE acc_number=? AND acc_pass=?";
            stmt = Main.Main.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if(rs.next()){
                int custID = rs.getInt(1);
                String acctype=rs.getString(2);
                String sql2 = "SELECT firstname FROM customer WHERE cust_ID=?";
                stmt = Main.Main.con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, custID);
                rs = stmt.executeQuery();
                if(rs.next()){
                    
                    TypeOfAccounts type;
                     
                    String name = rs.getString(1);
                    //here we check the type of the user to create the account and then store it inthe singelton class account
                    if(acctype.equals("vip")){
                        
                         type=new VIPAccount();
                         
                    }else{
                        
                         type=new NormalAccount();
                         
                    }
                    Account acc=Account.setInstance(type);
                    
                    acc.setCustomerID(custID);
                    acc.setAccNumber(Integer.valueOf(username));
                    acc.setAccPassword(Integer.valueOf(password));
                    acc.setaccounttype(acctype);
                    
                    
                   
                    acc.setGreetingTXT("Welcome back " +name);
                    acc.setUserTXT("Username: " +username);
                    
                }
            }
            else{
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
