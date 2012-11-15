package br.unipe.simuladores.soac.principal;

import br.unipe.simuladores.soac.internacional.Configuracao;
import br.unipe.simuladores.soac.internacional.Idioma;
import br.unipe.simuladores.soac.internacional.Labels;
import br.unipe.simuladores.soac.telas.TelaPrincipal;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main (String args[]) {

		Application.launch(args);
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//carrega o aquivo de configurações
		Configuracao.carregar();
		
		//carrega o arquivo de labels
		Labels.carregar(Configuracao.getIdioma());
		
		 //Ajusta a cena para ocupar toda a tela do monitor
        Rectangle2D tela = Screen.getPrimary().getVisualBounds();
		
		TelaPrincipal telaPrincipal = new TelaPrincipal(stage, 
				Labels.obterValor("soac"), 
				Color.WHITE, tela.getHeight(), tela.getWidth());
		telaPrincipal.exibir();
		
	}
	

}