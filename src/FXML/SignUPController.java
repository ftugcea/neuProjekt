/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.swing.JOptionPane;
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
         
         
         
         String name = namefield.getText();
         String pass = passfield.getText();
         String title = titlefield.getText();
         String level = levelfield.getText();
         
         

         String query1 = "INSERT INTO PERSON (name,title,level) VALUES (?,?,?)" ;
         String query2 = "INSERT INTO LOGIN (name,password) VALUES (?,?)";



         try {
             Connection con = Connectionsql.getConnection();
             PreparedStatement p;
             p = con.prepareStatement(query1);
             p.setString(1, name);
             p.setString(2, title);
             p.setString(3, level);
             
             
             
             p.executeUpdate();
             p.close();
             con.close();
         } catch (Exception e) {
             
             JOptionPane.showMessageDialog(null, "Ekleme yapılamadı" + e);
             
         }
         try {
             Connection conn = Connectionsql.getConnection();
             PreparedStatement pp;
             
             
             pp = conn.prepareStatement(query2);
             pp.setString(1, name);
             pp.setString(2, pass);
             
             pp.executeUpdate();
             JOptionPane.showMessageDialog(null, "Ekleme başarılı");
             pp.close();
             conn.close();
         } catch (Exception e) {
             
             JOptionPane.showMessageDialog(null, "Ekleme yapılamadı" + e);
             
         }

       
     }
         
     }

