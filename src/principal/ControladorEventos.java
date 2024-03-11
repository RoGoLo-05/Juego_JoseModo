package principal;

import java.awt.Rectangle;

/**
 * Clase ControladorEventos que maneja el evento especial en el juego (el teletransporte).
 * 
 * @author Roberto
 * @version 1.0
 */
public class ControladorEventos {
	
	PanelJuego pj;
	Rectangle eventRect;
	int eventRectX, eventRectY;
	
	/**
	 * Constructor de la clase ControladorEventos
	 * 
	 * @param pj El panel del juego para que funcione
	 */
	public ControladorEventos(PanelJuego pj) {
		this.pj = pj;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		
		eventRect.width = 2; //El evento ocurre cuando el personaje se toca esos pixeles de la baldosa.
		eventRect.height = 2;
		
		eventRectX = eventRect.x;
		eventRectY = eventRect.y;
	}
	
	/**
	 * Método para comprobar y manejar las teletransportaciones, llamando al método donde se realizan las teletransportaciones.
	 */
	public void comprobarEvento() {
		if(tocarEvento(32,44,"up") == true) { //Entro en el ammu nation
			teletransportar();
		} else if(tocarEvento(16,74,"up") == true) { //Salgo del ammu nation
			teletransportar();
		} else if(tocarEvento(21,21, "up") == true) { //Entro en el ilerna
			teletransportar();
		}
	}
	
	/**
	 * Método para verificar si el jugador toca un evento especial en una posición específica.
	 * 
	 * @param eventCol La columna donde ocurre el evento.
	 * @param eventFil La fila donde ocurre el evento.
	 * @param direccion La dirección en la que se mueve el jugador.
	 * @return true si el jugador toca el evento, false en caso contrario.
	 */
	public boolean tocarEvento(int eventCol, int eventFil, String direccion) {
		
		boolean tocar = false;
		
		pj.jugador.areaSolida.x = pj.jugador.mundoX + pj.jugador.areaSolida.x;
		pj.jugador.areaSolida.y = pj.jugador.mundoY + pj.jugador.areaSolida.y;
		eventRect.x = eventCol*pj.tamanioBaldosa + eventRect.x;
		eventRect.y = eventFil*pj.tamanioBaldosa + eventRect.y;
		
		if (pj.jugador.areaSolida.intersects(eventRect)) {
			if(pj.jugador.direccion.contentEquals(direccion) || direccion.contentEquals("up")) {
				tocar = true;
			}
		}
		
		pj.jugador.areaSolida.x = pj.jugador.areaSolidaX;
		pj.jugador.areaSolida.y = pj.jugador.areaSolidaY;
		eventRect.x = eventRectX;
		eventRect.y = eventRectY;
				
		return tocar;
		
	}
	
	/**
	 * Método para teletransportar al jugador a una nueva posición según el evento tocado.
	 */
	public void teletransportar() {
		System.out.println("Teletransportacion!");
		
		if(tocarEvento(32,44,"up") == true) {
			pj.jugador.mundoX = pj.tamanioBaldosa*16;
			pj.jugador.mundoY = pj.tamanioBaldosa*78;
		} else if(tocarEvento(16,74,"up") == true) {
			pj.jugador.mundoX = pj.tamanioBaldosa*35;
			pj.jugador.mundoY = pj.tamanioBaldosa*44;
		} else if(tocarEvento(21,21,"up") == true) {
			pj.jugador.mundoX = pj.tamanioBaldosa*35;
			pj.jugador.mundoY = pj.tamanioBaldosa*78;
		}
		
	}
	
}
