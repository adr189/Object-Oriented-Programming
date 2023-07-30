package oochess.app.facade.handlers;

/**
 * @author Alexandre Rodrigues, 54472
 */

import oochess.app.facade.utilizador.CatalogoDeUtilizadores;

public class RegistarUtilizadorHandler {
	
	private CatalogoDeUtilizadores cat;
	
	/**
	 * Inicia o handler
	 * 
	 * @param cat - catalogo de utilizadores
	 */
	public RegistarUtilizadorHandler(CatalogoDeUtilizadores cat) {
		this.cat = cat;
	}
	
	/**
	 * Regista um utilizador normal.
	 * 
	 * @param discordUsername - username discord do utilizador
	 * @param Username        - username do utilizador a ser criado
	 * @param Password        - password do utilizador a ser criado
	 * @ensures existe um utilizador com esse username
	 */
	public void registarUtilizador(String username, String password, String discordUsername) {
		cat
		.addUtilizador(username, password, discordUsername);
	}

	/**
	 * Verifica se existe um utilizador com o username dado
	 * 
	 * @param username
	 * @return true se existir um utilizador com o username dado, false cc
	 */
	public boolean usernameExists(String username) {
		return cat.utilizadorExiste(username);
	}
}
