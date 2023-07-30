package oochess.app.facade.utilizador;

/**
 * @author Alexandre Rodrigues, 54472
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import oochess.app.facade.torneio.Desafio;
import oochess.app.facade.torneio.Torneio;
import oochess.app.facade.torneio.Partida;

public class Utilizador {

	private String nome;
	private String discordUsername;
	private String pwd;
	private double elo;
	private Map<String, Desafio> listaDesafios;
	private List<Partida> listaDePartidas;

	/**
	 * construtor utilizador
	 * 
	 * @param nome            - nome do utilizador
	 * @param pwd             - password do utilizador
	 * @param discordUsername - nome discord do utilizador
	 */
	public Utilizador(String nome, String pwd, String discordUsername) {
		this.nome = nome;
		this.discordUsername = discordUsername;
		this.pwd = pwd;
		this.listaDesafios = new HashMap<>();
		this.listaDePartidas = new ArrayList<>();
	}

	/**
	 * get nome utilizador
	 * 
	 * @return {@code nome}
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * get nome discord do utilizador
	 * 
	 * @return {@code discordUsername}
	 */
	public String getDiscordUsername() {
		return discordUsername;
	}

	/**
	 * get password do utilizador
	 * 
	 * @return {@code pwd}
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * get elo do utilizador
	 * 
	 * @return {@code elo}
	 */
	public double getElo() {
		return elo;
	}

	/**
	 * altera o elo
	 * 
	 * @param newElo - novo elo
	 */
	public void changeElo (double newElo) {
		elo = newElo;
	}

	/**
	 * verifica se o elo do utilizador encontra dentro de um intervalo
	 * 
	 * @param delta - delta elo
	 * @param elo2  - elo alvo
	 * @param t     - torneio
	 * @return true caso o utilizador esteja dentro do intervalo, se
	 * {@code t != null} verifica se tambem se encontra no torneio dado. false
	 * caso contrario  
	 */
	public boolean estaDentroDelta(int delta, double elo2, Torneio t) {
		boolean b = Math.abs(elo - elo2) < delta;
		if (t != null) {
			b = b && t.estaInscrito(nome);
		}
		return b;
	}

	/**
	 * cria desafio
	 * 
	 * @param datahora - data do desafio
	 * @param msg      - mensagem
	 * @param us       - utilizador a ser desafiado
	 * @param t        - torneio
	 * @return codigo do desafio
	 */
	public String criaDesafio(LocalDateTime datahora, String msg, Utilizador us, Torneio t) {
		Desafio d = new Desafio(datahora, msg, this, us, t);
		us.recebeDesafio(d);
		String codigo = d.getCodigo();
		listaDesafios.put(codigo, d);
		return codigo;
	}

	/**
	 * veridica se existe um desafio com o codigo dado
	 * 
	 * @param cod - codigo desafio
	 * @return true caso exista um desafio com o codigo dado, false cc
	 */
	public boolean existCodigo(String cod) {
		return listaDesafios.containsKey(cod);
	}

	/**
	 * recebe desafio
	 * 
	 * @param d - desafio
	 */
	public void recebeDesafio(Desafio d) {
		listaDesafios.put(d.getCodigo(), d);
	}

	/**
	 * get desafio com o codigo dado
	 * 
	 * @param cod - codigo do desafio
	 * @return desafio com o codigo dado
	 */
	public Desafio getDesafio(String cod) {
		return listaDesafios.get(cod);
	}

	/**
	 * get desafios pendentes
	 * 
	 * @return lista de desafios pendentes
	 */
	public List<Desafio> getListaDesafiosPendentes() {
		return listaDesafios
				.values()
				.stream()
				.filter(d -> d.estaPendente())
				.collect(Collectors.toList());
	}

	/**
	 * remove desafio da lista
	 * 
	 * @param codigo - codigo do desafio a ser removido
	 */
	public void remove(String codigo) {
		listaDesafios.remove(codigo);
	}

	/**
	 * adiciona partida a lista de partidas
	 * 
	 * @param p - partida
	 */
	public void adicionarPartida (Partida p) {
		listaDePartidas.add(p);
	}

	/**
	 * obtem os ultimos 5 oponentes
	 * 
	 * @return lista com os ultimos 5 oponentes diferentes caso existam
	 */
	public List<UtilizadorDTO> obtemUltimosOponentes() {
		List<UtilizadorDTO> ultimOponent = new ArrayList<>();
		int i = listaDePartidas.size() - 1;
		while (i >= 0 && ultimOponent.size() != 5) {
			Partida p = listaDePartidas.get(i);
			UtilizadorDTO u = p.getUtilizador().getNome().equals(nome) 
					? p.getOponente() : p.getUtilizador();
			if (!ultimOponent.contains(u)) {
				ultimOponent.add(u);
			}
		}
		return ultimOponent;
	}

	/**
	 * print para o terminal o codigo de todos os desafios
	 */
	public void printDesafios() {
		List<Desafio> l = listaDesafios
		.values()
		.stream()
		.collect(Collectors.toList());
		
		for (Desafio desafio : l) {
			System.out.println(desafio.getCodigo());
		}
	}

}
