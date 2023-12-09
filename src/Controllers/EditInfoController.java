package Controllers;

import Models.Account;

import OperationFactory.Operations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.stage.Modality;


public class EditInfoController implements Operations, Initializable {
    //we use  lambda expressions to handle buttons clicks
    @FXML private Button button0;
    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    @FXML private Button button4;
    @FXML private Button button5;
    @FXML private Button button6;
    @FXML private Button button7;
    @FXML private Button button8;
    @FXML private Button button9;
    @FXML private Button button;
    @FXML private Button buttonCancel;
    @FXML private TextField resultArea;
    @FXML private Label lbl;
    private Account model;        
    @Override
    public void initialize(URL location, ResourceBundle resources) {model=Account.getInstance();}

    @FXML public void handleButtonAction(ActionEvent event) throws IOException, ClassNotFoundException {
         button0.setOnAction(events -> resultArea.appendText("0"));
         button1.setOnAction(events -> resultArea.appendText("1"));
         button2.setOnAction(events -> resultArea.appendText("2"));
         button3.setOnAction(events -> resultArea.appendText("3")); 
         button4.setOnAction(events -> resultArea.appendText("4"));
         button5.setOnAction(events -> resultArea.appendText("5"));
         button6.setOnAction(events -> resultArea.appendText("6"));
         button7.setOnAction(events -> resultArea.appendText("7"));
         button8.setOnAction(events -> resultArea.appendText("8"));
         button9.setOnAction(events -> resultArea.appendText("9"));
         buttonCancel.setOnAction(events->{
             try {
                 canclehandle(event);
             } catch (IOException ex) {
                 Logger.getLogger(EditInfoController.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
         
    }
    
    public void delete(MouseEvent event) {
        resultArea.clear();
    }
    
    public void editInfo(ActionEvent event) throws IOException {
        Alert alert;
        try {
            if(((Button)event.getSource()).getText().equals("Next")) {
                if (model.checkPassword(Integer.parseInt(resultArea.getText()))) {
                    button.setText("Edit");
                    lbl.setText("Enter your new PIN");
                    resultArea.clear();
                } else {
                    resultArea.clear();
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                    alert.setContentText("Wrong PIN");
                    alert.show();
                }
            }
            else if(((Button)event.getSource()).getText().equals("Edit"))
            {
                if(!model.is_4Digit(Integer.parseInt(resultArea.getText())))
                {
                    resultArea.clear();
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                    alert.setContentText("Your PIN need to be 4 digits");
                    alert.show();
                }
                else
                {
                    model.setPassword(Integer.parseInt(resultArea.getText()));
                    alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                    alert.setContentText("Your new PIN is set...");
                    alert.show();
                    resultArea.clear();
                    Parent root = FXMLLoader.load(getClass().getResource("/Views/OthersView.fxml"));
                    Scene scene = new Scene(root);
                    Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    newStage.setScene(scene);
                    newStage.show();
                }
            }
        } catch(IOException | ClassNotFoundException | NumberFormatException e) {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
            alert.setContentText("Something went wrong...");
            alert.show();
        }
    }


    @Override
    public void go(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/EditInfoView.fxml"));
        Scene scene = new Scene(root);
        Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setScene(scene);
        newStage.show();
    }

    private void canclehandle(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/Views/OthersView.fxml"));
            Scene scene=new Scene(root);
            Stage newStage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            newStage.setScene(scene);
            newStage.show(); 
    }
}
