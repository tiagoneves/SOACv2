package br.unipe.simuladores.soac.telas;

import br.unipe.simuladores.soac.enums.EstadoCiclo;
import br.unipe.simuladores.soac.internacional.Labels;

public class TelaMensagemCicloBusca extends TelaMensagemSimulacao{
	
	private static final String ATUALIZAR_PCTXT = Labels.obterValor("atualizarpctxt");
	private static final String MOVER_MARTXT = Labels.obterValor("movermartxt");
	private static final String COPIAR_READ_BARRAMENTOTXT = Labels.obterValor("compiareadbarramentotxt");
	private static final String COPIAR_VALOR_MAR_BARRAMENTOTXT = Labels.obterValor("copiarvalormarbarramentotxt");
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIATXT = Labels.obterValor("moverdadosbarramentomemoriatxt");
	private static final String TRANSFERIR_INSTRUCAOTXT = Labels.obterValor("transferirinstrucaotxt");
	private static final String ATUALIZAR_PC_PROX_INSTRUCAOTXT = Labels.obterValor("atualizarpcproxinstrucaotxt");
	private static final String NAO_HA_PROX_INSTRUCAOTXT = Labels.obterValor("naohaproxinstrucaotxt");
	private static final String COPIAR_MBR_PARA_IRTXT = Labels.obterValor("copiarmbrparairtxt");
	
	public TelaMensagemCicloBusca(EstadoCiclo estado) {
		super();
		
		super.estado = estado;
		
		modificarMensagem(obterTexto());
		
	}
	
	public String obterTexto() {
		
		switch(estado) {
		case INICIAL: return "Inicial";
		case ATUALIZAR_PC: return ATUALIZAR_PCTXT;
		case MOVER_MAR: return MOVER_MARTXT;
		case COPIAR_READ_BARRAMENTO: return COPIAR_READ_BARRAMENTOTXT;
		case COPIAR_VALOR_MAR_BARRAMENTO: return COPIAR_VALOR_MAR_BARRAMENTOTXT;
		case MOVER_DADOS_BARRAMENTO_MEMORIA: return MOVER_DADOS_BARRAMENTO_MEMORIATXT;
		case TRANSFERIR_INSTRUCAO: return TRANSFERIR_INSTRUCAOTXT;
		case ATUALIZAR_PC_PROX_INSTRUCAO: return ATUALIZAR_PC_PROX_INSTRUCAOTXT;
		case NAO_HA_PROX_INSTRUCAO: return NAO_HA_PROX_INSTRUCAOTXT;
		case COPIAR_MBR_PARA_IR: return COPIAR_MBR_PARA_IRTXT;
		}
		
		return null;
		
	}	

}
