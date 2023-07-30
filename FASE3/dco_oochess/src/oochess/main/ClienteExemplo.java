package oochess.main;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import oochess.app.OOChess;
import oochess.app.facade.Sessao;
import oochess.app.facade.handlers.DesafiarHandler;
import oochess.app.facade.handlers.ProcessarDesafiosHandler;
import oochess.app.facade.handlers.RegistarResultadoHandler;
import oochess.app.facade.handlers.RegistarUtilizadorHandler;
import oochess.app.facade.torneio.Desafio;
import oochess.app.facade.utilizador.UtilizadorDTO;

public class ClienteExemplo {
	
	private static String codigoDaPartida;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		OOChess p = new OOChess();
		
		RegistarUtilizadorHandler regHandler = p.getRegistarUtilizadorHandler();
		
		regHandler.registarUtilizador("Felismina", "hortadafcul", "fel1sgamer");
		regHandler.registarUtilizador("Silvino", "bardoc2", "s1lv1n0");
		regHandler.registarUtilizador("Maribel", "torredotombo", "SkubaPatr0l");
		
			
		
		
		// SSD - UC5
		Optional<Sessao> talvezSessao = p.autenticar("Silvino", "bardoc2");
		talvezSessao.ifPresent( (Sessao s) -> {
			
			DesafiarHandler desh = s.getDesafioParaPartidaHandler();
			
			desh.indicaTorneio("Torneio Xadrez da CADI");
			
			List<UtilizadorDTO> jogadoresEElos = desh.indicaDeltaElo(50);
			
			desh.indicaJogador("Maribel");
			codigoDaPartida = desh.indicaDetalhes(LocalDateTime.now().plusDays(1), "Amanhã vou finalmente derrotar-te!");
			
		});
		
		// SSD - UC6

		talvezSessao.ifPresent( (Sessao s) -> {
			RegistarResultadoHandler rh = s.getRegistarResultadoDePartida();
			rh.indicaPartidaEspontanea();
			rh.indicaDetalhes("Felismina", LocalDateTime.now());
			double novoEloDoSilvino = rh.indicarResultado("DERROTA");
			System.out.println("[NovoElo] Silvino: " + novoEloDoSilvino);
			
		});
		
		// SSD - UC7
		Optional<Sessao> talvezOutraSessao = p.autenticar("Maribel", "torredotombo");
		talvezOutraSessao.ifPresent( (Sessao s) -> {
			ProcessarDesafiosHandler pdh = s.getProcessarDesafios();
			boolean disponivel = true; 
			for (Desafio o : pdh.consultarDesafiosPendentes()) {
				pdh.respondeADesafio(o.getCodigo(), disponivel);
				if (!disponivel) {
					pdh.indicaNovaData(LocalDateTime.now().plusDays(2));
				}				
				disponivel = !disponivel;
			}
		});
		
		// SSD - UC6 (alt)
		
		talvezOutraSessao.ifPresent( (Sessao s) -> {
			RegistarResultadoHandler rh = s.getRegistarResultadoDePartida();
			rh.indicaDesafio(codigoDaPartida);
			double novoEloDaMaribel = rh.indicarResultado("VITORIA"); // Poderia ser também EMPATE
			System.out.println("[NovoElo] Maribel: " + novoEloDaMaribel);
			
		});
	}
}
		