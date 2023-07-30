package oochess.app.facade.torneio;

/**
 * @author Alexandre Rodrigues, 54472
 */

import java.time.LocalDateTime;
import java.util.Random;

import oochess.app.facade.utilizador.Utilizador;
import oochess.app.facade.utilizador.UtilizadorDTO;

public class Desafio {

	private String codigo;
	private LocalDateTime datahora;
	private String msg;
	private UtilizadorDTO uDesafiado;
	private UtilizadorDTO u;
	private Torneio t;
	private Random rng;
	private boolean resposta;

	/**
	 * Construtor desafio
	 * 
	 * @param datahora   - data do desafiio
	 * @param msg        - mensagem do desafio
	 * @param u          - utilizador que criou o desafio
	 * @param uDesafiado - utilizador desafiado
	 * @param t          - torneio associado caso exista
	 */
	public Desafio(LocalDateTime datahora, String msg, Utilizador u, Utilizador uDesafiado, Torneio t) {
		this.datahora = datahora;
		this.msg = msg;
		this.uDesafiado = new UtilizadorDTO(uDesafiado.getNome(), uDesafiado.getElo(), uDesafiado.getDiscordUsername());
		this.u = new UtilizadorDTO(u.getNome(), u.getElo(), u.getDiscordUsername());
		this.t = t;
		this.rng = new Random();
		this.resposta = false;
		do {
			this.codigo = createCodigo(u.getNome(), uDesafiado.getNome(), this.rng);
		} while (u.existCodigo(this.codigo));
		if (t != null) {
			t.associaDesafio(this);
		}
	}
	
	/**
	 * gera um codigo do desafio
	 * 
	 * @param u   - username
	 * @param us  - username
	 * @param rng - random number generato
	 * @return codigo gerado
	 */
	private static String createCodigo(String u, String us, Random rng) {
		String s = u + "" + us;
		return Integer.toHexString(rng.nextInt(0xffff)) + "" + s;
	}

	/**
	 * get msg
	 * 
	 * @return {@code msg}
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * get utilizador desafiado
	 * 
	 * @return {@code uDesafiado}
	 */
	public UtilizadorDTO getOponente() {
		return uDesafiado;
	}

	/**
	 * get utilizador que criou desafio
	 * 
	 * @return {@code u}
	 */
	public UtilizadorDTO getUtilizador() {
		return u;
	}

	/**
	 * get torneio
	 * 
	 * @return {@code t}
	 */
	public Torneio getTorneio() {
		return t;
	}
	
	/**
	 * get data do desafio
	 * 
	 * @return {@code datahora}
	 */
	public LocalDateTime getData() {
		return datahora;
	}

	/**
	 * get codigo do desafio
	 * 
	 * @return {@code codigo}
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * get resposta do desafio
	 * 
	 * @return {@code resposta}
	 */
	public boolean getResposta() {
		return resposta;
	}

	/**
	 * set nova data
	 * 
	 * @param datahora - nova data
	 */
	public void novaDataHora(LocalDateTime datahora) {
		this.datahora = datahora;
	}
	
	/**
	 * verifica se o desafio esta dependente
	 * 
	 * @return {@code !resposta}
	 */
	public boolean estaPendente() {
		return !resposta;
	}
	
	/**
	 * responde desafio
	 * 
	 * @param resposta - resposta ao desafio
	 */
	public void respostaDesafio(boolean resposta) {
		this.resposta = resposta;
	}

}

