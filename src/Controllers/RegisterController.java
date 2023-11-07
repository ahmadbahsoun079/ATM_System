package Controllers;

import Models.OperationsModel;
import Models.RegisterModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.microsoft.sqlserver.jdbc.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegisterController implements Initializable {
    
    @FXML private TextField firstname;
    @FXML private TextField lastname;
    @FXML private TextField phonenumber;
    @FXML private TextField address;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private DatePicker birthday;
    @FXML private ProgressIndicator progress;
    @FXML private Button create;
    @FXML private Label required;
    private RegisterModel model;
    private String BDay, gender;
    private Alert alert;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new RegisterModel();
    }

    @FXML public void getFirstName (ActionEvent event){
        
        if(!firstname.getText().isEmpty()){
            required.setText(null);
            lastname.setDisable(false);
            progress.setProgress(0.15);
        }
        else{
            required.setText("All the information above are required to create an account");
            lastname.setText("");
            lastname.setDisable(true);
            phonenumber.setText("");
            phonenumber.setDisable(true);
            address.setText("");
            address.setDisable(true);
            birthday.setValue(LocalDate.now());
            birthday.setDisable(true);
            male.setSelected(false);
            male.setDisable(true);
            female.setSelected(false);
            female.setDisable(true);
            create.setDisable(true);
            progress.setProgress(0.0);
        }
    }
    
    @FXML public void getLastName(ActionEvent event){
        if(!lastname.getText().isEmpty()){
            phonenumber.setDisable(false);
            progress.setProgress(0.3);
        }
        else{
            phonenumber.setText("");
            phonenumber.setDisable(true);
            address.setText("");
            address.setDisable(true);
            birthday.setValue(LocalDate.now());
            birthday.setDisable(true);
            male.setSelected(false);
            female.setSelected(false);
            male.setDisable(true);
            female.setDisable(true);
            create.setDisable(true);
            progress.setProgress(0.15);
        }
    }
    
    @FXML public void getPhoneNumber(ActionEvent event){
        if(!phonenumber.getText().isEmpty() 
                && StringUtils.isInteger(phonenumber.getText())
                && model.is_8Digit(Integer.valueOf(phonenumber.getText())))
        {
            required.setText("");
            address.setDisable(false);
            progress.setProgress(0.45);
        }
        else{
            required.setText("Invalid phone number..."); 
            address.setText("");
            address.setDisable(true);
            birthday.setValue(LocalDate.now());
            birthday.setDisable(true);
            male.setSelected(false);
            female.setSelected(false);
            male.setDisable(true);
            female.setDisable(true);
            create.setDisable(true);
            progress.setProgress(0.3);
        }
    }
    
    @FXML public void getAddress(ActionEvent event){
        if(!address.getText().isEmpty()){
            birthday.setDisable(false);
            progress.setProgress(0.6);
        }
        else{
            birthday.setValue(LocalDate.now());
            birthday.setDisable(true);
            male.setSelected(false);
            female.setSelected(false);
            male.setDisable(true);
            female.setDisable(true);
            create.setDisable(true);
            progress.setProgress(0.45);
        }
    }
    
    @FXML public void getDate(ActionEvent event){
        if(birthday.getValue() != null){
            LocalDate date = birthday.getValue();
            BDay = date.toString();
            male.setDisable(false);
            female.setDisable(false);
            progress.setProgress(0.8);
        }
    }
    
    @FXML public void getGender(ActionEvent event){        
        if(male.isSelected() || female.isSelected()){
            progress.setProgress(1);
            create.setDisable(false);
        }
    }
    
    @FXML public void onCreateAccount(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(male.isSelected())
            gender = male.getText();
        else
            if(female.isSelected())
                gender = female.getText();
        Boolean check = model.checkInformation(firstname.getText(), lastname.getText(), Integer.valueOf(phonenumber.getText()));
        if(check){
            Boolean flag = model.createAccount(firstname.getText(),
                    lastname.getText(), Integer.parseInt(phonenumber.getText()), address.getText(), BDay, gender);
            if(flag){
                try {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                    alert.setContentText("**This message appears only once**\n"
                            +"Your username: "+OperationsModel.getAccNumber()
                            +"\nYour PIN: "+OperationsModel.getAccPassword());
                    alert.show();
                    Parent view = FXMLLoader.load(getClass().getResource("/Views/OperationsView.fxml"));
                    Scene scene = new Scene(view);
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                alert.setContentText("Customer not found...");
                alert.show();
            }
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
            alert.setContentText("Opps...\n "+firstname.getText()+" "+lastname.getText()
                    +" already had a registration account in our system...");
            alert.show();
            required.setText("All the information above are required to create account");
            firstname.setText("");
            firstname.setDisable(true);
            lastname.setText("");
            lastname.setDisable(true);
            phonenumber.setText("");
            phonenumber.setDisable(true);
            address.setText("");
            address.setDisable(true);
            birthday.setValue(LocalDate.now());
            birthday.setDisable(true);
            male.setSelected(false);
            male.setDisable(true);
            female.setSelected(false);
            female.setDisable(true);
            create.setDisable(true);
            progress.setProgress(0.0);
        }
    }
    
    @FXML public void cancelRegister(ActionEvent event){
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/Views/LoginView.fxml"));
            Scene scene = new Scene(view);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
