package br.unipe.simuladores.soac.telas;

import br.unipe.simuladores.soac.internacional.Labels;

public class TelaErro extends TelaSimplesMensagem{

	public TelaErro(String mensagem) {
		
		super(Labels.obterValor("erro"), mensagem);
		
	}

}
