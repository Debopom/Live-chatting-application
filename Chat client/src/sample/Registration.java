package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.awt.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;

import java.io.*;
import java.util.ArrayList;

class regCheck{
    String id;
    String pass;
    regCheck(String id, String pass){
        this.id = id;
        this.pass = pass;

    }
}

public class Registration {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm;
    @FXML
    private Button back;
    @FXML
    private Button registration;
    @FXML
    private Label matchpass;
    @FXML
    void backbtn(ActionEvent event) throws IOException {

        Main m = new Main();
        m.setLogin("login.fxml");

    }
    @FXML
    void registrationbtn(ActionEvent event) throws IOException {

        registration();




    }
    void registration() throws IOException {
        ArrayList<regCheck> reginfo = new ArrayList<>();

        FileWriter writer = new FileWriter("userinfo.txt" , true);
        BufferedWriter fw = new BufferedWriter(writer);
        FileReader reader = new FileReader("userinfo.txt");
        BufferedReader br = new BufferedReader(reader);

        if(password.getText().contains(confirm.getText())) {
            if(exist(reginfo)==true){
                String info = username.getText() + " " + password.getText();
                matchpass.setText("Registration successful");
                fw.write("\n" + info);

            }else{
                matchpass.setText("Username already taken");
            }






        }

        else if (username.getText().isEmpty() && password.getText().isEmpty() && confirm.getText().isEmpty()) {
            matchpass.setText("Please enter your data.");
        }
        else {
            matchpass.setText("Password didn't match");
        }
        fw.close();



    }
    boolean exist(ArrayList<regCheck> reginfo) throws IOException {
        FileReader reader = new FileReader("userinfo.txt");
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            String id = parts[0];
            String Password = parts[1];
            regCheck check = new regCheck(id,Password);
            reginfo.add(check);
        }
        for (regCheck i :reginfo){
            if (username.getText().equals(i.id)){

                return false;


            }

        }

        return true;
    }
}







