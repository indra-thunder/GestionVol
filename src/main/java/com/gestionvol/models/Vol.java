package com.gestionvol.models;

public class Vol{
	private String nom,date , prix,id_vol,prixbagage;

	public Vol(String nom, String date, String prix, String id_vol, String prixbagage) {
		
		this.nom = nom;
		this.date = date;
		this.prix = prix;
		this.id_vol = id_vol;
		this.prixbagage = prixbagage;
	}

	public String getPrixbagage() {
		return prixbagage;
	}

	public void setPrixbagage(String prixbagage) {
		this.prixbagage = prixbagage;
	}

	
	public Vol() {
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getId_vol() {
		return id_vol;
	}

	public void setId_vol(String id_vol) {
		this.id_vol = id_vol;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Vol [nom=" + nom + ", date=" + date + ", prix=" + prix + ", id_vol=" + id_vol + "]";
	}
	
	
	
}