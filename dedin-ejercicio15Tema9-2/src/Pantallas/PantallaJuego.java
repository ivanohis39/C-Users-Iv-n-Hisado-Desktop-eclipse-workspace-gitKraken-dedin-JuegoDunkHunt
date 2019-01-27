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
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import base.IPantalla;
import base.PanelJuego;
import base.Sprite;

public class PantallaJuego implements IPantalla {

	PanelJuego panelJuego;
	// ancho y alto del misil
	private static final int anchoMisil = 10;
	private static final int altoMisil = 25;
	// velocidades en los ejes X e Y del misil
	private static final int velocidaMisilX = 0;
	private static final int velocidadMisilY = -20;
	// color del disparo del misil
	private Color[] disparoColor = { Color.RED, Color.YELLOW, Color.PINK, Color.CYAN, Color.GREEN };

	private ArrayList<Sprite> meteoro = new ArrayList<>();

	int meteoritos = 3;

	private BufferedImage imgSpace = null, imgMeteoro = null, imgFinVictoria = null, imgFinDerrota = null,
			imgExplota = null;

	private Image imagenReescalada;
	private Image imagenReescaladaVictoria;
	private Image imagenReescaladaDerrota;

	int milesimas = 0, segundos = 0, minutos = 0;
	int explosiones = 0;

	private Sprite nave, naveIZQ, naveDER;
	private Sprite misil, explotado;

	// inicio pnatalla
	Color colorLetras = Color.YELLOW;
	int contColorframes = 0;
	static final int CAMBIO_COLOR_INICIO = 5;

	boolean pintarNaveIZQ = false;
	boolean pintarNaveDER = false;

	boolean jugando = true;
	boolean derrota = false;
	boolean maquinon = false;

	public PantallaJuego(PanelJuego panelJuego) {
		super();
		this.panelJuego = panelJuego;
	}

