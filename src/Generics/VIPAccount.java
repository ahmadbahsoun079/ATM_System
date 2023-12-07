package Generics;

import Main.Main;
import Models.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VIPAccount implements TypeOfAccounts{
   
    Account acc=Account.getInstance();

    public VIPAccount() {
        
    }
    
    
    @Override
     public  float makeDeposit(float amount) throws ClassNotFoundException
    {
        
        
        try {
            Account acc2=Account.getInstance();
            
            acc2.settva((float) 0.000);
            
            String query="UPDATE account SET acc_balance=acc_balance+? WHERE acc_number = ?;";
            PreparedStatement stmt2;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            stmt2=Main.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt2.setFloat(1, amount);
            stmt2.setInt(2, acc2.getAccNumber());
            stmt2.executeUpdate();
            String sql="SELECT acc_balance FROM account WHERE acc_number = ?;";
            PreparedStatement stmt;

            stmt = Main.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, acc2.getAccNumber());
            ResultSet set =stmt.executeQuery();
            set.next();
            int c= set.getInt(1);
            System.out.println(c);
            String query2="INSERT INTO transactions(trans_name,trans_amount,trans_date,acc_num1) VALUES(?,?,?,?);";
            PreparedStatement stmt3=Main.con.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
            DateTimeFormatter date=DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
            LocalDateTime time=LocalDateTime.now();

            stmt3.setString(1, "Deposit");
            stmt3.setFloat(2, amount);
            stmt3.setString(3, date.format(time));
            stmt3.setInt(4,acc2.getAccNumber());
            stmt3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amount;
    }
     
     
     public  float withdraw(float amount) throws SQLException, ClassNotFoundException{
         
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String q1="UPDATE account SET acc_balance=acc_balance-? Where acc_number=?; ";
        PreparedStatement stmt=Main.con.prepareStatement(q1,Statement.RETURN_GENERATED_KEYS);
        stmt.setFloat(1, amount);
        stmt.setInt(2, acc.getAccNumber());
        stmt.executeUpdate();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        String query2="INSERT INTO transactions(trans_name,trans_amount,trans_date,acc_num1) VALUES(?,?,?,?);";
        PreparedStatement stmt3;
        stmt3=Main.con.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
        stmt3.setString(1, "Withdraw");
        stmt3.setFloat(2, amount);
        stmt3.setString(3, dateFormat.format(date));
        stmt3.setInt(4,acc.getAccNumber());
        stmt3.executeUpdate();
        return amount;
    }

    @Override
    public String getCountryName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}