package source;

import javax.swing.JButton;

/** Classe com o menu Principal*/

public class MenuPrincipal {
	private int largura; // 	largura da janela
	private int altura; // 	altura da janela
	private JButton btnStart;
	private int posButtonX;
	private int posButtonY;
	private int largButton;
	private int altButton;
	private int gameState;
	private boolean gameWin = false;
	private boolean gameOver = false;
	
	public MenuPrincipal(int largura, int altura, int gameState){
		this.largura = largura;
		this.altura = altura;
		btnStart = new JButton("Clique");
		btnStart.setBounds(0, 0, 200, 200);
		posButtonX = 700;
		posButtonY = 450;
		largButton = 200;
		altButton = 80;
	}
	
	public void desenhoMain(Tela tela) {
		// Fundo da tela
		tela.retangulo(0, 0, largura, altura, 250,250,250);
		// Texto
		tela.texto("Batalha Naval", 400, 250, 50, 0, 0, 0);
		// Botao de Start
		tela.imagem("imagens/MenuPrincipal/button.png",posButtonX, posButtonY, largButton, altButton, 0);
		tela.texto("Comecar", 710, 500, 25, 250,250,250);
		// Caso GameWin
		
	}
	
	public void desenhoGameWin(Tela tela, Score pontuacao) {
		desenhoMain(tela);
		tela.texto("Fim de Jogo, Voc� Ganhou: ", 400, 350, 25, 250, 0, 0);
		tela.texto("Pontos: "+String.valueOf(pontuacao.getPontos()), 400, 300, 15, 0, 0, 0);
		gameWin = true;
	}
	
	public void desenhoGameOver(Tela tela) {
		desenhoMain(tela);
		tela.texto("Fim de Jogo, Voc� Perdeu: ", 400, 350, 25, 250, 0, 0);
		gameWin = true;
	}
	
	public void setClick(int x, int y, BatalhaNaval jogo) throws InterruptedException {
		// verifica posicao de click
		if(x >= posButtonX && x <= posButtonX + largButton && y >= posButtonY && y <= posButtonY + altButton) {
			// inicio de jogo
			if (gameWin == false && gameOver == false) {
				jogo.setGameState(1);
			}else {
				//Reinicio
				jogo.setGameState(1);// chama o desenho do menu principal para reinicio
			}
			
		}
	}
}
