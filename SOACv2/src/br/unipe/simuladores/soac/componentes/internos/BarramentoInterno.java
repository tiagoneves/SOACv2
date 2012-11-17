package br.unipe.simuladores.soac.componentes.internos;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import br.unipe.simuladores.soac.componentes.interfaces.ComponenteInterno;
import br.unipe.simuladores.soac.internacional.Labels;

public class BarramentoInterno extends ComponenteInterno {
	
	private static final String BARRAMENTO_INTERNOTXT = Labels.obterValor("barramentointernotxt");
	
	private Rectangle dados;
	private Rectangle enderecos;
	private Rectangle controle;
	
	private Rectangle ligacaoMar;
	private Rectangle ligacaoMbr;
	private Rectangle ligacaoUc;
	
	public BarramentoInterno(){
		
		super();
		
	}

	@Override
	protected void adicionarTexto() {
		
		Text enderecos = new Text(Labels.obterValor("instrucoesmin"));
		enderecos.setX(1090);
		enderecos.setY(620);
		enderecos.setFont(new Font(10));
		
		Text instrucoes = new Text(Labels.obterValor("enderecosmin"));
		instrucoes.setX(1145);
		instrucoes.setY(620);
		instrucoes.setFont(new Font(10));
		
		Text dados = new Text(Labels.obterValor("dadosmin"));
		dados.setX(1201);
		dados.setY(620);
		dados.setFont(new Font(10));
			
		group.getChildren().addAll(enderecos, instrucoes, dados);
		
	}

	@Override
	protected void buildContent() {

	    ligacaoMar = new Rectangle(1030, 417, 120, 30);
		ligacaoMar.setFill(Color.THISTLE);
	    ligacaoMbr = new Rectangle(1030, 456, 170, 30);
		ligacaoMbr.setFill(Color.LIGHTSALMON);
	    ligacaoUc = new Rectangle(1030, 572, 70, 30);
		ligacaoUc.setFill(Color.CORAL);
		
		group.getChildren().removeAll(dados, enderecos, controle, ligacaoMar, 
				ligacaoMbr, ligacaoUc);
		
		dados = new Rectangle(1100, 75, 30, 530);
		dados.setFill(Color.LIGHTBLUE);
		enderecos = new Rectangle(1150, 75, 30, 530);
		enderecos.setFill(Color.LIGHTBLUE);
		controle = new Rectangle(1200, 75, 30, 530);
		controle.setFill(Color.LIGHTBLUE);
		
		
		group.getChildren().addAll(dados, enderecos, controle, ligacaoMar, 
				ligacaoMbr, ligacaoUc);
		
		
		ligacaoMar.toBack();
		ligacaoMbr.toBack();
		ligacaoUc.toBack();
		
		adicionarTexto();
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		
		// TODO 
		
	}

	@Override
	public String obterTextoExplicativo() {
		
		return BARRAMENTO_INTERNOTXT;
		
	}
	

}
