package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

import entidades.EntidadPadre;
import principal.PanelJuego;

/**
 * Clase Objeto_puerta, hija de EntidadPadre.
 * La puerta tiene una imagen asociada que se utiliza en el mapa y tiene la capacidad de tener colisiones con otras entidades.
 * 
 * @author Roberto
 * @version 1.0
 */
public class Objeto_puerta extends EntidadPadre{
	
	/**
	 * Constructor de la clase Objeto_puerta.
	 * Inicializa las características de la puerta, como su nombre, su imagen asociada, su tamaño y su capacidad de colisión.
	 * 
	 * @param pj El panel del juego para que funcione
	 */
	public Objeto_puerta(PanelJuego pj) {
		
		super(pj);
		
		nombre = "puerta";
		abajo1 = setup("/objetos/obj_puerta", pj.tamanioBaldosa, pj.tamanioBaldosa);
		
		colision = true;
	}
	
}
