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
public class Magnetic2Controller implements Initializable {

    @FXML
    private Button nextbutton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void nextButton(ActionEvent e) throws IOException{
        nextbutton.getScene().getWindow().hide();
        
        Stage magnetic = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Magnetic3.fxml"));
        Scene scene =new Scene(root);
        magnetic.setScene(scene);
        magnetic.show();
        magnetic.setResizable(false);
    }
}
