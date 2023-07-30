package oochess.app.facade.handlers;

/**
 * Caso de Uso 7
 * 
 * @author Alexandre Rodrigues, 54472
 */

import java.time.LocalDateTime;
import java.util.List;
import oochess.app.facade.torneio.Desafio;
import oochess.app.facade.utilizador.Utilizador;

public class ProcessarDesafiosHandler {
	
	private Desafio d = null;
	private Utilizador u;

	/**
	 * Inicia o handler
	 * 
	 * @param u - Utilizador autenticado
	 */
	public ProcessarDesafiosHandler(Utilizador u) {
		this.u = u;
	}

	/**
	 * Consulta a lista de desafios pendentes
	 * 
	 * @return lista de desafios pendentes
	 */
	public List<Desafio> consultarDesafiosPendentes() {
		return u.getListaDesafiosPendentes();
	}

	/**
	 * Função que aceita ou rejeita desafio
	 * 
	 * @param codigo   - codigo do desafio
	 * @param resposta - resposta ao desafio
	 * @ensures {@code d.getResposta() == resposta}
	 */
	public void respondeADesafio(String codigo, boolean resposta) {
		d = u.getDesafio(codigo);
		d.respostaDesafio(resposta);
	}

	/**
	 * Altera a data do desafio
	 * 
	 * @param datahora - nova data
	 * @ensures {@code d.getData() == data}
	 */
	public void indicaNovaData(LocalDateTime datahora) {
		d.novaDataHora(datahora);
	}

}
