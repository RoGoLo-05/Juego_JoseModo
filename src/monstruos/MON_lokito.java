package monstruos;

import java.util.Random;

import entidades.EntidadPadre;
import principal.PanelJuego;

/**
 * Clase MON_lokito, hija de EntidadPadre.
 * Esta clase representa a un monstruo llamado Lokito en el juego.
 * Lokito es un monstruo que se mueve aleatoriamente por el Ilerna y puede recibir al daño.
 * 
 * @author Roberto
 * @version 1.0
 */
public class MON_lokito extends EntidadPadre{

	PanelJuego pj;
	
	/**
	 * Constructor de la clase MON_lokito.
	 * Inicializa las características de Lokito, como su tipo, nombre, velocidad y vida máxima.
	 * También llama al método que carga las imágenes necesarias para la animación de Lokito.
	 * 
	 * @param pj El panel del juego para que funcione
	 */
	public MON_lokito(PanelJuego pj) {
		super(pj);
		
		this.pj=pj;
		
		tipo = 2;
		nombre = "lokito_monstruo";
		velocidad = 2;
		maxVida = 4;
		vida = maxVida;
		
		getImage();
	}
	
	/**
	 * Se cargan las imágenes para las diferentes direcciones en las que puede moverse Lokito y se define su tamaño.
	 */
	public void getImage() {
		abajo1 = setup("/enemigos/lokito_abajo1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		abajo2 = setup("/enemigos/lokito_abajo2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		abajo3 = setup("/enemigos/lokito_abajo3", pj.tamanioBaldosa, pj.tamanioBaldosa);
		arriba1 = setup("/enemigos/lokito_arriba1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		arriba2 = setup("/enemigos/lokito_arriba2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		arriba3 = setup("/enemigos/lokito_arriba3", pj.tamanioBaldosa, pj.tamanioBaldosa);
		derecha1 = setup("/enemigos/lokito_derecha1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		derecha2 = setup("/enemigos/lokito_derecha2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		derecha3 = setup("/enemigos/lokito_derecha3", pj.tamanioBaldosa, pj.tamanioBaldosa);
		izquierda1 = setup("/enemigos/lokito_izquierda1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		izquierda2 = setup("/enemigos/lokito_izquierda2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		izquierda3 = setup("/enemigos/lokito_izquierda3", pj.tamanioBaldosa, pj.tamanioBaldosa);
	}
	
	/**
	 * Lokito se moverá en una dirección aleatoria cada cierto intervalo de tiempo.
	 */
	public void setAction() {
		
		contadorAccion++;
		
		if(contadorAccion == 120) { //Cada 2 segundos
			Random random = new Random();
			int i = random.nextInt(100)+1; //Numero del 1 al 100
			
			if (i <= 25) {
				direccion = "arriba";//El 25% de las veces va pa arriba, al igual que las demás posiciones
			}
			
			if (i > 25 && i <= 50) {
				direccion = "abajo";
			}
			
			if (i > 50 && i <= 75) {
				direccion = "izquierda";
			}
			
			if (i > 75 && i <= 100) {
				direccion = "derecha";
			}
			
			contadorAccion = 0;
		}
		
		
	}
	
	/**
	 * Método que define la reacción de Lokito al recibir daño.
	 * Reinicia el contador de acción y ajusta la dirección de Lokito para que coincida con la dirección del jugador.
	 */
	public void reaccionDanio() {
		contadorAccion = 0;
		direccion = pj.jugador.direccion;
	}
}
