package Controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class SQLTable {
    
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty amount;
    private final SimpleObjectProperty<Date> date;
    private final SimpleIntegerProperty accnb1;
    private final SimpleIntegerProperty accnb2;

    public SQLTable (String n,Integer a,Date d,Integer a1,Integer a2)
    {
        super();
        this.name=new SimpleStringProperty(n);
        this.amount=new SimpleIntegerProperty(a);
        this.date=new SimpleObjectProperty<>(d);
        this.accnb1=new SimpleIntegerProperty(a1);
        this.accnb2=new SimpleIntegerProperty(a2);


    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAccnb2() {
        return accnb2.get();
    }

    public SimpleIntegerProperty accnb2Property() {
        return accnb2;
    }

    public void setAccnb2(int accnb2) {
        this.accnb2.set(accnb2);
    }

    public int getAccnb1() {
        return accnb1.get();
    }

    public SimpleIntegerProperty accnb1Property() {
        return accnb1;
    }

    public void setAccnb1(int accnb1) {
        this.accnb1.set(accnb1);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }
}
