package principal;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import baldosa.ConfiguradorBaldosa;
import entidades.EntidadPadre;
import entidades.Jugador;

/**
 * La clase PanelJuego representa el panel principal del juego donde se desarrolla la interacción del usuario.
 */
public class PanelJuego extends JPanel implements Runnable {

	final int baldosa = 16; //Las baldosas son de 16x16 píxeles, suficiente para los modelos de los dibujos
	final int escala = 3; //Le pongo una escala de 3, para que se vea más grandes.
	
	public final int tamanioBaldosa = baldosa * escala; //16*3=48 finalmente
	public final int baldosaAncho = 24; //24 columnas de baldosas
	public final int baldosaAlto = 18; //18 filas de baldosas
	
	public final int pantallaAncho = tamanioBaldosa * baldosaAncho; //1152 píxeles el ancho la pantalla
	public final int pantallaAlto = tamanioBaldosa * baldosaAlto; //864 píxeles el alto de la pantalla
	
	//CONFIGURACIÓN MUNDO
	public final int mundoColumnas = 70;
	public final int mundoFilas = 95;
	
	//FPS
	int FPS = 60;
	
	ConfiguradorBaldosa configBaldosa = new ConfiguradorBaldosa(this);
	
	public Movimiento mov = new Movimiento(this);
	
	Sonido sonido = new Sonido();	
	Sonido efectoSonido = new Sonido();
	
	public ComprobadorColision comprobando = new ComprobadorColision(this);
	public ColocarObjeto colocador = new ColocarObjeto(this);
	public UI ui = new UI(this);
	public ControladorEventos controlEvent = new ControladorEventos(this);

	
	Thread threadJuego; //Le doy como tiempo al juego, es un hijo el cual implementa la interfaz Runnable
	
	//Entidad y objeto
	public Jugador jugador = new Jugador(this, mov); //Con el this me traigo el "pj" que está en la clase Jugador
	
	public EntidadPadre obj[] = new EntidadPadre[10]; //Numero de objetos que se pueden mostrar a la vez
	public EntidadPadre npc[] = new EntidadPadre[10];
	public EntidadPadre monstruo[] = new EntidadPadre[20];
	ArrayList<EntidadPadre> entidadLista = new ArrayList<>(); //Dibujamos las entidades ordenadas por su valor en mundoY
	
	public int estadoJuego;
	public final int jugarJuego = 1;
	public final int pausarJuego = 2;
	public final int finalJuego = 3;
	
	/**
	 * Constructor PanelJuego.
	 * Crea un nuevo panel de juego.
	 */
	public PanelJuego() {
		this.setPreferredSize(new Dimension(pantallaAncho, pantallaAlto));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //Mejora el rendimiento
		
		this.addKeyListener(mov);
		this.setFocusable(true); //Se fuerza a que el gamepanel reciba el movimiento 
		
		
	}
	
	/**
	 * Configura el juego al iniciarlo.
	 */
	public void setupJuego() {
		colocador.setObjeto();
		colocador.setNPC();
		colocador.setMonstruo();
		playSonido(0);
		estadoJuego = jugarJuego;
	}
	
	/**
	 * Reinicia la configuración del juego.
	 * Esto sirve para cuando le des a "reintentar" cuando termines el juego, así se configura todo de nuevo.
	 */
	public void reintentar() {
		jugador.hasLlave = 0;
		jugador.hasBallesta = 0;
		jugador.setDefaultPositions();
		jugador.restaurarVida();
		colocador.setObjeto();
		colocador.setNPC();
		colocador.setMonstruo();
	}
	
	/**
	 * Inicia el hilo del juego.
	 */
	public void comienzoJuego() {
		threadJuego = new Thread(this);//El this es para decir que el PanelJuego se pasa a este threadJuego
		threadJuego.start(); //Llama al método run
	}

	@Override
	/**
	 * Configura el hilo del juego para que todo funcione de manera dinámica y con movimiento
	 */
	public void run() { //Esto es del Thread, es el núcleo del juego
		
		double dibujoIntervalo = 1000000000/FPS; //1 seg. Se dibuja la pantalla cada 0.01666...seg. Ya que son 60 FPS.
		double delta = 0;
		long horaAntigua = System.nanoTime();
		long tiempoActual;
		
		long temporizador = 0;
		long contador = 0;
		
		while(threadJuego != null) { //Bucle
			
			tiempoActual = System.nanoTime();
			
			delta = delta + (tiempoActual - horaAntigua) / dibujoIntervalo;
			temporizador = temporizador + (tiempoActual - horaAntigua);
			horaAntigua = tiempoActual;
			
			if (delta>=1) {
				update(); //Actualizar la posicion del personaje
				repaint(); //Dibujar la pantalla con la posición del personaje actualizada
				delta = delta -1;
				contador++;
			}
			
			if (temporizador >= 1000000000) {
				System.out.println("FPS: " + contador);
				contador = 0;
				temporizador = 0;
			}
			
		 }
	}
	
