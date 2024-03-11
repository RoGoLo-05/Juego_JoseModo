package principal;

import javax.swing.*;

/**
 * Clase HijoJFrame que representa la ventana principal del juego.
 * Extiende JFrame y contiene un PanelJuego dentro de ella.
 * 
 * @author Roberto
 * @version 1.0
 */
public class HijoJFrame extends JFrame {
	
	 /** El panel de juego que se agregará a la ventana */
	PanelJuego panelJuego = new PanelJuego();

	/**
	 * Constructor de la clase HijoJFrame.
	 * Configura la ventana del juego.
	 */
	public HijoJFrame() {
		
		setSize(1152,864); //Tamaño de la ventana
		setResizable(false); //No se puede redimensionar
		setLocationRelativeTo(null); //La ventana se pone en el centro de la pantalla
		setTitle("José Modo"); //Título de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Que deje de ejecutarse cuando salgas del juego
		setVisible(true); //Ventana visible
		
		add(panelJuego);
		
		panelJuego.setupJuego(); //Para que se pongan los objetos en el mapa
		
		panelJuego.comienzoJuego();
		panelJuego.requestFocus(); // Por si acaso, para obligar que el panel del juego tenga el focus para que compile bien. Osea depuración
	}
	
	/**
	 * Método main para iniciar la ventana del juego.
	 * Crea una instancia de HijoJFrame para mostrar la ventana del juego.
	 * 
	 * @param args Los argumentos de la línea de comandos (no utilizados)
	 */
	public static void main(String [] args){ //Creo aquí el main en esta clase 
		
		HijoJFrame ventana = new HijoJFrame(); 
     
    }
}