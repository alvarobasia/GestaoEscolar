package entities.exeptions;

public class InvalidCpfExeption  extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidCpfExeption(String msg) {
		super(msg);
	}

}
