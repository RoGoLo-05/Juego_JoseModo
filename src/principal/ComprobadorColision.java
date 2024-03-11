package principal;

import entidades.EntidadPadre;

/**
 *  Clase ComprobadorColision que se encarga de verificar las colisiones entre entidades en el juego.
 *  
 *  @author Roberto
 *  @version 1.0
 */
public class ComprobadorColision {

	PanelJuego pj;
	
	/**
	 * Constructor de la clase ComprobadorColision.
	 * 
	 * @param pj El panel del juego para que funcione
	 */
	public ComprobadorColision(PanelJuego pj) {
		this.pj = pj;
	}
	
	/**
	 * Método para comprobar colisiones con baldosas del mapa, ya que hay algunas que tienen colisión, como los árboles o muros, otras que no como el suelo..
	 * 
	 * @param entidad La entidad cuya colisión se va a comprobar.
	 */
	public void comprobarBaldosa(EntidadPadre entidad) { //Aquí no usé el .intersect (pero para el método comprobarObjeto sí) porque comprobaría TODAS las baldosas,en vez de solo la de la izquierda y derecha / arriba y abajo
		//LLamo a EntidadPadre porque este método se usa también para comprobar las colisiones de los NPCs y enemigos, no solo del jugador
		int entidadIzquierdaMundoX = entidad.mundoX + entidad.areaSolida.x;
		int entidadDerechaMundoX = entidad.mundoX + entidad.areaSolida.x + entidad.areaSolida.width;
		int entidadArribaMundoY = entidad.mundoY + entidad.areaSolida.y;
		int entidadAbajoMundoY = entidad.mundoY + entidad.areaSolida.y + entidad.areaSolida.height;

		int entidadIzquierdaCol = entidadIzquierdaMundoX/pj.tamanioBaldosa;
		int entidadDerechaCol = entidadDerechaMundoX/pj.tamanioBaldosa;
		int entidadArribaFil = entidadArribaMundoY/pj.tamanioBaldosa;
		int entidadAbajoFil = entidadAbajoMundoY/pj.tamanioBaldosa;
		
		int baldosaNum1, baldosaNum2; //Solo tenemos que comprobar dos baldosas para cada dirección, izquierda y derecha  o  arriba y abajo
	
		switch(entidad.direccion) {
		case "arriba":
			entidadArribaFil = (entidadArribaMundoY - entidad.velocidad)/pj.tamanioBaldosa;
			baldosaNum1 = pj.configBaldosa.numBaldosaMapa[entidadIzquierdaCol][entidadArribaFil]; //Esquinas del área sólida de mi personaje
			baldosaNum2 = pj.configBaldosa.numBaldosaMapa[entidadDerechaCol][entidadArribaFil];
			
			//Comprueba si está golpeando una baldosa, si lo hace entonces no se puede mover
			if(pj.configBaldosa.baldosa[baldosaNum1].colision==true || pj.configBaldosa.baldosa[baldosaNum2].colision==true) {
				entidad.colisionActivada = true;
			}
			
			break;
		case "abajo":
			entidadAbajoFil = (entidadAbajoMundoY + entidad.velocidad)/pj.tamanioBaldosa;
			baldosaNum1 = pj.configBaldosa.numBaldosaMapa[entidadIzquierdaCol][entidadAbajoFil];
			baldosaNum2 = pj.configBaldosa.numBaldosaMapa[entidadDerechaCol][entidadAbajoFil];
			
			if(pj.configBaldosa.baldosa[baldosaNum1].colision==true || pj.configBaldosa.baldosa[baldosaNum2].colision==true) {
				entidad.colisionActivada = true;
			}
			break;
		case "izquierda":
			entidadIzquierdaCol = (entidadIzquierdaMundoX - entidad.velocidad)/pj.tamanioBaldosa;
			baldosaNum1 = pj.configBaldosa.numBaldosaMapa[entidadIzquierdaCol][entidadArribaFil];
			baldosaNum2 = pj.configBaldosa.numBaldosaMapa[entidadIzquierdaCol][entidadAbajoFil];
			
			if(pj.configBaldosa.baldosa[baldosaNum1].colision==true || pj.configBaldosa.baldosa[baldosaNum2].colision==true) {
				entidad.colisionActivada = true;
			}
			break;
		case "derecha":
			entidadDerechaCol = (entidadDerechaMundoX + entidad.velocidad)/pj.tamanioBaldosa;
			baldosaNum1 = pj.configBaldosa.numBaldosaMapa[entidadDerechaCol][entidadArribaFil];
			baldosaNum2 = pj.configBaldosa.numBaldosaMapa[entidadDerechaCol][entidadAbajoFil];
			
			if(pj.configBaldosa.baldosa[baldosaNum1].colision==true || pj.configBaldosa.baldosa[baldosaNum2].colision==true) {
				entidad.colisionActivada = true;
			}
			break;
		}
	
	}
	
