package Main;

import DataBaseConnection.DBConnection;
import java.sql.SQLException;
import java.sql.Connection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static Connection con = null;
    
    @Override
    public void start(Stage stage) throws ClassNotFoundException {
        try {
            con = DBConnection.makeConnection();
            Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("MAO");
            stage.show();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
