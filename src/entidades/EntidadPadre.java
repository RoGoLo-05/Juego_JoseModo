package entidades;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import principal.Herramientas;
import principal.PanelJuego;

/**
 * Clase EntidadPadre, la cual carga y tiene los atributos principales de las entidades del juego
 * 
 * @author Roberto
 * @version 1.0
 */
public class EntidadPadre {

	
	PanelJuego pj;
	public int mundoX, mundoY;
	public int velocidad;
	
	public BufferedImage abajo1, abajo2, abajo3, arriba1, arriba2, arriba3, derecha1, derecha2, derecha3, izquierda1, izquierda2, izquierda3;//Guardamos las imágenes para poder usarlas
	public BufferedImage ataque_abajo1, ataque_abajo2, ataque_abajo3, ataque_arriba1, ataque_arriba2, ataque_arriba3, ataque_derecha1, ataque_derecha2, ataque_derecha3, ataque_izquierda1, ataque_izquierda2, ataque_izquierda3;//Guardamos las imágenes para poder usarlas

	public String direccion = "abajo";
	
	public int contadorSprite = 0;
	public int numSprite = 1;
	
	//Area de colision, no debe ser todo area el personaje porque si fuese asi habria que ser muy precisos para no golpearte con cada cosa y poder pasar por caminos estrechos.
	public Rectangle areaSolida = new Rectangle(0,0,48,48);
	public Rectangle areaAtaque = new Rectangle (0,0,0,0);
	
	public int areaSolidaX, areaSolidaY;
	
	public boolean colisionActivada = false;
	
	public int contadorAccion = 0;

	public boolean invencible = false;
	public int contadorInvencible = 0;
	public boolean atacando = false;
	
	public BufferedImage image, image2;
	public String nombre;
	public boolean colision = false;
	
	public int tipo; //Jugador: = 0, NPC = 1, Monstruo = 2;
	
	public boolean vivo = true;
	public boolean muriendo = false;
	public int contadorMuerto = 0;
	public boolean barraVidaActivada = false;
	public int contadorBarraVida = 0;
	
	//Estado del carácter
	public int maxVida;
	public int vida;
	
	/**
	 * Constructor EntidadPadre que guarda el pj
	 * 
	 * @param pj Me traigo el pj del PanelJuego
	 */
	public EntidadPadre(PanelJuego pj) {
		this.pj = pj;
	}
	
	/**
	 * Método que hace una accion
	 */
	public void setAction() {}
	
	/**
	 * Método que reacciona al daño que recibe una entidad
	 */
	public void reaccionDanio() {}
	
