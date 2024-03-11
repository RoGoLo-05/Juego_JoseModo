package entidades;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import baldosa.Baldosa;
import principal.Herramientas;
import principal.Movimiento;
import principal.PanelJuego;

/**
 * Clase Jugador, hija de EntidadPadre.
 * Esta clase configura todo lo del jugador, es decir, el personaje principal, el cual controlas.
 * 
 * @author Roberto
 * @version 1.0
 */
public class Jugador extends EntidadPadre {
	
	Movimiento mov;
	
	public final int pantallaX;
	public final int pantallaY;
	
	public int hasLlave = 0;
	public int hasBallesta = 0;
	
	/**
	 * Constructor Jugador que define el tamaño del jugador y sus distintas áreas de colisiones
	 * Llama a algunos métodos
	 * 
	 * @param pj El panel del juego para que funcione
	 * @param mov El movmimiento, para que no se quede quieto
	 */
	public Jugador(PanelJuego pj, Movimiento mov) { //Me los traigo pa acá
		super(pj);
		
		this.mov = mov;
		
		//Esto devuelve el punto medio de la pantalla
		pantallaX = pj.pantallaAncho/2 - (pj.tamanioBaldosa/2); // El "- (pj.tamanioBaldosa/2)" sirve para que el personaje se genere justo en el centro de la PANTALLA 
		pantallaY = pj.pantallaAlto/2 - (pj.tamanioBaldosa/2); //
		
		areaSolida = new Rectangle();
		//Lo puedo poner todo esto dentro del parentesis o fuera, da igual
		areaSolida.x = 8; //Con esto cojo una parte del personaje como área sólida, 8 pixeles desde la izquierda y derecha, y 16 desde arriba
		areaSolida.y = 16;
		
		areaSolidaX = areaSolida.x;
		areaSolidaY = areaSolida.y;

		areaSolida.width = 32; //Finalmente el área de colisión es de 32*32
		areaSolida.height = 32;
		
		areaAtaque.width = 48;
		areaAtaque.height = 48;
		
		setDefaultValues();
		getPlayerImages();
		getPlayerAttackImage();
	}
	
	/**
	 * Establece los valores predeterminados del jugador.
	 * Configura la posición inicial, la velocidad y la vida.
	 */
	public void setDefaultValues() { //Esto es lo que le pusimos en la clase PanelJuego
		mundoX = pj.tamanioBaldosa * 23; //Pongo el personaje en el MAPA
		mundoY = pj.tamanioBaldosa * 47; //Pongo el personaje en el MAPA
		velocidad = 4; //Se mueve 4 pixeles en cada movimiento
		
		direccion = "abajo"; //Es una direccion por defecto, da igual
		
		//Estado del jugador
		maxVida = 4;
		vida = maxVida;
	}
	
	/**
	 * Vuelve a establecer los valores predeterminados del jugador
	 */
	public void setDefaultPositions() {
		mundoX = pj.tamanioBaldosa * 23;
		mundoY = pj.tamanioBaldosa * 47;
		direccion = "abajo";
	}
	
	/**
	 * Restaura la vida y desactiva invencibilidad
	 */
	public void restaurarVida() {
		maxVida = 4;
		vida = maxVida;
		invencible = false;
	}
	
