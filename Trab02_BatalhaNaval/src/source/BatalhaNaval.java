package source;

/**	Orientacao a Objetos - FGA 2018/1
 * 	Turma B - Prof. Renato Sampaio
 * 	Batalha Naval 2.0 (EP2):
 * 	Lucas Maciel Aguiar 170070735
 */

/** Classe Principal do programa em que gerencia, manipula, e faz chamada das outras Classes*/

import java.awt.Canvas;
import java.util.Random;
import java.util.stream.IntStream;

public class BatalhaNaval {
	// atributos
	private int largura; 
	private int altura;
	private static Canvas canvas;
	private static Motor motor;
	private SubMenu subMenu;
	private MenuPrincipal menuPrincipal;
	private LerArquivo arquivo;
	private int larguraTab;
	private int alturaTab;
	private int tabela[][];
	private Embarcacao embarcacoes;
	private static Tabuleiro tabuleiro;
	private Recursos recursos;
	private Habilidades habilidades;
	private Regras regras;
	private Score pontuacao;
	private int gameState = 0; //[0 - Menu Principal][1 - Rodando][2 - GameWin][3 - GameOver]
	
	public static void main(String[] args) {
		// comeco do jogo
		comecar();
	}
	
	private static void comecar() {
		motor = new Motor(new BatalhaNaval(0));
	}
	
	public void reinicioJogo() throws InterruptedException {
		motor.pararLoop(true);
		motor.reinicio(new BatalhaNaval(2));
		pontuacao = new Score();
		gameState = 1;
	}
	
	
	public BatalhaNaval(int gameState) {
		// estado inicial do jogo como parametro
		this.gameState = gameState;
		// leitura do arquivo
		largura = 1000; // tamanho padrao da janela
		altura = 675;
		//Canvas Base
		canvas = new Canvas();
		// Menu Principal
		menuPrincipal = new MenuPrincipal(largura, altura, gameState);
		// * SELECAO DE MAPA *
		arquivo = new LerArquivo();
		larguraTab = arquivo.getLargura();
		alturaTab = arquivo.getAltura();
		tabela = arquivo.getTabela();
		embarcacoes = new Embarcacao(arquivo.getEmbarcacoes());
		//pontuacao
		pontuacao = new Score();
		// instancia do tabuleiro
		tabuleiro = new Tabuleiro(larguraTab, alturaTab, tabela, embarcacoes, pontuacao);
		// definicao de fundos para habilidades de acordo com o tamanho do tabuleiro
		// e quantidade de embarcacoes
		recursos = new Recursos(tabuleiro, embarcacoes);
		// definicao da quantidade de habilidades disponiveis
		habilidades = new Habilidades();
		// instancia do menu (pos x, pos y, largura, altura) com seus componentes
		subMenu = new SubMenu(0,0, largura, altura, embarcacoes, recursos, habilidades, arquivo, pontuacao);
		regras = new Regras(recursos);
	}
	
	//metodo para verificar estado de jogo
	public void estadoJogo() throws InterruptedException {
		
		switch (gameState) {
			case 0:// Menu Principal
				break;
			case 1: // Em jogo
				if(regras.gameState(recursos, embarcacoes, gameState) == 2 || 
					regras.gameState(recursos, embarcacoes, gameState) == 3) {
					// teste de estado de jogo a cada ciclo
					gameState = regras.gameState(recursos, embarcacoes, gameState);
					//reinicioJogo();
				}
				break;
			case 2: // Vitoria
				break;
		case 3:// Derrota
				break;
		}
	}
	
	// metodo para criacao de desenhos do jogo
	public void desenhar(Tela tela) {
		// Verifica estado do jogo para desenhar
		switch (gameState) {
			case 0:
				// Menu Principal
				menuPrincipal.desenhoMain(tela);
				break;
			case 1:
				// jogo rodando
				subMenu.desenho(tela);
				tabuleiro.desenho(tela);
				break;
			case 2:
				// Game Win
				menuPrincipal.desenhoGameWin(tela, pontuacao);
				break;
			case 3:
				// Game Over
				menuPrincipal.desenhoGameOver(tela);
				break;
		}	
	}
	
	
	// Metodos Gets
	public int getAltura() {
		return altura;
	}
	public int getLargura() {
		return largura;
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public Motor getMotor() {
		return motor;
	}
	public MenuPrincipal getMenuPrincipal() {
		return menuPrincipal;
	}
	public SubMenu getMenu() {
		return subMenu;
	}
	public  LerArquivo getLerArquivo () {
		return arquivo;
	}
	public int getLarguraTab() {
		return larguraTab;
	}
	public int getAlturaTab() {
		return alturaTab;
	}
	public int[][] getTabela(){
		return tabela;
	}
	public Embarcacao getEmbarcacoes() {
		return embarcacoes;
	}
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public Recursos getRecursos() {
		return recursos;
	}
	public Regras getRegras() {
		return regras;
	}
	public Habilidades getHabilidades() {
		return habilidades;
	}
	public int getGameState() {
		return gameState;
	}
	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
}
