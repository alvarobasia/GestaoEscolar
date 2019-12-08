package entities.exeptions;

/**
 * Exeção que informa um erro de banco de dados
 * @author Alvaro Basilio
 */
public class infoBancoExeption extends Exception {
    private static final long serialVersionUID = 1L;

    public infoBancoExeption(String msg) {
        super(msg);
    }
}
