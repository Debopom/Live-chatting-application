package sample;

import com.sun.prism.Image;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import javax.crypto.Cipher;
import java.io.*;
import java.util.ArrayList;

class loginInfo{
    String id;
    String password;
    loginInfo(String id , String password){
        this.id = id;
        this.password = password;
    }
}

public class LogIn {
    static String name;



    public LogIn() {

    }

    @FXML
    private Button login;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;




    public void userLogIn(ActionEvent event) throws Exception {
        checkLogin();


    }

     void checkLogin() throws Exception {
        Main m = new Main();
        ArrayList<loginInfo> info = new ArrayList<>();
        FileReader reader = new FileReader("userinfo.txt");
        BufferedReader read = new BufferedReader(reader);


        String line;

        while ((line = read.readLine()) != null) {
            String[] parts = line.split(" ");
            String id = parts[0];
            String Password = parts[1];
            loginInfo Info = new loginInfo(id,Password);
            info.add(Info);
        }


        for(loginInfo i : info) {
            if (username.getText().contains(i.id) && password.getText().contains(i.password)) {
                name = username.getText();

                //Controller c = new Controller();
                //System.out.println(c.welcome.getText());
                //c.welcome.setText("welcome"+name);
                //System.out.println(i.id+i.password);
                m.changeScene();


            } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
                wrongLogIn.setText("Please enter your data.");
            } else {
                wrongLogIn.setText("Wrong username or password!");

            }
        }
        read.close();




    }
    @FXML
    void reg(ActionEvent event) throws IOException {
        Main m = new Main();
        m.setReg("Registration.fxml");
    }
}