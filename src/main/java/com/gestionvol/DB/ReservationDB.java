package com.gestionvol.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gestionvol.DBAccess.EntityInterface;
import com.gestionvol.models.Reservation;
import com.gestionvol.models.Vol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReservationDB implements EntityInterface {

	private static final String RESERVATIONS_TABLE = "create table if not exists reservations ( "
		      + " id INT PRIMARY KEY AUTO_INCREMENT,"
		      + " npasseport VARCHAR(20),"
		      + " nom VARCHAR(20),"
		      + " prenom VARCHAR(20),"
		      + " datereservation DATETIME,"
		      + " bagage BOOLEAN,"
		      + " id_vol INT(11),"
		      + " status VARCHAR(11)"
		      + " )";

	private static final String TABLE_NAME = "reservations";
	
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
		  stm.executeUpdate(RESERVATIONS_TABLE);
		  
		
		
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

	public void addReservation (String passeport, String nom, String prenom,boolean bagage, int idvol) {
		// TODO Auto-generated method stub
		
	
		
		try {
			if((conn = DBConnection.getConnection())== null) {
				conn = DBConnection.setConnection();
			}
	
			
			
		
			  pstm = conn.prepareStatement("insert into "+TABLE_NAME+"(id, npasseport, nom, prenom,datereservation,bagage,id_vol,status) "
			  		+ "values(default, ?,?,?,now(),?,?,'confirmed')");
			  
			  pstm.setString(1, passeport);
			  pstm.setString(2, nom);
			  pstm.setString(3, prenom);
			  pstm.setBoolean(4, bagage);
			  pstm.setInt(5, idvol);
			  
			 
			  
			  pstm.executeUpdate();
			  
			  
			  pstm = conn.prepareStatement("update vols"
				  		+ " set nbdispo = nbdispo+1"
				  		+ " where id = ?");
				  
				  
			  pstm.setInt(1, idvol);
			  pstm.executeUpdate();
			  
		  }catch(SQLException e){
			 e.printStackTrace();
		  }
		  
	}


	public void removeById(int id_vol, int id_res) {
		// TODO Auto-generated method stub
		try {
			if((conn = DBConnection.getConnection())== null) {
				conn = DBConnection.setConnection();
			}
	
			
			
		
			  pstm = conn.prepareStatement("update "+ TABLE_NAME
			  		+ " set status = 'removed'"
			  		+ " where id = ?");
			  
			 
			  pstm.setInt(1, id_res);
			  pstm.executeUpdate();
			  
			  
			  pstm = conn.prepareStatement("update vols"
			  		+ " set nbdispo = nbdispo-1"
			  		+ " where id = ?");
			  
			  
			  pstm.setInt(1, id_vol);
			  pstm.executeUpdate();
			  
		  }catch(SQLException e){
			 e.printStackTrace();
		  }
	}

	@Override
	public void delete(String condition) {
		// TODO Auto-generated method stub
		
	}
	
	public ObservableList<Reservation> getRebyId(int id) {
		// TODO Auto-generated method stub
		try{

            // PreparedStatements can use variables and are more efficient
            pstm = conn.prepareStatement("select id_vol, r.id,nom, prenom,datedepart,depart,destination,npasseport,bagage,prixbagage,prixvol,datereservation"
                    		+ " from reservations r, vols v "
                    		+ " where v.id = id_vol and status='confirmed' and r.id = ?");
        
            pstm.setInt(1,id);
       
            rslt = pstm.executeQuery();
            
            ArrayList<Reservation> l = new ArrayList<Reservation>();
            Reservation r;
            while(rslt.next()) {
            	r = new Reservation();
            	r.setId_res(id);
            	r.setNom(rslt.getString("nom"));
            	r.setPrenom(rslt.getString("prenom"));
            	r.setDepart(rslt.getString("depart"));
            	r.setDatedepart(rslt.getString("datedepart"));
            	r.setDestination(rslt.getString("destination"));
            	r.setNpasseport(rslt.getString("npasseport"));
            	r.setId_vol(rslt.getInt("id_vol"));
            	r.setBagage(rslt.getBoolean("bagage"));
            	r.setPrixbagage(rslt.getFloat("prixbagage"));
            	r.setPrixvol(rslt.getFloat("prixvol"));
            	r.setDatereservation(rslt.getString("datereservation"));
            
            	System.out.println(r.toString());
            	

            	l.add(r);
            }
            	
            ObservableList data = FXCollections.observableList(l);
            
         
            
            
            return data;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
		return null;
        
	}

	
	public ObservableList<Reservation> getRebyPasseport(String id) {
		// TODO Auto-generated method stub
		try{

            // PreparedStatements can use variables and are more efficient
            pstm = conn.prepareStatement("select r.id,nom, prenom,datedepart,depart,destination,id_vol,bagage,prixbagage,prixvol,datereservation"
                    		+ " from reservations r, vols v "
                    		+ " where v.id = id_vol and status='confirmed' and npasseport= ?");
        
            pstm.setString(1,id);
            System.out.println("Passeport :"+id);
            rslt = pstm.executeQuery();
            
            ArrayList<Reservation> l = new ArrayList<Reservation>();
            Reservation r;
            while(rslt.next()) {
            	r = new Reservation();
            	r.setId_res(rslt.getInt("r.id"));
            	r.setNom(rslt.getString("nom"));
            	r.setPrenom(rslt.getString("prenom"));
            	r.setDepart(rslt.getString("depart"));
            	r.setDatedepart(rslt.getString("datedepart"));
            	r.setDestination(rslt.getString("destination"));
            	r.setNpasseport(id);
            	r.setId_vol(rslt.getInt("id_vol"));
            	r.setBagage(rslt.getBoolean("bagage"));
            	r.setPrixbagage(rslt.getFloat("prixbagage"));
            	r.setPrixvol(rslt.getFloat("prixvol"));
            	r.setDatereservation(rslt.getString("datereservation"));
            	System.out.println(r.toString());
            	

            	l.add(r);
            }
            	
            ObservableList data = FXCollections.observableList(l);
            
         
            
            
            return data;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
		return null;
        
	}
	
	public void update(int id, boolean b) {
		// TODO Auto-generated method stub
		try {
			if((conn = DBConnection.getConnection())== null) {
				conn = DBConnection.setConnection();
			}
	
			
			
		
			  pstm = conn.prepareStatement("update "+ TABLE_NAME
			  		+ " set bagage = ? "
			  		+ " where id = ?");
			  
			  pstm.setBoolean(1, b);
			  pstm.setInt(2, id);
			  
	
			  
			 
			  
			  pstm.executeUpdate();
				  
			  
		  }catch(SQLException e){
			 e.printStackTrace();
		  }
		
		
		
		
	
	}

	@Override
	public ResultSet getRows(PreparedStatement s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAll() {
		// TODO Auto-generated method stub
		
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
	
	public ResultSet SearchById(int id) {
		// TODO Auto-generated method stub
	
		
		try {
			if((conn = DBConnection.getConnection())== null) {
				conn = DBConnection.setConnection();
			}
	
			
			
		
			  pstm = conn.prepareStatement("select *"
			  		+ "from "+ TABLE_NAME
			  		+ "where id = ?");
			  
			  pstm.setInt(1, id);
			  
	
			  
			 
			  
			  rslt = pstm.executeQuery();
				  
			  
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
		
		
		
		
		return rslt;
	}
	
	
	public ResultSet SearchByPasseport(String passeport) {
		// TODO Auto-generated method stub
	
		
		try {
			if((conn = DBConnection.getConnection())== null) {
				conn = DBConnection.setConnection();
			}
	
			
			
		
			  pstm = conn.prepareStatement("select *"
			  		+ "from "+ TABLE_NAME
			  		+ "where npasseport = ?");
			  
			  pstm.setString(1, passeport);
			  
	
			  
			 
			  
			  rslt = pstm.executeQuery();
				  
			  
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
		
		
		
		
		return rslt;
	}
	
	
	
	
	
	
	

	@Override
	public boolean Search(Object obj) {
		// TODO Auto-generated method stub
	
		return false;
	}

	@Override
	public void update() {
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

}
