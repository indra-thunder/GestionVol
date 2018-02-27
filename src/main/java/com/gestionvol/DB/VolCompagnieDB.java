package com.gestionvol.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gestionvol.DBAccess.EntityInterface;
import com.gestionvol.models.Vol;
import com.gestionvol.models.VolCompanie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VolCompagnieDB implements EntityInterface {

	private static final String VOL_TABLE = "create table if not exists vols ( "
		      + " id INT PRIMARY KEY AUTO_INCREMENT,"
		      + " depart VARCHAR(20),"
		      + " destination VARCHAR(20),"
		      + " id_compagnie INT(11),"
		      + " datedepart DATE,"
		      + " heuredepart TIME,"
		      + " nbplace INT(3),"
		      + " nbdispo INT(3),"
		      + " classe INT(1),"
		      + " prixbagage FLOAT(6),"
		      + " prixvol FLOAT(6)"
		      + " )";
	
	private static final String COMPAGNIE_TABLE = "create table if not exists compagnies ( "
		      + " id INT PRIMARY KEY AUTO_INCREMENT,"
		      + " nom VARCHAR(20)"
		      + " )";
	

	private static final String TABLE_VOL_NAME = "vols";
	private static final String TABLE_COMPAGNIE_NAME = "compagnies";
	
	private PreparedStatement  pstm = null;
	private Statement stm = null;
	private ResultSet rslt = null;
	private Connection conn;
		
	
	
	
	
	@Override
	public void createEntity() {
		// TODO Auto-generated method stub
		try {
			
			if((conn = DBConnection.getConnection())== null) {
				conn = DBConnection.setConnection();
			}
	
			
		  stm = conn.createStatement();
		  stm.executeUpdate(VOL_TABLE);
		  stm.executeUpdate(COMPAGNIE_TABLE);		  
		  
		
		  System.out.println("CreateReservationsTableMySQL: table created.");
		
		} catch (SQLException e) {
		  System.out.println("error: failed to create a connection object.");
		  e.printStackTrace();
		} catch (Exception e) {
		  System.out.println("other error:");
		  e.printStackTrace();
		} finally {
		  try {
		    close();      
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		}
	}

	@Override
	public void removeEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String condition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getRows(PreparedStatement s) {
		// TODO Auto-generated method stub
		return null;
	}


	public ObservableList<Vol> getVol(String depart, String destination, String classe, String date) {
		// TODO Auto-generated method stub
		try{

            // PreparedStatements can use variables and are more efficient
            pstm = conn.prepareStatement("select c.nom, v.id, datedepart, prixvol,heuredepart, prixbagage"
                    		+ " from "+TABLE_VOL_NAME + " v,"+ TABLE_COMPAGNIE_NAME + " c"
                    		+ " where depart =? and destination = ? and classe = ? and  datedepart = ? and v.id = c.id and nbdispo >= 0");
        
            pstm.setString(1,depart);
            pstm.setString(2,destination);
            pstm.setString(3, classe);
            pstm.setString(4, date);
            rslt = pstm.executeQuery();
            
            ArrayList<Vol> l = new ArrayList<Vol>();
            Vol v;
            while(rslt.next()) {
            	v = new Vol();
            	v.setNom(rslt.getString("nom"));
            	v.setPrix(rslt.getString("prixvol"));
            	v.setDate(rslt.getString("datedepart")+" "+rslt.getString("heuredepart"));
            	v.setId_vol(rslt.getString("id"));
            	v.setPrixbagage(rslt.getString("prixbagage"));
            	
            	System.out.println(v.toString());
            	

            	l.add(v);
            }
            	
            ObservableList data = FXCollections.observableList(l);
            
         
            
            
            return data;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
		return null;
        
	}
	
	
	
	public ObservableList<String> getDepart() {
		// TODO Auto-generated method stub
		
		try{
			if((conn = DBConnection.getConnection())== null) {
				
				conn = DBConnection.setConnection();
			}

            // PreparedStatements can use variables and are more efficient
            stm = conn.createStatement();
        
            
            
            rslt = stm.executeQuery("select depart from "+TABLE_VOL_NAME );
            
            
            ArrayList<String> l = new ArrayList<String>();
            
            while(rslt.next()) {
            	l.add(rslt.getString("depart"));
            	System.out.println(rslt.getString("depart"));
            }
            	
            
            
            
            ObservableList data = FXCollections.observableList(l);
            
         
            
            
            return data;
          
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
		return null;
        
	}
	
	public ObservableList<String> getDestination() {
		// TODO Auto-generated method stub
		try{

            // PreparedStatements can use variables and are more efficient
            stm = conn.createStatement();
        
            
            
            rslt = stm.executeQuery("select destination from "+TABLE_VOL_NAME );
            
            ArrayList<String> l = new ArrayList<String>();
            
            while(rslt.next())
            	l.add(rslt.getString("destination"));
            
            
            ObservableList data = FXCollections.observableList(l);
            
         
            
            
            return data;
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
		return null;
        
	}
	
	@Override
	public void truncate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		try {
			if(stm != null)
				stm.close();
			if(rslt != null)
				rslt.close();
			if(pstm != null)
				pstm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean Search(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getAll() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
