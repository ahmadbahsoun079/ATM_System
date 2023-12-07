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
import Generics.*;
import java.io.IOException;

public  class Account<T extends TypeOfAccounts> {

    private static Account<?> account;
    private T object;

    private Account(T object){
        this.object = object;
    }

    public static  <T extends TypeOfAccounts> Account<T> setInstance(T object){
        if(account == null){
            account = new Account<>(object);
        }
        return (Account<T>) account;
    }
    public static  <T extends TypeOfAccounts> Account<T> getInstance(){
        return (Account<T>)account;
    }
   

    private String accounttype;
    private int acc_number, acc_pass, cust_ID;
    private String txt_username, txt_greeting;
    private int acc;
    private float tva;
    DateTimeFormatter d=DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
    LocalDateTime now=LocalDateTime.now();

    public void setaccounttype(String type){
        accounttype=type;
    }

    public String  getaccounttype(){
        return accounttype;
    }
    
     public  void settva(float tva){
      
        this.tva=tva;
    }
    public  Float gettva(){
       
        return tva;
    }
    
    public void setUserTXT(String username){
        this.txt_username = username;
    }
    public void setGreetingTXT(String greeting){
        this.txt_greeting = greeting;
    }
    public void setAccNumber(int acc_number){
        this.acc_number = acc_number;
    }
    public void setAccPassword(int acc_pass){
        this.acc_pass = acc_pass;
    }
    public  void setCustomerID(int cust_ID){
        this.cust_ID = cust_ID;
    }
    public  int getAccNumber(){
        return this.acc_number;
    }
    public  int getAccPassword(){
        return this.acc_pass;
    }
    public  int getCustID(){
        return this.cust_ID;
    }
    public  String getUserTXT(){
        return this.txt_username;
    }
    public  String getGreetingTXT(){
        return this.txt_greeting;
    }
   
    public float makeDeposit(float amount) throws ClassNotFoundException{
        return object.makeDeposit(amount);
    }
    public float getBalance() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sql = "SELECT acc_balance FROM account WHERE acc_number=?";
            PreparedStatement stmt = Main.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setFloat(1,this.getAccNumber());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getFloat(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
    
    

    
    
    
    
    public boolean checkamount(float amount) throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        float a;
        String query= "SELECT acc_balance FROM account WHERE acc_number=?;" ;
        PreparedStatement stmt =Main.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, getAccNumber());
        ResultSet set =stmt.executeQuery();
        if(set.next()) {
            a=set.getFloat(1);
            return a>=amount;
        }
        return false;
    }
    
    
    
    public void setAccount(int a){
       acc = a;
    }
    public int getAccount() {
        return acc;
    }

    
    
    public boolean checkAccount() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String query="SELECT acc_number FROM account WHERE acc_number=?";
        PreparedStatement stmt=Main.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, this.getAccount());
        ResultSet set=stmt.executeQuery();
        return set.next();
    }
    
    
    public void transferAmount(float amount) throws ClassNotFoundException, SQLException {
       
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String q1="UPDATE account SET acc_balance=acc_balance-? Where acc_number=?; ";
        PreparedStatement stmt=Main.con.prepareStatement(q1,Statement.RETURN_GENERATED_KEYS);
        stmt.setFloat(1, amount);
        stmt.setInt(2, getAccNumber());
        stmt.executeUpdate();
        String q2="UPDATE account SET acc_balance=acc_balance+? Where acc_number=?; ";
        PreparedStatement stmt1=Main.con.prepareStatement(q2,Statement.RETURN_GENERATED_KEYS);
        stmt1.setFloat(1, amount);
        stmt1.setInt(2, getAccount());
        stmt1.executeUpdate();
        DateTimeFormatter d=DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        String query2="INSERT INTO transactions(trans_name,trans_amount,trans_date,acc_num1,acc_num2) VALUES(?,?,?,?,?);";
        PreparedStatement stmt3;
        stmt3=Main.con.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
        stmt3.setString(1, "Transfer");
        stmt3.setFloat(2, amount);
        stmt3.setString(3, d.format(now));
        stmt3.setInt(4,getAccNumber());
        stmt3.setInt(5, getAccount());
        stmt3.executeUpdate();
    }



 public ResultSet printHistory( ) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String query = "SELECT * FROM transactions WHERE acc_num1=?;";
        PreparedStatement stmt = Main.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, getAccNumber());
        ResultSet set = stmt.executeQuery();
        return set;
    }
 public void setPassword(int password) throws ClassNotFoundException   {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String sql ="UPDATE account SET acc_pass=? WHERE acc_number=?;";
        PreparedStatement stmt;
        try {

            stmt= Main.con.prepareStatement(sql);
            stmt.setInt(1, password);
            stmt.setInt(2, getAccNumber());
            stmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean checkPassword(int pass) throws IOException {
        String sql ="SELECT acc_pass FROM account WHERE acc_number=?;";
        PreparedStatement stmt;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            stmt= Main.con.prepareStatement(sql);
            stmt.setInt(1, getAccNumber());
            ResultSet set = stmt.executeQuery();
            if(set.next())
            {
                if(set.getInt(1)==pass)
                    return true;
            }
        }catch(SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean is_4Digit(int nb) {
        int count = 0;
        while(nb!=0) {
            nb /= 10;
            count++;
        }
        return count == 4;
    }

   
}
