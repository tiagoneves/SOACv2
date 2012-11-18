package br.unipe.simuladores.soac.componentes.interfaces;

import javafx.scene.text.Text;
import br.unipe.simuladores.soac.enums.OpcaoJanelaMensagem;
import br.unipe.simuladores.soac.internacional.Configuracao;
import br.unipe.simuladores.soac.telas.TelaPrincipal;

public abstract class Componente {
	
	protected void exibirMensagemExplicativa() {
		
		if (Configuracao.obterInstancia().getOpcaoJanelaMensagem() 
				== OpcaoJanelaMensagem.EXIBIR 
				|| Configuracao.obterInstancia().getOpcaoJanelaMensagem() 
				== OpcaoJanelaMensagem.ESCONDER) {
		
			TelaPrincipal.getMensagem().setExpanded(true);
			TelaPrincipal.getMensagem().setContent(getTextoExplicativo());
		}
		
		if (Configuracao.obterInstancia().getOpcaoJanelaMensagem() == OpcaoJanelaMensagem.ESCONDER)
			TelaPrincipal.getMensagem().setVisible(true);
		
		if (Configuracao.obterInstancia().getOpcaoJanelaMensagem() == OpcaoJanelaMensagem.NAO_EXIBIR)
			TelaPrincipal.getMensagem().setVisible(false);
		
	}
	
	protected void esconderMensagemExplicativa() {
		
		if (!(Configuracao.obterInstancia().getOpcaoJanelaMensagem() 
				== OpcaoJanelaMensagem.NAO_EXIBIR)){
			TelaPrincipal.getMensagem().setExpanded(false);
			TelaPrincipal.colocarTextoPadraoMensagem();
		
			if (Configuracao.obterInstancia().getOpcaoJanelaMensagem() 
					== OpcaoJanelaMensagem.ESCONDER)
				TelaPrincipal.getMensagem().setVisible(false);
		}
		
	}
	
	public Text getTextoExplicativo() {
		return new Text(obterTextoExplicativo());
	}

	public abstract String obterTextoExplicativo();
	

}
