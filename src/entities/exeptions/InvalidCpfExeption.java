package entities.exeptions;

/**
 * Exeção que informa um erro de cpf inválido
 * @author Alvaro Basilio
 */
public class InvalidCpfExeption  extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidCpfExeption(String msg) {
		super(msg);
	}

}
