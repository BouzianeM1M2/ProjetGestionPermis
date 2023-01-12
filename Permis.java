import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public abstract class Permis implements GestionVehicule, Serializable{
	
	private int idPermis;
	private Date expiration;
	private Etat status;
	private Conducteur conducteur;
	private Assurance assurance;
	private ArrayList<Vehicule> listevehicules;
	private static final long serialVersionUID = 0;
	
	public Permis(int idPermis, Date expiration, Etat status, Conducteur conducteur, Assurance assurance) {
		this.idPermis = idPermis;
		this.expiration = expiration;
		this.status = status;
		this.conducteur = conducteur;
		listevehicules = new ArrayList<Vehicule>();
	}
	

	public int getIdPermis() {
		return idPermis;
	}

	public void setIdPermis(int idPermis) {
		this.idPermis = idPermis;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Conducteur getConducteur() {
		return conducteur;
	}

	public void setConducteur(Conducteur conducteur) {
		this.conducteur = conducteur;
	}

	public ArrayList<Vehicule> getListeVehicules() {
		return listevehicules;
	}

	public void setListeVehicules(ArrayList<Vehicule> listevehicules) {
		this.listevehicules = listevehicules;
	}

	public Etat getStatus() {
		return status;
	}

	public void setStatus(Etat statut) {
		this.status = statut;
	}

	public void setAssurance(Assurance assurance) {
		this.assurance = assurance;
	}
	
	
	public String AfficherVehicules() {
		String ListeDesVehicules="";
		ArrayList<Vehicule> liste = getListeVehicules();
		int i = 1;
		int a = 0;
		for(Vehicule vehicule : liste) {
			ListeDesVehicules = ListeDesVehicules+i+") IdVehicule: "+getListeVehicules().get(a).getIdVehicule()+"\n        Model: "+getListeVehicules().get(a).getModel()+"\n       Marque: "+getListeVehicules().get(a).getMarque()+"\n       Annee: "+getListeVehicules().get(a).getAnnee()+"\n\n\n";
			i++;
			a++;
		}
		return ListeDesVehicules;
	}

	public void Suspendre() {
		Etat etat = Etat.SUSPENDUS;
		setStatus(etat);
	}

	
	public void Renouvellement(Date expiration) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(expiration);
        cal.add(Calendar.YEAR,10);
        expiration = cal.getTime() ;
		setExpiration(expiration);
	}

	
	
	@Override
	public String toString() {
		return "IdPermis : "+this.idPermis+"\nExpiration :"+this.expiration.toString()+"\nStatus :"+this.status+"\nPrenom :"+this.conducteur.getPrenom()+"\nNom :"+this.conducteur.getNom()+"\n\n"+AfficherVehicules();
		//return "IdPermis : "+this.idPermis+"\nExpiration :"+this.expiration.toString()+"\nStatus :"+this.status
		//+"\nPrenom :"+this.conducteur.getPrenom()+"\nNom :"+this.conducteur.getNom()+"\nVehicule(s) :"+AfficherVehicules()+"\nAssurance :"+this.assurance.getNom()+"\nPrix :"+this.assurance.getPrix();
		
	}

	@Override
	public void AjouterVehicule(Vehicule vehicule) {
		this.getListeVehicules().add(vehicule);
		
	}

	@Override
	public void SupprimerVehicule(int position) {
		this.getListeVehicules().remove(position);
	}

}