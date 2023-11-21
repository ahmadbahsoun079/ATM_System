package OperationFactory;

import Controllers.*;
import javafx.scene.control.Button;

public class OperationsFactory {
    public static Operations createOperation(Button type)
    {
        
        switch (type.getText()) {
            case "Check Balance History":
                return new HistoryController();
            case "Edit information":
                return new EditInfoController();
            case "Deposit":
                return new DepositController();
            case "Withdraw":
                return new WithdrawController();
            case "Transfer":
                return new TransferController();
            default:
                break;
        }
        return null;
    }
}
