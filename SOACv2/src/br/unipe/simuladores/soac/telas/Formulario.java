package br.unipe.simuladores.soac.telas;

import br.unipe.simuladores.soac.excecoes.DadosInvalidosException;

public interface Formulario {

	void validarDados() throws DadosInvalidosException;
}
