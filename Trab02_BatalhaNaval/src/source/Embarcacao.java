package source;

/** Classe que gerencia a quantidade de embarcacoes restantes no tabuleiro*/

public class Embarcacao {
	private int embarcacoes[];
	//private Tabuleiro tab;
	public Embarcacao(int[] embarcacoes) {
		this.embarcacoes = embarcacoes;
		// atribuicao de quantidades de embarcacoes para cada tipo
		
	}
	public int[] getEmbarcacoes() {
		// Retorna as embarcacoes ainda nao destruidas
		// Metodo usado para quando se quer o vetor inteiro de embarcacoes
		return embarcacoes;
	}
	public void setEmbarcacoes(int[] embarcacoes) {
		// Pega os valores fornecidos por outras classes e coloca no vetor principal de embarcacoes
		// embarcacoes no vetor [0 a 4], ex: embarcao 1 esta no indice 0 (n-1)
		this.embarcacoes = embarcacoes;
	}
}
