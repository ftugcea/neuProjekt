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
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javax.xml.ws.BindingProvider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javax.swing.JOptionPane;
import neuprojekt.Connectionsql;




public class LoginMainController implements Initializable {

    @FXML
    private Button login;

    @FXML
    private Button signup;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        username.setStyle("-fx-text-inner-color: #1e272e");
        password.setStyle("-fx-text-inner-color: #1e272e");
        
        
    }    
    
    @FXML
    public void loginAction(ActionEvent e2){
        
        Connection con = Connectionsql.getConnection();
        String name = username.getText();
        String pass = password.getText();
        try {
            String guery = "SELECT* FROM LOGIN NAME=? AND PASSWORD=?";
        PreparedStatement statement = con.prepareStatement(guery);
        statement.setString(1, name);
        statement.setString(2, pass);
        
        ResultSet st = statement.executeQuery();
        if(st.next()){
        JOptionPane.showMessageDialog(null, "Giriş başarılı");
        
        login.getScene().getWindow().hide();
        
        Stage stg = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Raporsec.fxml"));
        Scene scene =new Scene(root);
        stg.setScene(scene);
        stg.show();
        stg.setResizable(false);
        
        } else {
        JOptionPane.showMessageDialog(null, "Kullanıcı adı ya da şifre hatalı.");
        }
            
        } catch (Exception e) {
        }
        
    }
    @FXML
    public void signupfunk(ActionEvent e1) throws IOException{
        login.getScene().getWindow().hide();
        
        Stage signup1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignUP.fxml"));
        Scene scene =new Scene(root);
        signup1.setScene(scene);
        signup1.show();
        signup1.setResizable(false);
        
        
    }
}
