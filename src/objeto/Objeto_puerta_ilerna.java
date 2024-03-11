package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

import entidades.EntidadPadre;
import principal.PanelJuego;

/**
 * Clase Objeto_puerta_ilerna, hija de EntidadPadre.
 * La puerta tiene una imagen asociada que se utiliza en el mapa y tiene la capacidad de tener colisiones con otras entidades.
 * 
 * @author Roberto
 * @version 1.0
 */
public class Objeto_puerta_ilerna extends EntidadPadre{
	
	/**
	 * Constructor de la clase Objeto_puerta_ilerna.
	 * Inicializa las características de la puerta de acceso a Ilerna, como su nombre, su imagen asociada, su tamaño y su capacidad de colisión.
	 * 
	 * @param pj El panel del juego para que funcione
	 */
	public Objeto_puerta_ilerna(PanelJuego pj) {
		
		super(pj);
		
		nombre = "puerta_ilerna";
		abajo1 = setup("/objetos/obj_puerta_ilerna", pj.tamanioBaldosa, pj.tamanioBaldosa);
		
		colision = true;
	}
}
