package com.gestionvol.models;

public class VolCompanie {
	private int id_vol;
	private String depart;
	private String destination;
	private String datedepart;
	private int nbplace;
	private int nbdispo;
	private int classe;
	private float prixbagage;
	private float prix;
	private String compagnie;
	
	
	
	
	public VolCompanie(int id_vol, String depart, String destination, String datedepart, int nbplace, int nbdispo,
			int classe, float prixbagage, float prix, String compagnie) {
		
		this.id_vol = id_vol;
		this.depart = depart;
		this.destination = destination;
		this.datedepart = datedepart;
		this.nbplace = nbplace;
		this.nbdispo = nbdispo;
		this.classe = classe;
		this.prixbagage = prixbagage;
		this.prix = prix;
		this.compagnie = compagnie;
	}
	
	public VolCompanie() {
		// TODO Auto-generated constructor stub
	}

	public int getId_vol() {
		return id_vol;
	}
	public void setId_vol(int id_vol) {
		this.id_vol = id_vol;
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
	public String getDatedepart() {
		return datedepart;
	}
	public void setDatedepart(String datedepart) {
		this.datedepart = datedepart;
	}
	public int getNbplace() {
		return nbplace;
	}
	public void setNbplace(int nbplace) {
		this.nbplace = nbplace;
	}
	public int getNbdispo() {
		return nbdispo;
	}
	public void setNbdispo(int nbdispo) {
		this.nbdispo = nbdispo;
	}
	public int getClasse() {
		return classe;
	}
	public void setClasse(int classe) {
		this.classe = classe;
	}
	public float getPrixbagage() {
		return prixbagage;
	}
	public void setPrixbagage(float prixbagage) {
		this.prixbagage = prixbagage;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public String getCompagnie() {
		return compagnie;
	}
	public void setCompagnie(String compagnie) {
		this.compagnie = compagnie;
	}
	
	
	
	
	
	
	
	
	

}
