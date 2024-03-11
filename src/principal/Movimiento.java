package principal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entidades.Jugador;

/**
 * La clase Movimiento maneja las acciones del teclado para controlar el movimiento del jugador en el juego.
 */
public class Movimiento implements KeyListener {
	
	PanelJuego pj;

	public boolean arriba, izquierda, derecha, abajo, enter;
	
	/**
	 * Constructor de la clase Movimiento.
	 * 
	 * @param pj El Panel del juego que hace que funcione
	 */
	public Movimiento(PanelJuego pj) {
		this.pj = pj;
	}

	@Override
	/**
	 * Este método maneja el evento de escribir un carácter. No se usa.
	 * 
	 * @param e El evento del teclado
	 */
	public void keyTyped(KeyEvent e) {
		//No lo voy a usar para nada
	}

	@Override
	/**
	 * Este método maneja el evento de pulsar una tecla.
	 * 
	 * @param e El evento del teclado
	 */
	public void keyPressed(KeyEvent e) { //Cuando presionas la tecla

		int codigo = e.getKeyCode(); //Devuelve el numero de la tecla que presionas
		
		if(pj.estadoJuego == pj.finalJuego) {
			if (codigo == KeyEvent.VK_W) {
				pj.ui.numeroComando--;
				if(pj.ui.numeroComando < 0) {
					pj.ui.numeroComando = 1;
				}
			}
			
			if (codigo == KeyEvent.VK_S) {
				pj.ui.numeroComando++;
				if(pj.ui.numeroComando > 1) {
					pj.ui.numeroComando = 0;
				}
			}
			if (codigo == KeyEvent.VK_ENTER) {
				if(pj.ui.numeroComando == 0) {
					pj.reintentar();
					pj.estadoJuego = pj.jugarJuego;		
					
				}
				if(pj.ui.numeroComando == 1) {
					System.exit(0);
				}
			}
			
		}
		
		if(pj.estadoJuego == pj.jugarJuego) {
			if (codigo == KeyEvent.VK_W) {
				arriba = true;
			}
			
			if (codigo == KeyEvent.VK_S) {
				abajo = true;
			}
			
			if (codigo == KeyEvent.VK_A) {
				izquierda = true;
			}

			if (codigo == KeyEvent.VK_D) {
				derecha = true;
			}
		
			if (codigo == KeyEvent.VK_ENTER) {
				
				if (pj.jugador.hasBallesta == 1) {
					enter = true;
				} else if (pj.jugador.hasBallesta == 0) {
					System.out.println("No puedes atacar, no tienes ballesta");
				}
			}
			
			if (codigo == KeyEvent.VK_P) {
				pj.estadoJuego = pj.pausarJuego;
			}
		} else if(pj.estadoJuego == pj.pausarJuego) {
			if (codigo == KeyEvent.VK_P) {
				pj.estadoJuego = pj.jugarJuego;
			}
		}
		
		
		
	}

	@Override
	/**
	 * Este método maneja el evento de dejar de pulsar una tecla.
	 * 
	 * @param  e El evento del teclado
	 */
	public void keyReleased(KeyEvent e) { //Cuando dejas de presionar la tecla

		int codigo = e.getKeyCode();
		
		if (codigo == KeyEvent.VK_W) {
			arriba = false;
		}
		
		if (codigo == KeyEvent.VK_S) {
			abajo = false;
		}
		
		if (codigo == KeyEvent.VK_A) {
			izquierda = false;
		}

		if (codigo == KeyEvent.VK_D) {
			derecha = false;
		}
		
		if (codigo == KeyEvent.VK_ENTER) {
			enter = false;
		}
		
	}


}