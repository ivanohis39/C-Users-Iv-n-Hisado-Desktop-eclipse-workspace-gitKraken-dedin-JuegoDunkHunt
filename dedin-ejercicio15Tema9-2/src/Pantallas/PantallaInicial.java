package Pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import base.IPantalla;
import base.PanelJuego;
import base.Sprite;

public class PantallaInicial implements IPantalla {

	PanelJuego panelJuego;

	BufferedImage imgSpaceInicial;
	Image imagenReescaladaInicial;

	Color colorLetras = Color.YELLOW;
	int contColorframes = 0;
	static final int CAMBIO_COLOR_INICIO = 5;

	public PantallaInicial(PanelJuego panelJuego) {
		super();
		this.panelJuego = panelJuego;
	}

	@Override
	public void inicializarPantalla() {
		try {
			// para poner la imagende fondo
			imgSpaceInicial = ImageIO.read(new File("Imagenes/galaxia.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void pintarPantalla(Graphics g) {
		g.drawImage(imagenReescaladaInicial, 0, 0, null);
		Font font = new Font("Arial", Font.BOLD, 40);
		g.setFont(font);
		g.setColor(colorLetras);
		g.drawString("METEOROS: CLIC PARA EMPEZAR", panelJuego.getWidth() / 2 - 350, panelJuego.getHeight() / 2 - 15);
	}

	@Override
	public void ejecutarFrame() {
		contColorframes++;
		if (contColorframes % CAMBIO_COLOR_INICIO == 0) {
			if (colorLetras.equals(Color.YELLOW)) {
				colorLetras = Color.RED;
			} else {
				colorLetras = Color.YELLOW;
			}
		}
	}

	@Override
	public void moverRaton(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		// jugando = true;

		PantallaJuego pantallaJuego = new PantallaJuego(panelJuego);
		pantallaJuego.inicializarPantalla();
		panelJuego.setPantallaActual(pantallaJuego);
	}

	@Override
	public void redimensionarPantalla(ComponentEvent e) {
		imagenReescaladaInicial = imgSpaceInicial.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				Image.SCALE_SMOOTH);
	}

}
