package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

import entidades.EntidadPadre;
import principal.PanelJuego;

/**
 * Clase Objeto_ballesta, hija de EntidadPadre.
 * La ballesta es un objeto que el jugador puede recoger y usar en el juego.
 * 
 * @author Roberto
 * @version 1.0
 */
public class Objeto_ballesta extends EntidadPadre{
	
	/**
	 * Constructor de la clase Objeto_ballesta.
	 * Inicializa las características de la ballesta, como su nombre, la imagen asociada y su tamaño.
	 *  
	 * @param pj El panel del juego para que funcione
	 */
	public Objeto_ballesta(PanelJuego pj) {
		
		super(pj);
		
		nombre = "ballesta";
		abajo1 = setup("/objetos/obj_ballesta", pj.tamanioBaldosa, pj.tamanioBaldosa);
		
	}
	
}
