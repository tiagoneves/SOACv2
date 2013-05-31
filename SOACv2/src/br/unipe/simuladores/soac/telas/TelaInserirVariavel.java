package br.unipe.simuladores.soac.telas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.unipe.simuladores.soac.componentes.internos.unidades.Variavel;
import br.unipe.simuladores.soac.componentes.internos.unidades.VariavelIdentificador;
import br.unipe.simuladores.soac.enums.TipoVariavel;
import br.unipe.simuladores.soac.excecoes.DadosInvalidosException;
import br.unipe.simuladores.soac.excecoes.VariavelExistenteException;
import br.unipe.simuladores.soac.internacional.Labels;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TelaInserirVariavel extends Tela implements Formulario{

	private final TextField tfIdentificador = new TextField();
	private final ChoiceBox<String> cbTipo = new ChoiceBox<String>();
	private final Text txtValor = new Text(Labels.obterValor("valorinicial"));
	private final TextField tfValor = new TextField();
	private final ToggleGroup tg = new ToggleGroup();
	private Button btnInserirVar = new Button(Labels.obterValor("inserir"));
	private TipoVariavel tipoVariavel;
	private final RadioButton rbNormal = new RadioButton(Labels.obterValor("normal"));
	private RadioButton rbPonteiro = new RadioButton(Labels.obterValor("ponteiro"));
	private Boolean normal;
	
	public TelaInserirVariavel(String titulo, Color cor) {		
		super(titulo, cor);
		criar();		
	}
	
	@Override
	public void criar() {
		
		VBox vBox = new VBox();
		vBox.setSpacing(20);
		vBox.setPadding(new Insets(10, 10, 10, 10));
		
		HBox hBox1 = new HBox();
		hBox1.setSpacing(10);
	    Text txtIdentificador = new Text(Labels.obterValor("identificador"));
		hBox1.getChildren().add(txtIdentificador);
		tfIdentificador.setMaxWidth(50);
		hBox1.getChildren().add(tfIdentificador);
		Text txtTipo = new Text(Labels.obterValor("tipo"));
		hBox1.getChildren().add(txtTipo);
		cbTipo.getItems().addAll(Labels.obterValor("inteiro"), Labels.obterValor("pontoflutuante"));
		cbTipo.getSelectionModel().selectFirst();
		hBox1.getChildren().add(cbTipo);
		vBox.getChildren().add(hBox1);
		
		HBox hBox2 = new HBox();
		hBox2.setSpacing(10);
		VBox vBox2 = new VBox();
		vBox2.setAlignment(Pos.CENTER);
		
		HBox hBox3 = new HBox();
		hBox3.setSpacing(10);
		hBox3.getChildren().add(txtValor);
		tfValor.setMaxWidth(50);
		hBox3.getChildren().add(tfValor);
		vBox2.getChildren().add(hBox3);
		
		hBox2.getChildren().add(vBox2);
		
		VBox vBox3 = new VBox();
		vBox3.setSpacing(10);
		
		rbNormal.setToggleGroup(tg);
		rbNormal.setSelected(true);
		rbNormal.setTranslateX(70);
		vBox3.getChildren().add(rbNormal);
		rbPonteiro.setToggleGroup(tg);
		rbPonteiro.setSelected(false);
		rbPonteiro.setTranslateX(70);
		vBox3.getChildren().add(rbPonteiro);
		
		hBox2.getChildren().add(vBox3);
		
		vBox.getChildren().add(hBox2);
		
		HBox hBox4 = new HBox();
		hBox4.setAlignment(Pos.CENTER);
		btnInserirVar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				try {
					inserirVariavelMemoria();
					fechar();
				} catch (DadosInvalidosException die) {
					TelaErro erro = new TelaErro(die.getMessage());
					erro.exibir();
				} catch (VariavelExistenteException vee) {
					TelaErro erro = new TelaErro(vee.getMessage());
					erro.exibir();
				}
				
			}
			
		});
		hBox4.getChildren().add(btnInserirVar);
		
		vBox.getChildren().add(hBox4);
		
		root.getChildren().add(vBox);
		
	}
	
	public void inserirVariavelMemoria() throws DadosInvalidosException, VariavelExistenteException{
		
		tipoVariavel = obterTipoVariavel();
		
		validarDados();
		
		Variavel variavel = new Variavel(tfValor.getText(), tipoVariavel, normal);
		TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().
			inserirDado(variavel, tfIdentificador.getText());
		
		Integer endereco = 
				TelaPrincipal.getComputador().getMemoriaPrincipal()
				.getMemoriaInterna().obterEnderecoVariavel(tfIdentificador.getText());
		
		String id;
		
		if (normal)
			id = tfIdentificador.getText();
		else
			id = "*"+tfIdentificador.getText();
		
		TelaPrincipal.getTabVariaveis().getItems().add(
				new VariavelIdentificador(id, 
						endereco.toString(),
						tfValor.getText()));
		
	}
	
	public void validarDados() throws DadosInvalidosException{
		
		String identificador = tfIdentificador.getText();
		String valor = tfValor.getText();
		if (tg.getSelectedToggle() == rbNormal)
			normal = true;
		else 
			normal = false;
		
		if (identificador.isEmpty()) 
			throw new DadosInvalidosException(Labels.obterValor("informeidentificador"));
		
		if (valor.isEmpty()) {
			if (normal) {
				tfValor.setText("0");
				valor = "0";
			} else {
				tfValor.setText("-1");
				valor = "-1";
			}
		}
		
		Pattern padraoId = Pattern.compile("([a-zA-Z])\\w*");
		Matcher pesquisa = padraoId.matcher(identificador);
		
		if(!pesquisa.matches())
			throw new DadosInvalidosException(identificador+" "+Labels.obterValor("identificadorinvalido"));
		
		if (tipoVariavel == TipoVariavel.INTEIRO) {
			
			try {
				Integer.parseInt(valor);
			}catch(NumberFormatException nfe) {
				throw new DadosInvalidosException(Labels.obterValor("valornaoehinteiro"));
			}
			
			
		} else if (tipoVariavel == TipoVariavel.PONTO_FLUTUANTE) {
			
			try {
				Float.parseFloat(valor);
			}catch(NumberFormatException nfe) {
				throw new DadosInvalidosException(Labels.obterValor("valornaoehfloat"));
			}
			
		}
		
		if (!normal) {

			
			if(!(tipoVariavel == TipoVariavel.INTEIRO))
				throw new DadosInvalidosException(
						Labels.obterValor("ponteirosoharmazenainteiros"));
			
			/*if (!TelaPrincipal.getComputador().getMemoriaPrincipal()
					.getMemoriaInterna().contemVar(new Integer(valor), true))
				throw new DadosInvalidosException(Labels.obterValor("naohavariavelcomendereco"));*/
			
			if(TelaPrincipal.getComputador().getMemoriaPrincipal()
					.getMemoriaInterna().ehPonteiro(new Integer(valor), true))

				throw new DadosInvalidosException(
						Labels.obterValor("ponteironaoreferenciaponteiro"));
			
		}
		
		
	}
	
	private TipoVariavel obterTipoVariavel() {
		
		TipoVariavel tpVariavel = null;
		
		switch( cbTipo.getSelectionModel().
				selectedIndexProperty().intValue()) {
		case 0: tpVariavel = TipoVariavel.INTEIRO; break;
		case 1: tpVariavel = TipoVariavel.PONTO_FLUTUANTE; break;
		}
		
		return tpVariavel;
		
	}

}
