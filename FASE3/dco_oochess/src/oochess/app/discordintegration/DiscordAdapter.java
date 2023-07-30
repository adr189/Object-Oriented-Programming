package oochess.app.discordintegration;

/**
 * @author Alexandre Rodrigues, 54472
 */

public interface DiscordAdapter {
	/**
	 *  envia mensagem
	 *  
	 *  @param discordUser - nome de utilizador no discord
	 *  @param msg         - mensagem a ser enviada
	 */
	void sendMensagem(String discordUser, String msg);
}