	/**
	 * Método que se actualiza constantemente y que hace gran parte de la lógica y de las funciones del juego funcione
	 */
	public void update() {
		setAction(); //El del NPC tiene prioridad
		colisionActivada = false;
		pj.comprobando.comprobarBaldosa(this);
		pj.comprobando.comprobarObjeto(this, false); //Es false porque el this NO es un jugador
		pj.comprobando.comprobarEntidad(this, pj.npc);
		pj.comprobando.comprobarEntidad(this, pj.monstruo);
		
		boolean tocaJugador = pj.comprobando.comprobarJugador(this);
		
		if(this.tipo == 2 && tocaJugador == true) {
			if (pj.jugador.invencible == false) { //Si ya nop es invencible, ya podemos recibir daño
				pj.playEfectoSonido(3);
				pj.jugador.vida -= 1;
				pj.jugador.invencible = true;
			}
		}
		
		if(colisionActivada==false) {
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
		
		if (invencible == true) {
			contadorInvencible++;
			if (contadorInvencible > 40) {
				invencible = false;
				contadorInvencible = 0;
			}
		}
	}
	
	/**
	 * Dibuja el mapa y las entidades así como la interfaz de los corazones o la vida del monstruo
	 * 
	 * @param g2 Recibe los gráficos necesario para dibujar el mapa
	 */
	public void draw(Graphics2D g2 ) {
		BufferedImage image = null;
		
		int pantallaX = mundoX - pj.jugador.mundoX + pj.jugador.pantallaX;
		int pantallaY = mundoY - pj.jugador.mundoY + pj.jugador.pantallaY; 
		
		//Mejora el rendimiento
		if (mundoX + pj.tamanioBaldosa > pj.jugador.mundoX - pj.jugador.pantallaX && mundoX - pj.tamanioBaldosa < pj.jugador.mundoX + pj.jugador.pantallaX && mundoY + pj.tamanioBaldosa > pj.jugador.mundoY - pj.jugador.pantallaY && mundoY - pj.tamanioBaldosa < pj.jugador.mundoY + pj.jugador.pantallaY) {
			
			switch(direccion) {
			case "arriba":
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
				
				break;
			case "abajo":
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
				
				break;
			case "izquierda":
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
				
				break;
			case "derecha":
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
				
				break;
			}
			
			//Barra de vida monstruo
			if(tipo == 2 && barraVidaActivada == true) {
				double escala1 = (double)pj.tamanioBaldosa/maxVida; //La barra de vida la divido en el numero de vidas que tiene el monstruo
				double barraVida = escala1*vida;
				
				g2.setColor(new Color(35,35,35));
				g2.fillRect(pantallaX - 1, pantallaY - 16, pj.tamanioBaldosa + 2, 12);
				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(pantallaX, pantallaY - 15, (int)barraVida, 10);
				
				contadorBarraVida++;
				
				if(contadorBarraVida > 600) { //Después de 10 segundos desaparece la barra a menos que le estemos golpeando.
					contadorBarraVida = 0;
					barraVidaActivada = false;
				}
				
			}
			
			
			if (invencible == true) { //Cuando el jugador toca un monstruo y se vuelve invencible, se vuelve un poco transparente
				barraVidaActivada = true;
				contadorBarraVida = 0;
				cambiarAlpha(g2, 0.5f);
			}
			
			if (muriendo == true) {
				muertoAnimacion(g2);
			}
			
			
			g2.drawImage(image, pantallaX, pantallaY, pj.tamanioBaldosa, pj.tamanioBaldosa, null); //Para los NPCs, que necesitan dibujarse con sus direcciones
			
			if (nombre != "jaime_npc" && nombre != "lokito_monstruo") { //Pa que no me dibuje el sprite del NPC o del monstruo hacia abajo constantemente, así se ven todas sus direcciones, no la de abajo siempre
				g2.drawImage(abajo1, pantallaX, pantallaY, pj.tamanioBaldosa, pj.tamanioBaldosa, null); //Para los objetos
			}
			
			cambiarAlpha(g2, 1f);

		}
	}
	
	/**
	 * Lógica de la animación del monstruo cuando muere
	 * 
	 * @param g2 Recibe los gráficos
	 */
	public void muertoAnimacion(Graphics2D g2) {
		contadorMuerto++;
		int i=5;
		
		if(contadorMuerto <= i) {
			cambiarAlpha(g2, 0f);
		}
		if(contadorMuerto > i && contadorMuerto <= i*2) {
			cambiarAlpha(g2, 1f);
		}
		if(contadorMuerto > i*2 && contadorMuerto <= i*3) {
			cambiarAlpha(g2, 0f);
		}
		if(contadorMuerto > i*3 && contadorMuerto <= i*4) {
			cambiarAlpha(g2, 1f);
		}
		if(contadorMuerto > i*4 && contadorMuerto <= i*5) {
			cambiarAlpha(g2, 0f);
		}
		if(contadorMuerto > i*5 && contadorMuerto <= i*6) {
			cambiarAlpha(g2, 1f);
		}
		if(contadorMuerto > i*6 && contadorMuerto <= i*7) {
			cambiarAlpha(g2, 0f);
		}
		if(contadorMuerto > i*7 && contadorMuerto <= i*8) {
			cambiarAlpha(g2, 1f);
		}
		if(contadorMuerto > i*8) {
			muriendo = false;
			vivo = false;
		}
	}
	
	/**
	 * Dibujo de la animación al morir, tras recibir la lógica
	 * 
	 * @param g2 Recibe los gráficos
	 * @param valorAlpha
	 */
	public void cambiarAlpha(Graphics2D g2, float valorAlpha) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, valorAlpha));
	}
	
	/**
	 * Recibe la imagen de las entidades
	 * 
	 * @param rutaImagen ruta del archivo de las entidades
	 * @param width El ancho de la entidad
	 * @param height El alto de la entidad
	 * @return La imagen
	 */
	public BufferedImage setup(String rutaImagen, int width, int height) {
		Herramientas herramienta = new Herramientas();
		BufferedImage imagen = null;
		
		try {
			imagen = ImageIO.read(getClass().getResourceAsStream(rutaImagen + ".png"));
			imagen = herramienta.imagenEscalada(imagen, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return imagen;
	}
	
}