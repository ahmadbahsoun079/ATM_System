/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proxy;

import Generics.NormalAccount;
import Generics.TypeOfAccounts;
import Generics.VIPAccount;
import Main.Main;
import Models.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 *
 * @author lenovo
 */
public class AccountProxiy implements TypeOfAccounts {
    Account acc=Account.getInstance();
    TypeOfAccounts model=null;
    
    public AccountProxiy() {
       if(acc.getaccounttype().equals("vip")){
          model = new VIPAccount();   
        }else{
          model = new NormalAccount();   
        }
       
    }

    
    
//here is the withdraw whic here only check the amount if we can withdraw
    @Override
    public float withdraw(float amount) throws SQLException, ClassNotFoundException {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        float a;
        String query= "SELECT acc_balance FROM account WHERE acc_number=?;" ;
        PreparedStatement stmt =Main.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, acc.getAccNumber());
        ResultSet set =stmt.executeQuery();
        if(set.next()) {
            a=set.getFloat(1);
            //this means that we cannt make withdraw
            if(a<amount){
                return -1;
            }
            else{
               //we can then we call the withdraw according to the type of account
                return model.withdraw(amount);
            }
        }
        return -1;
    }
    
    @Override
    public String getCountryName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float makeDeposit(float amount) throws ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
