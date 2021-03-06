package br.unipe.simuladores.soac.componentes.internos.unidades;

import br.unipe.simuladores.soac.internacional.Labels;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UC extends UnidadeUCP{
	
	private static final String UC_TXT = Labels.obterValor("uctxt"); 
	
	public UC() {
		
		super();
		
		construirForma(950, 575);
		adicionarTexto(975, 618);
		//atualizarValor("WRITE", 965, 593);
		
		group.getChildren().addAll(forma, txtNome, txtValor);
		
		definirAcoes();
		
	}
	
	@Override
	public void construirForma(double x, double y) {
		
		forma = new Rectangle();
		((Rectangle)forma).setWidth(65);
		((Rectangle)forma).setHeight(25);
		forma.setFill(Color.CORAL);
		((Rectangle)forma).setX(x);
		((Rectangle)forma).setY(y);
		
	}

	@Override
	public void adicionarTexto(double x, double y) {
		
		txtNome = new Text(Labels.obterValor("ucabrev"));
		txtNome.setX(x);
		txtNome.setY(y);
		txtNome.setFont(new Font(12));
		
	}

	@Override
	public void atualizarValor(Object valor, double x, double y) {
		
		super.valor = valor;
		
		atualizarTexto((String)valor, x, y);
		
	}

	@Override
	public String obterTextoExplicativo() {

		return UC_TXT;
		
	}

}
