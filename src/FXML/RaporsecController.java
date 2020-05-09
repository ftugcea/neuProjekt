/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tugce
 */
public class RaporsecController implements Initializable {

    @FXML
    private Button radyografik;

    @FXML
    private Button manyetik;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void manyetikAction (ActionEvent e) throws IOException {
        
        manyetik.getScene().getWindow().hide();
        
        Stage signup1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Magnetic1.fxml"));
        Scene scene =new Scene(root);
        signup1.setScene(scene);
        signup1.show();
        signup1.setResizable(false);
    }
    
}
