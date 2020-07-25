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
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import neuprojekt.Connectionsql;
import neuprojekt.KayitliKullanici;
import neuprojekt.RaporBilgileri;



/**
 * FXML Controller class
 *
 * @author tugce
 */
public class RaporsecController implements Initializable {

    @FXML
    private TextField musteri_field;

    @FXML
    private TextField adres_field;

    @FXML
    private Button ekleButton1;

    @FXML
    private TextField yuzeyDurumu_field;

    @FXML
    private Button ekleButton2;

    @FXML
    private TextField isemrino_field;

    @FXML
    private TextField teklifno_field;

    @FXML
    private DatePicker raportarihi;

    @FXML
    private Button manyetik;

    @FXML
    private Button radyografik;
    
    @FXML
    private ComboBox<String> operatorCombo;

    @FXML
    private ComboBox<String> degerlendirenCombo;

    @FXML
    private ComboBox<String> onayCombo;
    
    @FXML
    private TableColumn<KayitliKullanici, String> mustericolumn;

    @FXML
    private TableColumn<KayitliKullanici, String> adrescolumn;

    @FXML
    private TableColumn<RaporBilgileri, String> yuzeydurumucolumn;

    @FXML
    private TableColumn<RaporBilgileri, String> teklifnocolumn;

    @FXML
    private TableColumn<RaporBilgileri, String> isemrinocolumn;
    
    @FXML
    private TableView<RaporBilgileri> raporview;
    
    @FXML
    private TableView<KayitliKullanici> musteriview;
    
    @FXML
    private TableView<RaporBilgileri> teklifnoTable;
    
    @FXML
    private TableView<RaporBilgileri> isemrinoTable;

