public class Moto extends Vehicule
{
	private int cc; // cm3 d'une moto
	private String couleur;
	private int nbroue;
	
	public Moto(int idVehicule, int annee, String model, String marque, int cc, String couleur,int nbroue) {
		super(idVehicule, annee, model, marque);
		this.cc = cc;
		this.couleur = couleur;
		this.nbroue = nbroue;
	}

	public int getCc() {
		return cc;
	}

	public String getCouleur() {
		return couleur;
	}

	public int getNbroue() {
		return nbroue;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public void setNbroue(int nbroue) {
		this.nbroue = nbroue;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}


}