import java.io.Serializable;

public abstract class Vehicule implements Serializable{
	
	/**
	 * 
	 */
	private int idVehicule;
	private int annee;
	private String model;
	private String  marque;
	
	
	public Vehicule(int idVehicule, int annee, String model, String marque) {
		this.idVehicule = idVehicule;
		this.annee = annee;
		this.model = model;
		this.marque = marque;
	}

	public int getIdVehicule() {
		return idVehicule;
	}

	public int getAnnee() {
		return annee;
	}

	
	public String getModel() {
		return model;
	}


	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}
	
	public void setModel(String model) {
		this.model = model;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public void setIdvehicule(int idVehicule) {
		this.idVehicule = idVehicule;
	}
}