	/**
	 * Actualiza la posición de los NPCs y el monstruo del juego.
	 * Actualiza el sonido.
	 */
	public void update() { //Actualiza las coordenadas del personaje
		
		if(estadoJuego == jugarJuego) {
			//Jugador
			jugador.update();
			//NPC
			for(int i=0; i<npc.length;i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			//Monstruo
			for(int i=0; i<monstruo.length;i++) {
				if(monstruo[i] != null) {
					if(monstruo[i].vivo == true && monstruo[i].muriendo == false) {
						monstruo[i].update();
					}
					if(monstruo[i].vivo == false) {
						monstruo[i] = null;
					}
				}
			}
			
		}
		
		 if (estadoJuego == pausarJuego) {
		        if (sonido.isPlaying()) {
		            sonido.stop();
		        }
		 } else if (estadoJuego == jugarJuego) {
		        if (!sonido.isPlaying()) {
		            playSonido(0);
		        }
		 } else if (estadoJuego == finalJuego) {
		        // Detén la música cuando el juego termine
		        stopSonido();
		 }
		
	}
	
	/**
	 * Dibuja los elementos en el panel de juego y guarda de forma ordenada las entidades en un array
	 * 
	 * @param g Los gráficos para que se dibujen los elementos
	 */
	public void paintComponent(Graphics g) { //Dibuja el personaje con las posiciones actualizadas 
		super.paintComponent(g); //El super se refiere a la clase padre: JPanel
		
		Graphics2D g2 = (Graphics2D)g; //Tiene más funciones que el Graphics normal
		
		//Baldosa
		configBaldosa.draw(g2); //Primero se dibuja la baldosa y después el personaje, se hace así para que el personaje esté por encima del suelo, para que se vea.
		
		//Añadir entidades a la lista
		entidadLista.add(jugador);
		
		for(int i=0; i<npc.length; i++) {
			if(npc[i] != null) {
				entidadLista.add(npc[i]);
			}
		}
		
		for(int i=0; i<obj.length; i++) { //Escaneamos el array de objetos uno a uno
			if(obj[i] != null) { //Comprueba si hay algún objeto en esa posición
				entidadLista.add(obj[i]);
			}
		}

		for(int i=0; i<monstruo.length; i++) { //Escaneamos el array de objetos uno a uno
			if(monstruo[i] != null) { //Comprueba si hay algún objeto en esa posición
				entidadLista.add(monstruo[i]);
			}
		}
		
		//Ordenarlo
		Collections.sort(entidadLista, new Comparator<EntidadPadre>() {
			@Override
			public int compare(EntidadPadre entidad1, EntidadPadre entidad2) {
				int resultado = Integer.compare(entidad1.mundoY, entidad2.mundoY);
				return resultado;
			}
		});
		
		//Dibujar entidades
		for(int i=0; i<entidadLista.size(); i++) {
			entidadLista.get(i).draw(g2);
		}
		
		//Después de dibujar las entidades, eliminamos todas las entidades de la lista para que NO aumente la lista sin parar en cada bucle	
		entidadLista.clear();
		
		//UI
		ui.draw(g2);
		
		g2.dispose(); //Libera recursos
	}
	
	/**
	 * Reproduce el sonido del juego.
	 * 
	 * @param i Indice del archivo de sonido.
	 */
	public void playSonido(int i) {
		sonido.setFile(i);
		if (!sonido.isPlaying()) {
	        sonido.play();
	        sonido.loop();
	    }
	}
	
	/**
	 * Detiene el sonido del jueg
	 */
	public void stopSonido() {
		if (sonido.isPlaying()) {
	        sonido.stop();
	    }
	}
	
	/**
	 * Reproduce un efecto de sonido.
	 * 
	 * @param i Indice del archivo de efecto de sonido.
	 */
	public void playEfectoSonido(int i) { //Este método es solo para efectos de sonido, como cuando cojo la llave
		efectoSonido.setFile(i);
		efectoSonido.play();
	}

}