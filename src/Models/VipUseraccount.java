/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Main.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class VipUseraccount extends Account {
    
     @Override
     public  void makeDeposit(float amount) throws ClassNotFoundException
    {
        
        
        try {
            
        
            String query="UPDATE account SET acc_balance=acc_balance+? WHERE acc_number = ?;";
            PreparedStatement stmt2;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            stmt2=Main.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt2.setFloat(1, amount);
            stmt2.setInt(2, Account.getAccNumber());
            stmt2.executeUpdate();
            String sql="SELECT acc_balance FROM account WHERE acc_number = ?;";
            PreparedStatement stmt;

            stmt = Main.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Account.getAccNumber());
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
            stmt3.setInt(4,Account.getAccNumber());
            stmt3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public  void withdraw(float amount) throws SQLException, ClassNotFoundException{
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String q1="UPDATE account SET acc_balance=acc_balance-? Where acc_number=?; ";
        PreparedStatement stmt=Main.con.prepareStatement(q1,Statement.RETURN_GENERATED_KEYS);
        stmt.setFloat(1, amount);
        stmt.setInt(2, Account.getAccNumber());
        stmt.executeUpdate();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        String query2="INSERT INTO transactions(trans_name,trans_amount,trans_date,acc_num1) VALUES(?,?,?,?);";
        PreparedStatement stmt3;
        stmt3=Main.con.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
        stmt3.setString(1, "Withdraw");
        stmt3.setFloat(2, amount);
        stmt3.setString(3, dateFormat.format(date));
        stmt3.setInt(4,Account.getAccNumber());
        stmt3.executeUpdate();
    }
}
