package br.unipe.simuladores.soac.componentes.internos.unidades;

import br.unipe.simuladores.soac.internacional.Labels;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PC extends UnidadeUCP{

	private static final String PC_TXT = Labels.obterValor("pctxt");
	
	public PC() {
		
		super();
		
		construirForma(870, 420);
		adicionarTexto(848, 438);
		
		group.getChildren().addAll(forma, txtNome, txtValor);
		
		definirAcoes();
		
	}
	
	@Override
	public void construirForma(double x, double y) {
		
		forma = new Rectangle();
		((Rectangle)forma).setWidth(50);
		((Rectangle)forma).setHeight(25);
		forma.setFill(Color.SANDYBROWN);
		((Rectangle)forma).setX(x);
		((Rectangle)forma).setY(y);
		
	}

	@Override
	public void adicionarTexto(double x, double y) {
		
		txtNome = new Text(Labels.obterValor("pc"));
		txtNome.setX(x);
		txtNome.setY(y);
		txtNome.setFont(new Font(12));
		
	}

	@Override
	public void atualizarValor(Object valor, double x, double y) {
		
		super.valor = valor;
		
		atualizarTexto(valor.toString(), x, y);
		
	}

	@Override
	public String obterTextoExplicativo() {
	
		return PC_TXT;
		
	}
	
	

}
