package source;

/**	Classe que cria e gerencia o tabuleiro*/

public class Tabuleiro {
	private int alturaTab;// 		quantidade de celulas verticais
	private int larguraTab;// 		quantidade de celulas horizontais
	private int tabela[][];//		tabela com as posicoes das embarcacoes
	private int matrizClick[][];// 	matriz para setar cliques
	private int matrizHabilidadeA[][];
	private int posX; // 			posicao inicial para centralizar em x
	private int posY; // 			posicao inicial para centralizar em y
	private int celula = 40; // 	tamanho de cada celula da tabela (padrao)
	private boolean animation[][];//matriz q verifica se posicao ja fez a animacao
	private int encontrado[][];//matriz com o tipo encontrado para excluir da verificacao embarcacoes ja encontradas
	private int animation_counter = 0;
	private boolean animationClick = false;// ativa a animacao quando clica
	private int fundoBloco[][];//		fundo que fica depois da animacao
	private Embarcacao embarcacoes;
	private int encontradoP[][];//		parametro para desenhar barco vertical ou horizontal
	private Score pontuacao;
	
	private final int ESP = 1;//	espacamento entre uma celula e outra
	
	public Tabuleiro(int larguraTab, int alturaTab, int[][] tabela, Embarcacao embarcacoes, Score pontuacao){
		this.alturaTab = alturaTab;
		this.larguraTab = larguraTab;
		this.tabela = tabela;
		this.embarcacoes = embarcacoes;
		this.matrizClick = new int[this.alturaTab][this.larguraTab];
		this.animation = new boolean[this.alturaTab][this.larguraTab];
		this.fundoBloco = new int[this.alturaTab][this.larguraTab];
		this.encontrado = new int[this.alturaTab][this.larguraTab];
		this.celula = (tamanhoCelula()-1);// -1 para ajuste para baixo no tamaho da celula
		this.posX = (int)((1000 - (larguraTab * celula) - ESP * larguraTab + SubMenu.LARG_L)/2);//(1000:largura janela) tentando centralizar
		this.posY = (int)((645 - (alturaTab * celula) - ESP * alturaTab + SubMenu.ALT_S)/2);//(645:altura janela)
		this.encontradoP = new int[this.alturaTab][this.larguraTab];
		this.pontuacao = pontuacao;
	}
	
	private int tamanhoCelula() {
		// condicao para nao extrapolar o tamanho da celula em x e y
		if(larguraTab > alturaTab && ((1000 - SubMenu.LARG_L)/larguraTab * alturaTab) < (645 - SubMenu.ALT_S)){
			return (int)Math.floor((1000 - SubMenu.LARG_L)/ larguraTab);
		}else {
			return (int)Math.floor((645 - SubMenu.ALT_S)/ alturaTab);
		}
	}
	
