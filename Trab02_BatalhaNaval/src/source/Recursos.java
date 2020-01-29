package source;

/** Classe que gerencia a quantidade de ataques ainda disponiveis no jogo*/

public class Recursos {
	private int fundos;
	private Tabuleiro tabuleiro;
	private Embarcacao embarcacoes;
	
	public Recursos(Tabuleiro tabuleiro, Embarcacao embarcacoes) {
		this.tabuleiro = tabuleiro;
		this.embarcacoes = embarcacoes;
		// quantidade de fundos:
		// formula escolhida : quantidade de blocos contendo partes de embarcacoes mais diferenca entre 
		// a quantidade de blocos totais do tabuleiro e quantidade de blocos contendo partes de embarcacoes
		// divido por tres
		int embarc[] = (embarcacoes.getEmbarcacoes());
		int qtdEmbarc = 0;
		for(int i = 0; i < 5; i++) {
			qtdEmbarc += embarc[i] * (i+1); // i+1 devido a maneira de armazenamento
		}
		int blocosTot = (tabuleiro.getLarguraTab() * tabuleiro.getAlturaTab());
		fundos = (int)(qtdEmbarc + (blocosTot - qtdEmbarc)/3);
		
		// Quantidade de habilidades depende do tamanho do tabuleiro
		
	}
	
	public int getFundos() {
		return fundos;
	}
	
	public void setFundos(int fundos) {
		this.fundos = fundos;
	}
}
