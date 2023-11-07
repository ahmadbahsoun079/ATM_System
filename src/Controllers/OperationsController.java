package Controllers;

import Models.OperationsModel;
import OperationFactory.Operations;
import OperationFactory.OperationsFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Modality;

public class OperationsController implements Initializable {

    private OperationsModel model;
    @FXML private Label TXT_greeting;
    @FXML private Label TXT_username;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        model = new OperationsModel();
        TXT_greeting.setText(OperationsModel.getGreetingTXT());
        TXT_username.setText(OperationsModel.getUserTXT());
    }
    
    @FXML public void removeCard(ActionEvent event){
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/Main/MainView.fxml"));
            Scene scene = new Scene(view);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OperationsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML public void checkBalance(ActionEvent event){
        int balance = model.getBalance();
        Alert alert;        
        
        if(balance == -1){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
            alert.setContentText("Something went wrong...");
            alert.show();
        }
        else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
            alert.setContentText("Your balance amount is "+balance+"$");
            alert.show();
        }
    }
    
    @FXML public void othersPage(ActionEvent event){
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/Views/OthersView.fxml"));
            Scene scene = new Scene(view);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OperationsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void handleButton(ActionEvent event) throws IOException {
        Operations op= OperationsFactory.createOperation((Button) event.getSource());
        op.go(event);
    }
}
