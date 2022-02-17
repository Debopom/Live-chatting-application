package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void changeScene() throws Exception {
        chatBox ch = new chatBox();
        ch.start(stg);
        stg.setScene(ch.scene);
        stg.setTitle("Welcome " + LogIn.name);
    }
    public void setReg(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.setScene(new Scene(pane , 600,400));
        stg.setTitle("Registration");
    }
    public void setLogin(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.setScene(new Scene(pane , 600,400));
        stg.setTitle("Login");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
