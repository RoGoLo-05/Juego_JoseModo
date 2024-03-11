package principal;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Esta clase gestiona la reproducción de sonidos en el juego.
 */
public class Sonido {

	Clip clip;
	URL sonidoURL[] = new URL[8];
	public boolean isPlaying = false;
	
	/**
	 * Constructor de la clase Sonido.
	 * Inicializa las URL de los archivos de sonido.
	 */
	public Sonido() {
		sonidoURL[0] = getClass().getResource("/sonido/musicafondo.wav");
		sonidoURL[1] = getClass().getResource("/sonido/cogerllave.wav");
		sonidoURL[2] = getClass().getResource("/sonido/abrirpuerta.wav");
		sonidoURL[3] = getClass().getResource("/sonido/danio.wav");
		sonidoURL[4] = getClass().getResource("/sonido/pegar.wav");
		sonidoURL[5] = getClass().getResource("/sonido/sonido_ballesta.wav");
		sonidoURL[6] = getClass().getResource("/sonido/victoria.wav");
		sonidoURL[7] = getClass().getResource("/sonido/derrota.wav");
	}
	
	/**
	 * Establece el archivo de sonido que se va a reproducir.
	 * 
	 * @param i Indice del archivo de sonido en el array de URL.
	 */
	public void setFile(int i) {
		
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(sonidoURL[i]);
			clip = AudioSystem.getClip();
			clip.open(audio);
            isPlaying = false; // Reseteamos el estado de reproducción al cargar un nuevo sonido
		} catch(Exception e) {
			
		}
		
	}
	
	/**
	 * Reproduce el sonido.
	 */
	public void play() {
		clip.start();
		isPlaying = true;
	}
	
	/**
	 * Reproduce el sonido en un bucle continuo.
	 */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY); //Para que se repita sin parar, constantemente
	}
	
	/**
	 * Detiene la reproducción del sonido.
	 */
	public void stop() {
		clip.stop();
        isPlaying = false; // Actualizamos el estado de reproducción al detener el sonido
	}
	
	/**
	 * Verifica si actualmente se está reproduciendo un sonido.
	 * 
	 * @return true si se está reproduciendo un sonido, false si NO.
	 */
	public boolean isPlaying() {
        return isPlaying;
    }
}
