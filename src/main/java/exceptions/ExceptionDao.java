package exceptions;

public class ExceptionDao extends Exception {


	private static final long serialVersionUID = 457021682869030713L;

	public ExceptionDao(String mensagem , Exception exception) {
		super(mensagem,exception);
	}
	
	
}
