package Controllers;

import Models.Usermodel;
import OperationFactory.Operations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;

public class HistoryController implements Operations, Initializable {
    @FXML private  TableView<SQLTable> table;
    @FXML private TableColumn<SQLTable,String> name;
    @FXML private  TableColumn<SQLTable,Integer> amount;
    @FXML private TableColumn<SQLTable, Date> date;
    @FXML private TableColumn<SQLTable,Integer> accnb1;
    @FXML private  TableColumn<SQLTable,Integer>accnb2;
    private Usermodel model;
    private ObservableList<SQLTable> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model=new Usermodel();
            list = FXCollections.observableArrayList();
            ResultSet set= model.printHistory();
            
            while (set.next()) {
                try {
                    list.add(new SQLTable(set.getString(2), set.getInt(3), set.getDate(4), set.getInt(5), set.getInt(6)));
                    name.setCellValueFactory(new PropertyValueFactory<>("name"));
                    amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
                    date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    accnb1.setCellValueFactory(new PropertyValueFactory<>("accnb1"));
                    accnb2.setCellValueFactory(new PropertyValueFactory<>("accnb2"));
                    table.setItems(list);
                } catch (SQLException ex) {
                    Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void checkHistory(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        table.refresh();
        
        
    }
    
    public void returnBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/OthersView.fxml"));
        Scene scene=new Scene(root);
        Stage newStage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        newStage.setScene(scene);
        newStage.show();
    }


    @Override
    public void go(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/HistoryView.fxml"));
        Scene scene = new Scene(root);
        Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newStage.setScene(scene);
        newStage.show();
    }
}
