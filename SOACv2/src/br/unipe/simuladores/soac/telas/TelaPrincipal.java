package br.unipe.simuladores.soac.telas;

import br.unipe.simuladores.soac.botoes.BotaoPlay;
import br.unipe.simuladores.soac.botoes.BotaoStop;
import br.unipe.simuladores.soac.componentes.circulos.Computador;
import br.unipe.simuladores.soac.componentes.internos.unidades.VariavelIdentificador;
import br.unipe.simuladores.soac.enums.OpcaoJanelaMensagem;
import br.unipe.simuladores.soac.internacional.Configuracao;
import br.unipe.simuladores.soac.internacional.Idioma;
import br.unipe.simuladores.soac.internacional.Labels;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TelaPrincipal extends Tela{

	private static final Computador computador = new Computador();
	private static MenuBar menuSuperior;
	private static TitledPane mensagem;
	private static TitledPane variaveis;
	
	private static final Text defaultContentMensagem = new Text(Labels.obterValor("conteudodefaultmensagem"));
	private static final Text defaultContentVariaveis = new Text(Labels.obterValor("conteudodefaultvariaveis"));
	private static TableView<VariavelIdentificador> tabVariaveis;
	private static RadioMenuItem opExibirMensSimulacao;
	
	private static BotaoPlay botaoPlay;
	private static BotaoStop botaoStop;
	
	private Accordion accordion;
	
	public TelaPrincipal(Stage stage, String titulo, Color cor, double height, double width) {
		super(stage, titulo, cor, height, width);
		criar();
	}
	
	
	@Override
	public void criar() {
		
		botaoPlay = new BotaoPlay();
		botaoPlay.setTranslateX(550);
		botaoPlay.setTranslateY(660);
		botaoPlay.setVisible(false);
		
		botaoStop = new BotaoStop();
		botaoStop.setTranslateX(610);
		botaoStop.setTranslateY(670);
		botaoStop.setVisible(false);
		
		TelaPrincipal.adicionarAoPalco(botaoPlay);
		TelaPrincipal.adicionarAoPalco(botaoStop);

		root.getChildren().add(computador.getContent());
		
		menuSuperior = criarMenu(scene);
		root.getChildren().add(menuSuperior);
		
		mensagem = criarTitledPaneMensagem();
		root.getChildren().add(mensagem);
		
		variaveis = criarTitledPaneVariaveis();
		root.getChildren().add(variaveis);
		
		criarTabelaVariaveis();
		
		accordion = new Accordion();
		accordion.getPanes().addAll(mensagem, variaveis);
		accordion.setTranslateY(420);
		root.getChildren().add(accordion);
		
		
		
	}
	
	private MenuBar criarMenu(Scene cena) {
		
		MenuBar menuBar = new MenuBar();
		
		Menu inserir = new Menu(Labels.obterValor("inserir"));
		MenuItem instrucoes = new MenuItem(Labels.obterValor("instrucoes"));
		instrucoes.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	TelaInserirInstrucoes inserirInstrucoes = 
		    			new TelaInserirInstrucoes(Labels.obterValor("inseririnstrucoes"), 
		    					Color.rgb(245, 245, 245), 450, 760);
		    	inserirInstrucoes.exibir();
		    }
		});
		instrucoes.setDisable(true);
		inserir.getItems().add(instrucoes);
		MenuItem variavel = new MenuItem(Labels.obterValor("variavel"));
		variavel.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	TelaInserirVariavel inserirVariavel = 
		    			new TelaInserirVariavel(Labels.obterValor("inserirvariavel"), 
		    					Color.rgb(245, 245, 245));
		    	inserirVariavel.exibir();
		    }
		});
		variavel.setDisable(true);
		inserir.getItems().add(variavel);
		
		Menu outros = new Menu(Labels.obterValor("outros"));
		MenuItem sied = new MenuItem(Labels.obterValor("sied"));
		sied.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	TelaWebView telaSied = new TelaWebView(Color.WHITE, 
		    			Screen.getPrimary().getVisualBounds().getHeight(), 
		    			Screen.getPrimary().getVisualBounds().getWidth(), 
		    			"http://www.hilariotomaz.com.br/SI-ED/");
		        telaSied.exibir();
		    }
		});
		outros.getItems().add(sied);
		
		MenuItem sin = new MenuItem(Labels.obterValor("sin"));
		sin.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	TelaWebView telaSin = new TelaWebView(Color.WHITE, 
		    			Screen.getPrimary().getVisualBounds().getHeight(), 
		    			Screen.getPrimary().getVisualBounds().getWidth(), 
		    			"http://wrco.ccsa.ufpb.br:8080/SimuladorSO/");
		    	telaSin.exibir();
		    }
		});
		outros.getItems().add(sin);
		
		ToggleGroup tgGroupMensagem = new ToggleGroup();
		Menu janela = new Menu(Labels.obterValor("janela"));
		Menu mensagens = new Menu(Labels.obterValor("mensagens"));
		
		RadioMenuItem exibir = new RadioMenuItem(Labels.obterValor("exibir"));
		exibir.setToggleGroup(tgGroupMensagem);
		mensagens.getItems().add(exibir);
		
		exibir.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				Configuracao.obterInstancia().setOpcaoJanelaMensagem(
						OpcaoJanelaMensagem.EXIBIR);
				
				mensagem.setVisible(true);
				
			}
			
		});
		
		RadioMenuItem esconder = new RadioMenuItem(Labels.obterValor("esconder"));
		esconder.setToggleGroup(tgGroupMensagem);
		mensagens.getItems().add(esconder);
		
		esconder.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				Configuracao.obterInstancia().setOpcaoJanelaMensagem(
						OpcaoJanelaMensagem.ESCONDER);
				
				mensagem.setVisible(false);
				
			}
			
		});
		
		RadioMenuItem naoExibir = new RadioMenuItem(Labels.obterValor("naoexibir"));
		naoExibir.setToggleGroup(tgGroupMensagem);
		mensagens.getItems().add(naoExibir);
		
		naoExibir.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				Configuracao.obterInstancia().setOpcaoJanelaMensagem(
						OpcaoJanelaMensagem.NAO_EXIBIR);
				
				mensagem.setVisible(false);
				
			}
			
		});
		
		switch(Configuracao.obterInstancia().getOpcaoJanelaMensagem()) {
		case ESCONDER: tgGroupMensagem.selectToggle(esconder); break;
		case EXIBIR: tgGroupMensagem.selectToggle(exibir); break;
		case NAO_EXIBIR: tgGroupMensagem.selectToggle(naoExibir); break;
		}
		
		opExibirMensSimulacao = new RadioMenuItem(Labels.obterValor("exibirmenssimulacao"));
		
		if(Configuracao.obterInstancia().isExibirMensagensDeSimulacao())
			opExibirMensSimulacao.setSelected(true);
		else
			opExibirMensSimulacao.setSelected(false);
		
		opExibirMensSimulacao.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				Configuracao.obterInstancia().setExibirMensagensDeSimulacao(
						opExibirMensSimulacao.isSelected());
				
			}
			
		});
		
		mensagens.getItems().addAll(new SeparatorMenuItem(), opExibirMensSimulacao);
		
			
		janela.getItems().add(mensagens);
		
		ToggleGroup tgGroupVariaveis = new ToggleGroup();
	    Menu var = new Menu(Labels.obterValor("variaveis"));
		RadioMenuItem exibirVar = new RadioMenuItem(Labels.obterValor("exibir"));
		exibirVar.setToggleGroup(tgGroupVariaveis);
		var.getItems().add(exibirVar);
		
		exibirVar.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				Configuracao.obterInstancia().setExibirVariaveis(true);
				
				variaveis.setVisible(true);
				
			}
			
		});
		
		RadioMenuItem naoExibirVar = new RadioMenuItem(Labels.obterValor("naoexibir"));
		naoExibirVar.setToggleGroup(tgGroupVariaveis);
		var.getItems().add(naoExibirVar);
		
		naoExibirVar.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				Configuracao.obterInstancia().setExibirVariaveis(false);
				
				variaveis.setVisible(false);
				
			}
			
		});
		
		if (Configuracao.obterInstancia().isExibirVariaveis())
			tgGroupVariaveis.selectToggle(exibirVar);
		else
			tgGroupVariaveis.selectToggle(naoExibirVar);
		
		janela.getItems().add(var);
		
		ToggleGroup tgGroupIdioma = new ToggleGroup();
		Menu idioma = new Menu(Labels.obterValor("idioma"));
		
		RadioMenuItem portugues = new RadioMenuItem(Labels.obterValor("portugues"));
		portugues.setToggleGroup(tgGroupIdioma);
		idioma.getItems().add(portugues);
		
		portugues.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				Configuracao.obterInstancia().setIdioma(Idioma.PORTUGUES_BR);
				
				TelaAviso aviso = new TelaAviso
						(Color.rgb(245, 245, 245), Labels.obterValor("avisoidioma"));
				
				aviso.exibir();
				
			}
			
		});
		
		RadioMenuItem ingles = new RadioMenuItem(Labels.obterValor("ingles"));
		ingles.setToggleGroup(tgGroupIdioma);
		idioma.getItems().add(ingles);
		
		ingles.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				Configuracao.obterInstancia().setIdioma(Idioma.INGLES_US);
				
				TelaAviso aviso = new TelaAviso
						(Color.rgb(245, 245, 245), Labels.obterValor("avisoidioma"));
				
				aviso.exibir();
				
			}
			
		});
		
		Idioma idiom = Configuracao.obterInstancia().getIdioma();
		switch(idiom){
		case PORTUGUES_BR: tgGroupIdioma.selectToggle(portugues); break;
		case INGLES_US: tgGroupIdioma.selectToggle(ingles); break;
		}
		
		Menu ajuda = new Menu(Labels.obterValor("ajuda"));
		MenuItem sobre = new MenuItem(Labels.obterValor("sobre"));
		ajuda.getItems().add(sobre);
		
		sobre.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				//TODO 
				
			}
			
		});
		
		
		
		menuBar.getMenus().add(inserir);
		menuBar.getMenus().add(janela);
		menuBar.getMenus().add(outros);
		menuBar.getMenus().add(idioma);
		menuBar.getMenus().add(ajuda);
		
		menuBar.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth());
		
		
		return menuBar;
		
		
		
	}
	
	private TitledPane criarTitledPaneMensagem() {
		
		TitledPane mensagem = new TitledPane();
		mensagem.setText(Labels.obterValor("mensagem"));
		mensagem.setContent(defaultContentMensagem);
		
		switch(Configuracao.obterInstancia().getOpcaoJanelaMensagem()) {
		case ESCONDER: mensagem.setVisible(false); break;
		case EXIBIR: mensagem.setVisible(true); break;
		case NAO_EXIBIR: mensagem.setVisible(false); break;
		}
		
		return mensagem;
		
	}
	
	private TitledPane criarTitledPaneVariaveis() {
		
		TitledPane variaveis = new TitledPane();
		variaveis.setText(Labels.obterValor("variaveis"));
		variaveis.setContent(defaultContentVariaveis);
		
		if (Configuracao.obterInstancia().isExibirVariaveis())
			variaveis.setVisible(true);
		else
			variaveis.setVisible(false);
		
		return variaveis;
		
	}
	
	private void criarTabelaVariaveis() {
		
		tabVariaveis = new TableView<VariavelIdentificador>();
		tabVariaveis.setPlaceholder(new Text(Labels.obterValor("conteudodefaultvariaveis")));
		TableColumn<VariavelIdentificador, String> idCol = 
        		new TableColumn<VariavelIdentificador, String>();
		idCol.setText(Labels.obterValor("identificadorabrev"));
		idCol.setPrefWidth(40);
		idCol.setCellValueFactory(
        		new PropertyValueFactory<VariavelIdentificador, String>("id"));
		
		TableColumn<VariavelIdentificador, String> endCol = 
        		new TableColumn<VariavelIdentificador, String>();
		endCol.setText(Labels.obterValor("enderecoabrev"));
		endCol.setPrefWidth(47);
		endCol.setCellValueFactory(
        		new PropertyValueFactory<VariavelIdentificador, String>("endereco"));
		
		TableColumn<VariavelIdentificador, String> dataCol = 
        		new TableColumn<VariavelIdentificador, String>();
		dataCol.setText(Labels.obterValor("dado"));
		dataCol.setCellValueFactory(
        		new PropertyValueFactory<VariavelIdentificador, String>("data"));
		
		
		tabVariaveis.getColumns().addAll(idCol, endCol, dataCol);
		
		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
		tabVariaveis.setPrefSize(170, 253);
		scroll.setContent(tabVariaveis);
		scroll.setPrefSize(170, 253);
		variaveis.setContent(scroll);
		
	}
	
	
	public static void colocarTextoPadraoMensagem() {
		
		mensagem.setContent(defaultContentMensagem);
		
	}
	
	public static void colocarTextoPadraoVariaveis() {
		
		variaveis.setContent(defaultContentVariaveis);
		
	}

	public static void adicionarAoPalco(Node node) {
		
		root.getChildren().add(node);
		
	}
	
	public static void removerDoPalco(Node node) {
		
		root.getChildren().remove(node);
		
	}
	
	public static Computador getComputador() {
		return computador;
	}
	
	public static MenuBar getMenuSuperior() {
		return menuSuperior;
	}

	/*public static Accordion getAccordion() {
		return accordion;
	}


	public static void setAccordion(Accordion accordion) {
		TelaPrincipal.accordion = accordion;
	}*/


	public static TitledPane getMensagem() {
		return mensagem;
	}


	public static void setMensagem(TitledPane mensagem) {
		TelaPrincipal.mensagem = mensagem;
	}


	public static TitledPane getVariaveis() {
		return variaveis;
	}


	public static void setVariaveis(TitledPane variaveis) {
		TelaPrincipal.variaveis = variaveis;
	}

	public static void exibirMensagensDeSimulacao(
			boolean exibir) {
		
		Configuracao.obterInstancia().setExibirMensagensDeSimulacao(exibir);
		
		opExibirMensSimulacao.setSelected(exibir);
		
	}


	public static TableView<VariavelIdentificador> getTabVariaveis() {
		return tabVariaveis;
	}


	public static void setTabVariaveis(TableView<VariavelIdentificador> tabVariaveis) {
		TelaPrincipal.tabVariaveis = tabVariaveis;
	}


	public static BotaoPlay getBotaoPlay() {
		return botaoPlay;
	}


	public static void setBotaoPlay(BotaoPlay botaoPlay) {
		TelaPrincipal.botaoPlay = botaoPlay;
	}


	public static RadioMenuItem getOpExibirMensSimulacao() {
		return opExibirMensSimulacao;
	}


	public static void setOpExibirMensSimulacao(RadioMenuItem opExibirMensSimulacao) {
		TelaPrincipal.opExibirMensSimulacao = opExibirMensSimulacao;
	}


	public static BotaoStop getBotaoStop() {
		return botaoStop;
	}


	public static void setBotaoStop(BotaoStop botaoStop) {
		TelaPrincipal.botaoStop = botaoStop;
	}
	

}
