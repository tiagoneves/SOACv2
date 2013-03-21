package br.unipe.simuladores.soac.telas;

import br.unipe.simuladores.soac.internacional.Labels;

public class TelaAviso extends TelaSimplesMensagem {
	
 public TelaAviso(String mensagem) {
		
		super(Labels.obterValor("aviso"), mensagem);
		
	} 

}
