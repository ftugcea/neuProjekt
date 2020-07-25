/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import static FXML.RaporsecController.degLevel;
import static FXML.RaporsecController.degerlendiren;
import static FXML.RaporsecController.onay;
import static FXML.RaporsecController.opLevel;
import static FXML.RaporsecController.onayLevel;
import static FXML.RaporsecController.operator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import neuprojekt.Connectionsql;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 * FXML Controller class
 *
 * @author tugce
 */
public class Magnetic1Controller implements Initializable {

    @FXML
    private Button nextbutton;
    
    @FXML
    private ComboBox<String> musteri_choice;

    @FXML
    private ChoiceBox<String> project_choice;

    @FXML
    private TextField testyeri_field;

    @FXML
    private TextField muayene_stan_field;

    @FXML
    private TextField deg_stand_field;

    @FXML
    private TextField muayene_pros_field;

    @FXML
    private ChoiceBox<String> yuze_choice;

    @FXML
    private ChoiceBox<String> muayene_as_choice;

    @FXML
    private Spinner<Integer> muayene_kap_spinner;

    @FXML
    private TextField resim_no_field;

    @FXML
    private TextField sayfa_no_field;

    @FXML
    private Label rapor_no_label;

    @FXML
    private Label raportarihilabel;

    @FXML
    private ComboBox<String> isemriCombo;

