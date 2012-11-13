package br.unipe.simuladores.soac.excecoes;

public class DadosInvalidosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4106762582299737769L;
	
	public DadosInvalidosException() {
		
		super("Dados inválidos");
		
	}
	
	public DadosInvalidosException(String msg) {
		
		super(msg);
		
	}

}
