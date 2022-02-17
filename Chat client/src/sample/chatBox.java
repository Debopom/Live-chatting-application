package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class chatBox extends Application{
    private BufferedWriter writer;
    private BufferedReader reader;
    LogIn l = new LogIn();
    String myName =l.name;
    Scene scene;
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();
        TextField ms = new TextField();
        Button msgSend = new Button();
        msgSend.setText("Send");
        HBox btn = new HBox();
        btn.getChildren().addAll(ms, msgSend);
        VBox vBox = new VBox();
        try {
            Socket socket = new Socket("127.0.0.1", 8080);

            OutputStreamWriter o = new OutputStreamWriter(socket.getOutputStream());
            writer = new BufferedWriter(o);


            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(isr);
            writer.write(myName + "\n");
            writer.flush();


            Thread t = new Thread() {
                int i = 0;

                public void run() {
                    try {
                        String line = reader.readLine() + "\n";
                        while (line != null) {
                            Label msg = new Label();
                            Image image = null;
                            try {
                                image = new Image(new FileInputStream("F:\\Semester-4\\AOOP\\GUI test\\856550aa773911d00b76b24aaa4bc467.png"));
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }
                            ImageView imageView = new ImageView(image);
                            imageView.setFitWidth(20);
                            imageView.setFitHeight(20);
                            msg.setText(line);
                            HBox message = new HBox();
                            message.getChildren().addAll(imageView, msg);
                            vBox.getChildren().add(i, message);
                            i++;

                            line = reader.readLine() + "\n";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();


        } catch (IOException e) {
            e.printStackTrace();
        }



        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e)
            {
                try {
                    String msg = LogIn.name+" writes : " + ms.getText() + "\n";
                    writer.write(msg);
                    writer.flush();
                    ms.setText("");

                }
                catch (IOException ex){
                    ex.printStackTrace();
                }



            }
        };
        root.getChildren().add(vBox);
        root.getChildren().addAll(btn);
        msgSend.setOnAction(event);
        root.setBottomAnchor(btn, 10.0);
        scene = new Scene(root,400,800);
        msgSend.setOnAction(event);





    }


}



