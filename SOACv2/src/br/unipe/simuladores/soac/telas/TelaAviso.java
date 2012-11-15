package br.unipe.simuladores.soac.telas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import br.unipe.simuladores.soac.internacional.Labels;

public class TelaAviso extends TelaSimplesMensagem {
	
 public TelaAviso(Color cor, String mensagem) {
		
		super(Labels.obterValor("aviso"), cor, mensagem);
		
	}
	
	@Override
	public void criar() {
		
		adicionarComponentesComuns();
		
		vBox.getChildren().add(txtMensagem);
		
		hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		
		vBox.getChildren().add(hBox);
		
		root.getChildren().add(vBox);
		
		Button btn = new Button("OK");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				stage.close();
				
			}
			
		});
		hBox.getChildren().add(btn);
		
		
	}

}
