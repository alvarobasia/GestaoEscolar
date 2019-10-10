package entities.exeptions;

public class InvalidCharacterExeption extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidCharacterExeption(String msg) {
		super(msg);
	}	

}
