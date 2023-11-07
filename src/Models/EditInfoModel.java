package Models;

import Main.Main;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EditInfoModel  {
    
    public void setPassword(int password) throws ClassNotFoundException   {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String sql ="UPDATE account SET acc_pass=? WHERE acc_number=?;";
        PreparedStatement stmt;
        try {

            stmt= Main.con.prepareStatement(sql);
            stmt.setInt(1, password);
            stmt.setInt(2, OperationsModel.getAccNumber());
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
            stmt.setInt(1, OperationsModel.getAccNumber());
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
