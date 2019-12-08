package entities.exeptions;

/**
 * Exeção que informa um erro de números indevidos
 * @author Alvaro Basilio
 */
public class NumbersExeption extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NumbersExeption(String msg) {
		super(msg);
	}

}
