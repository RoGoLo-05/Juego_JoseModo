package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

import entidades.EntidadPadre;
import principal.PanelJuego;

/**
 * Clase Objeto_corazon, hija de EntidadPadre.
 * El corazón puede estar lleno o vacío, y su apariencia cambia en función de su estado.
 * 
 * @author Roberto
 * @version 1.0
 */
public class Objeto_corazon extends EntidadPadre{
		
	/**
	 * Constructor de la clase Objeto_corazon.
	 * Inicializa las características del corazón, como su nombre, las imágenes asociadas y su tamaño.
	 * 
	 * @param pj El panel del juego para que funcione
	 */
	public Objeto_corazon(PanelJuego pj) {
		
		super(pj);
		
		nombre = "corazon";
		image = setup("/objetos/corazon_lleno", pj.tamanioBaldosa, pj.tamanioBaldosa);
		image2 = setup("/objetos/corazon_vacio", pj.tamanioBaldosa, pj.tamanioBaldosa);
		
		
	}
}
