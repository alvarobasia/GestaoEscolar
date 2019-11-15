package entities.exeptions;

public class infoBancoExeption extends Exception {
    private static final long serialVersionUID = 1L;

    public infoBancoExeption(String msg) {
        super(msg);
    }
}
