/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import neuprojekt.MuayeneSonucTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 * FXML Controller class
 *
 * @author tugce
 */
public class Magnetic3Controller implements Initializable {

    @FXML
    private TextField kaynakno_field;

    @FXML
    private TextField kontrolUzun_field;

    @FXML
    private TextField kaynakyon_field;

    @FXML
    private TextField kalinlik_field;

    @FXML
    private TextField cap_field;

    @FXML
    private TextField hataTipi_field;

    @FXML
    private TextField hataninyeri_field;

    @FXML
    private ComboBox<String> sonuc_combo;

    @FXML
    private Button excelButton;

    @FXML
    private Button PDFButton;
    
    @FXML
    private TableView<MuayeneSonucTable> muayeneSonucview;

    @FXML
    private TableColumn<MuayeneSonucTable, String> sirano;

    @FXML
    private TableColumn<MuayeneSonucTable, String> kaynakno;

    @FXML
    private TableColumn<MuayeneSonucTable, String> kontroluzun;

    @FXML
    private TableColumn<MuayeneSonucTable, String> kaynakyon;

    @FXML
    private TableColumn<MuayeneSonucTable, String> kalinlik;

    @FXML
    private TableColumn<MuayeneSonucTable, String> cap;

    @FXML
    private TableColumn<MuayeneSonucTable, String> hatatipi;

    @FXML
    private TableColumn<MuayeneSonucTable, String> hatayeri;

    @FXML
    private TableColumn<MuayeneSonucTable, String> sonuc;

    @FXML
    private Button ekleButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        sonuc_combo.getItems().setAll(FXCollections.observableArrayList("OK","RED"));
        
        
        
    }    
    
    public void ExcelAction (ActionEvent e){
        if(sonuc_combo.getSelectionModel().getSelectedItem() == "RED" && (hataTipi_field.getText() == null || hataninyeri_field.getText() == null)){
            JOptionPane.showMessageDialog(null, "Lütfen Hata Tipi ve Hatanın Yeri bölgelerini doldurunuz.");
        } else if (sonuc_combo.getSelectionModel().getSelectedItem() == null || kaynakno_field.getText() == null ||
                kontrolUzun_field.getText() == null || kaynakyon_field.getText() == null || kalinlik_field.getText() == null){
            JOptionPane.showMessageDialog(null, "Lütfen zorunlu alanları doldurunuz.");
        } else {
            JOptionPane.showMessageDialog(null, "Excel işlemi başarıyla gerçekleştirilmiştir.");
        }
        
        
    }
    
    
    public static int a = 23;
    public static int siranodegisken = 0;
    public void ekleButtonAction(ActionEvent e){
        if(sonuc_combo.getSelectionModel().getSelectedItem() == "RED" && (hataTipi_field.getText().trim().isEmpty() || hataninyeri_field.getText().trim().isEmpty())){
            JOptionPane.showMessageDialog(null, "Lütfen Hata Tipi ve Hatanın Yeri bölgelerini doldurunuz.");
        } else if (sonuc_combo.getSelectionModel().getSelectedItem() == null || kaynakno_field.getText() == null ||
                kontrolUzun_field.getText() == null || kaynakyon_field.getText() == null || kalinlik_field.getText() == null){
            JOptionPane.showMessageDialog(null, "Lütfen zorunlu alanları doldurunuz.");
        } else {
        try {
            siranodegisken++;
            String siranoString = String.valueOf(siranodegisken);
            
            ObservableList<MuayeneSonucTable> muayeneList = FXCollections.observableArrayList();
            
            MuayeneSonucTable muayene =new MuayeneSonucTable(siranoString , kaynakno_field.getText(),kontrolUzun_field.getText(),kaynakyon_field.getText(),
            kalinlik_field.getText(),cap_field.getText(), hataTipi_field.getText() , hataninyeri_field.getText(),
            sonuc_combo.getSelectionModel().getSelectedItem());

            sirano.setCellValueFactory(new PropertyValueFactory<>("sirano"));
            kaynakno.setCellValueFactory(new PropertyValueFactory<>("kaynakno"));
            kontroluzun.setCellValueFactory(new PropertyValueFactory<>("kontrol"));
            kaynakyon.setCellValueFactory(new PropertyValueFactory<>("kaynakyon"));
            kalinlik.setCellValueFactory(new PropertyValueFactory<>("kalinlik"));
            cap.setCellValueFactory(new PropertyValueFactory<>("cap"));
            hatatipi.setCellValueFactory(new PropertyValueFactory<>("hatatipi"));
            hatayeri.setCellValueFactory(new PropertyValueFactory<>("hatayeri"));
            sonuc.setCellValueFactory(new PropertyValueFactory<>("sonuc"));

             muayeneSonucview.getItems().add(muayene);
             
            
             a++;
            try {
                
                FileInputStream file = new FileInputStream(new File("C:\\Users\\tugce\\Desktop\\Magnetic.xlsx"));
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(0);
                XSSFCell cell = null;
                
                cell = sheet.getRow(a).getCell(0);
                cell.setCellValue(siranoString);

                cell = sheet.getRow(a).getCell(1);
                cell.setCellValue(kaynakno_field.getText());

                cell = sheet.getRow(a).getCell(8);
                cell.setCellValue(kontrolUzun_field.getText());
                
                cell = sheet.getRow(a).getCell(11);
                cell.setCellValue(kaynakyon_field.getText());

                cell = sheet.getRow(a).getCell(17);
                cell.setCellValue(kalinlik_field.getText());
                
                cell = sheet.getRow(a).getCell(18);
                cell.setCellValue(cap_field.getText());

                cell = sheet.getRow(a).getCell(22);
                cell.setCellValue(hataTipi_field.getText());
                
                cell = sheet.getRow(a).getCell(24);
                cell.setCellValue(hataninyeri_field.getText());

                cell = sheet.getRow(a).getCell(27);
                cell.setCellValue(sonuc_combo.getSelectionModel().getSelectedItem());


                file.close();

                FileOutputStream outFile = new FileOutputStream(new File("C:\\Users\\tugce\\Desktop\\Magnetic.xlsx"));
                workbook.write(outFile);
                outFile.close();
            } catch (Exception ee) {
                System.out.println(ee);
            }
             
        } catch (Exception ee) {
            System.err.println(ee);
        }
        
            
            
        
        }
        
        
        
    }
    
    public void PDFAction (ActionEvent e) {
        
        Workbook workbook;
        try {
            workbook = new Workbook("C:\\Users\\tugce\\Desktop\\Magnetic.xlsx");
            workbook.save("MagneticReport.pdf", SaveFormat.PDF);
            JOptionPane.showMessageDialog(null, "PDF işlemi gerçekleştirildi.");
        } catch (Exception ex) {
            Logger.getLogger(Magnetic3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        
        
    } 
    
    
}
