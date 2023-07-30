package oochess.app.elostrategies;

/**
 * @author Alexandre Rodrigues, 54472
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class MyEloConfig {

	private MyEloConfig() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Funcao que retorna a estrategia de elo a ser usada
	 * 
	 * @return A estrageia de elo a ser usada
	 */
	public static Elo myEloConfig() {
		Properties p = new Properties();
		try (FileInputStream file = new FileInputStream("src/default.properties");) { 
			p.load(file);
			String klassName = (String) p.get("ELO_STRATEGY");
			Class<?> klass = Class.forName(klassName);
			return (Elo) klass.getConstructor().newInstance();

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

		return new ByOne();
	}
}
