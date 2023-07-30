package oochess.app.discordintegration;

/**
 * @author Alexandre Rodrigues, 54472
 */

import com.chavetasfechadas.JDAMock;
import com.chavetasfechadas.JDAMockBuilder;

public class JDAAdapter implements DiscordAdapter {
	
	private JDAMock discord;

	/**
	 * construtor do adapter
	 * 
	 * @param token - discord token
	 */
	public JDAAdapter(String token) {
		this.discord = new JDAMockBuilder()
				.createDefault(token)
				.disableCache(true)
				.setCompression(true)
				.setActivity("Playing chess")
				.build();
	}

	/**
	 *  envia mensagem
	 *  
	 *  @param discordUser - nome de utilizador no discord
	 *  @param msg         - mensagem a ser enviada
	 */
	@Override
	public void sendMensagem(String discordUser, String msg) {
		discord.sendMessage(discordUser, msg);
	}

}
