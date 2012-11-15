package br.unipe.simuladores.soac.internacional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import br.unipe.simuladores.soac.enums.OpcaoJanelaMensagem;

public class Configuracao {
	
	private static Idioma idioma;
	private static OpcaoJanelaMensagem opcaoJanelaMensagem;
	private static boolean exibirMensagensDeSimulacao;
	private static boolean exibirVariaveis;
	
	private static ResourceBundle resourceBundle;
	
	public static void carregar() {
		
		resourceBundle = ResourceBundle.getBundle("config");
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
		
	}
	
	public static void atualizar() {
		
		String idiom = null;
		
		switch(Configuracao.idioma) {
		case PORTUGUES_BR: idiom = "ptbr"; break;
		case INGLES_US: idiom = "enus"; break;
		}
		
		String mensagens = null;
		
		switch(Configuracao.opcaoJanelaMensagem) {
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
			
			File file = new File("src/config.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "Configuracao");
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static Idioma getIdioma() {
		return idioma;
	}

	public static void setIdioma(Idioma idioma) {
		
		Configuracao.idioma = idioma;
		
		atualizar();
		
	}

	public static OpcaoJanelaMensagem getOpcaoJanelaMensagem() {
		return opcaoJanelaMensagem;
	}

	public static void setOpcaoJanelaMensagem(
			OpcaoJanelaMensagem opcaoJanelaMensagem) {
		Configuracao.opcaoJanelaMensagem = opcaoJanelaMensagem;
	}

	public static boolean isExibirMensagensDeSimulacao() {
		return exibirMensagensDeSimulacao;
	}

	public static void setExibirMensagensDeSimulacao(
			boolean exibirMensagensDeSimulacao) {
		Configuracao.exibirMensagensDeSimulacao = exibirMensagensDeSimulacao;
	}

	public static boolean isExibirVariaveis() {
		return exibirVariaveis;
	}

	public static void setExibirVariaveis(boolean exibirVariaveis) {
		Configuracao.exibirVariaveis = exibirVariaveis;
	}

	public static ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public static void setResourceBundle(ResourceBundle resourceBundle) {
		Configuracao.resourceBundle = resourceBundle;
	}
	

}
