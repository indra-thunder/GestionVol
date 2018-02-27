package com.gestionvol.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gestionvol.DBAccess.EntityInterface;
import com.gestionvol.models.User;

public class UserDB implements EntityInterface{
	
	private static final String User_Table = "create table if not exists user ( "
		      + " id INT PRIMARY KEY AUTO_INCREMENT,"
		      + " login VARCHAR(20) UNIQUE,"
		      + " password VARCHAR(20) )";

	private static final String TABLE_NAME = "user";
	
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
		  stm.executeUpdate(User_Table);
		  
		  try {
			  stm.executeUpdate("insert into "+TABLE_NAME+"(id, login, password) values(default, 'yahya','yahya')");
		  }catch(SQLException e){
			  if(e.getErrorCode()== 2627 )
				  System.out.println("Admin user already in table ");
		  }
		  
		
		  System.out.println("CreateUserTableMySQL: table created.");
		
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
	public void getAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void truncate() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public ResultSet getRows(PreparedStatement s) {
		// TODO Auto-generated method stub
		return null;
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
		User u  = (User) obj;
		 try{

	            // PreparedStatements can use variables and are more efficient
	            pstm = conn
	                    .prepareStatement("select id "
	                    		+ "from "+TABLE_NAME
	                    		+ " where login =? and password = ?");
	            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
	            // Parameters start with 1
	            pstm.setString(1, u.getLogin());
	            pstm.setString(2, u.getPwd());
	            
	            rslt = pstm.executeQuery();
	            
	            if(rslt.next()){
	          
	                return true;
	            }
	        }catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        
		
		return false;
		
	}

}
