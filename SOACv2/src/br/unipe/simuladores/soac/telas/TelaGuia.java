package br.unipe.simuladores.soac.telas;

import br.unipe.simuladores.soac.internacional.Labels;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ToolBarBuilder;
import javafx.scene.layout.VBox;

public class TelaGuia extends Tela{
	
	private ToolBar toolBar;
	private TextArea textArea;
	private Button passo1;
	private Button passo2;
	private Button passo3;
	private Button passo4;
	private Button passo5;
	private Button passo6;
	
	public TelaGuia() {
		
		super(Labels.obterValor("guia"));
		stage.setResizable(false);
		criar();
		
	}

	@Override
	public void criar() {
		
		VBox vBox = new VBox();
		
		passo1 = new Button(Labels.obterValor("passo1"));
		passo2 = new Button(Labels.obterValor("passo2"));
		passo3 = new Button(Labels.obterValor("passo3"));
		passo4 = new Button(Labels.obterValor("passo4"));
		passo5 = new Button(Labels.obterValor("passo5"));
		passo6 = new Button(Labels.obterValor("passo6"));
		
		definirEventosBotoes();
		
		toolBar = ToolBarBuilder.create().id("passos").items(
				passo1,
				passo2,
				passo3,
				passo4,
				passo5,
				passo6
				).build();
		
		vBox.getChildren().add(toolBar);
		
		textArea = new TextArea();
		textArea.setMaxSize(404, 500);
		textArea.setText(Labels.obterValor("passo1txt"));
		textArea.setEditable(false);
		
		stage.setWidth(404);
		stage.setHeight(230);
		
		vBox.getChildren().add(textArea);
		
		root.getChildren().add(vBox);
		
		
	}
	
	private void definirEventosBotoes(){
		
		 passo1.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
					
					textArea.setText(Labels.obterValor("passo1txt"));
					
				}
		    	
		    });
		 
		 passo2.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
					
					textArea.setText(Labels.obterValor("passo2txt"));
					
				}
		    	
		    });
		 
		 passo3.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
					
					textArea.setText(Labels.obterValor("passo3txt"));
					
				}
		    	
		    });
		 
		 passo4.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
					
					textArea.setText(Labels.obterValor("passo4txt"));
					
				}
		    	
		    });
		 
		 passo5.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
					
					textArea.setText(Labels.obterValor("passo5txt"));
					
				}
		    	
		    });
		 
		 passo6.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
					
					textArea.setText(Labels.obterValor("passo6txt"));
					
				}
		    	
		    });
		
	}

}