	/**
	 * Método para comprobar colisiones con objetos del juego.
	 * 
	 * @param entidad La entidad cuya colisión con objetos se va a comprobar.
	 * @param jugador Un indicador booleano que especifica si la entidad es el jugador o si no(como un NPC o monstruo).
	 * @return El índice del objeto con el que la entidad colisiona, o 999 si no hay colisión.
	 */
	public int comprobarObjeto(EntidadPadre entidad, boolean jugador) { //Comprobamos si el jugador está golpeando algún objeto
		
		int index = 999; 
		
		for (int i=0; i<pj.obj.length; i++) {
			
			if(pj.obj[i] != null) {
				
				//Obtener la posición del área sólida de la entidad
				entidad.areaSolida.x = entidad.mundoX + entidad.areaSolida.x;
				entidad.areaSolida.y = entidad.mundoY + entidad.areaSolida.y;
				
				//Obtener la posición del área sólida del objeto
				pj.obj[i].areaSolida.x = pj.obj[i].mundoX + pj.obj[i].areaSolida.x;
				pj.obj[i].areaSolida.y = pj.obj[i].mundoY + pj.obj[i].areaSolida.y;
				
				switch(entidad.direccion) { //Posición de la entidad después de que se mueva
				case "arriba":
					entidad.areaSolida.y = entidad.areaSolida.y - entidad.velocidad;
					break;
				case "abajo":
					entidad.areaSolida.y = entidad.areaSolida.y + entidad.velocidad;
					break;
				case "izquierda":
					entidad.areaSolida.x = entidad.areaSolida.x - entidad.velocidad;		
					break;
				case "derecha":
					entidad.areaSolida.x = entidad.areaSolida.x + entidad.velocidad;
				}
				
				if(entidad.areaSolida.intersects(pj.obj[i].areaSolida)) { //Comprueba si la entidad está colisionando con el objeto o no
					if(pj.obj[i].colision == true) {
						entidad.colisionActivada = true;
					}
					if(jugador == true) { //Si es un NPC, un monstruo, osea NO un jugador, pues no ocurre nada
						index = i;
					}					
				}
				
				//Hay que reestablecer estos valores para que no vayan incrementandose sin parar.
				entidad.areaSolida.x = entidad.areaSolidaX;
				entidad.areaSolida.y = entidad.areaSolidaY;
				pj.obj[i].areaSolida.x = pj.obj[i].areaSolidaX;
				pj.obj[i].areaSolida.y = pj.obj[i].areaSolidaY;

			}
			
		}
		
		return index;
		
	}
	
