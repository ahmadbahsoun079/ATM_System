package OperationFactory;

import Controllers.*;
import javafx.scene.control.Button;

public class OperationsFactory {
    public static Operations createOperation(Button type)
    {
        if(type.getText().equals("Check Balance History"))
            return new HistoryController();
        else if(type.getText().equals("Edit information"))
            return new EditInfoController();
        else if(type.getText().equals("Deposit"))
            return new DepositController();
        else if(type.getText().equals("Withdraw"))
            return new WithdrawController();
        else if(type.getText().equals("Transfer"))
            return new TransferController();
        return null;
    }
}
