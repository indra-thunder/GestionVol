package com.gestionvol.DBAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public interface EntityInterface {
	public void createEntity();
	
	public void removeEntity();
	
	public void add(Object obj);
	
	public void removeById(int id);
	
	public void delete(String condition);
	
	public void update();
	
	public ResultSet getRows(PreparedStatement s);
	
	public void getAll();
	
	public void truncate();
	
	public void close();
	
	public boolean Search(Object obj);
	

	
}
