import java.io.Serializable;
import java.util.Date;

public class Assurance implements Serializable{
	
	private String nom;
	private double prix;
	private Date souscription;
	private int idContrat;
	
	public Assurance(String nom, double prix, Date souscription, int idContrat ){
		this.nom = nom;
		this.prix = prix;
		this.souscription = souscription;
		this.idContrat = idContrat;
	}

	
	public String getNom() {
		return nom;
	}

	public Date getSouscription(){
		return souscription;
	}

	public int getIdcontrat(){
		return idContrat;
	}


	public double getPrix() {
		return prix;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setSouscription(Date souscription)
	{
		this.souscription = souscription;
	}

	public void setIdcontrat(int idContrat)
	{
		this.idContrat = idContrat;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Numéro de contrat : "+idContrat+"\nNom de l'assurance : "+this.nom+" \nPrix : "+this.prix+"\n Date de souscription : "+souscription;
	}

	
	
}
