package br.unipe.simuladores.soac.telas;

import br.unipe.simuladores.soac.internacional.Labels;
import javafx.scene.paint.Color;

public class TelaFinal extends TelaMensagemSimulacao{
	
	private static final String MENSAGEM = Labels.obterValor("naohamaisinstrucoestxt");

	public TelaFinal() {
		
		super(Color.rgb(245, 245, 245));
		
		modificarMensagem(obterTexto());
		
	}

	@Override
	protected String obterTexto() {
		
		return MENSAGEM;
		
	}

}
