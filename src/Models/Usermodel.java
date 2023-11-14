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
public class Usermodel {
    private static int acc;
    DateTimeFormatter d=DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
    LocalDateTime now=LocalDateTime.now();
    //done
    public void makeDeposit(int amount) throws ClassNotFoundException
    {
        try {
            String query="UPDATE account SET acc_balance=acc_balance+? WHERE acc_number = ?;";
            PreparedStatement stmt2;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            stmt2=Main.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt2.setInt(1, amount);
            stmt2.setInt(2, OperationsModel.getAccNumber());
            stmt2.executeUpdate();
            String sql="SELECT acc_balance FROM account WHERE acc_number = ?;";
            PreparedStatement stmt;

            stmt = Main.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, OperationsModel.getAccNumber());
            ResultSet set =stmt.executeQuery();
            set.next();
            int c= set.getInt(1);
            System.out.println(c);
            String query2="INSERT INTO transactions(trans_name,trans_amount,trans_date,acc_num1) VALUES(?,?,?,?);";
            PreparedStatement stmt3=Main.con.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
            DateTimeFormatter date=DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
            LocalDateTime time=LocalDateTime.now();

            stmt3.setString(1, "Deposit");
            stmt3.setInt(2, amount);
            stmt3.setString(3, date.format(time));
            stmt3.setInt(4,OperationsModel.getAccNumber());
            stmt3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usermodel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
    
    public  void withdraw(int amount) throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String q1="UPDATE account SET acc_balance=acc_balance-? Where acc_number=?; ";
        PreparedStatement stmt=Main.con.prepareStatement(q1,Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, amount);
        stmt.setInt(2, OperationsModel.getAccNumber());
        stmt.executeUpdate();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        String query2="INSERT INTO transactions(trans_name,trans_amount,trans_date,acc_num1) VALUES(?,?,?,?);";
        PreparedStatement stmt3;
        stmt3=Main.con.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
        stmt3.setString(1, "Withdraw");
        stmt3.setInt(2, amount);
        stmt3.setString(3, dateFormat.format(date));
        stmt3.setInt(4,OperationsModel.getAccNumber());
        stmt3.executeUpdate();
    }
    public  boolean checkamount(int amount) throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        int a;
        String query= "SELECT acc_balance FROM account WHERE acc_number=?;" ;
        PreparedStatement stmt =Main.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, OperationsModel.getAccNumber());
        ResultSet set =stmt.executeQuery();
        if(set.next()) {
            a=set.getInt(1);
            return a>=amount;
        }
        return false;
    }
    
    
    
    public  void setAccount(int a){
        Usermodel.acc = a;
    }
    public int getAccount() {
        return Usermodel.acc;
    }

    public Usermodel() {
    }
    
    public  boolean checkAccount() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String query="SELECT acc_number FROM account WHERE acc_number=?";
        PreparedStatement stmt=Main.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, this.getAccount());
        ResultSet set=stmt.executeQuery();
        return set.next();
    }
    
    
    public void transferAmount(int amount) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String q1="UPDATE account SET acc_balance=acc_balance-? Where acc_number=?; ";
        PreparedStatement stmt=Main.con.prepareStatement(q1,Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, amount);
        stmt.setInt(2, OperationsModel.getAccNumber());
        stmt.executeUpdate();
        String q2="UPDATE account SET acc_balance=acc_balance+? Where acc_number=?; ";
        PreparedStatement stmt1=Main.con.prepareStatement(q2,Statement.RETURN_GENERATED_KEYS);
        stmt1.setInt(1, amount);
        stmt1.setInt(2, getAccount());
        stmt1.executeUpdate();
        DateTimeFormatter d=DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        String query2="INSERT INTO transactions(trans_name,trans_amount,trans_date,acc_num1,acc_num2) VALUES(?,?,?,?,?);";
        PreparedStatement stmt3;
        stmt3=Main.con.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
        stmt3.setString(1, "Transfer");
        stmt3.setInt(2, amount);
        stmt3.setString(3, d.format(now));
        stmt3.setInt(4,OperationsModel.getAccNumber());
        stmt3.setInt(5, getAccount());
        stmt3.executeUpdate();
    }



 public ResultSet printHistory( ) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String query = "SELECT * FROM transactions WHERE acc_num1=?;";
        PreparedStatement stmt = Main.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, OperationsModel.getAccNumber());
        ResultSet set = stmt.executeQuery();
        return set;
    }
}

