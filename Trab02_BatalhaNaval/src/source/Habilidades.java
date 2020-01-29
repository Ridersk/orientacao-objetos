package source;

import javax.swing.JOptionPane;

/** Classe para gerenciar as habilidades disponiveis*/

public class Habilidades {
	private Recursos recursos;
	private int quantidade;
	private Tabuleiro tabuleiro;
	private Motor motor;
	private int habilidade = 0; //[0 - desabilitado][1 - habilidade radar 2x2][2- ataque 2x2][3 - ataque linha]
	
	public Habilidades() {
		
	}
	
	public void setHabilidade(int num) {
		this.habilidade = num;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void limpaValor() {
		habilidade = 0;
	}
	
	public int getHabilidade() {
		return habilidade;
	}
}