	/**
	 * Carga las imágenes del jugador y establece su tamaño
	 */
	public void getPlayerImages() {
		
		abajo1 = setup("/jugador/jc_abajo1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		abajo2 = setup("/jugador/jc_abajo2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		abajo3 = setup("/jugador/jc_abajo3", pj.tamanioBaldosa, pj.tamanioBaldosa);
		arriba1 = setup("/jugador/jc_arriba1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		arriba2 = setup("/jugador/jc_arriba2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		arriba3 = setup("/jugador/jc_arriba3", pj.tamanioBaldosa, pj.tamanioBaldosa);
		derecha1 = setup("/jugador/jc_derecha1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		derecha2 = setup("/jugador/jc_derecha2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		derecha3 = setup("/jugador/jc_derecha3", pj.tamanioBaldosa, pj.tamanioBaldosa);
		izquierda1 = setup("/jugador/jc_izquierda1", pj.tamanioBaldosa, pj.tamanioBaldosa);
		izquierda2 = setup("/jugador/jc_izquierda2", pj.tamanioBaldosa, pj.tamanioBaldosa);
		izquierda3 = setup("/jugador/jc_izquierda3", pj.tamanioBaldosa, pj.tamanioBaldosa);
		
	}
	
	/**
	 * Carga las imágenes del jugador atacando con la ballesta y establece su tamaño
	 */
	public void  getPlayerAttackImage() {
		ataque_abajo1 = setup("/jugador/jc_ballesta_abajo1", pj.tamanioBaldosa, pj.tamanioBaldosa*2);
		ataque_abajo2 = setup("/jugador/jc_ballesta_abajo2", pj.tamanioBaldosa, pj.tamanioBaldosa*2);
		ataque_abajo3 = setup("/jugador/jc_ballesta_abajo3", pj.tamanioBaldosa, pj.tamanioBaldosa*2);
		ataque_arriba1 = setup("/jugador/jc_ballesta_arriba1", pj.tamanioBaldosa, pj.tamanioBaldosa*2);
		ataque_arriba2 = setup("/jugador/jc_ballesta_arriba2", pj.tamanioBaldosa, pj.tamanioBaldosa*2);
		ataque_arriba3 = setup("/jugador/jc_ballesta_arriba3", pj.tamanioBaldosa, pj.tamanioBaldosa*2);
		ataque_derecha1 = setup("/jugador/jc_ballesta_derecha1", pj.tamanioBaldosa*2, pj.tamanioBaldosa);
		ataque_derecha2 = setup("/jugador/jc_ballesta_derecha2", pj.tamanioBaldosa*2, pj.tamanioBaldosa);
		ataque_derecha3 = setup("/jugador/jc_ballesta_derecha3", pj.tamanioBaldosa*2, pj.tamanioBaldosa);
		ataque_izquierda1 = setup("/jugador/jc_ballesta_izquierda1", pj.tamanioBaldosa*2, pj.tamanioBaldosa);
		ataque_izquierda2 = setup("/jugador/jc_ballesta_izquierda2", pj.tamanioBaldosa*2, pj.tamanioBaldosa);
		ataque_izquierda3 = setup("/jugador/jc_ballesta_izquierda3", pj.tamanioBaldosa*2, pj.tamanioBaldosa);
	}
	
	/**
	 * Actualiza el estado del jugador en cada iteración del bucle de juego.
	 * Controla el movimiento, las colisiones, la interacción con objetos y NPCs, el ataque y la invencibilidad del jugador.
	 */
	public void update() {
		
		if (atacando == true) {
			atacar();
		} else if (mov.arriba == true || mov.abajo == true || mov.izquierda == true || mov.derecha == true || mov.enter == true) {
		//Esto es para que el personaje solo cambie su imagen cuando presionas una tecla, y cuando no pues se queda igual	
			if (mov.arriba == true) {
				direccion = "arriba";
			} else if (mov.abajo == true) {
				direccion = "abajo";
			} else if (mov.izquierda == true) {
				direccion = "izquierda";
			} else if (mov.derecha == true) {
				direccion = "derecha";
			}
			
			//Comprobar colisión de las baldosas
			colisionActivada = false;
			pj.comprobando.comprobarBaldosa(this);
			
			//Comprobar colisión de los objetos
			int objIndex = pj.comprobando.comprobarObjeto(this, true);
			recogerObjeto(objIndex);
			
			//Comprobar colisión de los NPCs
			int npcIndex = pj.comprobando.comprobarEntidad(this, pj.npc);
			interactuarNPC(npcIndex);
			
			//Comprobar colisión de los monstruos
			int monstruoIndex = pj.comprobando.comprobarEntidad(this, pj.monstruo);
			tocarMonstruo(monstruoIndex);
			
			//Comprobar eventos
			pj.controlEvent.comprobarEvento();
			
			
			
			//Si no hay colisión con baldosas sólidas, el jugador se puede mover
			if(colisionActivada==false && mov.enter == false) {
				switch(direccion) {
				case "arriba":
					mundoY = mundoY - velocidad; //Ir hacia arriba resta porque el (0,0) está en la esquina arriba-izquierda
					break;
				case "abajo":
					mundoY = mundoY + velocidad;
					break;
				case "izquierda":
					mundoX = mundoX - velocidad;
					break;
				case "derecha":
					mundoX = mundoX + velocidad;
					break;	
				}
			}
			
			pj.mov.enter = false;
			
			contadorSprite++;
			
			if (contadorSprite > 10) { //Cada 10 fps cambia la iamgen del jugador
				if (numSprite == 1) {
					numSprite = 2;
				} else if (numSprite == 2) {
					numSprite = 3;
				} else if (numSprite == 3) {
					numSprite = 4;
				} else if (numSprite == 4) {
					numSprite = 1;
				}
				contadorSprite = 0;
			}
			
		}
		
		if (invencible == true) {
			contadorInvencible++;
			if (contadorInvencible > 60) {
				invencible = false;
				contadorInvencible = 0;
			}
		}
		
		if(vida<=0) {
			pj.estadoJuego = pj.finalJuego;
			pj.playEfectoSonido(7);
		}
		if(pj.monstruo[0].vida == 0) {
			pj.estadoJuego = pj.finalJuego;
			pj.playEfectoSonido(6);
		}
		
		
	}
	
	/**
	 * Realiza la lógica de ataque del jugador.
	 * Comprueba las colisiones con los monstruos.
	 */
	public void atacar() {
		
		contadorSprite++;
		
		if (contadorSprite <= 15) {
			numSprite = 1;
		}
		if (contadorSprite > 15 && contadorSprite <= 30) {
			numSprite = 2;
			//Guardar el mundoX,Y actual y el areaSolida
			int currentMundoX = mundoX;
			int currentMundoY = mundoY;
			int areaSolidaAncho = areaSolida.width;
			int areaSolidaAlto = areaSolida.height;
			
			//Ajustar el mundoX y mundoY del jugador para el área de ataque
			switch(direccion) {
			case "arriba":
				mundoY -= areaAtaque.height;
				break;
			case "abajo":
				mundoY += areaAtaque.height;
				break;
			case "izquierda":
				mundoY -= areaAtaque.width;
				break;
			case "derecha":
				mundoY += areaAtaque.width;
				break;
			}
			
			//El área de ataque se convierte en área sólida
			areaSolida.width = areaAtaque.width;
			areaSolida.height = areaAtaque.height;
			
			//Comprobar la colisión de los monstruos con el actualizado mundoX, mundoY y área sólida
			int monstruoIndex = pj.comprobando.comprobarEntidad(this, pj.monstruo);
			danioMonstruo(monstruoIndex);
			
			//Volver a la info original
			mundoX = currentMundoX;
			mundoY = currentMundoY;
			areaSolida.width = areaSolidaAncho;
			areaSolida.height = areaSolidaAlto;
		}
		if (contadorSprite > 30 && contadorSprite <= 45) {
			numSprite = 3;
			
			//Guardar el mundoX,Y actual y el areaSolida
			int currentMundoX = mundoX;
			int currentMundoY = mundoY;
			int areaSolidaAncho = areaSolida.width;
			int areaSolidaAlto = areaSolida.height;
			
			//Ajustar el mundoX y mundoY del jugador para el área de ataque
			switch(direccion) {
			case "arriba":
				mundoY -= areaAtaque.height;
				break;
			case "abajo":
				mundoY += areaAtaque.height;
				break;
			case "izquierda":
				mundoY -= areaAtaque.width;
				break;
			case "derecha":
				mundoY += areaAtaque.width;
				break;
			}
			
			//El área de ataque se convierte en área sólida
			areaSolida.width = areaAtaque.width;
			areaSolida.height = areaAtaque.height;
			
			//Comprobar la colisión de los monstruos con el actualizado mundoX, mundoY y área sólida
			int monstruoIndex = pj.comprobando.comprobarEntidad(this, pj.monstruo);
			danioMonstruo(monstruoIndex);
			
			//Volver a la info original
			mundoX = currentMundoX;
			mundoY = currentMundoY;
			areaSolida.width = areaSolidaAncho;
			areaSolida.height = areaSolidaAlto;
		}
		if (contadorSprite > 45 && contadorSprite <= 60) {
			numSprite = 1;
			contadorSprite = 0;
			atacando = false;
		}
	}
	
	/**
	 * Gestiona la interacción del jugador con los objetos del juego
	 * Permite al jugador recoger objetos como llaves y ballesta, y abrir puertas.
	 * 
	 * @param index El índice del objeto con el que el jugador está interactuando.
	 */
	public void recogerObjeto(int index) {
		
		if(index != 999) { //Osea si hemos tocado un objeto. Da igual cuál sea el index mientras no se use en el array del objeto como índice
			String nombreObjeto = pj.obj[index].nombre; //El nombre que guardamos en la variable name
			switch(nombreObjeto) {
			case "llave": //Compruebo si el jugador tiene una llave
				pj.playEfectoSonido(1);
				hasLlave++; //Guardo la llave
				pj.obj[index] = null;//Borra el objeto que acabo de tocar
				pj.ui.mostrarMensaje("¡Has conseguido una llave!");
				break;
			case "puerta":
				if(hasLlave > 0) { //Si tengo una llave..
					pj.playEfectoSonido(2);
					pj.obj[index] = null;
					hasLlave--;
					pj.ui.mostrarMensaje("Bienvenido al Ammu-Nation");
				} else {
					pj.ui.mostrarMensaje("¡Necesitas la primera llave para abrir la puerta!");
				}
				break;
			case "ballesta":
				pj.playEfectoSonido(1);
				hasBallesta++; //Guardo la ballesta
				pj.obj[index] = null;//Borra el objeto que acabo de tocar
				pj.ui.mostrarMensaje("¡Has conseguido una ballesta!");
				break;
			case "puerta_ilerna":
				if(hasBallesta > 0) { //Si tengo la ballesta...
					pj.playEfectoSonido(2);
					pj.obj[index] = null;
					pj.ui.mostrarMensaje("Bienvenido al Ilerna");
					hasLlave--;
				} else {
					pj.ui.mostrarMensaje("¡Necesitas la segunda llave para abrir la puerta!");
				}
				break;
			}
		}
		
	}
	
	/**
	 * Gestiona la interacción del jugador con los NPCs del juego.
	 * 
	 * @param i El índice del NPC con el que el jugador está interactuando.
	 */
	public void interactuarNPC(int i) {
		if(pj.mov.enter == true) {
			if(i != 999) {
				System.out.println("Estas tocando un NPC");
			} else {
				pj.playEfectoSonido(5);
				atacando = true;
			}
		}
	}
	
	/**
	 * Gestiona la interacción del jugador con los monstruos del juego.
	 * Reduce la vida del jugador cuando entra en contacto con un monstruo y te da un pequeño tiempo de invencibilidad
	 * 
	 * @param i El índice del monstruo con el que el jugador está interactuando.
	 */
	public void tocarMonstruo(int i) {
		if(i != 999) {
			if(invencible == false) {
				pj.playEfectoSonido(3);
				vida -= 1;
				invencible = true; //Te deja un tiempo pa que seas invencible, así cuando toques un monstruo NO te quita todas las vidas del tirón.
			}
		}
	}
	
	/**
	 * Inflije daño a un monstruo cuando el jugador lo ataca.
	 * Reduce la vida del monstruo y activa su estado de muerte si corresponde.
	 * 
	 * @param i  El indice del monstruo que está siendo atacado.
	 */
	public void danioMonstruo(int i) {
		if(i != 999) {
			System.out.println("Atacando a un monstruo");
			if(pj.monstruo[i].invencible == false) {
				pj.playEfectoSonido(4);
				pj.monstruo[i].vida -= 1;
				pj.monstruo[i].invencible = true;
				pj.monstruo[i].reaccionDanio();
				
				if(pj.monstruo[i].vida <= 0) {
					pj.monstruo[i].muriendo = true;
				}
			}
		} else {
			System.out.println("Atacando a la nada");
		}

	}
	
	/**
	 * Dibuja al jugador en su estado normal o atacando
	 * 
	 * @param g2 Los gráficos
	 */
	public void draw(Graphics2D g2) {
		BufferedImage image = null; //Creo una variable donde guardo una imagen. La declaro como null porque no contiene ninguna imagen todavía
		int temporalPantallaX = pantallaX;
		int temporalPantallaY = pantallaY;
		
		switch(direccion) {
		
		case "arriba":
			if(atacando == false) {
				if (numSprite == 1) {
					image = arriba2;
				}
				if (numSprite == 2) {
					image = arriba1;
				}
				if (numSprite == 3) {
					image = arriba2;
				}
				if (numSprite == 4) {
					image = arriba3;
				}
			}
			
			if(atacando == true) {
				temporalPantallaY = pantallaY - pj.tamanioBaldosa;
				if (numSprite == 1) {
					image = ataque_arriba1;
				}
				if (numSprite == 2) {
					image = ataque_arriba2;
				}
				if (numSprite == 3) {
					image = ataque_arriba3;
				}
			}
			
			break;
			
		case "abajo":
			if(atacando == false) {
				if (numSprite == 1) {
					image = abajo2;
				}
				if (numSprite == 2) {
					image = abajo1;
				}
				if (numSprite == 3) {
					image = abajo2;
				}
				if (numSprite == 4) {
					image = abajo3;
				}
			}
			
			if(atacando == true) {
				if (numSprite == 1) {
					image = ataque_abajo1;
				}
				if (numSprite == 2) {
					image = ataque_abajo2;
				}
				if (numSprite == 3) {
					image = ataque_abajo3;
				}
			}
			
			break;
			
		case "izquierda":
			if(atacando == false) {
				if (numSprite == 1) {
					image = izquierda2;
				}
				if (numSprite == 2) {
					image = izquierda1;
				}
				if (numSprite == 3) {
					image = izquierda2;
				}
				if (numSprite == 4) {
					image = izquierda3;
				}
			}
			
			if(atacando == true) {
				temporalPantallaX = pantallaX - pj.tamanioBaldosa;
				if (numSprite == 1) {
					image = ataque_izquierda1;
				}
				if (numSprite == 2) {
					image = ataque_izquierda2;
				}
				if (numSprite == 3) {
					image = ataque_izquierda3;
				}
			}
			
			break;
			
		case "derecha":
			if(atacando == false) {
				if (numSprite == 1) {
					image = derecha2;
				}
				if (numSprite == 2) {
					image = derecha1;
				}
				if (numSprite == 3) {
					image = derecha2;
				}
				if (numSprite == 4) {
					image = derecha3;
				}
			}
			
			if(atacando == true) {
				if (numSprite == 1) {
					image = ataque_derecha1;
				}
				if (numSprite == 2) {
					image = ataque_derecha2;
				}
				if (numSprite == 3) {
					image = ataque_derecha3;
				}
			}
			
			break;	
		}
		
		if (invencible == true) { //Cuando el jugador toca un monstruo y se vuelve invencible, se vuelve un poco transparente
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		}
		
		g2.drawImage(image, temporalPantallaX, temporalPantallaY, null);//Se dibuja la imagen en la pantalla
		
		//Resetear la opacidad
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
	}

}