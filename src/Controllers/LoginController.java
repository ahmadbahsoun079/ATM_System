package Controllers;

import Models.LoginModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    
    private LoginModel model;
    @FXML private TextField username;
    @FXML private TextField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new LoginModel();
    }    

    @FXML public void loginAccount(ActionEvent event) {
        if(model.LoggingAccount(username.getText(), password.getText())){
            try {
                Parent view = FXMLLoader.load(getClass().getResource("/Views/OperationsView.fxml"));
                Scene scene = new Scene(view);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
            alert.setContentText("Invalid username or PIN");
            alert.show();
        }
    }

    @FXML public void createAccount(MouseEvent event) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/Views/RegisterView.fxml"));
            Scene scene = new Scene(view);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML public void returnToMainPage(ActionEvent event){
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/Main/MainView.fxml"));
            Scene scene = new Scene(view);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
