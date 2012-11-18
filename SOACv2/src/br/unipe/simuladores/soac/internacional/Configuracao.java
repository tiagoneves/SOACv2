package br.unipe.simuladores.soac.internacional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import br.unipe.simuladores.soac.enums.OpcaoJanelaMensagem;

public class Configuracao {
	
	private Idioma idioma;
	private OpcaoJanelaMensagem opcaoJanelaMensagem;
	private boolean exibirMensagensDeSimulacao;
	private boolean exibirVariaveis;
	
	private ResourceBundle resourceBundle;
	
	private static Configuracao instancia;
	
	private Configuracao() {
		
		carregar();
		
	}
	
	public static Configuracao obterInstancia() {
		
		if (instancia == null)
			instancia = new Configuracao();
		
		return instancia;
		
	}
	
	private void carregar() {
		
		FileInputStream fis;
		
		try {
			
			fis = new FileInputStream("resources/config.properties");
			resourceBundle = new PropertyResourceBundle(fis);
			fis.close();
			
			Enumeration<String> keys = resourceBundle.getKeys();
			
			String key;
			int valorNum;
			
			while(keys.hasMoreElements()) {
				
				key = keys.nextElement();
				
				if (key.equals("variaveis")) {
					
					valorNum = Integer.parseInt(resourceBundle.getString(key));
					
					if (valorNum == 0)
						exibirVariaveis = false;
					else
						exibirVariaveis = true;
					
				} else if(key.equals("mensagens")) {
					
					valorNum = Integer.parseInt(resourceBundle.getString(key));
					
					switch(valorNum){
					case 1: opcaoJanelaMensagem = OpcaoJanelaMensagem.EXIBIR; break;
					case 2: opcaoJanelaMensagem = OpcaoJanelaMensagem.ESCONDER; break;
					case 3: opcaoJanelaMensagem = OpcaoJanelaMensagem.NAO_EXIBIR; break;
					}
					
				} else if (key.equals("exibirmensagens")) {
					
					valorNum = Integer.parseInt(resourceBundle.getString(key));
					
					if (valorNum == 0)
						exibirMensagensDeSimulacao = false;
					else
						exibirMensagensDeSimulacao = true;
					
				} else {
					
					String valor = resourceBundle.getString(key);
					
					if (valor.equals("ptbr")) 
						idioma = Idioma.PORTUGUES_BR;
					else if (valor.equals("enus"))
						idioma = Idioma.INGLES_US;
					
				}
			
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	private void atualizar() {
		
		String idiom = null;
		
		switch(idioma) {
		case PORTUGUES_BR: idiom = "ptbr"; break;
		case INGLES_US: idiom = "enus"; break;
		}
		
		String mensagens = null;
		
		switch(opcaoJanelaMensagem) {
		case EXIBIR: mensagens = "1"; break;
		case ESCONDER: mensagens = "2"; break;
		case NAO_EXIBIR: mensagens = "3"; break;
		}
		
		try {
			Properties properties = new Properties();
			properties.setProperty("idioma", idiom);
			properties.setProperty("mensagens", mensagens);
			
			if (exibirMensagensDeSimulacao)
				properties.setProperty("exibirmensagens", "1");
			else
				properties.setProperty("exibirmensagens", "0");
			
			if (exibirVariaveis)
				properties.setProperty("variaveis", "1");
			else
				properties.setProperty("variaveis", "0");
			
			File file = new File("resources/config.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "Configuracao");
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		
		this.idioma = idioma;
		
		atualizar();
		
	}

	public OpcaoJanelaMensagem getOpcaoJanelaMensagem() {
		return opcaoJanelaMensagem;
	}

	public void setOpcaoJanelaMensagem(
			OpcaoJanelaMensagem opcaoJanelaMensagem) {
		
		this.opcaoJanelaMensagem = opcaoJanelaMensagem;
		
		atualizar();
	}

	public boolean isExibirMensagensDeSimulacao() {
		return exibirMensagensDeSimulacao;
	}

	public void setExibirMensagensDeSimulacao(
			boolean exibirMensagensDeSimulacao) {
		
		this.exibirMensagensDeSimulacao = exibirMensagensDeSimulacao;
		
		atualizar();
		
	}

	public boolean isExibirVariaveis() {
		return exibirVariaveis;
	}

	public void setExibirVariaveis(boolean exibirVariaveis) {
		
		this.exibirVariaveis = exibirVariaveis;
		
		atualizar();
		
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
	

}
