package Generics;

import java.sql.SQLException;



public interface TypeOfAccounts {
    //here we have an interface class were the 2 classes mplemented from it the vipaccount and normalaccount
    public String getCountryName();
     public  float makeDeposit(float amount) throws ClassNotFoundException;
     public  float withdraw(float amount) throws SQLException, ClassNotFoundException;
}