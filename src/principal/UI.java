package principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entidades.EntidadPadre;
import objeto.Objeto_corazon;
import objeto.Objeto_llave;

/**
 * Esta clase maneja la interfaz de usuario
 */
public class UI {

	PanelJuego pj;
	Graphics2D g2;
	Font fuente1;
	BufferedImage imagenLlave;
	public boolean mensajeOn = false;
	BufferedImage corazon_lleno, corazon_vacio;
	public String mensaje = ""; //Mensaje de notificación cuando ocurre algo como intentar abrir una puerta o al ganar por ejemplo.
	int mensajeContador = 0;
	int numeroComando = 0;
	
	/**
	 * Constructor de la interfaz de usuario.
	 * 
	 * @param pj El panel del juego para que funcione
	 */
	public UI(PanelJuego pj) {
		this.pj = pj;
		
		fuente1 = new Font("Arial", Font.BOLD , 40); 
		
		Objeto_llave llave = new Objeto_llave(pj);
		imagenLlave = llave.image; //Cojo la imagen del objeto llave, osea la llave.
		
		EntidadPadre corazon = new Objeto_corazon(pj);
		corazon_lleno = corazon.image;
		corazon_vacio = corazon.image2;
	}
	
	/**
	 * Muestra un mensaje en la pantalla del juego.
	 * 
	 * @param texto El texto del mensaje que quieres mostrar
	 */
	public void mostrarMensaje(String texto) {
		mensaje = texto;
		mensajeOn = true;
	}
	
	/**
	 * Dibuja los elementos de la interfaz de usuario, como las llaves que tiene el jugador y los mensajes al interactuar con objetos.
	 * Llama a otros métodos dependiendo del estado del juego 
	 * 
	 * @param g2 Los gráficos
	 */
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(fuente1);
		g2.setColor(Color.YELLOW);
		g2.drawImage(imagenLlave, pj.tamanioBaldosa*6, pj.tamanioBaldosa/2, pj.tamanioBaldosa, pj.tamanioBaldosa, null); //24 pixeles en vez de 48, osea que muestro el mensaje 24 pixeles abajo y a la derecha
		g2.drawString("x " + pj.jugador.hasLlave, 340, 65); //Muestra arriba a la izquierda las llaves que tenemos
	
		//Mensaje
		if(mensajeOn == true) {
			g2.setFont(g2.getFont().deriveFont(30F)); //Pongo el texto más pequeño
			g2.drawString(mensaje, pj.tamanioBaldosa*2, pj.tamanioBaldosa*15); //Muestro el mensaje lo equivalente a 2 baldosas a la derecha y 15 baldosas abajo
			mensajeContador++;
			
			if(mensajeContador > 120) { //Cuando pasan 2 segundos (120 frames), se deja de mostrar el mensaje
				mensajeContador = 0;
				mensajeOn = false;
			}
			
		}
		
		
		if(pj.estadoJuego == pj.jugarJuego) {
			drawJugadorVida(g2);

		}
		
		if(pj.estadoJuego == pj.pausarJuego) {
			drawJugadorVida(g2);
			drawPantallaPausa();
		}
		
		if(pj.estadoJuego == pj.finalJuego) {
			drawPantallaFinal();
		}
		
		
	}
	
	/**
	 * Dibuja la pantalla final del juego, la de perder o ganar.
	 */
	public void drawPantallaFinal() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, pj.pantallaAncho, pj.pantallaAlto);
		
		int x;
		int y;
		
		String texto = "";
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		
		texto = "FINAL DEL JUEGO";
		//Sombra
		g2.setColor(Color.BLACK);
		x = getXtextoCentrado(texto);
		y = pj.tamanioBaldosa*4;
		g2.drawString(texto, x, y);
		
		//Letras
		g2.setColor(Color.YELLOW);
		g2.drawString(texto, x-4, y-4);
		
		//Ganado o perdido
		String texto2 = "";
		if(pj.jugador.vida <= 0) {
			texto2 = "HAS PERDIDO";
			g2.setColor(Color.RED);
			
		} else if(pj.monstruo[0].vida == 0) {
			texto2 = "HAS GANADO";
			g2.setColor(Color.GREEN);
		}
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60f)); // Ajusta el tamaño de la fuente según sea necesario
		x = getXtextoCentrado(texto2); // Usa el mismo método para centrar el texto
		y += pj.tamanioBaldosa*4; // Ajusta la posición en Y para el nuevo mensaje
		g2.drawString(texto2, x, y);
		
		//Reintentar
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(50f));
		texto = "Reintentar";
		x = getXtextoCentrado(texto);
		y += pj.tamanioBaldosa*4;
		g2.drawString(texto, x, y);
		if(numeroComando == 0) {
			g2.drawString(">", x-40, y);
		}
		
		//Salir
		texto = "Salir";
		x = getXtextoCentrado(texto);
		y += 55;
		g2.drawString(texto, x, y);
		if(numeroComando == 1) {
			g2.drawString(">", x-40, y);
		}
	}
	
	/**
	 * Dibuja las vidas del jugador.
	 * 
	 * @param g2 Los gráficos
	 */
	public void drawJugadorVida(Graphics2D g2) {
		
		int x = pj.tamanioBaldosa/2;
		int y = pj.tamanioBaldosa/2;
		
		int i = 0;
		
		//Corazones vacíos
		while(i < pj.jugador.maxVida) {
			g2.drawImage(corazon_vacio, x, y, null);
			i++;
			x = x +pj.tamanioBaldosa;
		}
		
		//Resetear
		x = pj.tamanioBaldosa/2;
		y = pj.tamanioBaldosa/2;
		i = 0;
		
		//Corazones llenos
		while(i < pj.jugador.vida) {
			g2.drawImage(corazon_lleno, x, y, null);
			i++;
			x = x + pj.tamanioBaldosa;
		}
		
	}
	
	/**
	 * Dibuja la pantalla de pausa
	 */
	public void drawPantallaPausa() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		
		String texto = "PAUSADO";
		
		int x = getXtextoCentrado(texto);
		int y = pj.pantallaAlto/2;
		
		g2.drawString(texto, x, y);
	}
	
	/**
	 * Calcula la posición X centrada para el texto.
	 * @param texto El texto del cual se calculará la posición X centrada.
	 * @return La posición X centrada para el texto.
	 */
	public int getXtextoCentrado(String texto) {
		int length = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
		int x = pj.pantallaAncho/2 - length/2;
		return x;
	}
	
}
