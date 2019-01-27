package base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private BufferedImage buffer;

	private Color color = Color.WHITE;
	// variables de dimension
	private int ancho;
	private int alto;
	// variables de colocacion
	private int posX;
	private int posY;
	// variables de velocidad
	private int velX;
	private int velY;
	// nos creamos un string para poder pasar cualquier imagen
	private String rutaImagen;

	int cont;

	public Sprite() {
		super();
		actualizarBuffer();

	}

	// para los meteoros
	public Sprite(int ancho, int alto, int posX, int posY, String rutaImagen) {
		super();
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.rutaImagen = rutaImagen;
		actualizarBuffer();
	}

	// para la los meteoros
	public Sprite(int ancho, int alto, int posX, int posY, int velX, int velY, String rutaImagen) {
		super();
		this.ancho = ancho;
		this.alto = alto;
		this.posX = posX;
		this.posY = posY;
		this.velX = velX;
		this.velY = velY;
		this.rutaImagen = rutaImagen;
		actualizarBuffer();
	}

	// para el movimineto de los meteoros
	public void mover(int width, int height) {
		// eje X
		if (posX + ancho >= width) {
			// le cogemos el valor absoluto para saber el signo de X
			velX = Math.abs(velX) * -1;
		}
		if (posX < 0) {
			velX = Math.abs(velX);
		}
		// eje Y
		if (posY + alto >= height) {
			// le cogemos el valor absoluto para saber el signo de X
			velY = Math.abs(velY) * -1;
		}
		if (posY < 0) {
			velY = Math.abs(velY);
		}
		posX = posX + velX;
		posY = posY + velY;

	}

	// para saber la posicion del disparo
	public void disparar() {
		posX = posX + velX;
		posY = posY + velY;

	}

	public void elegirNave() {
		posX = posX + velX;
		posY = posY + velY;

	}

	// comprobamos las colisiones de un obejeto con otro
	public boolean colisionan(Sprite otroSprite) {
		// Checkeamos si comparten algún espacio a lo ancho:
		boolean colisionAncho = false;
		if (posX < otroSprite.getPosX()) { // El Sprite actual se encuentra más cerca del eje de las X.
			colisionAncho = posX + ancho >= otroSprite.getPosX();
		} else { // El otro Sprite se encuentra más cerca del eje de las X.
			colisionAncho = otroSprite.getPosX() + otroSprite.getAncho() >= posX;
		}

		// Checkeamos si comparten algún espacio a lo alto:
		boolean colisionAlto = false;
		if (posY < otroSprite.getPosY()) {
			colisionAlto = alto > otroSprite.getPosY() - posY;
		} else {
			colisionAlto = otroSprite.getAlto() > posY - otroSprite.getPosY();
		}

		return colisionAncho && colisionAlto;
	}

	// metodo para actualizar el buffer
	public void actualizarBuffer() {
		cont++;
		if (cont == 16) {
			cont = 0;
		}
		buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.getGraphics();
		// nos creamos un buffer nuevo para poder redimensionar los meteoros y la nave

		try {
			if (!rutaImagen.contains("asteroide")) {
				BufferedImage imgMeteoro = ImageIO.read(new File(rutaImagen));
				g.drawImage(imgMeteoro.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH), 0, 0, null);
			} else {
				BufferedImage imgMeteoro = ImageIO.read(new File("Imagenes/asteroides/asteroide_" + cont + ".png"));
				g.drawImage(imgMeteoro.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH), 0, 0, null);
			}

		} catch (Exception e) {
			g.setColor(color);
			g.fillRect(0, 0, ancho, alto);
			g.dispose();
		}

	}

	// pinta el pantalla los elementos del buffer
	public void pintar(Graphics g) {
		g.drawImage(buffer, posX, posY, null);
		actualizarBuffer();
	}

	public BufferedImage getBuffer() {
		return buffer;
	}

	public void setBuffer(BufferedImage buffer) {
		this.buffer = buffer;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		// volvemos a actualizar el buffer para aniadir el color
		// actualizarBuffer();
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}