public class Voiture extends Vehicule
{
	
	private Carburant carburant;
	private int nbplace;

	public Voiture(int idVehicule, int annee, String model, String marque, int nbplace, Carburant carburant ) {
		super(idVehicule, annee, model, marque);
		this.nbplace = nbplace;
		this.carburant = carburant;
	}

	public int getNbplace() {
		return nbplace;
	}

	public Carburant getCarburant() {
		return carburant;
	}

	public void setNbplace(int nbplace) {
		this.nbplace = nbplace;
	}

	public void setCarburant(Carburant carburant) {
		this.carburant = carburant;
	}


}