	public void desenho(Tela tela) {
		// sequencia da animacao
		// ativa a contagem das animacoes
		if(animation_counter < 23 && animationClick == true) {
			// contagem so inicia quando a animationClick esta ativada
    		animation_counter ++;
		}else{
			animation_counter = 0;
			animationClick = false;
		}
		// construcao da parte grafica
		int countEmbarc = 1;
		for (int i = 0; i < alturaTab; i++) {
			for(int j = 0; j < larguraTab; j++) {
				tela.retangulo(j*celula + posX + j*ESP, i*celula + posY + i*ESP, celula, celula, 230,232,250);
				
				// fundo depois da animacao para tiro acertado
				if(animation[i][j] == true && fundoBloco[i][j] == 1) {
					tela.imagem("imagens/Flags/flagAcerto.png", j*celula + posX + j*ESP, 
							i*celula + posY + i*ESP, celula, celula, 0);
				}
				
				// fundo depois da animacao para tiro na agua
				if(animation[i][j] == true && fundoBloco[i][j] == 2) {
					tela.retangulo(j*celula + posX + j*ESP, i*celula + posY + i*ESP, celula, celula, 50,153,204);
					
				}
				
				// fundo quando embarcacao foi destruida:
				if(animation[i][j] == true && fundoBloco[i][j] == 3) {
					switch (encontrado[i][j]) {
						case 1:
							tela.imagem("imagens/Flags/ShipPatrolHull_Cut1.png", j*celula + posX + j*ESP, 
									i*celula + posY + i*ESP, celula, celula, 0);
							break;
						case 2:
//							if(encontradoP[i][j] == 1) {
//								// desenho horizontal (partes da imagem)
//								countEmbarc ++;
//								tela.imagem("imagens/Flags/ShipDestroyerHull_Cut"+countEmbarc+".png", j*celula + posX + j*ESP, 
//									i*celula + posY + i*ESP, celula, celula, Math.PI/2);
//								
//								
//							}//else if(encontradoP[i][j] == 2) {
////								// desenho vertical (partes da imagem)
////								tela.imagem("imagens/Flags/ShipDestroyerHull_Cut"+String.valueOf(i)+".png", j*celula + posX + j*ESP, 
////										i*celula + posY + i*ESP, celula, celula, 0);
////							}
//							break;
//						default:
//							break;
					}
					
				}
				
				// Muda o desenho do bloco quando clique simples:
					// animacao quando acerta:
				if(matrizClick[i][j] == 1 && animation[i][j] == false && tabela[i][j] != 0) {
					tela.imagem("imagens/explosion/explosion_"+ String.valueOf(animation_counter)+".png", 
							j*celula + posX + j*ESP, i*celula + posY + i*ESP, celula, celula ,0);
					if(animation_counter >= 23) {
						animation[i][j] = true;// essa posicao terminou sua animacao
						// fundo estatico para tiro acertado
						if(fundoBloco[i][j] != 3) {
							fundoBloco[i][j] = 1;
						}
					}
				}
					// animacao quando erra:
				if(matrizClick[i][j] == 1 && animation[i][j] == false && tabela[i][j] == 0) {
					tela.imagem("imagens/water_animation/water_splash_"+ String.valueOf(animation_counter)+".png", 
						j*celula + posX + j*ESP, i*celula + posY + i*ESP, celula, celula ,0);
					if(animation_counter >= 23) {
						animation[i][j] = true;// essa posicao terminou sua animacao
						// fundo estatico para tiro na água
						fundoBloco[i][j] = 2;
					}
				
				}
			}
		}
	}
	
	
	private void testeMatriz(int x, int y) {
		// *teste de acertos com ultima posicao clicada da tabela com as embarcacoes*
		// prototipo de game win
		int auxCount [] = embarcacoes.getEmbarcacoes(); // variavel temp auxiliar para deletar embarcacao encontrada
		// teste inicial de acerto
		if(matrizClick[y][x] == 1 && tabela[y][x] != 0) {
			//pega o tipo a ser testado, para nao remover embarcacoes que ja foram removidas
			int tipoTeste = tabela[y][x];
			// se a embarcacao atingida for de tamanho 1, nao participara dos loops
			if(tabela[y][x] == 1) {
				auxCount[tabela[y][x]-1] -= 1;// auxcount [0 a 4]
				embarcacoes.setEmbarcacoes(auxCount);
				this.encontrado[y][x] = 1;
				// pontos
				int acrescimo = pontuacao.getPontos();
				acrescimo += 2;
				pontuacao.setPontos(acrescimo);
				// fundo para embarcacao destruida
				fundoBloco[y][x] = 3;
			}else {
				// teste na linha ou altura clicada;
				testeLinha(x, y, tipoTeste, auxCount);
				
				//teste na coluna ou largura clicada;
				testeColuna(x, y, tipoTeste, auxCount);
			}
		}
	}
	
