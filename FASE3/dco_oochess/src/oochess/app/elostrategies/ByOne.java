package oochess.app.elostrategies;

/**
 * @author Alexandre Rodrigues, 54472
 */

import oochess.app.facade.utilizador.Utilizador;

public class ByOne implements Elo {

	/**
	 * Funcao que retorna o elo inicial de cada Utilizador
	 */
	public double startElo() {
		return 5.00;
	}

	/**
	 * Funcao que calcula o elo do utilizador e do oponente dependedo do resultado
	 */
	public void calcularElo (Utilizador user, Utilizador oponente, String resultado) {
		if(resultado.equalsIgnoreCase("VITORIA")) {
			user.changeElo(user.getElo() + 1);
			oponente.changeElo(oponente.getElo() - 1);
		}else if (resultado.equalsIgnoreCase("DERROTA")) {
			user.changeElo(user.getElo() - 1);
			oponente.changeElo(oponente.getElo() + 1);
		}
	}

}
