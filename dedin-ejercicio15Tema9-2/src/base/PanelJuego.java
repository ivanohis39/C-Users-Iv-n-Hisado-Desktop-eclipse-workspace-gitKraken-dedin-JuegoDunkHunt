package base;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Pantallas.PantallaInicial;

public class PanelJuego extends JPanel implements Runnable, MouseListener, ComponentListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IPantalla pantallaActual;

	public PanelJuego() {
		this.addMouseListener(this);

		this.addComponentListener(this);
		this.addMouseMotionListener(this);

	
		new Thread(this).start();
		
		pantallaActual = new PantallaInicial(this);
		pantallaActual.inicializarPantalla();


	}

	@Override
	public void paintComponent(Graphics g) {
		pantallaActual.pintarPantalla(g);
	}

	@Override
	public void run() {
		while (true) {
			this.repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pantallaActual.ejecutarFrame();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		pantallaActual.pulsarRaton(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		// hacemos que la nave se mueva junto al raton
		// y a la par reedimensionamos la nave
		pantallaActual.moverRaton(e);

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {

	}

	@Override
	public void componentResized(ComponentEvent e) {
		// rescalamos continuamente con el refresco la imagen del fondo
		pantallaActual.redimensionarPantalla(e);

	}

	@Override
	public void componentShown(ComponentEvent arg0) {

	}

	public IPantalla getPantallaActual() {
		return pantallaActual;
	}

	public void setPantallaActual(IPantalla pantallaActual) {
		this.pantallaActual = pantallaActual;
	}

}
