package br.unipe.simuladores.soac.telas;

import br.unipe.simuladores.soac.internacional.Labels;

public class TelaSobre extends TelaSimplesMensagem{

	public TelaSobre() {
		super(Labels.obterValor("sobre"), Labels.obterValor("sobretxt"));
	}

}
