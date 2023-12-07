package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OthersModel {
    
    private int acc_number, acc_pass;
    private ResultSet rs;
    PreparedStatement stmt;
    
    public int getAcc_number(){
        return this.acc_number;
    }
    public int getAcc_password(){
        return this.acc_pass;
    }
    
    public Boolean createAccount() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sql = "INSERT INTO account(acc_pass, acc_balance, cust_number) values(?,?,?)";
            stmt = Main.Main.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            this.acc_pass = (int)(Math.random()*10000);
            stmt.setInt(1, this.acc_pass);
            stmt.setInt(2, 0);
            //stmt.setInt(3, Account.getCustID());
            if(stmt.executeUpdate() == 1){
                
                rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    this.acc_number = rs.getInt(1);
                    return true;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OthersModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            rs.close();
            stmt.close();
        }
        return false;
    }
}
