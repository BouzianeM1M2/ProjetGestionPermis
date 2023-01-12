public class ExceptionPermis extends Exception {

	private static final long serialVersionUID = 0;
	
	public ExceptionPermis() {
		super("Un permis posséde déjà cette ID");
	}

}