    @FXML
    private ChoiceBox<String> teklifno;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Müşteri seçim listesi
        ObservableList<String> musterilist = FXCollections.observableArrayList();
        try {
            Connection con = Connectionsql.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM KUNDE");
            while (rs.next()) {
                musterilist.add(rs.getString("FIRMA_NAME"));
                
            }
            
            musteri_choice.setItems(musterilist);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Magnetic1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Proje seçim listesi
        project_choice.getItems().setAll(FXCollections.observableArrayList("Kaynakçı Testi","A Testi","B Testi"));
       // project_choice.setValue("Kaynakçı Testi");
       // Default değerler Magnetic1
        
        muayene_stan_field.setText("TS EN ISO 17638");
        deg_stand_field.setText("TS EN ISO 23278 Class B");
        muayene_pros_field.setText("P-101-004");
        sayfa_no_field.setText("1");
        
        muayene_kap_spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        muayene_kap_spinner.getValueFactory().setValue(100);
        
        resim_no_field.setText("-");
        muayene_as_choice.getItems().setAll(FXCollections.observableArrayList("Untreated","aa","bb"));
        
        // tarihi başka ekrandan çekme
        try {
            
            raportarihilabel.setText(RaporsecController.tarih.toString());
            rapor_no_label.setText(raportarihilabel.getText() + 1);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        // Yüzey durumu atama  
        ObservableList<String> yuzeyList = FXCollections.observableArrayList();
        try {
            Connection con = Connectionsql.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM MAGNETIC1_TABLE WHERE YUZEY_DURUMU IS NOT NULL");
            while (rs.next()) {
                yuzeyList.add(rs.getString("YUZEY_DURUMU"));
                
            }
            
            yuze_choice.setItems(yuzeyList);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Magnetic1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        // İş Emri No atama
        ObservableList<String> isemriList = FXCollections.observableArrayList();
        try {
            Connection con = Connectionsql.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM MAGNETIC1_TABLE WHERE ISEMRINO IS NOT NULL");
            while (rs.next()) {
                isemriList.add(rs.getString("ISEMRINO"));
                
            }
            
            isemriCombo.setItems(isemriList);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Magnetic1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Teklif no atama
        ObservableList<String> teklifnoList = FXCollections.observableArrayList();
        try {
            Connection con = Connectionsql.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM MAGNETIC1_TABLE WHERE TEKLIFNO IS NOT NULL");
            while (rs.next()) {
                teklifnoList.add(rs.getString("TEKLIFNO"));
                
            }
            
            teklifno.setItems(teklifnoList);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Magnetic1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //opLevel degerleri için
        
        try {
            Connection con = Connectionsql.getConnection();
            String query = "SELECT * FROM PERSON WHERE NAME=? ";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, operator);
        
        ResultSet st = statement.executeQuery();
        while(st.next()){
            opLevel = st.getString("LEVEL");
        }
        } catch (Exception e) {
            System.err.println(e);
        }
        
        try {
            Connection con1 = Connectionsql.getConnection();
            String query1 = "SELECT * FROM PERSON WHERE NAME=? ";
        PreparedStatement statement = con1.prepareStatement(query1);
        statement.setString(1, degerlendiren);
        
        ResultSet st1 = statement.executeQuery();
        while(st1.next()){
            degLevel = st1.getString("LEVEL");
        }
            System.out.println(degLevel);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        try {
            Connection con2 = Connectionsql.getConnection();
            String query2 = "SELECT * FROM PERSON WHERE NAME=? ";
        PreparedStatement statement = con2.prepareStatement(query2);
        statement.setString(1, onay);
        
        ResultSet st2 = statement.executeQuery();
        while(st2.next()){
            onayLevel = st2.getString("LEVEL");
        }
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }  
    public void TestyeriAction(ActionEvent e){
        String value = musteri_choice.getSelectionModel().getSelectedItem();        
        try {
            Connection con = Connectionsql.getConnection();
            
            String query = "SELECT * FROM KUNDE WHERE FIRMA_NAME=? ";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, value);
            ResultSet st = statement.executeQuery();
            while(st.next()){
                String a = st.getString("ADRESSE");
                testyeri_field.setText(a);
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Magnetic1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void nextButton(ActionEvent e) throws IOException{
        
        if(musteri_choice.getSelectionModel().getSelectedItem()== null || project_choice.getSelectionModel().getSelectedItem() == null ||
                testyeri_field.getText()==null || muayene_stan_field.getText() == null ||
                deg_stand_field.getText()==null || muayene_pros_field.getText() == null ||
                muayene_kap_spinner.getValue() == null || yuze_choice.getSelectionModel().getSelectedItem()==null ||
                muayene_as_choice.getSelectionModel().getSelectedItem() == null || isemriCombo.getSelectionModel().getSelectedItem() == null ||
                teklifno.getSelectionModel().getSelectedItem()==null) {
            JOptionPane.showMessageDialog(null, "Lütfen zorunlu alanları doldurunuz!");
        } else {
                try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\tugce\\Desktop\\Magnetic.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFCell cell = null;


            cell = sheet.getRow(2).getCell(3);
            cell.setCellValue(musteri_choice.getSelectionModel().getSelectedItem());

            cell = sheet.getRow(3).getCell(3);
            cell.setCellValue(project_choice.getSelectionModel().getSelectedItem());

            cell = sheet.getRow(4).getCell(3);
            cell.setCellValue(testyeri_field.getText());

            cell = sheet.getRow(5).getCell(3);
            cell.setCellValue(muayene_stan_field.getText());

            cell = sheet.getRow(6).getCell(3);
            cell.setCellValue(deg_stand_field.getText());

            cell = sheet.getRow(2).getCell(19);
            cell.setCellValue(muayene_pros_field.getText());

            //Muayene kapsamı gelecek
            cell = sheet.getRow(3).getCell(19);
            cell.setCellValue(muayene_kap_spinner.getValue()+"%");

            cell = sheet.getRow(4).getCell(19);
            cell.setCellValue(resim_no_field.getText());

            cell = sheet.getRow(5).getCell(19);
            cell.setCellValue(yuze_choice.getSelectionModel().getSelectedItem());

            cell = sheet.getRow(6).getCell(19);
            cell.setCellValue(muayene_as_choice.getSelectionModel().getSelectedItem());

            cell = sheet.getRow(2).getCell(26);
            cell.setCellValue(sayfa_no_field.getText());

            cell = sheet.getRow(3).getCell(26);
            cell.setCellValue(rapor_no_label.getText());

            cell = sheet.getRow(4).getCell(26);
            cell.setCellValue(raportarihilabel.getText());

            cell = sheet.getRow(5).getCell(26);
            cell.setCellValue(isemriCombo.getSelectionModel().getSelectedItem());

            cell = sheet.getRow(6).getCell(26);
            cell.setCellValue(teklifno.getSelectionModel().getSelectedItem());
            
            // operatör değerlendiren onay ve tarih bölgesi
            cell = sheet.getRow(39).getCell(5);
            cell.setCellValue(RaporsecController.operator);
            
            cell = sheet.getRow(40).getCell(5);
            cell.setCellValue(RaporsecController.opLevel);
            
            cell = sheet.getRow(41).getCell(5);
            cell.setCellValue(RaporsecController.tarih);
            
            cell = sheet.getRow(39).getCell(15);
            cell.setCellValue(RaporsecController.degerlendiren);
            
            cell = sheet.getRow(40).getCell(15);
            cell.setCellValue(RaporsecController.degLevel);
            
            cell = sheet.getRow(41).getCell(15);
            cell.setCellValue(RaporsecController.tarih);
            
            cell = sheet.getRow(39).getCell(20);
            cell.setCellValue(RaporsecController.onay);
            
            cell = sheet.getRow(40).getCell(20);
            cell.setCellValue(RaporsecController.onayLevel);
            
            cell = sheet.getRow(41).getCell(20);
            cell.setCellValue(RaporsecController.tarih);
            
            
            file.close();

            FileOutputStream outFile = new FileOutputStream(new File("C:\\Users\\tugce\\Desktop\\Magnetic.xlsx"));
            workbook.write(outFile);
            outFile.close();
            } catch (Exception ee) {
                System.out.println(ee);
            }
                nextbutton.getScene().getWindow().hide();

            Stage magnetic = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Magnetic2.fxml"));
            Scene scene =new Scene(root);
            magnetic.setScene(scene);
            magnetic.show();
            magnetic.setResizable(false);
        }
        
        
    }
   
}
