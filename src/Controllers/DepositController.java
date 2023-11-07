package Controllers;


import Models.DepositModel;
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
    private DepositModel model;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new DepositModel();
    }
    
    @FXML public void handleButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
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
        }else if (event.getSource()== buttonDeposit) {
            try {
                int amount=Integer.parseInt(resultArea.getText());
                model.makeDeposit(amount);
                resultArea.clear();
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
                alert.setContentText("Your Operation went successfuly, " +amount+"$ were added to your balance.");
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
            }
        }
        else if (event.getSource()==buttonCancel) {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/OperationsView.fxml"));
            Scene scene=new Scene(root);
            Stage newStage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            newStage.setScene(scene);
            newStage.show();
        }
    }
    
    public void delete(MouseEvent event) {
        resultArea.clear();
    }

    @Override
    public void go(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/DepositView.fxml"));
        Scene scene = new Scene(root);
        Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setScene(scene);
        newStage.show();
    }

}
