package oochess.app.facade.torneio;

/**
 * @author Alexandre Rodrigues, 54472
 */

import java.time.LocalDateTime;

import oochess.app.facade.utilizador.UtilizadorDTO;

public class Partida {

	private String resultado;
	private UtilizadorDTO user;
	private UtilizadorDTO oponente;
	private LocalDateTime data;
	private Desafio desafio = null;
	
	/**
	 * construtor partida
	 * 
	 * @param user     - jogador
	 * @param oponente - jogador
	 * @param data     - data da partida
	 */
	public Partida (UtilizadorDTO user, UtilizadorDTO oponente, LocalDateTime data) {
		this.user = user;
		this.oponente = oponente;
		this.data = data;
	}
	
	/**
	 * get utilizador que desafiou
	 * 
	 * @return {@code user}
	 */
	public UtilizadorDTO getUtilizador() {
		return user;
	}
	
	/**
	 * get utilizador que recebeu utilizador
	 * 
	 * @return {@code oponente}
	 */
	public UtilizadorDTO getOponente() {
		return oponente;
	}
	
	/**
	 * get resultado
	 * 
	 * @return {@code resultado}
	 */
	public String getResultado() {
		return resultado;
	}
	
	/**
	 * get data
	 * 
	 * @return {@code data}
	 */
	public LocalDateTime getData() {
		return data;
	}
	
	/**
	 * get desafio
	 * 
	 * @return {@code desafio}
	 */
	public Desafio getDesafio() {
		return desafio;
	}
	
	/**
	 * set data
	 * 
	 * @param data 
	 */
	public void setData (LocalDateTime data) {
		this.data = data;
	}
	
	public void setResultado (String resultado) {
		this.resultado = resultado;
	}
	
	/**
	 * set desafio
	 * 
	 * @param d - desafio
	 */
	public void setDesafio(Desafio d) {
		desafio = d;
	}
}
