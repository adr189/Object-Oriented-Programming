package oochess.app.discordintegration;

/**
 * @author Alexandre Rodrigues, 54472
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Properties;

public class MyDiscordConfig {

	private MyDiscordConfig() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * funcao que retorna a o adapter discord usado caso seja encontrado
	 * 
	 * @return {@code Optional<DiscordAdapter>} caso seja encontrado o adapter,
	 * {@code Optional.empty()} caso contrario 
	 */
	public static Optional<DiscordAdapter> myDiscordConfig() {
		Properties p = new Properties();
		try (FileInputStream file = new FileInputStream("src/default.properties");) {
			p.load(file);
			String klassName = (String) p.get("DISCORD_CLASS");
			String discordToken = (String) p.get("DISCORD_TOKEN");

			Class<?> klass = Class.forName(klassName);
			DiscordAdapter discord = (DiscordAdapter) klass
					.getConstructor(String.class)
					.newInstance(discordToken);

			return Optional.of(discord);		


		}catch (FileNotFoundException e) {
			System.out.println("O ficheiro nao foi encontrado!");
		}catch (IOException e) {
			System.out.println("Problemas a ler o ficheiro!");
		} catch (ClassNotFoundException e) {
			System.out.println("Class nao encontrada");
		} catch (InstantiationException e) {
			System.out.println("Parametros passados ao construtor nao estao corretos");
		} catch (IllegalAccessException e) {
			System.out.println("jar nao tem permições");
		} catch (IllegalArgumentException e) {
			System.out.println("argumento incorreto");
		} catch (InvocationTargetException e) {
			System.out.println("instancia não tem o construtor usado");
		} catch (NoSuchMethodException e) {
			System.out.println("metodo nao existe");
		} catch (SecurityException e) {
			System.out.println("exceção de seguranca");
		}
		return Optional.empty();
	}

}
