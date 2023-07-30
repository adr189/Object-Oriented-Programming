package oochess.app.facade.handlers;

/**
 * Caso de Uso 5
 * 
 * @author Alexandre Rodrigues, 54472
 */
import java.time.LocalDateTime;
import java.util.List;

import oochess.app.discordintegration.MyDiscordConfig;
import oochess.app.facade.torneio.CatalogoDeTorneios;
import oochess.app.facade.torneio.Torneio;
import oochess.app.facade.utilizador.CatalogoDeUtilizadores;
import oochess.app.facade.utilizador.Utilizador;
import oochess.app.facade.utilizador.UtilizadorDTO;

public class DesafiarHandler {

	private CatalogoDeTorneios catTorneios;
	private CatalogoDeUtilizadores catUtilizadores;
	private Torneio t = null;
	private Utilizador u;
	private Utilizador us;

	/**
	 * Inicia o handler
	 * 
	 * @param u               - utilizador autenticado
	 * @param catUtilizadores - catalogo de utilizadores
	 * @param catTorneios     - catalogo de torneios
	 */
	public DesafiarHandler(Utilizador u, CatalogoDeUtilizadores catUtilizadores,
			CatalogoDeTorneios catTorneios) {
		this.u = u;
		this.catTorneios = catTorneios;
		this.catUtilizadores = catUtilizadores;
	}

	/**
	 * Indica um torneio
	 * 
	 * @param nome - nome do torneio
	 * @requires existe {@code Utilizador u} autenticado
	 * @ensures é criada um {@code Torneio t} com {@code t.getNome() == nome}
	 */
	public void indicaTorneio(String nome) {
		t = catTorneios.getTorneio(nome);
	}

	/**
	 * Procura utilizadores dentro de um delta elo
	 * 
	 * @param delta - delta usado para a procura
	 * @return lista de utilizadores dentro do delta indicado
	 * @requires existe {@code Utilizador u} autenticado
	 * @ensures é criada uma lista {@code Lista<UtilizadorDTO>} onde
	 * {@code Math.abs(l.get(i).getElo() - u.getElo()) < delta } 
	 */
	public List<UtilizadorDTO> indicaDeltaElo(int delta) {
		double elo = u.getElo();
		return catUtilizadores.obtemListaDeOponentes(delta, elo, t, u.getNome());
	}

	/**
	 * Indica utilizador que se pretende desafia
	 * 
	 * @param nome - nome do utilizador a ser desafiado
	 * @requires existe {@code Utilizador u} autenticado
	 * @ensures existe {@code Utilizador us} onde {@code us.getNome() == nome}
	 */
	public void indicaJogador(String nome) {
		this.us = catUtilizadores.getUtilizador(nome);
	}

	/**
	 * Indica detalhes do desafio
	 * 
	 * @param datahora - data da partida
	 * @param msg      - smack talk a ser enviado
	 * @return codigo do desafio criado
	 * @requires existe {@code Utilizador u} autenticado
	 * @ensures é criada um {@code Desafio d} com {@code d.getData() == datahora
	 * && d.getMsg() = msg}, o desafio é adicionado ao {@code Utilizador u} e é
	 * envidado ao {@code Utilizador us}, é gerado um codigo {@code == d.getCodigo()}
	 */
	public String indicaDetalhes(LocalDateTime datahora, String msg) {
		String cod = u.criaDesafio(datahora, msg, us, t);
		String disMsg = "O jogador " + u.getNome() + " convidou-o para uma " 
				+ "partida de xadrez em com o codigo > " + cod + " < em " 
				+ datahora + ":" + msg;
		MyDiscordConfig
			.myDiscordConfig()
			.ifPresent(o -> o.sendMensagem(us.getDiscordUsername(), disMsg));
		return cod;
	}
}
