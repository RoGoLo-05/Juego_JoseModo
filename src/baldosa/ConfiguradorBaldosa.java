package baldosa;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import principal.Herramientas;
import principal.PanelJuego;
/**
 * Clase ConfiguradorBaldosa, en la que cargamos y dibujamos las imágenes, en forma de baldosas
 * 
 * @author Roberto
 * @version 1.0
 */
public class ConfiguradorBaldosa {

	PanelJuego pj;
	public Baldosa[] baldosa;
	public int numBaldosaMapa[][];
	
	/**
	 * Constructor de ConfiguradorBaldosa, donde defino el nº de baldosas del mapa y llamo al método de cargar imágenes
	 * y al de cargar el mapa.
	 * 
	 * @param pj Clase importante que me traigo para que el juego funcione y empiece
	 */
	public ConfiguradorBaldosa(PanelJuego pj) {
		this.pj = pj;
		baldosa = new Baldosa[80]; //80 baldosas distintas
		numBaldosaMapa = new int [pj.mundoColumnas][pj.mundoFilas]; //Todos los numeros del mapa se guardan en esta matriz
		
		getBaldosaImage();
		cargarMapa("/mapas/mundo1.txt");
	}
	
	/**
	 * Carga las imágenes
	 */
	public void getBaldosaImage() {
		
		setup(0, "Agua", true);
		setup(1, "Muro_gris", true); //Para que no pueda pasar, porque es un muro, activo la colisión
		setup(2, "Suelo_hierba", false);
		setup(3, "Suelo_madera", true);
		setup(4, "arbol", true);
		setup(5, "arbol2", true);
		setup(6, "pared_blanca", true);
		setup(7, "pared_blanca_arriba", true);
		setup(8, "pared_blanca_abajo", true);
		setup(9, "pared_blanca_izquierda", true);
		setup(10, "pared_blanca_derecha", true);
		setup(11, "pared_blanca_arriba_izquierda", true);
		setup(12, "pared_blanca_arriba_derecha", true);
		setup(13, "I", true);
		setup(14, "L", true);
		setup(15, "E", true);
		setup(16, "R", true);
		setup(17, "N", true);
		setup(18, "A", true);
		setup(19, "techo_gris", true);
		setup(20, "puerta", true);
		setup(21, "cuadrado_negro", true);
		setup(22, "acera", false);
		setup(23, "ventana", true);
		setup(24, "asfalto", false);
		setup(25, "linea_carretera_horizontal", false);
		setup(26, "linea_carretera_vertical", false);
		setup(27, "linea_carretera_cruce", false);
		setup(28, "asfalto_arriba", false);
		setup(29, "asfalto_abajo", false);
		setup(30, "asfalto_izquierda", false);
		setup(31, "asfalto_derecha", false);
		setup(32, "asfalto_arriba_izquierda", false);
		setup(33, "asfalto_arriba_derecha", false);
		setup(34, "asfalto_abajo_izquierda", false);
		setup(35, "asfalto_abajo_derecha", false);
		setup(36, "ladrillos_amarillos", true);
		setup(37, "puerta2", true);
		setup(38, "ladrillo_amarillo_ococ", true);
		setup(39, "ladrillo_techo", true);
		setup(40, "ladrillo_techo2", true);
		setup(41, "ladrillo_techo_arriba", true);
		setup(42, "ladrillo_techo_total", true);
		setup(43, "ladrillo_techo_total2", true);
		setup(44, "ladrillo_techo_final", true);
		setup(45, "ladrillo_techo_final2", true);
		setup(46, "ladrillo_techo_doble", true);
		setup(47, "ladrillo_techo_ultimo", true);
		setup(48, "ladrillo_techo_chimenea", true);
		setup(49, "ammu_pared", true);
		setup(50, "ammu_pared_izquierda", true);
		setup(51, "ammu_pared_derecha", true);
		setup(52, "ammu_pared_arriba", true);
		setup(53, "ammu_pared_arriba_izquierda", true);
		setup(54, "ammu_pared_arriba_derecha", true);
		setup(55, "ammu_pared_arriba_total", true);
		setup(56, "ammu_a", true);
		setup(57, "ammu_m", true);
		setup(58, "ammu_u", true);
		setup(59, "nation_n", true);
		setup(60, "nation_a", true);
		setup(61, "nation_t", true);
		setup(62, "nation_i", true);
		setup(63, "nation_o", true);
		setup(64, "nation_n2", true);
		setup(65, "ammu_pared_medio", true);
		setup(66, "ammu_puerta", true);
		setup(67, "ammu_puerta2", true);
		setup(68, "suelo_tablones", false);
		setup(69, "suelo_ilerna", false);
		setup(70, "muro_ilerna", true);
		setup(71, "muro_ilerna2", true);
		setup(72, "muro_ilerna_esquina", true);
		setup(73, "muro_ilerna_esquina2", true);
		setup(74, "armas", true);
		setup(75, "armas2", true);
		setup(76, "armas3", true);
		setup(77, "pc_abajo", true);
		setup(78, "pc_arriba", true);
		setup(79, "pc_arriba2", true);
		
	}
	
