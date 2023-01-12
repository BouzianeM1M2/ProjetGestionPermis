import java.util.Date;

public class PermisVoiture extends Permis{

	private Date obtention;

	public PermisVoiture(int idPermis, Date expiration, Etat status, Conducteur conducteur, Assurance assurance, Date obtention) {
		super(idPermis, expiration, status, conducteur,assurance);
		this.obtention = obtention;
	}

	public Date getObtention()
	{
		return obtention;
	}

	public void setObtention(Date obtention)
	{
		this.obtention = obtention;
	}
}