	/**
	 * Método para comprobar colisiones con otras entidades (NPCs y monstruos).
	 * 
	 * @param entidad La entidad cuya colisión con otras entidades se va a comprobar.
	 * @param target Un array de entidades con las que se va a comprobar la colisión.
	 * @return El índice de la entidad con la que la entidad dada colisiona, o 999 si no hay colisión.
	 */
	public int comprobarEntidad(EntidadPadre entidad, EntidadPadre[] target) { //Colisión NPC y MONSTRUO
		int index = 999; 
		
		for (int i=0; i<target.length; i++) {
			
			if(target[i] != null) {
				
				//Obtener la posición del área sólida de la entidad
				entidad.areaSolida.x = entidad.mundoX + entidad.areaSolida.x;
				entidad.areaSolida.y = entidad.mundoY + entidad.areaSolida.y;
				
				//Obtener la posición del área sólida del objeto
				target[i].areaSolida.x = target[i].mundoX + target[i].areaSolida.x;
				target[i].areaSolida.y = target[i].mundoY + target[i].areaSolida.y;
				
				switch(entidad.direccion) { //Posición de la entidad después de que se mueva
				case "arriba":
					entidad.areaSolida.y = entidad.areaSolida.y - entidad.velocidad;
					break;
				case "abajo":
					entidad.areaSolida.y = entidad.areaSolida.y + entidad.velocidad;
					break;
				case "izquierda":
					entidad.areaSolida.x = entidad.areaSolida.x - entidad.velocidad;
					break;
				case "derecha":
					entidad.areaSolida.x = entidad.areaSolida.x + entidad.velocidad;
					break;
				}
				
				if(entidad.areaSolida.intersects(target[i].areaSolida)) { //Comprueba si la entidad está colisionando con el objeto o no
					if(target[i]!=entidad) {
						entidad.colisionActivada = true;
						index = i;
					}
					
				}
				//Hay que reestablecer estos valores para que no vayan incrementandose sin parar.
				entidad.areaSolida.x = entidad.areaSolidaX;
				entidad.areaSolida.y = entidad.areaSolidaY;
				target[i].areaSolida.x = target[i].areaSolidaX;
				target[i].areaSolida.y = target[i].areaSolidaY;

			}
			
		}
		
		return index;
	}
	
	/**
	 * Método para comprobar colisión con el jugador.
	 * 
	 * @param entidad La entidad cuya colisión con el jugador se va a comprobar.
	 * @return true si la entidad colisiona con el jugador, false si NO.
	 */
	public boolean comprobarJugador(EntidadPadre entidad) {
		
		boolean tocaJugador = false;
		
		//Obtener la posición del área sólida de la entidad
		entidad.areaSolida.x = entidad.mundoX + entidad.areaSolida.x;
		entidad.areaSolida.y = entidad.mundoY + entidad.areaSolida.y;
		
		//Obtener la posición del área sólida del objeto
		pj.jugador.areaSolida.x = pj.jugador.mundoX + pj.jugador.areaSolida.x;
		pj.jugador.areaSolida.y = pj.jugador.mundoY + pj.jugador.areaSolida.y;
		
		switch(entidad.direccion) { //Posición de la entidad después de que se mueva
		case "arriba":
			entidad.areaSolida.y = entidad.areaSolida.y - entidad.velocidad;	
			break;
		case "abajo":
			entidad.areaSolida.y = entidad.areaSolida.y + entidad.velocidad;
			break;
		case "izquierda":
			entidad.areaSolida.x = entidad.areaSolida.x - entidad.velocidad;
			break;
		case "derecha":
			entidad.areaSolida.x = entidad.areaSolida.x + entidad.velocidad;
			break;
		}
		
		if(entidad.areaSolida.intersects(pj.jugador.areaSolida)) { //Comprueba si la entidad está colisionando con el objeto o no
			entidad.colisionActivada = true;
			tocaJugador = true;
		}
		
		//Hay que reestablecer estos valores para que no vayan incrementandose sin parar.
		entidad.areaSolida.x = entidad.areaSolidaX;
		entidad.areaSolida.y = entidad.areaSolidaY;
		pj.jugador.areaSolida.x = pj.jugador.areaSolidaX;
		pj.jugador.areaSolida.y = pj.jugador.areaSolidaY;
		
		return tocaJugador;
	}
	
}
