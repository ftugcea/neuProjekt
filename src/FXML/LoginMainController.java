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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import neuprojekt.Connectionsql;
import neuprojekt.UserTable;







public class LoginMainController implements Initializable {
    
    @FXML
    private Tab LoginPage;
    
    @FXML
    private Tab UserControlPage;
    
    @FXML
    private TableView<UserTable> usertable;

    @FXML
    private Button userlist;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button updatebutton;

    @FXML
    private Button login;

    @FXML
    private Button signup;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    @FXML
    private TableColumn<UserTable, String> namecolumn;

    @FXML
    private TableColumn<UserTable, String> titlecolumn;

    @FXML
    private TableColumn<UserTable, String> levelcolumn;
    
    @FXML
    private Button editButton;

    @FXML
    private TextField title_edit;

    @FXML
    private TextField name_edit;

    @FXML
    private TextField level_edit;
    
    @FXML
    private Button kaydet;
    
    ObservableList<UserTable> oblist = FXCollections.observableArrayList();
    
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
            String query = "SELECT * FROM PERSON WHERE NAME=? AND PASSWORD=?";
        PreparedStatement statement = con.prepareStatement(query);
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
    @FXML
    public void userlistAction(ActionEvent e3) throws SQLException {
        
        Connection con = Connectionsql.getConnection();
        
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM PERSON");
        
        while (rs.next()) {
            oblist.add(new UserTable(rs.getString("name"), rs.getString("title"), rs.getString("level")));
        }
        
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        titlecolumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        levelcolumn.setCellValueFactory(new PropertyValueFactory<>("level")); 
        
        usertable.setItems(oblist);
        
        }
    @FXML
    public void deleteAction(ActionEvent e5) {
        
        usertable.setOnMouseClicked((MouseEvent event) -> {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            System.out.println(usertable.getSelectionModel().getSelectedItem());
        }
    });
        
        try {
            
            Connection con = Connectionsql.getConnection();
            
            String query = "DELETE FROM PERSON WHERE name = ?";
            
            PreparedStatement pst;
            pst = con.prepareStatement(query);
            pst.setString(1, usertable.getSelectionModel().getSelectedItem().getName());
            System.out.println(query);
            pst.executeUpdate();
            pst.close();
            
            
            
            JOptionPane.showMessageDialog(null,"Kullanıcı Silindi!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Silinemedi!\n" + ex);
        }
        
        
        
        
    }
    @FXML
    public void editAction(ActionEvent e) throws SQLException{
        usertable.setOnMouseClicked((MouseEvent event) -> {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            System.out.println(usertable.getSelectionModel().getSelectedItem());
        }
    });
        name_edit.setText(usertable.getSelectionModel().getSelectedItem().getName());
        title_edit.setText(usertable.getSelectionModel().getSelectedItem().getTitle());
        level_edit.setText(usertable.getSelectionModel().getSelectedItem().getLevel());
        
        
    }
    @FXML
    public void saveAction(ActionEvent e7) {
        
            usertable.setOnMouseClicked((MouseEvent event) -> {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    System.out.println(usertable.getSelectionModel().getSelectedItem());
                }
            });
            String name = name_edit.getText();
            String title = title_edit.getText();
            String level = level_edit.getText();
            
            
            try {
            Connection con = Connectionsql.getConnection();
            PreparedStatement ps;
            
            String query = "UPDATE PERSON SET name = ? , title = ? , level = ? WHERE name = ? AND title = ? AND level = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, title);
            ps.setString(3, level);
            ps.setString(4, usertable.getSelectionModel().getSelectedItem().getName());
            ps.setString(5, usertable.getSelectionModel().getSelectedItem().getTitle());
            ps.setString(6, usertable.getSelectionModel().getSelectedItem().getLevel());
            ps.executeUpdate();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Düzenleme Başarılı");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Düzenleme Başarısız" + ex);
        } 
    }
}
