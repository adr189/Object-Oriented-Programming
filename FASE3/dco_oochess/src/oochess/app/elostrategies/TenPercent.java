package oochess.app.elostrategies;

/**
 * @author Alexandre Rodrigues, 54472
 */

import static java.lang.Math.abs;

import oochess.app.facade.utilizador.Utilizador;

public class TenPercent implements Elo {

	/**
	 * Funcao que retorna o elo inicial de cada Utilizador
	 */
	public double startElo() {
		return 50.00;
	}

	/**
	 * Funcao que calcula o elo do utilizador e do oponente dependedo do resultado
	 */
	public void calcularElo (Utilizador user, Utilizador oponente, String resultado) {

		double elo = abs(user.getElo() - oponente.getElo()) * 0.1 + 5;

		if(resultado.equalsIgnoreCase("VITORIA")) {
			user.changeElo(user.getElo() + elo);
			oponente.changeElo(oponente.getElo() - elo);
		}else if (resultado.equalsIgnoreCase("DERROTA")) {
			user.changeElo(user.getElo() - elo);
			oponente.changeElo(oponente.getElo() + elo);
		}else if (resultado.equalsIgnoreCase("EMPATE")){
			double diferencaElo = abs(user.getElo() - oponente.getElo()) * 0.05;
			if(user.getElo() > oponente.getElo()) {
				user.changeElo(user.getElo() - diferencaElo);
				oponente.changeElo(oponente.getElo() + diferencaElo);
			}else {
				user.changeElo(user.getElo() - diferencaElo);
				oponente.changeElo(oponente.getElo() + diferencaElo);
			}
		}
	}

}
