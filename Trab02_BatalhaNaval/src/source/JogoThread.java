package source;

/** Classe que opera a looping (trhread) do prograa*/

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class JogoThread extends Thread{
	//private Canvas canvas;
	//private Tela desenho;
	private BatalhaNaval jogo;
	BufferStrategy strategy;
	private boolean running = true;
	JogoThread(Canvas canvas, BufferStrategy strategy, BatalhaNaval jogo){
		//this.canvas = canvas;
		this.strategy = strategy;
		this.jogo = jogo;
	}
	@ Override
	public void run() {
		while(!Thread.interrupted()) {
			try {
				// processos de looping
				sleep(33); // 30 fps
				Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
				g.setColor(Color.black);
				g.fillRect(0,0,jogo.getLargura(),jogo.getAltura()); // criar fundo preto
				jogo.desenhar(new Tela(g));
				jogo.estadoJogo();
				strategy.show();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
