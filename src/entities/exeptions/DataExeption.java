package entities.exeptions;

/**
 * Exeção que informa um erro de data
 * @author Alvaro Basilio
 */
public class DataExeption extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DataExeption(String msg) {
		super(msg);
	}

}
