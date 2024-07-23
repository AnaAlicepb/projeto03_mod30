package exceptions;

public class ExceptionElementoNaoConhecido extends Exception {

	

	private static final long serialVersionUID = -2784252569204746993L;

	public ExceptionElementoNaoConhecido(String mensagem) {
		this(mensagem,null);
		
		/*
		Este construtor recebe um único argumento, que é uma String que representa a 
		mensagem que descreve a exceção. Ele não recebe nenhuma informação adicional 
		sobre a causa da exceção. Isso significa que se você usar este construtor, 
		você estará apenas fornecendo uma descrição básica do erro,
		sem nenhum detalhe sobre o que pode ter causado isso.
		 */
	}
	
	public ExceptionElementoNaoConhecido(String mensagem, Throwable e) {
		super(mensagem,e);
		/*
		 * O segundo construtor é usado para exceções mais complexas onde você deseja fornecer uma mensagem 
		 * e a causa subjacente da exceção. 
		 * Essa informação adicional pode ser útil para fins de depuração e solução de problemas.
		 * 
		 * 	Throwable e: Este argumento permite que você especifique uma causa subjacente para a exceção. 
		 * 	Pode ser qualquer objeto que estenda a classe Throwable, que inclui tipos de exceção comuns como IOException ou NullPointerException.
		 *	Ao fornecer este argumento,
		 *	você está dando mais contexto sobre a causa raiz da ExceptionElementoNaoConhecido.
		 */
	}
	
}