	@Override
	public void inicializarPantalla() {
		// aniadimos la fakin nave

		lanzaMeteoritos();

		try {
			// para poner la imagende fondo
			imgSpace = ImageIO.read(new File("Imagenes/space.jpg"));
			imgFinVictoria = ImageIO.read(new File("Imagenes/victoria.jpeg"));
			imgFinDerrota = ImageIO.read(new File("Imagenes/derrota.jpeg"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		nave = new Sprite(50, 50, 300, 200, "Imagenes/nave.png");
		naveIZQ = new Sprite(50, 50, 300, 200, "Imagenes/nave_izq.png");
		naveDER = new Sprite(50, 50, 300, 200, "Imagenes/nave_der.png");

		reescalarImagen();
		reescalarImagenVictoria();
		reescalarImagenDerrota();

	}

	public void lanzaMeteoritos() {
		Random rd = new Random();

		for (int i = 0; i < meteoritos; i++) {
			meteoro.add(new Sprite(80, 80, 10, 10, rd.nextInt(15) + 1, rd.nextInt(15) + 1,
					"Imagenes/asteroides/asteroide_0.png"));

		}
	}

	@Override
	public void pintarPantalla(Graphics g) {
		if (jugando) {
			rellenarFondo(g);
			// pintamos los cuadrados
			for (int i = 0; i < meteoro.size(); i++) {
				meteoro.get(i).pintar(g);

			}

			// pintamos the favolous nave
			if (misil != null) {
				misil.pintar(g);
			}

			nave.pintar(g);

		} else {
			rellenarFondo(g);
		}
	}

	public void pintarCrono(Graphics grafico) {

		// pintamos el cronometro en la pantalla
		if (minutos <= 0) {
			if (segundos < 10) {
				grafico.drawString("0" + segundos + ":" + milesimas, 50, 50);
			} else {
				grafico.drawString(segundos + ":" + milesimas, 50, 50);
			}
		} else {
			if (segundos < 10) {
				grafico.drawString(minutos + ":0" + segundos + ":" + milesimas, 50, 50);
			} else {
				grafico.drawString(minutos + ":" + segundos + ":" + milesimas, 50, 50);
			}
		}
	}

	public void rellenarFondo(Graphics grafico) {
		if (jugando) {
			grafico.drawImage(imagenReescalada, 0, 0, null);
			Font font = new Font("Arial", Font.BOLD, 40);
			grafico.setFont(font);
			grafico.setColor(Color.YELLOW);

			pintarCrono(grafico);

			// pintamos el numero de meteoros explotados en la pantalla
			grafico.drawString("Meteoros explotados: " + explosiones, panelJuego.getWidth() - 450, 50);
		} else if (!derrota) {
			grafico.drawImage(imagenReescaladaVictoria, 0, 0, null);

			Font font = new Font("Arial", Font.BOLD, 40);
			grafico.setFont(font);
			grafico.setColor(Color.YELLOW);
			grafico.drawString("ERES UN ANIMAL! ENHORABUENA!", panelJuego.getWidth() / 2 - 350,
					panelJuego.getHeight() / 2 - 150);

			grafico.drawString("HAZ CLIC PARA VOLVER A JUGAR", panelJuego.getWidth() / 2 - 350,
					panelJuego.getHeight() / 2 + 100);

		} else {
			grafico.drawImage(imagenReescaladaDerrota, 0, 0, null);

			Font font = new Font("Arial", Font.BOLD, 40);
			grafico.setFont(font);
			grafico.setColor(Color.YELLOW);
			grafico.drawString("ERES UN AUTENTICO PAQUETE", panelJuego.getWidth() / 2 - 350,
					panelJuego.getHeight() / 2 - 180);
			font = new Font("Arial", Font.BOLD, 80);
			grafico.setFont(font);
			grafico.drawString("INUUUTIL!", panelJuego.getWidth() / 2 - 150, panelJuego.getHeight() / 2 + 20);
			font = new Font("Arial", Font.BOLD, 30);
			grafico.setFont(font);
			grafico.drawString("HAZ CLIC PARA INTENTARLO DE NUEVO!", panelJuego.getWidth() / 2 - 350,
					panelJuego.getHeight() / 2 + 120);

		}

	}

	public void cronometro() {
		try {
			Thread.sleep(1);
			milesimas += 3;
			if (milesimas >= 100) {
				segundos++;
				milesimas = 0;
			}
			if (segundos == 60) {
				minutos++;
				segundos = 0;
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ejecutarFrame() {

		comprobarColision();
		if (jugando) {
			cronometro();
			moverSprites();
		}
	}

	public void moverSprites() {
		// movemos los meteoros por la pantalla
		for (int i = 0; i < meteoro.size(); i++) {
			meteoro.get(i).mover(panelJuego.getWidth(), panelJuego.getHeight());

		}
		// disparamos el misil
		if (misil != null) {
			misil.disparar();
			if (misil.getAlto() + misil.getPosY() <= 0) {
				misil = null;
			}
		}

	}

	public void comprobarColision() {

		for (int i = 0; i < meteoro.size() && misil != null; i++) {
			// empieza en i+1 porque no lo queremos comprobar conmigo mismo
			if (meteoro.get(i).colisionan(misil)) {

				explotado = (new Sprite(40, 40, meteoro.get(i).getPosX(), meteoro.get(i).getPosY(), 0, 0, "caca.png"));

				meteoro.add(explotado);
				meteoro.remove(i);

				misil = null;
				explosiones++;
				if (explosiones == meteoritos) {
					jugando = false;
				}
			}
		}
		for (int i = 0; i < meteoro.size(); i++) {
			if ((meteoro.get(i).colisionan(nave))) {
				derrota = true;
				jugando = false;
			}
		}
	}

	@Override
	public void moverRaton(MouseEvent e) {
		nave.setPosX(e.getX() - nave.getAncho() / 2);
		nave.setPosY(e.getY() - nave.getAlto() / 2);

		naveIZQ.setPosX(e.getX() - naveIZQ.getAncho() / 2);
		naveIZQ.setPosY(e.getY() - naveIZQ.getAlto() / 2);

		naveDER.setPosX(e.getX() - naveDER.getAncho() / 2);
		naveDER.setPosY(e.getY() - naveDER.getAlto() / 2);

	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		Random rd = new Random();

		if (misil == null) {
			misil = new Sprite(anchoMisil, altoMisil, e.getX() - anchoMisil / 2, e.getY() - altoMisil / 2,
					velocidaMisilX, velocidadMisilY, null);
			misil.setColor(disparoColor[rd.nextInt(5)]);
		}

		if (!jugando) {
			PantallaJuego pantallaJuego = new PantallaJuego(panelJuego);
			pantallaJuego.inicializarPantalla();
			panelJuego.setPantallaActual(pantallaJuego);
		}
	}

	public void reescalarImagen() {

		imagenReescalada = imgSpace.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				Image.SCALE_SMOOTH);

	}

	public void reescalarImagenVictoria() {
		imagenReescaladaVictoria = imgFinVictoria.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				Image.SCALE_SMOOTH);
		maquinon = true;
	}

	public void reescalarImagenDerrota() {
		imagenReescaladaDerrota = imgFinDerrota.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				Image.SCALE_SMOOTH);
		maquinon = false;
	}

	@Override
	public void redimensionarPantalla(ComponentEvent e) {
		if (jugando) {
			reescalarImagen();
		} else {

			reescalarImagenVictoria();
		}
		if (derrota) {

			reescalarImagenDerrota();
		}
	}

}