    public static LocalDate tarih;
    public static String operator;
    public static String degerlendiren;
    public static String onay;
    public static String opLevel;
    public static String degLevel;
    public static String onayLevel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> personList = FXCollections.observableArrayList();
        try {
            Connection con = Connectionsql.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM PERSON");
            
            while (rs.next()) {
                personList.add(rs.getString("NAME"));
            }
            
            operatorCombo.setItems(personList);
            degerlendirenCombo.setItems(personList);
            onayCombo.setItems(personList);
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Magnetic1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
    public void manyetikAction (ActionEvent e) throws IOException {
        
        tarih = raportarihi.getValue();
        operator = operatorCombo.getSelectionModel().getSelectedItem();
        degerlendiren = degerlendirenCombo.getSelectionModel().getSelectedItem();
        onay = onayCombo.getSelectionModel().getSelectedItem();
        
        if (operator == null){
            JOptionPane.showMessageDialog(null, "Lütfen operatör seçiniz.");
        } else if (degerlendiren == null){
            JOptionPane.showMessageDialog(null, "Lütfen değerlendiren seçiniz.");
        } else if (onay == null){
            JOptionPane.showMessageDialog(null, "Lütfen onaylayan seçiniz.");
        } else if (tarih == null){
            JOptionPane.showMessageDialog(null, "Lütfen tarih seçiniz.");
        } else {
            try {
            Stage magnetic = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Magnetic1.fxml"));
            Scene scene =new Scene(root);
            magnetic.setScene(scene);
            magnetic.show();
            magnetic.setResizable(false);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        
        manyetik.getScene().getWindow().hide();
        }
        
        
        
        
    }
    
    public void Ekle1Action (ActionEvent e){
        if(musteri_field.getText() == null){
            JOptionPane.showMessageDialog(null, "Lütfen müşteri bilgisini giriniz.");
        } else if (adres_field.getText() == null){
            JOptionPane.showMessageDialog(null, "Lütfen adres bilgisini giriniz.");
        } else {
            String query = "INSERT INTO KUNDE (firma_name , adresse) VALUES (?,?)";
            try {
             Connection con = Connectionsql.getConnection();
             PreparedStatement p;
             p = con.prepareStatement(query);
             p.setString(1, musteri_field.getText());
             p.setString(2, adres_field.getText());
                          
             p.executeUpdate();
             p.close();
             con.close();
             
             JOptionPane.showMessageDialog(null, "Ekleme yapıldı");
         } catch (Exception ee) {
             
             JOptionPane.showMessageDialog(null, "Ekleme yapılamadı" + ee);
             
         }
        }
    }
    
    public void Ekle2Action(ActionEvent e){
      //  String query = "INSERT INTO MAGNETIC1_TABLE (yuzey_durumu , teklifno , isemrino) VALUES ( ? , ? , ?)";
        boolean c ;
        c = yuzeyDurumu_field.getText().isEmpty();
        if(c == false){
            String query = "INSERT INTO MAGNETIC1_TABLE (yuzey_durumu) VALUES ( ? )";
            try {
                 Connection con = Connectionsql.getConnection();
                 PreparedStatement p;
                 p = con.prepareStatement(query);

                 p.setString(1, yuzeyDurumu_field.getText());
                 
                 p.executeUpdate();
                 p.close();
                 con.close();

               //  JOptionPane.showMessageDialog(null, "Ekleme yapıldı1");
             } catch (Exception ee) {

                 JOptionPane.showMessageDialog(null, "Ekleme yapılamadı" + ee);

             }
        }
        boolean b ;
        b = teklifno_field.getText().isEmpty();
        if(b == false){
            String query = "INSERT INTO MAGNETIC1_TABLE (teklifno) VALUES ( ? )";
            try {
                 Connection con = Connectionsql.getConnection();
                 PreparedStatement p;
                 p = con.prepareStatement(query);

                 p.setString(1, teklifno_field.getText());
                 
                 p.executeUpdate();
                 p.close();
                 con.close();

              //   JOptionPane.showMessageDialog(null, "Ekleme yapıldı2");
             } catch (Exception ee) {

                 JOptionPane.showMessageDialog(null, "Ekleme yapılamadı" + ee);

             }
        }
        
        boolean a ;
        a = isemrino_field.getText().isEmpty();
        if(a == false){
            String query = "INSERT INTO MAGNETIC1_TABLE (isemrino) VALUES ( ? )";
            try {
                 Connection con = Connectionsql.getConnection();
                 PreparedStatement p;
                 p = con.prepareStatement(query);

                 p.setString(1, isemrino_field.getText());
                 
                 p.executeUpdate();
                 p.close();
                 con.close();

             //    JOptionPane.showMessageDialog(null, "Ekleme yapıldı3");
             } catch (Exception ee) {

                 JOptionPane.showMessageDialog(null, "Ekleme yapılamadı" + ee);

             }
            JOptionPane.showMessageDialog(null, "Ekleme yapıldı.");
        }
    }
    ObservableList<KayitliKullanici> kullanici = FXCollections.observableArrayList();
    public void KayitliKullaniciAction(ActionEvent e) throws SQLException{
        Connection con = Connectionsql.getConnection();
        
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM KUNDE");
        
        while (rs.next()) {
            kullanici.add(new KayitliKullanici(rs.getString("firma_name"), rs.getString("adresse")));
        }
        
        mustericolumn.setCellValueFactory(new PropertyValueFactory<>("musteri"));
        adrescolumn.setCellValueFactory(new PropertyValueFactory<>("adres"));
        
        
        musteriview.setItems(kullanici);
    }
    ObservableList<RaporBilgileri> yuzeyList = FXCollections.observableArrayList();
    ObservableList<RaporBilgileri> teklifList = FXCollections.observableArrayList();
    ObservableList<RaporBilgileri> isemriList = FXCollections.observableArrayList();
    public void RaporBilgisiAction(ActionEvent e) {
        try {
            Connection con = Connectionsql.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM MAGNETIC1_TABLE WHERE YUZEY_DURUMU IS NOT NULL");

            while (rs.next()) {
                yuzeyList.add(new RaporBilgileri(rs.getString("yuzey_durumu"), rs.getString("teklifno"), rs.getString("isemrino")));
            }
            yuzeydurumucolumn.setCellValueFactory(new PropertyValueFactory<>("yuzeydurumu"));
            raporview.setItems(yuzeyList);
            con.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
        
        try {
            Connection con1 = Connectionsql.getConnection();
            ResultSet rs1 = con1.createStatement().executeQuery("SELECT * FROM MAGNETIC1_TABLE WHERE TEKLIFNO IS NOT NULL");

            while (rs1.next()) {
                teklifList.add(new RaporBilgileri(rs1.getString("yuzey_durumu"), rs1.getString("teklifno"), rs1.getString("isemrino")));
            }
            teklifnocolumn.setCellValueFactory(new PropertyValueFactory<>("teklifno"));
            teklifnoTable.setItems(teklifList);

            con1.close();
        } catch (Exception e2) {
            System.err.println(e2);
        }
        
        try {
            Connection con2 = Connectionsql.getConnection();
            ResultSet rs2 = con2.createStatement().executeQuery("SELECT * FROM MAGNETIC1_TABLE WHERE ISEMRINO IS NOT NULL");

            while (rs2.next()) {
                isemriList.add(new RaporBilgileri(rs2.getString("yuzey_durumu"), rs2.getString("teklifno"), rs2.getString("isemrino")));
            }
            isemrinocolumn.setCellValueFactory(new PropertyValueFactory<>("isemrino"));
            isemrinoTable.setItems(isemriList);

            con2.close();
        } catch (Exception e3) {
            System.err.println(e3);
        }
        
    }
    
}
