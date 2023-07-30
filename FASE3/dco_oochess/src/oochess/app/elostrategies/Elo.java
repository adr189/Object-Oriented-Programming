package oochess.app.elostrategies;

/**
 * @author Alexandre Rodrigues, 54472
 */

import oochess.app.facade.utilizador.Utilizador;

public interface Elo {
	void calcularElo (Utilizador user, Utilizador oponente, String resultado);
	double startElo();
}
