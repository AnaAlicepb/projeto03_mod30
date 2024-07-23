package exceptions;

public class ExceptionTipoChaveNaoEncontrada extends Exception {

	private static final long serialVersionUID = -64693043393218341L;
	
	public ExceptionTipoChaveNaoEncontrada(String msg) {
        this(msg, null);
    }

    public ExceptionTipoChaveNaoEncontrada(String msg, Throwable e) {
        super(msg, e);
    }
	
	
	

}
