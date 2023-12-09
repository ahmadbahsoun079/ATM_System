package Controllers;





import Generics.*;
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
import javafx.fxml.Initializable;
import javafx.stage.Modality;
import Models.Account;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DepositController implements Operations, Initializable {


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
    @FXML private Button buttonDeposit;
    @FXML private Button buttonCancel;
    @FXML private TextField resultArea;
    
    Account acc=null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Account acc = Account.getInstance();

       
       
        
        
    }
    
    @FXML public void handleButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
       
        //we use  lambda expressions to handle buttons clicks
        
        
        
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
         buttonDeposit.setOnAction(events -> {
            
            try {
                depositehandle(event);
            } catch (IOException ex) {
                Logger.getLogger(DepositController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        buttonCancel.setOnAction(events->{
            try {
                canclehandle(event);
            } catch (IOException ex) {
                Logger.getLogger(DepositController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
        if (event.getSource()==buttonCancel) {
            
        }
    }
    
    public void delete(MouseEvent event) {
        resultArea.clear();
    }
    
    public void depositehandle(ActionEvent event) throws IOException{
       try {
                Account acc = Account.getInstance();
                float amount=Integer.parseInt(resultArea.getText());
                float famount=acc.makeDeposit(amount);
                float fees=amount-famount;
                resultArea.clear();
                  
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
              
                alert.setContentText("Your Operation went successfuly, " +famount+"$ were added to your balance since we take "+fees+"$ fees");
                alert.show();
                Parent root = FXMLLoader.load(getClass().getResource("/Views/OperationsView.fxml"));
                Scene scene=new Scene(root);
                Stage newStage=(Stage) ((Node)event.getSource()).getScene().getWindow();
                newStage.setScene(scene);
                newStage.show();
            }
            catch(NumberFormatException e) {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                alert.setContentText("You need to enter a valid amount");
                alert.show();
                resultArea.clear();
            } catch (ClassNotFoundException ex) { 
            Logger.getLogger(DepositController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void go(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/DepositView.fxml"));
        Scene scene = new Scene(root);
        Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setScene(scene);
        newStage.show();
    }

    private void canclehandle(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/OperationsView.fxml"));
            Scene scene=new Scene(root);
            Stage newStage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            newStage.setScene(scene);
            newStage.show();
    }

}
