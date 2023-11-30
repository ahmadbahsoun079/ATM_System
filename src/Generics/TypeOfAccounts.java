package Generics;

import java.sql.SQLException;



public interface TypeOfAccounts {
    public String getCountryName();
     public  float makeDeposit(float amount) throws ClassNotFoundException;
     public  float withdraw(float amount) throws SQLException, ClassNotFoundException;
}