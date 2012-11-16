package br.unipe.simuladores.soac.componentes.internos.unidades;

import br.unipe.simuladores.soac.internacional.Labels;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MBR extends UnidadeUCP{
	
	private static final String MBR_TXT = "O MBR (Memory Buffer Register) é " +
			"responsável por\n armazenar os dados a serem escritos na memória\n" +
			"principal pelo barramento de dados e por receber\n os dados ou " +
			"instruções da memória, em uma\n operação de leitura, através desse " +
			"mesmo barramento.";

	public MBR() {
		
		super();
		
		construirForma(913, 460);
		adicionarTexto(880, 478);
		atualizarValor("", 923, 478);
		
		group.getChildren().addAll(forma, txtNome, txtValor);
		
		definirAcoes();
		
	}
	
	@Override
	public void construirForma(double x, double y) {
		
		forma = new Rectangle();
		((Rectangle)forma).setWidth(103);
		((Rectangle)forma).setHeight(25);
		forma.setFill(Color.LIGHTSALMON);
		((Rectangle)forma).setX(x);
		((Rectangle)forma).setY(y);
		
	}

	@Override
	public void adicionarTexto(double x, double y) {
		
		txtNome = new Text(Labels.obterValor("mbr"));
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

		return MBR_TXT;
		
	}

}