	private void testeLinha(int x, int y, int tipoTeste, int auxCount[]) {
		int auxPartLinha[] =new int[5]; // variavel auxiliar para contar partes destruidas da embarcacao, [5] tipos de embarcacoes
		boolean teste1 = false;
		boolean teste2 = false;
		boolean teste3 = false;
		boolean teste4 = false;
		boolean valido = true;
		for (int i = y; i < y+1; i++) {
			for (int j= 0; j < larguraTab; j++) {
				if(matrizClick[i][j] == 1 && tabela[i][j] != 0 && tabela[i][j] == tipoTeste) {
					// condicao para nao ocorrer extrapolacao de matriz -1 ou +indice
					if(j <= 0) {
						teste1 = false;
						teste2 = tabela[i][j] == tabela[i][j+1];
					}else if(j >= larguraTab-1) {
						teste1 = tabela[i][j] == tabela[i][j-1];
						teste2 = false;
					}else {
						teste1 = tabela[i][j] == tabela[i][j-1];
						teste2 = tabela[i][j] == tabela[i][j+1];
					}
					// condicao para desambiguar embarcacoes cruzadas(juntas)
					if(i <= 0) {
						teste3 = false;
						teste4 = tabela[i+1][j] == tabela[i][j];
					}else if(i >= alturaTab-1) {
						teste3 = tabela[i-1][j] == tabela[i][j];
						teste4 = false;
					}else {
						teste3 = tabela[i-1][j] == tabela[i][j];
						teste4 = tabela[i+1][j] == tabela[i][j];
					}
					
					if((!teste1&&teste2 || teste1&!teste2) && (teste3 || teste4)) {
						// teste com operador xor
						// verifica se esta em uma valido do barco ou na outra, e se tiver um mesmo tipo do lado, entao barco invalido
						// se é falso entao essa parte faz parte de outro barco, nao contabiliza como acerto
						valido = false;
						auxPartLinha[tabela[i][j] - 1] = 0;
					}
					
					// teste de destruicao 
					if((teste1 || teste2) && valido && encontrado[i][j] == 0) {
						auxPartLinha[tabela[i][j] - 1] += 1;
						if (auxPartLinha[tabela[i][j] - 1] == tabela[i][j]) {
							// excluindo da verificacao
							for(int k= tabela[i][j], z = j; k > 0; k--, z--) {
								this.encontrado[i][z] = tabela[i][j];
								//fundoBloco[i][z] = 3;
								// parametro para saber posicao da embarcacao (horizontal)
								//encontradoP[i][z] = 1;
							}
							// embarcacao completamente destruida
							auxCount[tabela[i][j]-1] -= 1;// subtrai um barco destruido dos restantes
							embarcacoes.setEmbarcacoes(auxCount);
							// pontuacao
							int acrescimo = pontuacao.getPontos();
							acrescimo += tabela[i][j] * 2;
							pontuacao.setPontos(acrescimo);
							// ativa imagem estatica de embarcacao destruida
							
							auxPartLinha[tabela[i][j]-1] = 0;
						}
					}else {
						// se ocorrer desigualdade entre clique e embarcao, reseta o contador de partes destruidas
						auxPartLinha[tabela[i][j]-1] = 0;
					}
				}
			}
		}
	}
	
	private void testeColuna(int x, int y, int tipoTeste, int auxCount[]) {
		int auxPartColuna[] = new int[5]; // variavel auxiliar para contar partes destruidas da embarcacao, [5] tipos de embarcacoes
		boolean teste1 = false;
		boolean teste2 = false;
		boolean teste3 = false;
		boolean teste4 = false;
		boolean valido = true;
		for (int i = 0; i < alturaTab; i++) {
			for (int j = x; j < x + 1; j++) {
				valido = true;
				if(matrizClick[i][j] == 1 && tabela[i][j] != 0 && tabela[i][j] == tipoTeste) {
					// condicao para nao ocorrer extrapolacao de matriz -1 ou +indice
					if(i <= 0) {
						teste1 = false;
						teste2 = tabela[i][j] == tabela[i+1][j];
					}else if(i >= alturaTab-1) {
						teste1 = tabela[i][j] == tabela[i-1][j];
						teste2 = false;
					}else {
						teste1 = tabela[i][j] == tabela[i-1][j];
						teste2 = tabela[i][j] == tabela[i+1][j];
					}
					// condicao para desambiguar embarcacoes cruzadas(juntas)
					if(j <= 0) {
						teste3 = false;
						teste4 = tabela[i][j] == tabela[i][j+1];
					}else if(j >= larguraTab-1) {
						teste3 = tabela[i][j] == tabela[i][j-1];
						teste4 = false;
					}else {
						teste3 = tabela[i][j] == tabela[i][j-1];
						teste4 = tabela[i][j] == tabela[i][j+1];
					}
					
					if((!teste1&&teste2 || teste1&!teste2) && (teste3 || teste4)) {
						// teste com operador xor
						// se é falso entao essa parte faz parte de outro barco, nao contabiliza como acerto
						valido = false;
						auxPartColuna[tabela[i][j] - 1] = 0;
					}
					// teste de destruicao 
					if((teste1 || teste2) && valido && encontrado[i][j] == 0) {
						auxPartColuna[tabela[i][j] - 1] += 1;
						if (auxPartColuna[tabela[i][j] - 1] == tabela[i][j]) {
							// excluindo da verificacao
							for(int k= tabela[i][j], z = i; k > 0; k--, z--) {
								this.encontrado[z][j] = tabela[i][j];
								//fundoBloco[z][j] = 3;
								// parametro para saber posicao da embarcacao (vertical)
								//encontradoP[z][j] = 2;
							}
							// embarcacao completamente destruida
							auxCount[tabela[i][j]-1] -= 1;// subtrai um barco destruido dos restantes
							embarcacoes.setEmbarcacoes(auxCount);
							// pontuacao
							int acrescimo = pontuacao.getPontos();
							acrescimo += tabela[i][j] * 2;
							pontuacao.setPontos(acrescimo);
							auxPartColuna[tabela[i][j]-1] = 0;
						}
					}else {
						// se ocorrer desigualdade entre clique e embarcacao, reseta o contador de partes destruidas
						auxPartColuna[tabela[i][j]-1] = 0;
					}
				}
			}
		}
	}
	
