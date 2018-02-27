package com.gestionvol;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.gestionvol.DB.DBConnection;
import com.gestionvol.DB.ReservationDB;
import com.gestionvol.DB.VolCompagnieDB;
import com.gestionvol.models.Reservation;
import com.gestionvol.models.Vol;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;

public class ControlPanelController implements Initializable {
	 @FXML
	    private ComboBox<String> classe;

	    @FXML
	    private ComboBox<String> depart;

	    @FXML
	    private ComboBox<String> destination;

	    @FXML
	    private DatePicker date;

	    @FXML
	    private TableView<Vol> volTable;
	    
	    @FXML
	    private TableColumn<Vol, String> tvol_companie;

	    @FXML
	    private TableColumn<Vol, String> tvol_date;

	    @FXML
	    private TableColumn<Vol, String> tvol_prix;

	    @FXML
	    private TableColumn<Vol, String> tvol_id_Vol;


	    @FXML
	    private Button scan;

	    @FXML
	    private TextField nom;

	    @FXML
	    private TextField prenom;

	    @FXML
	    private TextField passeport;

	    @FXML
	    private TextField companie;

	    @FXML
	    private TextField depart2;

	    @FXML
	    private TextField date2;

	    @FXML
	    private TextField prix;

	    @FXML
	    private CheckBox bagage;

	    @FXML
	    private Button reserve;

	    @FXML
	    private Tab n_passeport;

	    @FXML
	    private TextField id_res;

	    @FXML
	    private TextField num_passeport;

	    @FXML
	    private Button scan_id_res;

	    @FXML
	    private Button scan_passeport;

	    @FXML
	    private TableView<Reservation> restable;

	    @FXML
	    private TableColumn<Reservation, String> restable_nom;

	    @FXML
	    private TableColumn<Reservation, String> restable_prenom;

	    @FXML
	    private TableColumn<Reservation, String> restable_depart;

	    @FXML
	    private TableColumn<Reservation, String> restable_destination;

	    @FXML
	    private TextField res_nom;
	    
	    @FXML
	    private AnchorPane parent;
	    
	    @FXML
	    private TextField res_prenom;

	    @FXML
	    private TextField res_passport;

	    @FXML
	    private TextField res_dateres;

	    @FXML
	    private TextField res_depart;

	    @FXML
	    private TextField res_destination;

	    @FXML
	    private CheckBox res_bagage;

	    @FXML
	    private TextField res_prix;

	    @FXML
	    private TextField res_datedep;
	    
