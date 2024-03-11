package principal;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Clase Herramientas que escala imágenes.
 * 
 * @author Roberto
 * @version 1.0
 */
public class Herramientas { //ESTA CLASE AUMENTA EL RENDIMIENTO DEL JUEGO

	/**
	 * Método para escalar una imagen al tamaño especificado.
	 * 
	 * @param original La imagen original que se va a escalar.
	 * @param width El ancho al que queremos escalar la imagen.
	 * @param height La altura a la que queremos escalar la imagen.
	 * @return La imagen escalada.
	 */
	public BufferedImage imagenEscalada(BufferedImage original, int width, int height) {
		BufferedImage imagenEscalada = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = imagenEscalada.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		return imagenEscalada;
	}
	
}
