package Models;

import Main.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WithdrawModel  {
    
    DateTimeFormatter d=DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
    LocalDateTime now=LocalDateTime.now();
    
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
}
