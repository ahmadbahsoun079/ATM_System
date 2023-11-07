package Controllers;

import Models.OperationsModel;
import Models.TransferModel;
import OperationFactory.Operations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Modality;

public class TransferController implements Operations, Initializable {

    @FXML Label label;
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
    @FXML private TextField resultArea; //This is text field where whatever user types in appears
    private TransferModel model;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new TransferModel();
    }
    
    @FXML public void handleButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException //This function handles button events
            , IOException {
        //From buttons0 to buttons9 all when pressed append the number pressed to the end of the already typed number
        if (event.getSource() == button0) {
            resultArea.appendText("0");
        } else if (event.getSource() == button1) {
            resultArea.appendText("1");
        } else if (event.getSource() == button2) {
            resultArea.appendText("2");
        } else if (event.getSource() == button3) {
            resultArea.appendText("3");
        } else if (event.getSource() == button4) {
            resultArea.appendText("4");
        } else if (event.getSource() == button5) {
            resultArea.appendText("5");
        } else if (event.getSource() == button6) {
            resultArea.appendText("6");
        } else if (event.getSource() == button7) {
            resultArea.appendText("7");
        } else if (event.getSource() == button8) {
            resultArea.appendText("8");
        } else if (event.getSource() == button9) {
            resultArea.appendText("9");
        } else if (event.getSource() == buttonCancel) {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/OperationsView.fxml"));
            Scene scene = new Scene(root);
            Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newStage.setScene(scene);
            newStage.show();
        }
    }

    public void delete(MouseEvent event) {
        resultArea.clear();
    }

    @Override
    public void go(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/TransferView.fxml"));
        Scene scene = new Scene(root);
        Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML public void transferAmount(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert;
        try{
            if (((Button) event.getSource()).getText().equals("Check")) {
                if((Integer.parseInt(resultArea.getText()) )!= OperationsModel.getAccNumber()){
                    model.setAccount(Integer.parseInt(resultArea.getText()));
                    if(model.checkAccount()) {
                        resultArea.clear();
                        button.setText("Transfer");
                        label.setText("Enter the amount to be transfered");
                    }
                    else {
                        alert=new Alert(Alert.AlertType.ERROR);
                        alert.initModality(Modality.APPLICATION_MODAL);
                        alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                        alert.setContentText("Please enter a valid account");
                        alert.show();
                        resultArea.clear();
                    }
                }
                else {
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                    alert.setContentText("Please enter a different account");
                    alert.show();
                    resultArea.clear();
                }
            }
            else if(((Button)event.getSource()).getText().equals("Transfer")){
                if(model.checkamount(Integer.parseInt(resultArea.getText()))){
                    try {
                        model.transferAmount(Integer.parseInt(resultArea.getText()));
                        alert=new Alert(Alert.AlertType.CONFIRMATION);
                        alert.initModality(Modality.APPLICATION_MODAL);
                        alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                        alert.setContentText("Amount is valid.\nOperation went successfully, "
                                +resultArea.getText()+"$ were transfered from your balance");
                        alert.show();
                        Parent root = FXMLLoader.load(getClass().getResource("/Views/OperationsView.fxml"));
                        Scene scene = new Scene(root);
                        Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        newStage.setScene(scene);
                        newStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(TransferController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                    alert.setContentText("Amount exceeded the balance...");
                    alert.show();
                    resultArea.clear();
                }
            }
        }
        catch(NumberFormatException e){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
            alert.setContentText("Please enter a valid account");
            alert.show();
            resultArea.clear();
        }
    }
}
