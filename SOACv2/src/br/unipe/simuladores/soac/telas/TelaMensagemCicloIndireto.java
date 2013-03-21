package br.unipe.simuladores.soac.telas;

import br.unipe.simuladores.soac.enums.EstadoCiclo;
import br.unipe.simuladores.soac.enums.OperandoCicloIndireto;
import br.unipe.simuladores.soac.internacional.Labels;

public class TelaMensagemCicloIndireto extends TelaMensagemSimulacao{

	private OperandoCicloIndireto operando;
	
	private static final String VERIFICAR_IRTXT = Labels.obterValor("verificarirtxt");
	private static final String TRANSFERIR_OPERANDO_MARTXT = Labels.obterValor("transferiroperandomartxt");
	private static final String TRANSFERIR_OPERANDO_MARXT_NAO_HA = Labels.obterValor("transferiroperandomartxtnaoha");
	private static final String COPIAR_READ_BARRAMENTO_INDTXT = Labels.obterValor("copiarreadbarramentoindtxt");
	private static final String COPIAR_VALOR_MAR_BARRAMENTO_INDTXT = Labels.obterValor("copiarvalormarbarramentoindtxt");
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIA_INDTXT = Labels.obterValor("moverdadosbarramentomemoriaindtxt");
	private static final String TRANSFERIR_DADO_REFERENCIA_INDIRETA_BARRAMENTOTXT = Labels.obterValor("transferirdadoreferenciaindiretabarramentotxt");
	private static final String TRANSFERIR_MBR_PARA_IRTXT = Labels.obterValor("transferirmbrparairtxt");
	
	public TelaMensagemCicloIndireto(EstadoCiclo estado) {
		super();
		
		super.estado = estado;
		
		modificarMensagem(obterTexto());
	}
	
	public TelaMensagemCicloIndireto(EstadoCiclo estado, OperandoCicloIndireto operando) {
		super();
		
		super.estado = estado;
		
		this.operando = operando;
		
		modificarMensagem(obterTexto());
	}

	@Override
	protected String obterTexto() {
		
		switch(estado) {
		case VERIFICAR_IR: return VERIFICAR_IRTXT;
		case TRANSFERIR_OPERANDO_MAR: {
			if (operando == OperandoCicloIndireto.PRIMEIRO)
				return Labels.obterValor("oprimeiro") + TRANSFERIR_OPERANDO_MARTXT;
			else if (operando == OperandoCicloIndireto.SEGUNDO)
				return Labels.obterValor("osegundo") + TRANSFERIR_OPERANDO_MARTXT;
			else
				return TRANSFERIR_OPERANDO_MARXT_NAO_HA;
		}
		case COPIAR_READ_BARRAMENTO_IND: return COPIAR_READ_BARRAMENTO_INDTXT;
		case COPIAR_VALOR_MAR_BARRAMENTO_IND: return COPIAR_VALOR_MAR_BARRAMENTO_INDTXT;
		case MOVER_DADOS_BARRAMENTO_MEMORIA_IND: return MOVER_DADOS_BARRAMENTO_MEMORIA_INDTXT;
		case TRANSFERIR_DADO_REFERENCIA_INDIRETA_BARRAMENTO: return TRANSFERIR_DADO_REFERENCIA_INDIRETA_BARRAMENTOTXT;
		case TRANSFERIR_MBR_PARA_IR: return TRANSFERIR_MBR_PARA_IRTXT;
		}
		
		return null;
	}

	
}