	public int getLarguraTab() {
		return larguraTab;
	}
	public int getAlturaTab() {
		return alturaTab;
	}
	public int[][] getTabela() {
		// construcao da parte logica
		return tabela;
	}
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	public int getCelula() {
		return celula;
	}
	
	public void setClick(int x, int y, Recursos recursos, Habilidades habilidades) {
		// verifica se essa posicao ja nao foi clicada anteriormente
		// Com clique simples:
		// so aceita clique se a animacao do anterior ja tiver acabado
		int atual = recursos.getFundos();
		if(matrizClick[y][x] != 1 && animationClick == false) {
			// teste habilidade utilizada
			switch (habilidades.getHabilidade()) {
				case 0:// clique simples
					matrizClick[y][x] = 1;
					animationClick = true;
					testeMatriz(x, y);
					atual -= 1;
					break;
				case 2:// ataque 2x2
					// tratamento para nao extrapolar matriz
					if (x >= larguraTab -1 && y >= alturaTab -1) {
						for(int i = y; i >= y - 1; i--) {
							for(int j = x; j >= x - 1; j--) {
								matrizClick[i][j] = 1;
								animationClick = true;
								testeMatriz(j, i);
							}
						}
					} else if (x >= larguraTab -1 && y < alturaTab -1) {
						for(int i = y; i <= y + 1; i++) {
							for(int j = x; j >= x - 1; j--) {
								matrizClick[i][j] = 1;
								animationClick = true;
								testeMatriz(j, i);
							}
						}
					} else if (x < larguraTab -1 && y >= alturaTab -1) {
						for(int i = y; i >= y - 1; i--) {
							for(int j = x; j <= x + 1; j++) {
								matrizClick[i][j] = 1;
								animationClick = true;
								testeMatriz(j, i);
							}
						}
					} else {
						for(int i = y; i <= y + 1; i++) {
							for(int j = x; j <= x + 1; j++) {
								matrizClick[i][j] = 1;
								animationClick = true;
								testeMatriz(j, i);
							}
						}
					}
					atual -= 3;
					break;
				case 3:// ataque linha
					for (int j = 0; j < larguraTab; j++) {
						matrizClick[y][j] = 1;
						animationClick = true;
						testeMatriz(j, y);
					}
					atual -= larguraTab * 0.9;// 0.9 para dar alguma vantagem ao jogador
					break;
				case 4:// ataque coluna
					for (int i = 0; i < alturaTab; i++) {
						matrizClick[i][x] = 1;
						animationClick = true;
						testeMatriz(x, i);
					}
					atual -= alturaTab * 0.9;
					break;
			}
			
			// Descontar fundos pelo click
			recursos.setFundos(atual);
		}
		// reseta habilidade
		habilidades.setHabilidade(0);
	}
	
}
