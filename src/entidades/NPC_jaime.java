package entidades;

import java.util.Random;

import principal.PanelJuego;

/**
 * Clase NPC_jaime, hija de EntidadPadre.
 * Esta clase representa a un personaje no jugador (NPC) llamado Jaime en el juego.
 *  Jaime es un NPC que se mueve aleatoriamente por el escenario.
 *  
 *  @author Roberto
 *  @version 1.0
 */
public class NPC_jaime extends EntidadPadre{

	PanelJuego pj;
	
	/**
	 * Constructor de la clase NPC_jaime.
	 * Inicializa las características de Jaime, como su nombre, dirección y velocidad.
	 * También carga las imágenes necesarias para la animación de Jaime.
	 * 
	 * @param pj El panel del juego para que funcione
	 */
	public NPC_jaime(PanelJuego pj) {
		super(pj);
		this.pj = pj;
		
		nombre = "jaime_npc";
		direccion = "abajo";
		velocidad = 2;
		
		getImage();
	
	}
	
	/**
	 * Carga las imágenes necesarias en las diferentes direcciones de Jaime.
	 */
	public void getImage() {
		abajo1 = setup("/npc/jaime_abajo1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		abajo2 = setup("/npc/jaime_abajo2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		abajo3 = setup("/npc/jaime_abajo3", pj.tamanioBaldosa, pj.tamanioBaldosa);
		arriba1 = setup("/npc/jaime_arriba1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		arriba2 = setup("/npc/jaime_arriba2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		arriba3 = setup("/npc/jaime_arriba3", pj.tamanioBaldosa, pj.tamanioBaldosa);
		derecha1 = setup("/npc/jaime_derecha1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		derecha2 = setup("/npc/jaime_derecha2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		derecha3 = setup("/npc/jaime_derecha3", pj.tamanioBaldosa, pj.tamanioBaldosa);
		izquierda1 = setup("/npc/jaime_izquierda1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		izquierda2 = setup("/npc/jaime_izquierda2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		izquierda3 = setup("/npc/jaime_izquierda3", pj.tamanioBaldosa, pj.tamanioBaldosa);
	}

	/**
	 * Jaime se moverá en una dirección aleatoria cada cierto intervalo de tiempo.
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
	
}
