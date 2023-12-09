package Controllers;

import Models.Account;

import OperationFactory.Operations;
import OperationFactory.OperationsFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;




public class OthersController implements Initializable {
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   }
    
    @FXML public void editInformation(ActionEvent event){}
    
    @FXML public void checkBalanceHistory(ActionEvent event){}
    
    @FXML public void createAnotherAccount(ActionEvent event) throws SQLException{
        
       
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/Views/RegisterView.fxml"));
            Scene scene = new Scene(view);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML public void returnToOperations(ActionEvent event){
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/Views/OperationsView.fxml"));
            Scene scene = new Scene(view);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleButton(ActionEvent event) throws IOException {
        Operations op= OperationsFactory.createOperation((Button) event.getSource());
        op.go(event);
    }

}
