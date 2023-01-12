import java.util.Date;

public class PermisMoto extends Permis{

	private boolean bsr; // Permet de savoir si l'utilisateur à obtenu sont Brevet de Sécurité Routiere

	public PermisMoto(int idPermis, Date expiration, Etat status, Conducteur conducteur, Assurance assurance, boolean bsr) {
		super(idPermis, expiration, status, conducteur, assurance);
		this.bsr = bsr;
	}

	public boolean getBsr()
	{
		return bsr;
	}

	public void setObtention(boolean bsr)
	{
		this.bsr = bsr;
	}
}