	/**
	 * Configurar imágenes, dándoles colisión a las necesarias y poniéndoles el tamaño predeterminado
	 * 
	 * @param index del array de cada baldosa
	 * @param nombreImagen Parte de la ruta del archivo(de la imagen), concretamente el nombre
	 * @param colision de la baldosa
	 */
	public void setup(int index, String nombreImagen, boolean colision) {
		Herramientas herramienta = new Herramientas();
		
		try {
			baldosa[index] = new Baldosa();
			baldosa[index].image = ImageIO.read(getClass().getResourceAsStream("/azulejos/" + nombreImagen + ".png"));
			baldosa[index].image = herramienta.imagenEscalada(baldosa[index].image, pj.tamanioBaldosa, pj.tamanioBaldosa);
			baldosa[index].colision = colision;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga el mapa a través de las imágenes en forma de baldosas y del archivo con los numeros distribuidos
	 * 
	 * @param rutaArchivo El mapa con los numeritos, los cuales indican una baldosa (su nº) cada uno
	 */
	public void cargarMapa(String rutaArchivo) { //El rutaArchivo por si creo varios mapas, así no tengon que crear otro método igual
		
		try {
			// InputStream es una clase abstracta que sirve como la superclase para todas las clases de entrada de bytes.
			InputStream IS = getClass().getResourceAsStream(rutaArchivo); //Importa el archivo de texto
			BufferedReader BR = new BufferedReader(new InputStreamReader(IS)); //Lee el archivo del texto
			
			int fil = 0;
			int col = 0;
			
			while(col < pj.mundoColumnas && fil < pj.mundoFilas) {
				String linea = BR.readLine(); // Lee una sola linea del archivo de texto
				
				//Una vez leída la linea...
				while(col < pj.mundoColumnas) {
					String numeros[] = linea.split(" "); //Obtiene los numeros uno por uno, ya que están separados por espacios
					int num = Integer.parseInt(numeros[col]); //La columna es el indice del array. Pasamos el numero de String a entero.
					
					numBaldosaMapa[col][fil] = num; //Guardamos aquí el numero
					col++; //Continúa así hasta que todos los números son guardados en el numBaldosaMapa[][]
				}
				
				if(col == pj.mundoColumnas) { //Si el indice es igual que el numero de columnas...
					col = 0; //Cambia a la fila de abajo desde la izquierda del todo
					fil++;
				}					
				
			}
			
			BR.close();
			
		} catch(Exception e) {
			
		}
		
	}
	
	/**
	 * Dibujo del mapa con las baldosas cargadas
	 * 
	 * @param g2 Para que pueda dibujarse
	 */
	public void draw(Graphics2D g2) {

		int mundoFil = 0;
		int mundoCol = 0;
		
		while(mundoCol < pj.mundoColumnas && mundoFil < pj.mundoFilas) {
			
			int baldosaNum = numBaldosaMapa[mundoCol][mundoFil]; //Esto servirá como un índice de la matriz baldosa
			
			int mundoX = mundoCol * pj.tamanioBaldosa; //*48. Es la posicion en el mapa
			int mundoY = mundoFil * pj.tamanioBaldosa; //*48. Es la posicion en el mapa
			int pantallaX = mundoX - pj.jugador.mundoX + pj.jugador.pantallaX; //Es la parte de la pantalla en la que dibujamos el azulejo.
			int pantallaY = mundoY - pj.jugador.mundoY + pj.jugador.pantallaY; //Es la parte de la pantalla en la que dibujamos el azulejo.
			
			//Mejora el rendimiento
			if (mundoX + pj.tamanioBaldosa > pj.jugador.mundoX - pj.jugador.pantallaX && mundoX - pj.tamanioBaldosa < pj.jugador.mundoX + pj.jugador.pantallaX && mundoY + pj.tamanioBaldosa > pj.jugador.mundoY - pj.jugador.pantallaY && mundoY - pj.tamanioBaldosa < pj.jugador.mundoY + pj.jugador.pantallaY) {
				//Solo dibujamos las baldosas que estén en el límite entre el jugador y la pantalla (+1, para que en la transición no se vean los límites negros)
				g2.drawImage(baldosa[baldosaNum].image, pantallaX, pantallaY, null);
				//Depende del numero que obtengamos dibujará en una baldosa agua, césped, muro..., ya que arriba en getBaldosaImageestá el índice de cada bloque
			}
			
			mundoCol++;
			//Le sumo 48 al x para pasar a la siguiente baldosa
			
			if (mundoCol == pj.mundoColumnas) { //Si el numero de columnas es igual al de las baldosas a lo ancho (24)...
				mundoCol = 0;
				mundoFil++; //Vuelvo a poner la columnas en 0 para empezar desde la izquierda pero en la siguiente fila.
				 //Le sumo 48 al y para pasar a la siguiente baldosa
			}
			
		}
		
	}
}