	    private VolCompagnieDB volcompagniedb ;
	    private ReservationDB reservationdb;
	    private  Vol selectedVol;
	    private  Reservation selectedRes;
	    
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		volcompagniedb = new VolCompagnieDB();
		reservationdb = new ReservationDB();
		selectedVol = null;
		selectedRes = null;
		volcompagniedb.createEntity();
		reservationdb.createEntity();
		volcompagniedb.close();
		initClasse();
		initDepart();
		initDestination();
		initTables();
		
	}
	
	public void initTables() {
		tvol_companie.setCellValueFactory(new PropertyValueFactory("nom"));
		tvol_date.setCellValueFactory(new PropertyValueFactory("date"));
		tvol_prix.setCellValueFactory(new PropertyValueFactory("prix"));
		tvol_id_Vol.setCellValueFactory(new PropertyValueFactory("id_vol"));
		
		
		
		restable_nom.setCellValueFactory(new PropertyValueFactory("nom"));
		restable_prenom.setCellValueFactory(new PropertyValueFactory("prenom"));
		restable_depart.setCellValueFactory(new PropertyValueFactory("depart"));
		restable_destination.setCellValueFactory(new PropertyValueFactory("destination"));
		
		volTable.setOnMouseClicked((MouseEvent event) -> {
		    if (event.getClickCount() > 1) {
		        onVolEdit();
		    }
		});
		
		
		restable.setOnMouseClicked((MouseEvent event) -> {
		    if (event.getClickCount() > 1) {
		        onReservationEdit();
		    }
		});
		
	}
	public void initClasse() {
		classe.getItems().addAll("1","2");
		classe.setValue("1");
		
	}
	

    void initDepart() {
	   System.out.println("depart");
	   ObservableList<String> l = volcompagniedb.getDepart();
		if(l != null)
			depart.setItems( l);
		
        volcompagniedb.close();    
		
	}
	

   void initDestination() {
	   System.out.println("destination");
	   ObservableList<String> l = volcompagniedb.getDestination();
		if(l != null)
			destination.setItems( l);
		
        volcompagniedb.close();    
		
	}
	

	 
	public void resetAll() {
		selectedVol = null;
		selectedRes = null;
		

		resetScanReserve();
		resetScanVol();
		
		volTable.setItems(null);
		restable.setItems(null);
		
		depart.setValue("");
		destination.setValue("");
		date.setValue(null);
	}
	
	
	
	
	
	
	 @FXML
    void updatePrixRes(ActionEvent event) {
		if (res_bagage.isSelected()){
		    float prix = Float.parseFloat(res_prix.getText());
		    prix += selectedRes.getPrixbagage();
		    
		    res_prix.setText(prix+"");
		    
		}else {
			float prix = Float.parseFloat(res_prix.getText());
	        prix -= selectedRes.getPrixbagage();
	        
	        res_prix.setText(prix+"");
		}
		
		

		  
    }
	 
	 
	 
	 @FXML
	    void updatePrix(ActionEvent event) {
			if (bagage.isSelected()){
			    float prix = Float.parseFloat(this.prix.getText());
			    prix += Float.parseFloat(selectedVol.getPrixbagage());
			    
			    this.prix.setText(prix+"");
			}else {
				float prix = Float.parseFloat(this.prix.getText());
		        prix -= Float.parseFloat(selectedVol.getPrixbagage());
		        
		        this.prix.setText(prix+"");
			}

			  
	    }
	
    @FXML
    void deleteReservation(ActionEvent event) {
    	if(selectedRes != null) {
    		reservationdb.removeById(selectedRes.getId_vol(), selectedRes.getId_res());
    		resetAll();
    	}else {
    		new Alert(AlertType.WARNING, "Veuillez selectionner une réservation!!!").showAndWait();
    	}
    }

    
    
 

    @FXML
    void reserve(ActionEvent event) {
    	String nom, prenom,passeport;
    	int idvol;
    	nom = this.nom.getText().trim();
    	prenom = this.prenom.getText().trim();
    	passeport = this.passeport.getText().trim();
    	idvol = Integer.parseInt(selectedVol.getId_vol());
    	
    	
    	if(nom.equals("") || prenom.equals("") || passeport.equals("") ||selectedVol == null) {
    		new Alert(AlertType.WARNING, "Veuillez remplir tous les champs!!!").showAndWait();
    	}else {
    		
        	reservationdb.addReservation(passeport,nom,prenom,bagage.isSelected(),idvol);
        	resetAll();
    	}
    	
    	
    }

    @FXML
    void scanByID(ActionEvent event) {
    	int id;
    	num_passeport.setText("");
		id = Integer.parseInt(this.id_res.getText());
		
    	
		ObservableList<Reservation> l = reservationdb.getRebyId(id);	
		if(l != null) {
			restable.setItems(l);
			resetScanReserve();
		}
    }

    @FXML
    void scanByPasseport(ActionEvent event) {
    	String id;
    	id_res.setText("");
		id = num_passeport.getText().trim();
		
    	
		ObservableList<Reservation> l = reservationdb.getRebyPasseport(id);	
		if(l != null) {
			restable.setItems(l);
			resetScanReserve();
		}
    
    }

    @FXML
    void scanVol(ActionEvent event) {
		String depart, destination, classe, date;
		
		depart = this.depart.getValue();
		destination = this.destination.getValue();
		classe = this.classe.getValue();
		date = this.date.getValue().toString();
    	
		ObservableList<Vol> l = volcompagniedb.getVol(depart, destination, classe, date);
		
		if(l != null) {
			volTable.setItems(l);
			resetScanVol();
		}
    }

    @FXML
    void updateReservation(ActionEvent event) {
    	if(selectedRes != null) {
    		int id = selectedRes.getId_res();
        	boolean b = res_bagage.isSelected();
        	reservationdb.update(id, b);
        	resetScanReserve();
    	}else {
    		new Alert(AlertType.WARNING, "Veuillez selectionner une réservation!!!").showAndWait();
    	}
    	
    }
    

    
    public void onReservationEdit() {
        // check the table's selected item and get selected item
        if (restable.getSelectionModel().getSelectedItem() != null) {
        	selectedRes = restable.getSelectionModel().getSelectedItem();
            res_nom.setText(selectedRes.getNom());
            res_prenom.setText(selectedRes.getPrenom());
            res_depart.setText(selectedRes.getDepart());
            res_destination.setText(selectedRes.getDestination());
            res_datedep.setText(selectedRes.getDatedepart());
            res_dateres.setText(selectedRes.getDatereservation());
            res_passport.setText(selectedRes.getNpasseport());
            res_bagage.setSelected(selectedRes.isBagage());
            if(selectedRes.isBagage()) {
            	float prix;
            	prix = selectedRes.getPrixvol() + selectedRes.getPrixbagage();
            	res_prix.setText(prix+"");
            }else {
            	res_prix.setText(selectedRes.getPrixvol()+"");
            }
        }
    }
    
    public void onVolEdit() {
        // check the table's selected item and get selected item
        if (volTable.getSelectionModel().getSelectedItem() != null) {
        	selectedVol = volTable.getSelectionModel().getSelectedItem();
            companie.setText(selectedVol.getNom());
            date2.setText(selectedVol.getDate());
            depart2.setText(depart.getValue());
            prix.setText(selectedVol.getPrix());
            bagage.setSelected(false);
        }
    }
    
    public void resetScanVol() {
    
        companie.setText("");
        date2.setText("");
        depart2.setText("");
        prix.setText("");
        bagage.setSelected(false);
        selectedVol = null;
        nom.setText("");
        prenom.setText("");
        passeport.setText("");
	}
    public void resetScanReserve() {
    	selectedRes = null;
    	res_prix.setText("");
        res_nom.setText("");
        res_prenom.setText("");
        res_depart.setText("");
        res_destination.setText("");
        res_datedep.setText("");
        res_dateres.setText("");
        res_passport.setText("");
        res_bagage.setSelected(false);
    }
}
