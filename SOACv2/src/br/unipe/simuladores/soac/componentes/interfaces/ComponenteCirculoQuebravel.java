package br.unipe.simuladores.soac.componentes.interfaces;

import br.unipe.simuladores.soac.telas.TelaPrincipal;

public abstract class ComponenteCirculoQuebravel extends ComponenteCirculo{

	protected void exibirMenus() {
		
		if (TelaPrincipal.getComputador().todosComponentesInternosExpandidos()){
			
			TelaPrincipal.getMenuSuperior().getMenus().get(0).getItems().get(0)
				.setDisable(false);
	
			TelaPrincipal.getMenuSuperior().getMenus().get(0).getItems().get(1)
				.setDisable(false);
			
		}
			
		
	}
	
	public abstract void quebrar(double time);
	
}
