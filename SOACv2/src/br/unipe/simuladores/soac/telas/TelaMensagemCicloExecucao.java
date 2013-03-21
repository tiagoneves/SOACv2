package br.unipe.simuladores.soac.telas;

import br.unipe.simuladores.soac.enums.EstadoCiclo;
import br.unipe.simuladores.soac.internacional.Labels;

public class TelaMensagemCicloExecucao extends TelaMensagemSimulacao{
	
	private static final String TRANSFERIR_IR_MBRTXT = Labels.obterValor("transferirirmbrtxt");
	private static final String COPIAR_VALOR_MAR_BARRAMENTO_EXECUCAOTXT = Labels.obterValor("copiarvalormarbarramentoexecucaotxt");
	private static final String TRANSFERIR_IR_MARTXT = Labels.obterValor("transferirirmartxt");
	private static final String COPIAR_VALOR_MBR_BARRAMENTO_EXECUCAOTXT = Labels.obterValor("copiarvalormbrbarramentoexecucaotxt");
	private static final String COPIAR_WRITE_BARRAMENTOTXT = Labels.obterValor("copiarwritebarramentotxt");
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIA_ESCRITATXT = Labels.obterValor("moverdadosbarramentomemoriaescritatxt");
	private static final String MOVER_DADO_MEMORIATXT = Labels.obterValor("moverdadomemoriatxt");
	private static final String MOVER_DADO_REGISTRADORTXT = Labels.obterValor("moverdadoregistradortxt");
	private static final String MOVER_REFERENCIA_INDIRETA_REGISTRADOR_IRTXT = Labels.obterValor("moverreferenciaindiretaregistradorirtxt");
	private static final String TRANSFERIR_IR_MAR_2TXT = Labels.obterValor("transferirirmar2txt");
	private static final String COPIAR_READ_BARRAMENTO_EXECUCAOTXT = Labels.obterValor("copiarreadbarramentoexecucaotxt");
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIA_LEITURATXT = Labels.obterValor("moverdadosbarramentomemorialeituratxt");
	private static final String TRANSFERIR_DADO_LEITURA_BARRAMENTOTXT = Labels.obterValor("transferirdadoleiturabarramentotxt");
	private static final String TRANSFERIR_MBR_PARA_IR_EXECUCAOTXT = Labels.obterValor("transferirmbrparairexecucaotxt");
	private static final String MOVER_DADO_REGISTRADOR_IRTXT = Labels.obterValor("moverdadoregistradorirtxt");
	private static final String FIM_EXECUCAOTXT = Labels.obterValor("fimexecucaotxt");
	private static final String TRANSFERIR_IR_MAR_1TXT = Labels.obterValor("transferirirmar1txt");
	private static final String TRANSFERIR_MBR_IR_ULATXT = Labels.obterValor("transferirmbrirulatxt");
	private static final String EFETUAR_OPERACAO_ARITMETICATXT = Labels.obterValor("efetuaroperacaoaritmeticatxt");
	private static final String TRANSFERIR_ULA_MBRTXT = Labels.obterValor("transferirulambrtxt");
	private static final String TRANSFERIR_REGISTRADOR_IR_ULATXT = Labels.obterValor("transferirregistradorirulatxt");
	private static final String MOVER_DADO_ULA_REGISTRADORTXT = Labels.obterValor("moverdadoularegistradortxt");
	private static final String TRANSFERIR_MBR_REGISTRADOR_ULATXT = Labels.obterValor("transferirmbrregistradorulatxt");
	private static final String TRANSFERIR_REG_REG_ULATXT = Labels.obterValor("transferirregregulatxt");
	private static final String TRANSFEIR_IR_MAR_WRITETXT = Labels.obterValor("transferirirmarwritetxt");
	

	public TelaMensagemCicloExecucao(EstadoCiclo estado) {
		
		super();
		
		super.estado = estado;
		
		modificarMensagem(obterTexto());
		
	}

	@Override
	protected String obterTexto() {
		
		switch(estado) {
		case TRANSFERIR_IR_MBR: return TRANSFERIR_IR_MBRTXT;
		case TRANSFERIR_IR_MAR: return TRANSFERIR_IR_MARTXT;
		case COPIAR_VALOR_MAR_BARRAMENTO_EXECUCAO: return COPIAR_VALOR_MAR_BARRAMENTO_EXECUCAOTXT;
		case COPIAR_VALOR_MBR_BARRAMENTO_EXECUCAO: return COPIAR_VALOR_MBR_BARRAMENTO_EXECUCAOTXT;
		case COPIAR_WRITE_BARRAMENTO: return COPIAR_WRITE_BARRAMENTOTXT;
		case MOVER_DADOS_BARRAMENTO_MEMORIA_ESCRITA: return MOVER_DADOS_BARRAMENTO_MEMORIA_ESCRITATXT;
		case MOVER_DADO_MEMORIA: return MOVER_DADO_MEMORIATXT;
		case MOVER_DADO_REGISTRADOR: return MOVER_DADO_REGISTRADORTXT;
		case MOVER_REFERENCIA_INDIRETA_REGISTRADOR_IR: return MOVER_REFERENCIA_INDIRETA_REGISTRADOR_IRTXT;
		case TRANSFERIR_IR_MAR_2: return TRANSFERIR_IR_MAR_2TXT;
		case COPIAR_READ_BARRAMENTO_EXECUCAO: return COPIAR_READ_BARRAMENTO_EXECUCAOTXT;
		case MOVER_DADOS_BARRAMENTO_MEMORIA_LEITURA: return MOVER_DADOS_BARRAMENTO_MEMORIA_LEITURATXT;
		case TRANSFERIR_DADO_LEITURA_BARRAMENTO: return TRANSFERIR_DADO_LEITURA_BARRAMENTOTXT;
		case TRANSFERIR_MBR_PARA_IR_EXECUCAO: return TRANSFERIR_MBR_PARA_IR_EXECUCAOTXT;
		case MOVER_DADO_REGISTRADOR_IR: return MOVER_DADO_REGISTRADOR_IRTXT;
		case FIM_EXECUCAO: return FIM_EXECUCAOTXT;
		case TRANSFERIR_IR_MAR_1: return TRANSFERIR_IR_MAR_1TXT;
		case TRANSFERIR_MBR_IR_ULA: return TRANSFERIR_MBR_IR_ULATXT;
		case EFETUAR_OPERACAO_ARITMETICA: return EFETUAR_OPERACAO_ARITMETICATXT;
		case TRANSFERIR_ULA_MBR: return TRANSFERIR_ULA_MBRTXT;
		case TRANSFERIR_REGISTRADOR_IR_ULA: return TRANSFERIR_REGISTRADOR_IR_ULATXT;
		case MOVER_DADO_ULA_REGISTRADOR: return MOVER_DADO_ULA_REGISTRADORTXT;
		case TRANSFERIR_MBR_EGISTRADOR_ULA: return TRANSFERIR_MBR_REGISTRADOR_ULATXT;
		case TRANSFERIR_REG_REG_ULA: return TRANSFERIR_REG_REG_ULATXT;
		case TRANSFEIR_IR_MAR_WRITE: return TRANSFEIR_IR_MAR_WRITETXT;
		}
		
		return null;
	}

}
