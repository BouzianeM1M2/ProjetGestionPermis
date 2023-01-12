public enum Etat
{
	ACTIF(true), SUSPENDUS(false);
	public boolean val;

	private Etat(boolean val)
	{
		this.val=val;
	}

	public boolean getVal()
	{
		return this.val;
	}
}