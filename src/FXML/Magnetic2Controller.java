/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class Magnetic2Controller implements Initializable {
    
    @FXML
    private CheckBox alinKaynagi_check;

    @FXML
    private CheckBox koseKaynagi_check;

    @FXML
    private Button nextbutton;

    @FXML
    private TextField kutupmesafesifield;

    @FXML
    private TextField miknatislama_field;

    @FXML
    private TextField mptasiyici_field;

    @FXML
    private TextField cihaz_field;

    @FXML
    private TextField uvisikSiddeti_field;

    @FXML
    private TextField isikmesafesi_field;

    @FXML
    private TextField muayeneBolgesi_field;

    @FXML
    private ChoiceBox<String> akim_choice;

    @FXML
    private TextField luxmetre_field;

    @FXML
    private TextField muayeneOrtami_field;

    @FXML
    private TextField miknatisGiderimi_field;

    @FXML
    private TextField isilIslem_field;

    @FXML
    private TextField muayeneBolgeAkim_field;

    @FXML
    private TextField yuzey_field;

    @FXML
    private TextField isCihazi_field;

    @FXML
    private TextField kaldirmaTestiTarihi_field;

    @FXML
    private TextArea standarttanSapmalar_field;

    @FXML
    private TextArea aciklamalar_field;

    @FXML
    private Button kaydetButton;

    @FXML
    private ComboBox<String> ekipmansec;

    @FXML
    private Label sicaklik_label;

    @FXML
    private Label muayeneTarihi_label;
    
    @FXML
    private ImageView image1;
    
    @FXML
    private ImageView image2;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        muayeneBolgesi_field.setText("KAYNAK+HAZ");
        akim_choice.getItems().setAll(FXCollections.observableArrayList("AC","DC"));
        luxmetre_field.setText("1200 Lux");
        muayeneBolgeAkim_field.setText("3.2 kA/m");
        yuzey_field.setText("TAŞLANMIŞ / GRINDING");
        isCihazi_field.setText("***");
        //yüzey sıcaklığı
        standarttanSapmalar_field.setText("Standarttan sapmalar yoktur.");
        
        
        
        try {
            
            muayeneTarihi_label.setText(RaporsecController.tarih.toString());
            
        } catch (Exception e) {
            System.err.println(e);
        }
        try {
            Image image = new Image("/images/Adsiz.png");
            image1.setImage(image);
            image1.setCache(true);
            
            Image imagee = new Image("/images/Adsiz2.png");
            image2.setImage(imagee);
            image2.setCache(true);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        //Ekipman Bilgisi seçim
        ObservableList<String> ekipmanList = FXCollections.observableArrayList();
        try {
            Connection con = Connectionsql.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM EQUIPMENT_INFO");
            while (rs.next()) {
                ekipmanList.add(rs.getString("EKIPMAN_BILGISI"));
                
            }
            
            ekipmansec.setItems(ekipmanList);
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ekipman bilgisi seçilemedi" + ex);
        }
        
        
        
        
        
        
    }    
    public void nextButton(ActionEvent e) throws IOException{
        
        if(ekipmansec.getSelectionModel().getSelectedItem() == null || kutupmesafesifield.getText() == null ||
                cihaz_field.getText() == null || mptasiyici_field.getText() == null || 
                miknatislama_field.getText() == null || uvisikSiddeti_field.getText() == null ||
                isikmesafesi_field.getText() == null || muayeneBolgeAkim_field.getText() == null ||
                muayeneBolgesi_field.getText() == null || akim_choice.getSelectionModel().getSelectedItem() == null ||
                luxmetre_field.getText() == null || yuzey_field.getText() == null ||
                isCihazi_field.getText() == null || kaldirmaTestiTarihi_field.getText() == null){
            JOptionPane.showMessageDialog(null, "Lütfen zorunlu alanları doldurunuz");
        } else {
            
            try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\tugce\\Desktop\\Magnetic.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFCell cell = null;


            cell = sheet.getRow(8).getCell(4);
            cell.setCellValue(kutupmesafesifield.getText());

            cell = sheet.getRow(9).getCell(4);
            cell.setCellValue(cihaz_field.getText());

            cell = sheet.getRow(10).getCell(4);
            cell.setCellValue(mptasiyici_field.getText());

            cell = sheet.getRow(11).getCell(4);
            cell.setCellValue(miknatislama_field.getText());
            
            cell = sheet.getRow(12).getCell(4);
            cell.setCellValue(uvisikSiddeti_field.getText());

            cell = sheet.getRow(13).getCell(4);
            cell.setCellValue(isikmesafesi_field.getText());

            cell = sheet.getRow(8).getCell(16);
            cell.setCellValue(muayeneBolgesi_field.getText());
            
            cell = sheet.getRow(9).getCell(16);
            cell.setCellValue(akim_choice.getSelectionModel().getSelectedItem());

            cell = sheet.getRow(10).getCell(16);
            cell.setCellValue(luxmetre_field.getText());

            cell = sheet.getRow(11).getCell(16);
            cell.setCellValue(muayeneOrtami_field.getText());
            
            cell = sheet.getRow(12).getCell(16);
            cell.setCellValue(miknatisGiderimi_field.getText());
            
            cell = sheet.getRow(13).getCell(16);
            cell.setCellValue(isilIslem_field.getText());
            
            //yüzey sıcaklığı gelecek
          //  cell = sheet.getRow(8).getCell(25);
          //  cell.setCellValue(muayeneOrtami_field.getText());
            
            cell = sheet.getRow(9).getCell(25);
            cell.setCellValue(muayeneBolgeAkim_field.getText());
            
            cell = sheet.getRow(10).getCell(25);
            cell.setCellValue(yuzey_field.getText());
            
            cell = sheet.getRow(11).getCell(25);
            cell.setCellValue(isCihazi_field.getText());
            
            cell = sheet.getRow(12).getCell(25);
            cell.setCellValue(kaldirmaTestiTarihi_field.getText());
            
            //checkbozlar gelecek
            
            
            cell = sheet.getRow(19).getCell(7);
            cell.setCellValue(standarttanSapmalar_field.getText());
            
            cell = sheet.getRow(20).getCell(7);
            cell.setCellValue(muayeneTarihi_label.getText());
            
            cell = sheet.getRow(21).getCell(7);
            cell.setCellValue(aciklamalar_field.getText());
            
            file.close();

            FileOutputStream outFile = new FileOutputStream(new File("C:\\Users\\tugce\\Desktop\\Magnetic.xlsx"));
            workbook.write(outFile);
            outFile.close();
            } catch (Exception ee) {
                System.out.println(ee);
            }
            nextbutton.getScene().getWindow().hide();
        
            Stage magnetic = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Magnetic3.fxml"));
            Scene scene =new Scene(root);
            magnetic.setScene(scene);
            magnetic.show();
            magnetic.setResizable(false);
        
        }
        
    }
    
    public void EkipmanAction(ActionEvent e){
        String value = ekipmansec.getSelectionModel().getSelectedItem();
        try {
            Connection con = Connectionsql.getConnection();
            
            String query = "SELECT * FROM EQUIPMENT_INFO WHERE ekipman_bilgisi = ? ";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, value);
            ResultSet st = statement.executeQuery();
            while(st.next()){
                String a = st.getString("POLE_DISTANCE");
                kutupmesafesifield.setText(a);
                
                String b = st.getString("EQUIPMENT");
                cihaz_field.setText(b);
                
                String c = st.getString("MPCARRIERMEDIUM");
                mptasiyici_field.setText(c);
                
                String d = st.getString("MAG_TECH");
                miknatislama_field.setText(d);
                
                String f = st.getString("UVLIGHTINTENSITY");
                uvisikSiddeti_field.setText(f);
                
                String g = st.getString("DISTANCEOFLIGHT");
                isikmesafesi_field.setText(g); 
            }
            
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ekipman action çalışmıyor" + ex);
        }
        
    }
    
    
    
    
}
