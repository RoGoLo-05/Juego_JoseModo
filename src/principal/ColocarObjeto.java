package principal;

import entidades.NPC_jaime;
import monstruos.MON_lokito;
import objeto.Objeto_ballesta;
import objeto.Objeto_llave;
import objeto.Objeto_puerta;
import objeto.Objeto_puerta_ilerna;

/**
 * Clase ColocarObjeto que se encarga de colocar diferentes objetos, NPCs y monstruos en el mapa.
 * 
 * @author Roberto
 * @version 1.0
 */
public class ColocarObjeto {

	PanelJuego pj;
	
	/**
	 * Constructor de la clase ColocarObjeto.
	 * 
	 * @param pj El panel del juego para que funcione
	 */
	public ColocarObjeto(PanelJuego pj) {
		this.pj = pj;
	}
	
	/**
	 * Método para colocar objetos en el panel de mapa.
	 */
	public void setObjeto() {
		pj.obj[0] = new Objeto_llave(pj);
		pj.obj[0].mundoX = 55 * pj.tamanioBaldosa;
		pj.obj[0].mundoY = 55 * pj.tamanioBaldosa;
		
		pj.obj[1] = new Objeto_puerta(pj);
		pj.obj[1].mundoX = 36 * pj.tamanioBaldosa;
		pj.obj[1].mundoY = 45 * pj.tamanioBaldosa;		
		
		pj.obj[2] = new Objeto_ballesta(pj);
		pj.obj[2].mundoX = 17 * pj.tamanioBaldosa;
		pj.obj[2].mundoY = 76 * pj.tamanioBaldosa;		
		
		pj.obj[3] = new Objeto_puerta_ilerna(pj);
		pj.obj[3].mundoX = 21 * pj.tamanioBaldosa;
		pj.obj[3].mundoY = 22 * pj.tamanioBaldosa;
		
		pj.obj[4] = new Objeto_llave(pj); //Otra llave
		pj.obj[4].mundoX = 15 * pj.tamanioBaldosa;
		pj.obj[4].mundoY = 76 * pj.tamanioBaldosa;
		
	}
	
	/**
	 * Método para colocar NPCs en el mapa.
	 */
	public void setNPC() {
		pj.npc[0] = new NPC_jaime(pj);
		pj.npc[0].mundoX = pj.tamanioBaldosa*50;
		pj.npc[0].mundoY = pj.tamanioBaldosa*20;
		
		pj.npc[1] = new NPC_jaime(pj);
		pj.npc[1].mundoX = pj.tamanioBaldosa*30;
		pj.npc[1].mundoY = pj.tamanioBaldosa*50;
		
	}
	
	/**
	 * Método para colocar el monstruo en el mapa.
	 */
	public void setMonstruo() {
		pj.monstruo[0] = new MON_lokito(pj);
		pj.monstruo[0].mundoX = pj.tamanioBaldosa*45;
		pj.monstruo[0].mundoY = pj.tamanioBaldosa*80;
	}
	
}
