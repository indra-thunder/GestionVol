/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionvol;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.gestionvol.DB.DBConnection;
import com.gestionvol.DB.UserDB;
import com.gestionvol.models.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author CHABBAB
 */
public class LoginController implements Initializable {
    

 
    @FXML
    private TextField login;
    @FXML
    private PasswordField pwd;
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        
		DBConnection dbconnection = new DBConnection();
		UserDB user = new UserDB();
		dbconnection.setConnection();
		user.createEntity();
		user.close();
	
		User u = new User(login.getText(),pwd.getText());
		if(user.Search(u)) {
            System.out.println("success");
            user.close();
            openNext(event);

        }else{
        	System.out.println("failed");
        }
        user.close();
        dbconnection.closeConnection();
	}
    
    public void openNext(ActionEvent ev){
        Parent root;
        Stage stage = new Stage();
        try {
            root = FXMLLoader.load(getClass().getResource("/ControlPanel.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Control Panel");
            
            stage.show();
            
            Stage mystage = (Stage) pwd.getScene().getWindow();
            // do what you have to do
            mystage.close();
            
            
        
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
