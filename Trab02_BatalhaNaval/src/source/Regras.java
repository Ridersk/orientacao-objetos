package source;

import javax.swing.JOptionPane;

/** Classe com regras do jogo:
 * 	Condicao de Vitoria;
 * 	Condicao de Derrota;
 * 	Rank
 */
public class Regras {
	private Tabuleiro tabuleiro;
	private Recursos recursos;
	
	public Regras(Recursos recursos) {
		this.recursos = recursos;
	}
	
	public int gameState(Recursos recursos, Embarcacao embarcacoes, int gameState) {
		// verifica se o estado do jogo deve mudar
		int numfundos = recursos.getFundos();
		boolean gameWin = false;
		int teste = 0;
		for(int i = 0 ; i < 5; i++) {
			teste += embarcacoes.getEmbarcacoes()[i];
		}
		if(teste == 0) gameWin = true;
		
		if(gameWin == true) {
			return 2;
		}else if(numfundos <= 0 && gameWin == false) {
			return 3;
		}else {
			return gameState;// retorna 0 ou 1 (menu ou game rodando)
		}
		
	}
	
}
