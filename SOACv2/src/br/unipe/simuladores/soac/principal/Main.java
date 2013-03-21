package br.unipe.simuladores.soac.principal;

import br.unipe.simuladores.soac.internacional.Configuracao;
import br.unipe.simuladores.soac.internacional.Labels;
import br.unipe.simuladores.soac.telas.TelaPrincipal;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static TelaPrincipal telaPrincipal;
	
	public static void main (String args[]) {

		Application.launch(args);
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//carrega o arquivo de labels
		Labels.carregar(Configuracao.obterInstancia().getIdioma());
		
		 //Ajusta a cena para ocupar toda a tela do monitor
        Rectangle2D tela = Screen.getPrimary().getVisualBounds();
		
		telaPrincipal = new TelaPrincipal(stage, 
				Labels.obterValor("soac"), 
				Color.WHITE, tela.getHeight(), tela.getWidth());
		telaPrincipal.exibir();
		
	}
	
	public static void adicionarAoPalco(Node node){
		
		telaPrincipal.adicionarAoPalco(node);
		
	}
	

}