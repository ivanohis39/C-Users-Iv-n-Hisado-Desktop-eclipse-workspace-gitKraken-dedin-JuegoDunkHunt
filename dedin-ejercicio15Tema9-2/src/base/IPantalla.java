package base;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

public interface IPantalla {

	public void inicializarPantalla();
	public void pintarPantalla(Graphics g);
	public void ejecutarFrame();
	public void moverRaton(MouseEvent e);
	public void pulsarRaton(MouseEvent e);
	public void redimensionarPantalla(ComponentEvent e);
}
