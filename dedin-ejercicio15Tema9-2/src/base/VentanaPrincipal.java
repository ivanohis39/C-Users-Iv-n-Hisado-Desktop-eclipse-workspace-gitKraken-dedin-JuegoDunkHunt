package base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author ivan hisado
 */

public class VentanaPrincipal {

	JFrame frame = new JFrame("METEOROS POWER MIX");
	PanelJuego panelJuego;

	public VentanaPrincipal() {
		frame.setBounds(100, 100, 800, 600);
		frame.setLayout(new GridLayout(1, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void inicializarComponentes() {
		panelJuego = new PanelJuego();
		frame.add(panelJuego);

	}

	// inicializa todos los componentes de la ventana principal
	public void inicializar() {
		frame.setVisible(true);
		inicializarComponentes();
	}
}
