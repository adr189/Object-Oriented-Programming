package oochess.app.discordintegration;

/**
 * @author Alexandre Rodrigues, 54472
 */

import net.padroesfactory.Discord4JMock;

public class Discord4JAdapter implements DiscordAdapter {

	private Discord4JMock discord;
	
	/**
	 * construtor do adapter
	 * 
	 * @param token - discord token
	 */
	public Discord4JAdapter(String token) {
		this.discord = new Discord4JMock(token);
	}
	
	/**
	 *  envia mensagem
	 *  
	 *  @param discordUser - nome de utilizador no discord
	 *  @param msg         - mensagem a ser enviada
	 */
	@Override
	public void sendMensagem(String discordUser, String msg) {
		discord.getChannel(discordUser).sendMessage(msg);		
	}

}
