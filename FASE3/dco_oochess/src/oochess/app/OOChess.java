package oochess.app;

/**
 * @author Alexandre Rodrigues, 54472
 */

import java.util.Optional;

import oochess.app.facade.Sessao;
import oochess.app.facade.handlers.RegistarUtilizadorHandler;
import oochess.app.facade.torneio.CatalogoDeTorneios;
import oochess.app.facade.utilizador.CatalogoDeUtilizadores;
import oochess.app.facade.utilizador.Utilizador;

/**
 * Esta é a classe do sistema.
 */
public class OOChess {
	
	private CatalogoDeUtilizadores catUtilizadores = 
			CatalogoDeUtilizadores.getInstance();
	private CatalogoDeTorneios catTorneios = CatalogoDeTorneios.getInstance();
	
	/**
	 * get RegistarUtilizadorHandler
	 * 
	 * @return {@code RegistarUtilizadorHandler}
	 */
	public RegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler(catUtilizadores);
	}
	
	/**
	 * Returns an optional Session representing the authenticated user.
	 * 
	 * @param username - username do utilizador
	 * @param password - password do utilizador
	 * @return optional Session caso seja possivel fazer login, 
	 * {@code Optional.empty()} caso contrario
	 */
	public Optional<Sessao> autenticar(String username, String password) {
		Optional<Utilizador> optU = catUtilizadores.login(username, password);
		if (optU.isEmpty()) {
			return Optional.empty();
		}
		
		Utilizador u = optU.get();
		return Optional.of(new Sessao(u, catUtilizadores, catTorneios));
	}

	
	
}
