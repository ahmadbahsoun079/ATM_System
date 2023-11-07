package Controllers;

import Models.OperationsModel;
import Models.OthersModel;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

public class OthersController implements Initializable {
    private OthersModel model;
    @FXML VBox otherOp;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new OthersModel();
   }
    
    @FXML public void editInformation(ActionEvent event){}
    
    @FXML public void checkBalanceHistory(ActionEvent event){}
    
    @FXML public void createAnotherAccount(ActionEvent event) throws SQLException{
        Stage stage = (Stage) otherOp.getScene().getWindow();
        Alert alert  = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.setContentText("Are your sure you want to create another account?");
        alert.setHeaderText("Creating another account");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            if(model.createAccount()){
                alert = new Alert(Alert.AlertType.WARNING);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setContentText("**This message appears only once**\n"
                        +"Your username: "+model.getAcc_number()
                        +"\nYour PIN: "+model.getAcc_password());
                alert.show();
            }
            else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setContentText("Something went wrong...");
                alert.show();
            }
        }
        else
            System.out.println("Canceled the operation...");
    }
    
    @FXML public void returnToOperations(ActionEvent event){
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/Views/OperationsView.fxml"));
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
