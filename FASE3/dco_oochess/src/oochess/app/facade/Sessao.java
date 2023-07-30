package oochess.app.facade;

/**
 * @author Alexandre Rodrigues, 54472
 */

import oochess.app.facade.handlers.DesafiarHandler;
import oochess.app.facade.handlers.ProcessarDesafiosHandler;
import oochess.app.facade.handlers.RegistarResultadoHandler;
import oochess.app.facade.torneio.CatalogoDeTorneios;
import oochess.app.facade.utilizador.CatalogoDeUtilizadores;
import oochess.app.facade.utilizador.Utilizador;

public class Sessao {

	private CatalogoDeTorneios catTorneios;
	private CatalogoDeUtilizadores catUtilizadores;
	private Utilizador u;
	
	/**
	 * cria uma sessao
	 * 
	 * @param u               - utilizador autenticado
	 * @param catUtilizadores - catalogo de utilizadores
	 * @param catTorneios     - catalogo de torneios
	 */
	public Sessao(Utilizador u, CatalogoDeUtilizadores catUtilizadores,
			CatalogoDeTorneios catTorneios) {
		
		this.u = u;
		this.catUtilizadores = catUtilizadores;
		this.catTorneios = catTorneios;
	}

	/**
	 * get DesafiarHandler
	 * 
	 * @return DesafiarHandler
	 */
	public DesafiarHandler getDesafioParaPartidaHandler() {
		return new DesafiarHandler(u, catUtilizadores, catTorneios);
	}

	/**
	 * get RegistarResultadoHandler
	 * 
	 * @return RegistarResultadoHandler
	 */
	public RegistarResultadoHandler getRegistarResultadoDePartida() {
		return new RegistarResultadoHandler(u, catUtilizadores);
	}

	/**
	 * get ProcessarDesafiosHandler
	 * 
	 * @return ProcessarDesafiosHandler
	 */
	public ProcessarDesafiosHandler getProcessarDesafios() {
		return new ProcessarDesafiosHandler(u);
	}
}
