package entities.exeptions;

/**
 * Exeção que informa um erro de caractere invalido
 * @author Alvaro Basilio
 */
public class InvalidCharacterExeption extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidCharacterExeption(String msg) {
		super(msg);
	}	

}
