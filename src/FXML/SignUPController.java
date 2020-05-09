/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import neuprojekt.Connectionsql;

/**
 * FXML Controller class
 *
 * @author tugce
 */
public class SignUPController implements Initializable {

    @FXML
    private Button signup;

    @FXML
    private Button login;
    
    @FXML
    private TextField namefield;

    @FXML
    private TextField titlefield;

    @FXML
    private TextField levelfield;

    @FXML
    private PasswordField passfield;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void loginfunk(ActionEvent e) throws IOException{
        signup.getScene().getWindow().hide();
        
        Stage login1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
        Scene scene =new Scene(root);
        login1.setScene(scene);
        login1.show();
        login1.setResizable(false);
        
        
    }
     public void signupfunk(ActionEvent e1) throws IOException{
         
         Connection con = Connectionsql.getConnection();
         
         String name = namefield.getText();
         String pass = passfield.getText();
         String title = titlefield.getText();
         String level = levelfield.getText();
         
         

         String query1 = "INSERT INTO person (name,title,level) VALUES (+ "'" + name +"'," + ",'" + title +"'," + ",'" + level +"'," + ",'" + password +"'," + ",'" + name +"')" ;
         String query2 = "INSERT INTO login (name,password) VALUES (+"'" + name +"'," + ",'" + password +")";




       static void addPerson(){

        
        Stage st = new Stage();
        
         try {
            st.executeUpdate(query1);
            st.executeUpdate(query2);
         } catch (Exception e) {
           }
      }
         
         
     }

