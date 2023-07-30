package oochess.app.facade.utilizador;

/**
 * @author Alexandre Rodrigues, 54472
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import oochess.app.elostrategies.MyEloConfig;
import oochess.app.facade.torneio.Torneio;

public class CatalogoDeUtilizadores {
	
	private Map<String, Utilizador> cat;

	/**
	 * construtor protected para impedir a criacao de instancias
	 */
	protected CatalogoDeUtilizadores() {
		this.cat = new HashMap<>();
	}
	
	private static CatalogoDeUtilizadores INSTANCE = null;
	
	/**
	 * get instancia do catalogo
	 * 
	 * @return instancia de CatalogoDeUtilizadores
	 */
	public static CatalogoDeUtilizadores getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CatalogoDeUtilizadores();
		}
		return CatalogoDeUtilizadores.INSTANCE;
	}
	
	/**
	 * adiciona utilizador ao catalogo
	 * 
	 * @param nome            - username utilizador
	 * @param pwd             - password do utilizador
	 * @param discordUsername - username discord do utilizador
	 * @return utilizador criado
	 */
	public Utilizador addUtilizador(String nome, String pwd, String discordUsername) {
		Utilizador u = new Utilizador(nome, pwd, discordUsername);
		cat.putIfAbsent(nome, u);
		u.changeElo(MyEloConfig.myEloConfig().startElo());
		System.out.println(nome + " : " + u.getElo());
		return u;
	}
	
	/**
	 * get utilizador
	 * 
	 * @param nome - nome do utilizador
	 * @return {@code Utilizador u}
	 */
	public Utilizador getUtilizador(String nome) {
		return cat.get(nome);
	}
	
	/**
	 * get Utilizador com o username e a password dada
	 * 
	 * @param nome - nome do utilizador
	 * @param pwd  - password do utilizador
	 * @return {@code Optional<Utilizador>} caso exista um username com a 
	 * password dada, {@code Optional.empty()} caso contrario 
	 */
	public Optional<Utilizador> login(String nome, String pwd) {
		Utilizador u = getUtilizador(nome);
		if (u != null && u.getPwd().equals(pwd)) {
			return Optional.of(u);
		}
		return Optional.empty();
	}
	
	/**
	 * Verifica se utilizador existe
	 * 
	 * @param nome - nome do utilizador
	 * @return true se utilizador existe, false cc
	 */
	public boolean utilizadorExiste(String nome) {
		return cat.containsKey(nome);
	}
	
	
	/**
	 * lista de utilizadores dentro de um certo delta
	 * 
	 * @param delta                     - delta elo
	 * @param elo                       - elo do utilizador autenticado
	 * @param t                         - torneio
	 * @param utilizadorAutenticadoNome - username do utilizador autenticado
	 * @return lista de utilizadores dentro do delta elo dado e caso 
	 * {@code t != null} utilizadores que tambem estejam inscritos no torneio dadp
	 */
	public List<UtilizadorDTO> obtemListaDeOponentes(int delta, double elo, Torneio t,
			String utilizadorAutenticadoNome) {
		
		List<UtilizadorDTO> l = new ArrayList<>();
		cat.forEach((k,v) -> {
			if (!k.equals(utilizadorAutenticadoNome)) {
				boolean b = v.estaDentroDelta(delta, elo, t);
				if (b) {
					l.add(new UtilizadorDTO(v.getNome(), v.getElo(), v.getDiscordUsername()));
				}
			}
		});
		return l;
	}
}
