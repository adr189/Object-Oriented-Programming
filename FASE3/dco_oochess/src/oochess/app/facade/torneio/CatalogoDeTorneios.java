package oochess.app.facade.torneio;

/**
 * @author Alexandre Rodrigues, 54472
 */

import java.util.HashMap;
import java.util.Map;

public class CatalogoDeTorneios {
	
	private Map<String, Torneio> cat;
	
	/**
	 * construtor protected para impedir a criacao de instancias
	 */
	protected CatalogoDeTorneios() {
		this.cat = new HashMap<>();
	}
	
	private static CatalogoDeTorneios INSTANCE = null;
	
	/**
	 * get instancia do catalogo
	 * 
	 * @return instancia de CatalogoDeTorneios
	 */
	public static CatalogoDeTorneios getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CatalogoDeTorneios();
		}
		return CatalogoDeTorneios.INSTANCE;
	}
	
	/**
	 * adiciona um torneio ao catalogo
	 * 
	 * @param nome - nome do torneio
	 * @ensures se um torneio com o nome dado nao existir e criado e adicionado
	 * um {@code Torneio t} ao catalogo;
	 */
	public void addTorneio(String nome) {
		cat.putIfAbsent(nome, new Torneio(nome));
	}
	
	/**
	 * get um torneio dado um nome
	 * 
	 * @param nome - nome do torneio
	 * @return caso exista return {@code Torneio t}, null caso contrario
	 */
	public Torneio getTorneio(String nome) {
		return cat.get(nome);
	}
	
}
