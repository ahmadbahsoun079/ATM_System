package Controllers;


import Generics.NormalAccount;
import Generics.TypeOfAccounts;
import Generics.VIPAccount;
import Models.Account;

import OperationFactory.Operations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.event.EventHandler;
import javafx.stage.Modality;
import proxy.AccountProxiy;



public class WithdrawController implements Initializable, Operations {
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
    @FXML private Button buttonWithdraw;
    @FXML private Button buttonCancel;
    @FXML private TextField resultArea;
  //private TypeOfAccounts model;
    private TypeOfAccounts proxiyaccount;
    Account acc=null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        acc=Account.getInstance();

         proxiyaccount=new AccountProxiy();
         
        
    }
    @FXML public void handleButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException , IOException {
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
          
         buttonWithdraw.setOnAction(events->withdrawHandle(event));
         
         if(event.getSource()==buttonWithdraw) {
            
         
        
    }
    }
    public void delete(MouseEvent event) {
        resultArea.clear();
    }
    
    @Override
    public void go(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/WithdrawView.fxml"));
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

    private void withdrawHandle(ActionEvent event) {
       try {
                int amount=Integer.parseInt(resultArea.getText());
                float famount=proxiyaccount.withdraw(amount);
                if(famount!=-1) {
                   
                    
                   
                    float fees=amount-famount;
                    resultArea.clear();
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                    
                    alert.setContentText("Operation went successfully, "
                            +famount+"$ was withdrawn from your account since we take  "+fees+"$ fees");
                    alert.show();
                    Parent root = FXMLLoader.load(getClass().getResource("/Views/OperationsView.fxml"));
                    Scene scene=new Scene(root);
                    Stage newStage=(Stage) ((Node)event.getSource()).getScene().getWindow();
                    newStage.setScene(scene);
                    newStage.show();
                }
                else {
                    Alert alert=new Alert(Alert.AlertType.WARNING);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                    alert.setContentText("Amount entered exceed balance");
                    alert.show();
                    resultArea.clear();
                }
            }catch(NumberFormatException e) {
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                alert.setContentText("Please enter a valid amount");
                alert.show();
                resultArea.clear();
            } catch (IOException ex) {
            Logger.getLogger(WithdrawController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WithdrawController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WithdrawController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

            

