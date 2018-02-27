package com.gestionvol.models;

public class Reservation {
	private String nom;
	private String prenom;
	private String depart;
	private String destination;
	private float prixvol;
	private float prixbagage;
	private String datedepart;
	private int id_res;
	
	private String npasseport;
	private int id_vol;
	private boolean bagage;
	private String datereservation;
	



	public Reservation() {}
	
	
	


	public Reservation(String nom, String prenom, String depart, String destination, float prixvol, float prixbagage,
			String datedepart, String npasseport, int id_vol, boolean bagage, String datereservation) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.depart = depart;
		this.destination = destination;
		this.prixvol = prixvol;
		this.prixbagage = prixbagage;
		this.datedepart = datedepart;
		this.npasseport = npasseport;
		this.id_vol = id_vol;
		this.bagage = bagage;
		this.datereservation = datereservation;
	}


	public int getId_res() {
		return id_res;
	}


	public void setId_res(int id_res) {
		this.id_res = id_res;
	}


	public String getDatereservation() {
		return datereservation;
	}


	public void setDatereservation(String datereservation) {
		this.datereservation = datereservation;
	}
	
	
	public String getDatedepart() {
		return datedepart;
	}



	public void setDatedepart(String datedepart) {
		this.datedepart = datedepart;
	}



	public String getDepart() {
		return depart;
	}



	public void setDepart(String depart) {
		this.depart = depart;
	}



	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}



	public float getPrixvol() {
		return prixvol;
	}



	public void setPrixvol(float prixvol) {
		this.prixvol = prixvol;
	}



	public float getPrixbagage() {
		return prixbagage;
	}



	public void setPrixbagage(float prixbagage) {
		this.prixbagage = prixbagage;
	}



	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNpasseport() {
		return npasseport;
	}
	public void setNpasseport(String npasseport) {
		this.npasseport = npasseport;
	}
	public int getId_vol() {
		return id_vol;
	}
	public void setId_vol(int id_vol) {
		this.id_vol = id_vol;
	}
	public boolean isBagage() {
		return bagage;
	}
	public void setBagage(boolean bagage) {
		this.bagage = bagage;
	}
	

}
