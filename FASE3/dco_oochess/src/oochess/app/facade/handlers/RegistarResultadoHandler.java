package oochess.app.facade.handlers;

import java.time.LocalDateTime;
import java.util.List;

import oochess.app.facade.utilizador.CatalogoDeUtilizadores;
import oochess.app.facade.utilizador.Utilizador;
import oochess.app.facade.utilizador.UtilizadorDTO;
import oochess.app.facade.torneio.Partida;
import oochess.app.elostrategies.MyEloConfig;
import oochess.app.facade.torneio.Desafio;

/**
 * Caso de Uso 6
 * 
 * @author Alexandre Rodrigues, 54472
 */

public class RegistarResultadoHandler {

	private CatalogoDeUtilizadores utilizadores;
	private Utilizador user;
	private Utilizador oponente;
	private Partida p;
	private Desafio desafio;

	
	/**
	 * Handler que regista o resultado das partidas
	 * 
	 * @param user - utilizador autenticado
	 * @param utilizadores - Catalogo de Utilizadores
	 */
	public RegistarResultadoHandler(Utilizador user, CatalogoDeUtilizadores utilizadores) {
		this.user = user;
		this.utilizadores = utilizadores;
	}

	/**
	 * Regista o desafio 
	 * 
	 * @param codigoDesafio - codigo do desafio
	 */
	public void indicaDesafio(String codigoDesafio) {
		desafio = user.getDesafio(codigoDesafio);
		oponente = utilizadores.getUtilizador(obterOponenteDesafio());
		
		p = new Partida(new UtilizadorDTO(user.getNome(), user.getElo(), user.getDiscordUsername()),
				new UtilizadorDTO(oponente.getNome(), oponente.getElo(), oponente.getDiscordUsername()),
				desafio.getData());
		
		p.setDesafio(desafio);
	}

	/**
	 * Em caso de uma partida espontanea retorna uma lista dos ultimos 5 oponentes
	 * 
	 * @return lista dos ultimos 5 oponentes
	 */
	public List<UtilizadorDTO> indicaPartidaEspontanea() {
		return user.obtemUltimosOponentes();
	}

	/**
	 * Em caso de uma partida espontanea regista o username do oponente e a hora da partida
	 * 
	 * @param username - username do oponente
	 * @param datahora - hora da partida
	 */
	public void indicaDetalhes(String username, LocalDateTime datahora) {
		oponente = utilizadores.getUtilizador(username);
		p = new Partida(new UtilizadorDTO(user.getNome(), user.getElo(), user.getDiscordUsername()),
				new UtilizadorDTO(oponente.getNome(), oponente.getElo(), oponente.getDiscordUsername()),
				datahora);
		user.adicionarPartida(p);
		oponente.adicionarPartida(p);
	}

	/**
	 * Fun��o que retorna o Elo atualizado do utilizador dependente do resultado
	 * 
	 * @param resultado - resultado da partida
	 * @return Elo atualizado do utilizador
	 */
	public double indicarResultado(String resultado) {
		p.setResultado(resultado);
		MyEloConfig.myEloConfig().calcularElo(user, oponente, resultado);
		return user.getElo();
	}
	
	/**
	 * Fun��o que retorna o Oponente
	 * 
	 * @return Oponente
	 */
	private String obterOponenteDesafio() {
		String s;
		if (desafio.getOponente().getNome().equals(user.getNome())) {
			s = desafio.getUtilizador().getNome();
		} else {
			s = desafio.getOponente().getNome();
		}
		return s;
	}
}
