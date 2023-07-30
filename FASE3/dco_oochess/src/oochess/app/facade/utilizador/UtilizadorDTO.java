package oochess.app.facade.utilizador;

/**
 * @author Alexandre Rodrigues, 54472
 */

public class UtilizadorDTO {
	
	private String nome;
	private String discordUsername;
	private double elo;

	/**
	 * construtor de um utilizador utilizador o padrao DTO
	 * 
	 * @param nome    - nome do utilizador
	 * @param elo     - elo do utilizador
	 * @param discord - username do discord
	 */
	public UtilizadorDTO(String nome, double elo, String discord) {
		this.nome = nome;
		this.elo = elo;
		this.discordUsername = discord;
	}
	
	/**
	 * get nome do utilizador
	 * 
	 * @return {@code nome}
	 */
	public String getNome() {
		return nome;
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
	 * get nome discord do utilizador
	 * 
	 * @return {@code discordUsername}
	 */
	public String getDiscordUsername() {
		return discordUsername;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discordUsername == null) ? 0 : discordUsername.hashCode());
		long temp;
		temp = Double.doubleToLongBits(elo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof UtilizadorDTO))
			return false;
		UtilizadorDTO other = (UtilizadorDTO) obj;
		if (discordUsername == null) {
			if (other.discordUsername != null)
				return false;
		} else if (!discordUsername.equals(other.discordUsername))
			return false;
		if (Double.doubleToLongBits(elo) != Double.doubleToLongBits(other.elo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
