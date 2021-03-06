package br.unipe.simuladores.soac.componentes.internos;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import br.unipe.simuladores.soac.componentes.interfaces.ComponenteInterno;
import br.unipe.simuladores.soac.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.soac.componentes.internos.unidades.Variavel;
import br.unipe.simuladores.soac.excecoes.VariavelExistenteException;
import br.unipe.simuladores.soac.internacional.Labels;

public class MemoriaInterna extends ComponenteInterno{
	
	private  ObservableList<Instrucao> instrucoes;

	private  ObservableList<Variavel> variaveis;
	
	private TableView<Instrucao> tabelaInstrucoes;
	
	private TableView<Variavel> tabelaVariaveis;
	
	private Map<String, Integer> mapaEnderecos;
	
	private TabPane tabPane;
	
	private Integer nextEnd;
	
	private static final String MEMORIA_INTERNATXT = Labels.obterValor("memoriainternatxt");
	
	public MemoriaInterna() {
		
		super();
		variaveis = FXCollections.observableArrayList();
		instrucoes = FXCollections.observableArrayList();
		mapaEnderecos = new HashMap<String, Integer>();
		nextEnd = 1;
		
	}

	@Override
	protected void adicionarTexto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buildContent() {
		
		group.getChildren().removeAll(tabPane);
		
		tabPane = new TabPane();
		tabPane.setSide(Side.TOP);
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        TableColumn<Instrucao, Integer> enderecoColInst = 
        		new TableColumn<Instrucao, Integer>();
        enderecoColInst.setText(Labels.obterValor("endereco"));
        enderecoColInst.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("endereco"));
        TableColumn<Instrucao, Integer> opcodeCol = 
        		new TableColumn<Instrucao, Integer>();
        opcodeCol.setText(Labels.obterValor("opcode"));
        opcodeCol.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("opcode"));
        TableColumn<Instrucao, Integer> referenciaOp1Col = 
        		new TableColumn<Instrucao, Integer>();
        referenciaOp1Col.setText(Labels.obterValor("operando1"));
        referenciaOp1Col.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("referenciaOp1"));
        TableColumn<Instrucao, Integer> referenciaOp2Col = 
        		new TableColumn<Instrucao, Integer>();
        referenciaOp2Col.setText(Labels.obterValor("operando2"));
        referenciaOp2Col.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("referenciaOp2"));
        
        TableColumn<Variavel, Integer> enderecoColDado = 
        		new TableColumn<Variavel, Integer>();
        enderecoColDado.setText(Labels.obterValor("endereco"));
        enderecoColDado.setCellValueFactory(
        		new PropertyValueFactory<Variavel, Integer>("endereco"));
        TableColumn<Variavel, String> dataCol = 
        		new TableColumn<Variavel, String>();
        dataCol.setText(Labels.obterValor("dado"));
        dataCol.setCellValueFactory(
        		new PropertyValueFactory<Variavel, String>("data"));
               
        tabelaInstrucoes = new TableView<Instrucao>();
        tabelaInstrucoes.setPlaceholder(new Text(Labels.obterValor("memoriaseminst")));
        tabelaInstrucoes.setItems(instrucoes);
        tabelaInstrucoes.getColumns().addAll(enderecoColInst, opcodeCol, referenciaOp1Col, 
        		referenciaOp2Col);
        
        tabelaVariaveis = new TableView<Variavel>();
        tabelaVariaveis.setPlaceholder(new Text(Labels.obterValor("memoriasemdados")));
        tabelaVariaveis.setItems(variaveis);
        tabelaVariaveis.getColumns().clear();
        dataCol.setMinWidth(230);
        tabelaVariaveis.getColumns().addAll(enderecoColDado, dataCol);
        
        tabPane.setTranslateX(700);
        tabPane.setTranslateY(20);
        tabPane.setMaxHeight(330);
        
        Tab instrucoesTab = new Tab();
        instrucoesTab.setText(Labels.obterValor("instrucoes"));
        instrucoesTab.setContent(tabelaInstrucoes);
        
        tabPane.getTabs().add(instrucoesTab);
        
        Tab dadosTab = new Tab();
        dadosTab.setText(Labels.obterValor("dados"));
        dadosTab.setContent(tabelaVariaveis);
        
        tabPane.getTabs().add(dadosTab);
		
		group.getChildren().addAll(tabPane);
		
	}
	
	public void inserirDado(Variavel v, String id) throws VariavelExistenteException {
		
		if (contemVar(id, false))
			throw new VariavelExistenteException("A vari�vel j� existe. Informe outro identificador");
			
		v.endereco.setValue(nextEnd);
		variaveis.add(v);
		tabelaVariaveis.setItems(variaveis);
		mapaEnderecos.put(id, nextEnd);
		nextEnd++;
		
	}
	
	public void inserirDadoSemIdentificador(String dado){
		
		Variavel var = new Variavel(dado, null, true);
		var.endereco.setValue(nextEnd);
		variaveis.add(var);
		tabelaVariaveis.setItems(variaveis);
		nextEnd++;
		
	}
	
	public void inserirInstrucao(Instrucao i) {
		
		i.endereco.setValue(nextEnd);
		instrucoes.add(i);
		tabelaInstrucoes.setItems(instrucoes);
		nextEnd++;
		
	}
	
	public boolean contemVar(Object valor, boolean endereco) {
		
		if(endereco) {
			
			if (mapaEnderecos.containsValue((Integer)valor))
				return true;
			
			return false;
		}
		
		if (mapaEnderecos.containsKey((String)valor))
			return true;
		
		return false;
		
	}
	
	public boolean ehPonteiro(Object valor, boolean endereco) {
		
		if (!contemVar(valor, endereco))
			return false;
		
		Variavel v;
		
		if (endereco) 
			v = procurarVariavelPorEndereco(valor);
		else
			v = procurarVariavelPorIdentificador(valor);
		
		if (v.getNormal())
			return false;
		else 
			return true;
		
		
	}
	
	private Variavel procurarVariavelPorEndereco(Object endereco) {
		
		for (Variavel v : variaveis) {
			
			if (v.endereco.getValue().equals((Integer)endereco)) 
				return v;
			
		}
		
		return null;
		
	}
	
	private Variavel procurarVariavelPorIdentificador(Object id) {
		
		Integer endereco = mapaEnderecos.get((String)id);
		
		return procurarVariavelPorEndereco(endereco);
		
	}
	
	public Integer obterEnderecoVariavel(String identificador) {
		
		Variavel variavel = procurarVariavelPorIdentificador(identificador);
		
		return variavel.endereco.getValue();
		
	}
	
	public String obterDadoVariavel(Integer end) {
		
		for (Variavel var : variaveis) {
			
			if (var.endereco.getValue().equals(end))
				return var.dataProperty().getValue();
			
		}
		
		return null;
		
	}
	
	public void atualizarValoresVariaveis() {
		
		tabelaVariaveis.setItems(variaveis);
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		
		//TODO 
		
	}
	
	public Instrucao obterInstrucao(Integer endereco) {
		
		
		for (Instrucao instr : instrucoes) {
			
			if (instr.endereco.getValue().equals(endereco))
				return instr;
			
		}
		
		return null;
	}
	
	public Variavel obterVariavel(Integer endereco) {
		
		for (Variavel var : variaveis) {
			
			if (var.enderecoProperty().getValue().equals(endereco))
				return var;
			
		}
		
		return null;
		
	}
	
	public ObservableList<Instrucao> getInstrucoes() {
		return instrucoes;
	}

	public void setInstrucoes(ObservableList<Instrucao> instrucoes) {
		this.instrucoes = instrucoes;
	}

	public ObservableList<Variavel> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(ObservableList<Variavel> variaveis) {
		this.variaveis = variaveis;
	}

	public TableView<Instrucao> getTabelaInstrucoes() {
		return tabelaInstrucoes;
	}

	public void setTabelaInstrucoes(TableView<Instrucao> tabelaInstrucoes) {
		this.tabelaInstrucoes = tabelaInstrucoes;
	}

	public TableView<Variavel> getTabelaVariaveis() {
		return tabelaVariaveis;
	}

	public void setTabelaVariaveis(TableView<Variavel> tabelaVariaveis) {
		this.tabelaVariaveis = tabelaVariaveis;
	}

	public Map<String, Integer> getMapaEnderecos() {
		return mapaEnderecos;
	}

	public void setMapaEnderecos(Map<String, Integer> mapaEnderecos) {
		this.mapaEnderecos = mapaEnderecos;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public Integer getNextEnd() {
		return nextEnd;
	}

	public void setNextEnd(Integer nextEnd) {
		this.nextEnd = nextEnd;
	}

	@Override
	public String obterTextoExplicativo() {
		
		return MEMORIA_INTERNATXT;
		
	}
	
	

}
