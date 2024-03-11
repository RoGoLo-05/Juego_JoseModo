package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

import entidades.EntidadPadre;
import principal.PanelJuego;

/**
 * Clase Objeto_llave, hija de EntidadPadre.
 * La llave tiene una imagen asociada que se utiliza tanto en la interfaz de usuario como en el mapa.
 * 
 * @author Roberto
 * @version 1.0
 */
public class Objeto_llave extends EntidadPadre{
	
	/**
	 * Constructor de la clase Objeto_llave.
	 * Inicializa las características de la llave, como su nombre, su imagen asociada y su tamaño.
	 * 
	 * @param pj El panel del juego para que funcione
	 */
	public Objeto_llave(PanelJuego pj) {
		super(pj);		
		nombre = "llave";
		
		image = setup("/objetos/obj_llave", pj.tamanioBaldosa, pj.tamanioBaldosa); //Para la interfaz de usuario que pone la cantidad que llevas
		abajo1 = setup("/objetos/obj_llave", pj.tamanioBaldosa, pj.tamanioBaldosa); //Las del mapa
		//Le pongo abajo1 o la direccion que sea, ya que no se va a mover como un NPC, entonces da igual, se va a quedar igual.
		
	}
	
}
