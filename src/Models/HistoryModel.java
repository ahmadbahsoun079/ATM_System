package Models;

import Main.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HistoryModel {
    
    public ResultSet printHistory( ) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String query = "SELECT * FROM transactions WHERE acc_num1=?;";
        PreparedStatement stmt = Main.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, OperationsModel.getAccNumber());
        ResultSet set = stmt.executeQuery();
        return set;
    }
}
