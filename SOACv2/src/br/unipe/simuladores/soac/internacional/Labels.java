package br.unipe.simuladores.soac.internacional;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class Labels {
	
	private static Map<String, String> labels = new HashMap<String, String>();
	private static ResourceBundle resourceBundle;
	
	public static void carregar(Idioma idioma){
		
		String nomeArquivo = null;
		
		switch(idioma){
		case PORTUGUES_BR: nomeArquivo = "Labels_PT-BR"; break;
		case INGLES_US: nomeArquivo = "Labels_EN-US"; break;
		}
		
		resourceBundle = ResourceBundle.getBundle("br.unipe.simuladores.soac.internacional."+nomeArquivo);
		Enumeration<String> keys = resourceBundle.getKeys();
		
		while(keys.hasMoreElements()) {
			
			String key = keys.nextElement();
			String valor = resourceBundle.getString(key);
			
			labels.put(key, valor);
			
		}
		
	}
	
	public static void main(String[] args) {
		
		Set<String> chaves = labels.keySet();
		
		carregar(Idioma.INGLES_US);
		
		for (String chave : chaves) 
			System.out.println(chave + ": "+ labels.get(chave));
		
	}
	
	public static String obterValor(String chave) {
		
		return labels.get(chave);
		
	}

}
