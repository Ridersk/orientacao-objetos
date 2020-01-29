package source;

/** Classe para criar os desenhos e importar imagens do programa*/

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;

public class Tela extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Graphics2D graphics;
	Tela (Graphics2D graphics){
		this.graphics = graphics;
	}
	//insere retangulo
	public void retangulo(int x, int y, int largura, int altura, int r,int g, int b) {
		// formato (pos x, pos y, larg, alt, cor r, cor g, cor b)
        graphics.setColor(new Color(r,g,b));
        graphics.fillRect(x, y, largura, altura);
    }
	//insere texto
	public void texto(String texto, int x, int y, int tamanho, int r, int g, int b) {
        graphics.setColor(new Color(r, g, b));
        graphics.setFont(new Font("Arial", Font.BOLD, tamanho));
        graphics.drawString(texto, x, y);
    }
	// insere imagem
	public void imagem(String arquivo, int x, int y, int largura, int altura, double rotacao) {
		ImageIcon icone = new ImageIcon(arquivo);
		Image img = icone.getImage();
		AffineTransform trans = graphics.getTransform();
		// funcao da classe padrao Graphics2D, para rotacao de objetos
		// Obs: como padrao ela funicona no sentido anti-horario, entao -rotacao para sentido horario
		graphics.rotate(-rotacao, x + largura/2, y + altura/2);
		graphics.drawImage(img, x, y, largura,altura,null); 
		graphics.setTransform(trans);
	}
}
