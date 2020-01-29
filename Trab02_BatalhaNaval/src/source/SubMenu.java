package source;

/** Classe para a criacao e redesenho dos submenus lateral e superior*/

public class SubMenu {
	private int larg; // 	largura da janela
	private int alt; // 	altura da janela
	private int x;
	private int y;
	private Embarcacao embarcacoes;
	private Recursos recursos; 
	private Habilidades habilidades;
	private LerArquivo arquivo;
	private Score pontuacao;
	
	// posicao e tamanho dos icones do menu
	private int posXIconA = 70;// 	posicao incial em x do primeiro icone
	private int posYIconA = 200;// 	posicao inicial em y do primeiro icone
	private int largIconA = 60;// 	largura do primeiro icone
	private int altIconA = 60;// 	altura do primeiro icone
	
	private int posXIconB = 75;
	private int posYIconB = 300;
	private int largIconB = 50;
	private int altIconB = 64;
	
	private int posXIconC = 70;
	private int posYIconC = 400;
	private int largIconC = 60;
	private int altIconC = 60;
	
	private int posXIconD = 70;
	private int posYIconD = 500;
	private int largIconD = 60;
	private int altIconD = 60;
	
	public static final int ALT_S = 30;// altura fixa do menu superior
	public static final int LARG_L = 200; // largura fixa do menu lateral
	
	public SubMenu(int x, int y, int larg, int alt, Embarcacao embarcacoes, Recursos recursos, Habilidades habilidades, 
			LerArquivo arquivo, Score pontuacao){
		this.x = x;
		this.y = y;
		this.larg = larg;
		this.alt = alt;
		this.embarcacoes = embarcacoes;
		this.recursos = recursos;
		this.habilidades = habilidades;
		this.arquivo = arquivo;
		this.pontuacao = pontuacao;
	}
	public void desenho(Tela tela) {
		//this.tela = tela;
		// menu superior
		tela.retangulo(x, y, larg, ALT_S, 140,23,23);
		//tirulo
		tela.texto("BATALHA NAVAL", x+20, y+20, 20, 230,232,250);
		// menu lateral
		tela.retangulo(x, y + ALT_S, LARG_L, alt-ALT_S, 112,128,144);
		
		// * ELEMENTOS DO MENU SUPERIOR *
		desenhoMenuS(tela);
		
		// *ELEMENTOS DO MENU LATERAL*
		desenhoMenuL(tela);
	}
	
	public void desenhoMenuS(Tela tela) {
		// As posicoes em y variaram pois as imagens tem margens diferentes
		int numEmbarcacoes[] = embarcacoes.getEmbarcacoes();
		tela.texto("Restantes: ", 225, 20, 12, 230, 232, 250);
		tela.texto(String.valueOf(numEmbarcacoes[0]), 320, 20, 12, 230,232, 250);
		tela.imagem("imagens/PatrolBoat/ShipPatrolHull.png", 350,-10, 13, 55, Math.PI/2);//argumento de rotacao em radianos(sentido horario)
		tela.texto(String.valueOf(numEmbarcacoes[1]), 400, 20, 12, 230,232, 250);
		tela.imagem("imagens/Destroyer/ShipDestroyerHull.png", 440,-23, 20, 80, Math.PI/2);
		tela.texto(String.valueOf(numEmbarcacoes[2]), 510, 20, 12, 230,232, 250);
		tela.imagem("imagens/Cruiser/ShipCruiserHull.png", 560,-33, 20, 100, Math.PI/2);
		tela.texto(String.valueOf(numEmbarcacoes[3]), 640, 20, 12, 230,232, 250);
		tela.imagem("imagens/Carrier/ShipCarrierHull.png", 700,-45, 25, 120, Math.PI/2);
		tela.texto(String.valueOf(numEmbarcacoes[4]), 800, 20, 12, 230,232, 250);
		tela.imagem("imagens/Battleship/ShipBattleshipHull.png", 885,-70, 25, 170, Math.PI/2);
	}
	
	public void desenhoMenuL(Tela tela) {
		// Mapa Escolhido:
		tela.texto("Mapa " + String.valueOf(arquivo.getNumArquivo()), 10, 60, 12, 250,250,250);
		// pontos
		tela.texto("Pontos = "+String.valueOf(pontuacao.getPontos()), 10, 80, 12, 250, 250, 250);
		// Fundos de ataque:
		int numFundos = recursos.getFundos();
		tela.texto("Ataque Disponivel: ", 10, 100, 12, 250, 250, 250);
		tela.texto(String.valueOf(numFundos), 145, 100,12, 250, 250, 250);
		// Icones de Habilidade:
		//Descobrir 2x2
		tela.imagem("imagens/Radar/Radar.gif", posXIconA, posYIconA, largIconA, altIconA, 0);
		tela.texto("Descobrir 2x2", posXIconA, posYIconA - 10, 12, 250, 250, 250);
		//tela.retangulo(posXIconA+largIconA, posYIconA, largIconA, altIconA, 250, 250, 250);
		// Ataque 2x2
		tela.imagem("imagens/Plane/PlaneF-35Lightning2.png", posXIconB, posYIconB, largIconB, altIconB, 0);
		tela.texto("Ataque 2x2", posXIconB, posYIconB - 10, 12, 250, 250, 250);
		// Ataque Linha/Coluna
		tela.imagem("imagens/Torpedo/Torpedo.png", posXIconC, posYIconC, largIconC, altIconC, 0);
		tela.texto("Ataque Linha", posXIconC, posYIconC - 10, 12, 250, 250, 250);
		tela.imagem("imagens/Torpedo/Torpedo.png", posXIconD, posYIconD, largIconD, altIconD, Math.PI);
		tela.texto("Ataque Coluna", posXIconD, posYIconD - 10, 12, 250, 250, 250);
	}
	
	public void setClick(int x, int y) {
		// Teste de click no icone
		if ((x >= posXIconA) && (x <= posXIconA + largIconA) && (y >= posYIconA && y <= posYIconA + altIconA)) {
			habilidades.setHabilidade(1);
		}
		else if ((x >= posXIconB) && (x <= posXIconB + largIconB) && (y >= posYIconB && y <= posYIconB + altIconB)) {
			habilidades.setHabilidade(2);
		}
		else if ((x >= posXIconC) && (x <= posXIconC + largIconC) && (y >= posYIconC && y <= posYIconC + altIconC)) {
			habilidades.setHabilidade(3);
		}
		else if ((x >= posXIconD) && (x <= posXIconD + largIconD) && (y >= posYIconD && y <= posYIconD + altIconD)) {
			habilidades.setHabilidade(4);
		}
	}
}
