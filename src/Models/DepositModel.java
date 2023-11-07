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

public class DepositModel  {
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
            Logger.getLogger(DepositModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
