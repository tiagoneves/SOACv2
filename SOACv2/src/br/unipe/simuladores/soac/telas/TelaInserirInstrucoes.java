package br.unipe.simuladores.soac.telas;

import java.util.ArrayList;
import java.util.List;

import br.unipe.simuladores.soac.componentes.circulos.CaixaFormulario;
import br.unipe.simuladores.soac.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.soac.enums.ModoEnderecamento;
import br.unipe.simuladores.soac.enums.Operacao;
import br.unipe.simuladores.soac.excecoes.DadosInvalidosException;
import br.unipe.simuladores.soac.internacional.Labels;
import br.unipe.simuladores.soac.simulacao.Controlador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TelaInserirInstrucoes extends Tela implements Formulario{

	private ChoiceBox<String> cbTipo = new ChoiceBox<String>();
	private ChoiceBox<String> cbModEnd1 = new ChoiceBox<String>();
	private TextField tfValor1 = new TextField();
	private ChoiceBox<String> cbModEnd2 = new ChoiceBox<String>();
	private TextField tfValor2 = new TextField();
	private Button btnInserir = new Button(Labels.obterValor("inserirseta"));
	private ListView<String> lstInstrucoes = new ListView<String>();
	private Button btnRemover = new Button(Labels.obterValor("removerselecionada"));
	private Button btnIniciar = new Button(Labels.obterValor("iniciarsimulacao"));
	private ModoEnderecamento modo1;
	private ModoEnderecamento modo2;
	private Operacao operacao;
	private ObservableList<String> instrucoes = FXCollections.observableArrayList();
	private List<Instrucao> instrucoesList = new ArrayList<Instrucao>();
	
	Controlador movimentador;
	
	public TelaInserirInstrucoes(String titulo, Color cor, double height, double width) {
		super(titulo, cor, height, width);
		criar();
	}
	
	@Override
	public void criar() {
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10, 10, 10, 10));
		vBox.setSpacing(10);
		
		HBox hBox1 = new HBox();
		hBox1.setSpacing(10);
		hBox1.setAlignment(Pos.CENTER);
		Text tipo = new Text(Labels.obterValor("tipo"));
		hBox1.getChildren().add(tipo);
		cbTipo.getItems().addAll("MOV", "ADD", "SUB", "MUL", "DIV");
		cbTipo.getSelectionModel().selectFirst();
		hBox1.getChildren().add(cbTipo);
		vBox.getChildren().add(hBox1);
		
		HBox hBox2 = new HBox();
		hBox2.setSpacing(50);
		Text operando1 = new Text(Labels.obterValor("operando1"));
		operando1.setTranslateX(120);
		operando1.setTranslateY(5);
		operando1.setFill(Color.BLUE);
		hBox2.getChildren().add(operando1);
		Text operando2 = new Text(Labels.obterValor("operando2"));
		operando2.setTranslateX(375);
		operando2.setTranslateY(5);
		operando2.setFill(Color.BLUE);
		hBox2.getChildren().add(operando2);
		vBox.getChildren().add(hBox2);
		
		HBox hBox3 = new HBox();
		hBox3.setSpacing(60);
		hBox3.setPadding(new Insets(0, 0, 0, 20));
		
		GridPane grid1 = new GridPane();
		grid1.setTranslateY(10);
		grid1.setHgap(10);
		grid1.setVgap(10);
		
		Text modEnd1 = new Text(Labels.obterValor("modoenderecamento"));
		grid1.add(modEnd1, 0, 0);
		cbModEnd1.getItems().addAll(Labels.obterValor("imediato"), 
				Labels.obterValor("direto"), Labels.obterValor("indireto"), 
				Labels.obterValor("registrador"), Labels.obterValor("indiretoregistrador"));
		cbModEnd1.getSelectionModel().selectFirst();
		grid1.add(cbModEnd1, 1, 0);
		Text valor1 = new Text(Labels.obterValor("valor"));
		grid1.add(valor1, 0, 1);
		tfValor1.setMaxWidth(50);
		grid1.add(tfValor1, 1, 1);
		hBox3.getChildren().add(grid1);
		
		GridPane grid2 = new GridPane();
		grid2.setTranslateY(10);
		grid2.setHgap(10);
		grid2.setVgap(10);
		
		Text modEnd2 = new Text(Labels.obterValor("modoenderecamento"));
		grid2.add(modEnd2, 0, 0);
		cbModEnd2.getItems().addAll(Labels.obterValor("imediato"), 
				Labels.obterValor("direto"), Labels.obterValor("indireto"), 
				Labels.obterValor("registrador"), Labels.obterValor("indiretoregistrador"));
		cbModEnd2.getSelectionModel().selectFirst();
		grid2.add(cbModEnd2, 1, 0);
		Text valor2 = new Text(Labels.obterValor("valor"));
		grid2.add(valor2, 0, 1);
		tfValor2.setMaxWidth(50);
		grid2.add(tfValor2, 1, 1);
		hBox3.getChildren().add(grid2);
		
		vBox.getChildren().add(hBox3);
		
		VBox vBox2 = new VBox();
		vBox2.setSpacing(50);
		
		HBox hBox4 = new HBox();
		hBox4.setAlignment(Pos.CENTER);
		btnInserir.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				try {
										
					validarDados();
					
					inserirInstrucaoNaLista();
					
					Instrucao instrucao = construirInstrucao();
					instrucoesList.add(instrucao);
					
				} catch(DadosInvalidosException die) {
					
					TelaErro erro = new TelaErro(die.getMessage());
					
					erro.exibir();
					
				}
				
			}
			
		});
		btnInserir.setTranslateY(40);
		hBox4.getChildren().add(btnInserir);
		vBox2.getChildren().add(hBox4);
		
		HBox hBox5 = new HBox();
		hBox5.setAlignment(Pos.CENTER);
		hBox5.setSpacing(80);
		lstInstrucoes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lstInstrucoes.setMaxWidth(200);
		lstInstrucoes.setMaxHeight(100);
		lstInstrucoes.setEditable(false);
		lstInstrucoes.setTranslateY(10);
		hBox5.getChildren().add(lstInstrucoes);
		VBox vBox3 = new VBox();
		vBox3.setAlignment(Pos.CENTER);
		btnRemover.setTranslateY(10);
		btnRemover.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				Integer index = 
						lstInstrucoes.getSelectionModel().selectedIndexProperty().
						getValue();
				
				instrucoes.remove(index.intValue());
				instrucoesList.remove(index.intValue());
				
				lstInstrucoes.setItems(instrucoes);
				
			}
			
		});
		vBox3.getChildren().add(btnRemover);
		hBox5.getChildren().add(vBox3);
		vBox2.getChildren().add(hBox5);
		
		HBox hBox6 = new HBox();
		hBox6.setAlignment(Pos.CENTER);
		btnIniciar.setTranslateY(10);
	    hBox6.getChildren().add(btnIniciar);
	    
	    btnIniciar.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				for (Instrucao instrucao : instrucoesList) 
					TelaPrincipal.getComputador().getMemoriaPrincipal().
						getMemoriaInterna().inserirInstrucao(instrucao);
				
				fechar();
				
				Controlador controlador = Controlador.obterReferencia();
				controlador.iniciarSimulacao();
				
			}
	    	
	    });
	    
	    vBox2.getChildren().add(hBox6);
	    
	    vBox.getChildren().add(vBox2);
		
		root.getChildren().add(vBox);
		
		inserirCaixasFormulario();
		
	}
	
	private void inserirCaixasFormulario() {
		
		double pontos[][] = new double[6][2];
		pontos[0][0] = 15;
		pontos[0][1] = 55;
		pontos[1][0] = 120;
		pontos[1][1] = 55;
		pontos[2][0] = 200;
		pontos[2][1] = 55;
		pontos[3][0] = 355;
		pontos[3][1] = 55;
		pontos[4][0] = 355;
		pontos[4][1] = 150;
		pontos[5][0] = 15;
		pontos[5][1] = 150;
		
		CaixaFormulario caixa1 = new CaixaFormulario(pontos, Color.GREY);
		CaixaFormulario caixa2 = new CaixaFormulario(pontos, Color.GREY);
		caixa2.setTranslateX(370);
		
		root.getChildren().add(caixa1);
		root.getChildren().add(caixa2);
		
	}

	@Override
	public void validarDados() throws DadosInvalidosException {
		
		String valor1 = tfValor1.getText();
		String valor2 = tfValor2.getText();
		
		if (valor1.isEmpty()) 
			throw new DadosInvalidosException(Labels.obterValor("informeoperando1"));
		
		if (valor2.isEmpty()) 
			throw new DadosInvalidosException(Labels.obterValor("informeoperando2"));
		
		modo1 = obterModoEnderecamento(cbModEnd1);
		modo2 = obterModoEnderecamento(cbModEnd2);
		
		validarEnderecamentoOperando1(valor1);
		validarEnderecamentoOperando2(valor2);			
		
	}
	
	private void validarEnderecamentoOperando1 (String valor) throws DadosInvalidosException {
		
		switch(modo1) {
		case IMEDIATO: throw new DadosInvalidosException
			(Labels.obterValor("modoop1naopodeimediato"));
		case DIRETO:{ 
			if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().contemVar(valor, false))
				throw new DadosInvalidosException(Labels.obterValor("valorvarop1naoexiste"));
		};break;
		case INDIRETO: {
			if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().contemVar(valor, false))
				throw new DadosInvalidosException(Labels.obterValor("valorvarop1naoexiste"));
			else {
				if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().ehPonteiro(valor, false))
					throw new DadosInvalidosException(Labels.obterValor("valorvarop1naoehponteiro"));
			};
		}break;
		case REGISTRADOR: {
			if(!TelaPrincipal.getComputador().getUCP().getUCPInterna().contemRegistrador(valor))
				throw new DadosInvalidosException(Labels.obterValor("registradorvalorop1naoexiste"));
		}break;
		case INDIRETO_REGISTRADOR: {
			if(!TelaPrincipal.getComputador().getUCP().getUCPInterna().contemRegistrador(valor))
				throw new DadosInvalidosException(Labels.obterValor("registradorvalorop1naoexiste"));
		}break;
		}
		
	}
	
	private void validarEnderecamentoOperando2 (String valor) throws DadosInvalidosException {
		
		switch(modo2) {
		case IMEDIATO: {
			try {
				Integer.parseInt(valor);
			} catch(NumberFormatException nfe) {
				try {
					Float.parseFloat(valor);
				}catch(NumberFormatException nfe2) {
					throw new DadosInvalidosException(Labels.obterValor("valorop2precisaserintoufloat"));
				}
				throw new DadosInvalidosException(Labels.obterValor("valorop2precisaserintoufloat"));
			}
		}break;
		case DIRETO:{ 
			if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().contemVar(valor, false))
				throw new DadosInvalidosException(Labels.obterValor("valorvarop2naoexiste"));
		};break;
		case INDIRETO: {
			if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().contemVar(valor, false))
				throw new DadosInvalidosException(Labels.obterValor("valorvarop2naoexiste"));
			else {
				if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().ehPonteiro(valor, false))
					throw new DadosInvalidosException(Labels.obterValor("valorvarop2naoehponteiro"));
			};
		}break;
		case REGISTRADOR: {
			if(!TelaPrincipal.getComputador().getUCP().getUCPInterna().contemRegistrador(valor))
				throw new DadosInvalidosException(Labels.obterValor("registradorvalorop2naoexiste"));
		}break;
		case INDIRETO_REGISTRADOR: {
			if(!TelaPrincipal.getComputador().getUCP().getUCPInterna().contemRegistrador(valor))
				throw new DadosInvalidosException(Labels.obterValor("registradorvalorop2naoexiste"));
		}break;
		}
		
	}
	
	private ModoEnderecamento obterModoEnderecamento (ChoiceBox<String> cb) {
				
		switch(cb.getSelectionModel().selectedIndexProperty().intValue()) {
		case 0: return ModoEnderecamento.IMEDIATO;
		case 1: return ModoEnderecamento.DIRETO;
		case 2: return ModoEnderecamento.INDIRETO;
		case 3: return ModoEnderecamento.REGISTRADOR;
		case 4: return ModoEnderecamento.INDIRETO_REGISTRADOR;
		}
		
		return null;
			
	}
	
	private Operacao obterOperacao (ChoiceBox<String> cb) {
		
		switch(cb.getSelectionModel().selectedIndexProperty().intValue()) {
		case 0: return Operacao.MOV;
		case 1: return Operacao.ADD;
		case 2: return Operacao.SUB;
		case 3: return Operacao.MUL;
		case 4: return Operacao.DIV;
		}
		
		return null;
			
	}
	
	private void inserirInstrucaoNaLista() {
		
		String instrucao = new String();
		
		operacao = obterOperacao(cbTipo);
		
		switch (operacao) {
		case MOV: instrucao += "MOV ";break;
		case ADD: instrucao += "ADD ";break;
		case SUB: instrucao += "SUB ";break;
		case MUL: instrucao += "MUL ";break;
		case DIV: instrucao += "DIV ";break;
		}
		
		ModoEnderecamento modo1 = obterModoEnderecamento(cbModEnd1);
		ModoEnderecamento modo2 = obterModoEnderecamento(cbModEnd2);
		
		if (modo1 == ModoEnderecamento.INDIRETO || modo1 == ModoEnderecamento.INDIRETO_REGISTRADOR)
			instrucao += "("+tfValor1.getText()+")";
		else
			instrucao += tfValor1.getText();
		
		instrucao += ", ";
		
		if (modo2 == ModoEnderecamento.INDIRETO || modo2 == ModoEnderecamento.INDIRETO_REGISTRADOR)
			instrucao += "("+tfValor2.getText()+")";
		else
			instrucao += tfValor2.getText();
		
		instrucoes.add(instrucao);
		lstInstrucoes.setItems(instrucoes);
		
	}
	
	private Instrucao construirInstrucao() {
		
		Integer refOperando1 = 0;
		Integer refOperando2;
		
		String valor1, valor2;
		
		if (tfValor1.getText().contains("("))
			valor1 = tfValor1.getText().substring(0, tfValor1.getText().length());
		else
			valor1 = tfValor1.getText();
		
		if (tfValor2.getText().contains("("))
			valor2 = tfValor2.getText().substring(0, tfValor2.getText().length());
		else
			valor2 = tfValor2.getText();
		
		if (modo1 == ModoEnderecamento.DIRETO || modo1 == ModoEnderecamento.INDIRETO) 
			refOperando1 = TelaPrincipal.getComputador().getMemoriaPrincipal().
			getMemoriaInterna().obterEnderecoVariavel(valor1);
		else if (modo1 == ModoEnderecamento.REGISTRADOR || modo1 == ModoEnderecamento.INDIRETO_REGISTRADOR) 
			refOperando1 = TelaPrincipal.getComputador().getUCP().getUCPInterna().
			obterEnderecoRegistrador(valor1);
		
		if (modo2 == ModoEnderecamento.DIRETO || modo2 == ModoEnderecamento.INDIRETO) 
			refOperando2 = TelaPrincipal.getComputador().getMemoriaPrincipal().
			getMemoriaInterna().obterEnderecoVariavel(valor2);
		else if (modo2 == ModoEnderecamento.REGISTRADOR || modo2 == ModoEnderecamento.INDIRETO_REGISTRADOR) 
			refOperando2 = TelaPrincipal.getComputador().getUCP().getUCPInterna().
			obterEnderecoRegistrador(valor2);
		else
			refOperando2 = Integer.parseInt(valor2);
		
		Instrucao instrucao = 
				new Instrucao(refOperando1, refOperando2, operacao, modo1, modo2);
		
		return instrucao;
		
	}
	

}
