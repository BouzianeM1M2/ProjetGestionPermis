import java.io.Serializable;
import java.util.Date;


public class Conducteur implements Serializable{
	
	
	private String nom;
	private String prenom;
	private Date ddn; // ddn --> date de naissance
	private String vdn; // vdn --> ville de naissance
	
	public Conducteur(String nom, String prenom, Date ddn, String vdn) {
		this.nom = nom;
		this.prenom = prenom;
		this.ddn = ddn;
		this.vdn = vdn;
	}
	

	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}

	public String getVdn() {
		return vdn;
	}

	public Date getDdn() {
		return ddn;
	}

	public void setDdn(Date ddn) {
		this.ddn = ddn;
	}

		public void setVdn(String vdn) {
		this.vdn = vdn;
	}

		public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom+" "+prenom+" "+ddn+" "+vdn;
	}
}