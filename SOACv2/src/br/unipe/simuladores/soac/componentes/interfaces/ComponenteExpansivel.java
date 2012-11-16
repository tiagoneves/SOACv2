package br.unipe.simuladores.soac.componentes.interfaces;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;

public abstract class ComponenteExpansivel extends Componente{
	
	protected Label nome;
	
	protected Group group;
	
	protected Timeline timeline;
	
	public ComponenteExpansivel() {
		
		group = new Group();
		
	}
	
	public Group getContent() {
		
		return group;
		
	}
	
	protected abstract void adicionarTexto();
	
	protected abstract void buildContent();
	
	protected abstract void definirAcoesGerais() ;
	
	protected abstract void definirAcoesEspecificas();
	
	

}
