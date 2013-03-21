package br.unipe.simuladores.soac.telas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public abstract class TelaSimplesMensagem extends Tela{

	private String mensagem;
	protected VBox vBox;
	protected Text txtMensagem;
	protected HBox hBox;
	
	public TelaSimplesMensagem(String titulo, String mensagem) {
		
		super(titulo, Color.rgb(245, 245, 245));
		this.mensagem = mensagem;
		criar();
		
	}
	
	public void criar(){
		
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
	
	protected void adicionarComponentesComuns() {
		
		vBox = new VBox();
		vBox.setPadding(new Insets(5, 5, 5, 5));
		vBox.setSpacing(20);
		txtMensagem = new Text(mensagem);
		
		
	}

}
