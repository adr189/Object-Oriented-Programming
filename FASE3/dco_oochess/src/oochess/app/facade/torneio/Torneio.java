package oochess.app.facade.torneio;

/**
 * @author Alexandre Rodrigues, 54472
 */

import java.util.HashMap;
import java.util.Map;

import oochess.app.facade.utilizador.Utilizador;

public class Torneio {
	
	private String nome;
	private Map<String, Utilizador> utilizadoresInscritos;
	private Map<String, Desafio> desafiosTorneio;

	/**
	 * constrotor torneio
	 * 
	 * @param nome - nome torneio
	 */
	public Torneio(String nome) {
		this.nome = nome;
		this.utilizadoresInscritos = new HashMap<>();
		this.desafiosTorneio = new HashMap<>();
	}

	/**
	 * Verifica se utilizador esta inscrito
	 * 
	 * @param nome - username do utilizador
	 * @return true se utilizador estiver inscrito no torneio, false cc
	 */
	public boolean estaInscrito(String nome) {
		return utilizadoresInscritos.containsKey(nome);
	}
	
	/**
	 * increve utilizador no torneio
	 * 
	 * @param u - utilizador
	 */
	public void inscrever(Utilizador u) {
		utilizadoresInscritos.put(u.getNome(), u);
	}
	
	/**
	 * get nome do torneio
	 * 
	 * @return {@code nome}
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * associa desafios ao torneio
	 * 
	 * @param d - desafio
	 */
	public void associaDesafio(Desafio d) {
		desafiosTorneio.put(d.getCodigo(), d);
	}

}
