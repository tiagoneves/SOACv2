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
	
	private static final Text defaultContentMensagem = new Text("Não há mensagens");
	private static final Text defaultContentVariaveis = new Text("Não há variáveis");
	private static OpcaoJanelaMensagem opcaoJanelaMensagem;
	private static boolean exibirMensagensDeSimulacao = true;
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
		    			new TelaInserirVariavel("Inserir Variável", 
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
		    	TelaWebView telaSied = new TelaWebView("Outros Simuladores", Color.WHITE, 
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
		    	TelaWebView telaSin = new TelaWebView("Outros Simuladores", Color.WHITE, 
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
				
				setOpcaoJanelaMensagem(OpcaoJanelaMensagem.EXIBIR);
				mensagem.setVisible(true);
				
			}
			
		});
		
		RadioMenuItem esconder = new RadioMenuItem(Labels.obterValor("esconder"));
		esconder.setToggleGroup(tgGroupMensagem);
		mensagens.getItems().add(esconder);
		
		esconder.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				setOpcaoJanelaMensagem(OpcaoJanelaMensagem.ESCONDER);
				mensagem.setVisible(false);
				
			}
			
		});
		
		RadioMenuItem naoExibir = new RadioMenuItem(Labels.obterValor("naoexibir"));
		naoExibir.setToggleGroup(tgGroupMensagem);
		mensagens.getItems().add(naoExibir);
		
		naoExibir.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				setOpcaoJanelaMensagem(OpcaoJanelaMensagem.NAO_EXIBIR);
				mensagem.setVisible(false);
				
			}
			
		});
		
		tgGroupMensagem.selectToggle(esconder);
		
		opExibirMensSimulacao = new RadioMenuItem(Labels.obterValor("exibirmenssimulacao"));
		opExibirMensSimulacao.setSelected(true);
		
		exibirMensagensDeSimulacao = true;
		
		opExibirMensSimulacao.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				exibirMensagensDeSimulacao = opExibirMensSimulacao.isSelected();
				
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
				
				variaveis.setVisible(true);
				
			}
			
		});
		
		RadioMenuItem naoExibirVar = new RadioMenuItem(Labels.obterValor("naoexibir"));
		naoExibirVar.setToggleGroup(tgGroupVariaveis);
		var.getItems().add(naoExibirVar);
		
		naoExibirVar.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				variaveis.setVisible(false);
				
			}
			
		});
		
		tgGroupVariaveis.selectToggle(exibirVar);
		
		janela.getItems().add(var);
		
		ToggleGroup tgGroupIdioma = new ToggleGroup();
		Menu idioma = new Menu(Labels.obterValor("idioma"));
		
		RadioMenuItem portugues = new RadioMenuItem(Labels.obterValor("portugues"));
		portugues.setToggleGroup(tgGroupIdioma);
		idioma.getItems().add(portugues);
		
		portugues.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				Configuracao.setIdioma(Idioma.PORTUGUES_BR);
				
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
				
				Configuracao.setIdioma(Idioma.INGLES_US);
				
				TelaAviso aviso = new TelaAviso
						(Color.rgb(245, 245, 245), Labels.obterValor("avisoidioma"));
				
				aviso.exibir();
				
			}
			
		});
		
		Idioma idiom = Configuracao.getIdioma();
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
		mensagem.setText("Mensagem");
		mensagem.setContent(defaultContentMensagem);
		mensagem.setVisible(true);
		setOpcaoJanelaMensagem(OpcaoJanelaMensagem.EXIBIR);
		
		return mensagem;
		
	}
	
	private TitledPane criarTitledPaneVariaveis() {
		
		TitledPane variaveis = new TitledPane();
		variaveis.setText("Variáveis");
		variaveis.setContent(defaultContentVariaveis);
		
		return variaveis;
		
	}
	
	private void criarTabelaVariaveis() {
		
		tabVariaveis = new TableView<VariavelIdentificador>();
		TableColumn<VariavelIdentificador, String> idCol = 
        		new TableColumn<VariavelIdentificador, String>();
		idCol.setText("ID");
		idCol.setPrefWidth(40);
		idCol.setCellValueFactory(
        		new PropertyValueFactory<VariavelIdentificador, String>("id"));
		
		TableColumn<VariavelIdentificador, String> endCol = 
        		new TableColumn<VariavelIdentificador, String>();
		endCol.setText("END");
		endCol.setPrefWidth(47);
		endCol.setCellValueFactory(
        		new PropertyValueFactory<VariavelIdentificador, String>("endereco"));
		
		TableColumn<VariavelIdentificador, String> dataCol = 
        		new TableColumn<VariavelIdentificador, String>();
		dataCol.setText("Dado");
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


	public static OpcaoJanelaMensagem getOpcaoJanelaMensagem() {
		return opcaoJanelaMensagem;
	}


	public static void setOpcaoJanelaMensagem(
			OpcaoJanelaMensagem opcaoJanelaMensagem) {
		TelaPrincipal.opcaoJanelaMensagem = opcaoJanelaMensagem;
	}


	public static boolean isExibirMensagensDeSimulacao() {
		return exibirMensagensDeSimulacao;
	}


	public static void setExibirMensagensDeSimulacao(
			boolean exibirMensagensDeSimulacao) {
		TelaPrincipal.exibirMensagensDeSimulacao = exibirMensagensDeSimulacao;
		opExibirMensSimulacao.setSelected(exibirMensagensDeSimulacao);
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
