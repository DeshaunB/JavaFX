package Home;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.util.Timer;
import java.util.Vector;

public class Main extends Application {

    private Profile user;

    public static boolean page1 = true;

    public static String startPage = "Main";

    public static Storyboard story = new Storyboard();

    @Override
    public void start(Stage primaryStage) throws Exception{
        user = new Profile();
        if (Profile.ProfileExists())
            Profile.OpenProfile();
        else
            Profile.CreateProfile();

        Parent root = FXMLLoader.load(getClass().getResource("fxml/" + startPage +".fxml"));
        primaryStage.setTitle("ScriptWriter");
        primaryStage.setScene(new Scene(root, 1213, 641));
        primaryStage.setFullScreen(true);
        //primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
