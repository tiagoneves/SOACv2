package br.unipe.simuladores.soac.telas;

import br.unipe.simuladores.soac.internacional.Labels;

public class TelaFinal extends TelaMensagemSimulacao{
	
	private static final String MENSAGEM = Labels.obterValor("naohamaisinstrucoestxt");

	public TelaFinal() {
		
		super();
		
		modificarMensagem(obterTexto());
		
	}

	@Override
	protected String obterTexto() {
		
		return MENSAGEM;
		
	}

}
