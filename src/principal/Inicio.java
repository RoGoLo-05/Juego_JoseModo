package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * La clase Inicio representa la ventana de inicio del juego.
 * Esta ventana permite al usuario iniciar el juego o salir del programa.
 */
public class Inicio extends JFrame implements ActionListener {
	
	/** Panel de imagen de fondo */
    PanelImagen panelFondo = new PanelImagen();
    
    
    /** Clip para reproducir la música de fondo */
    private Clip musicaDeFondo;
    
    /** Etiqueta para el título del juego */
    public JLabel titulo;
    
    /** Etiqueta para la imagen GIF de José */
    public JLabel jose_gif;
    
    /** Botón para iniciar el juego */
    public JButton botonJugar;
    
    /** Botón para salir del programa */
    public JButton botonSalir;
    
    /**
     * Constructor de la clase Inicio.
     * Configura la ventana de inicio del juego.
     */
    public Inicio() {
        // Tamaño de la ventana
        setSize(800,800);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("José Modo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        reproducirMusicaDeFondo();
        
        //Titulo del juego
        titulo = new JLabel("José Modo");
        titulo.setFont(new Font("Arial", Font.BOLD, 70));
        titulo.setForeground(Color.BLACK);
        titulo.setHorizontalAlignment(SwingConstants.RIGHT);
        titulo.setBounds(200, 100, 375, 50);
        add(titulo);
        
        ImageIcon josekar_corriendo = new ImageIcon(getClass().getResource("/gif/josekar_corriendo.gif"));
        jose_gif = new JLabel(josekar_corriendo);
        jose_gif.setBounds(300, 225, 200, 200);
        add(jose_gif);
        
        // Botón Jugar
        botonJugar = new JButton("Jugar");
        botonJugar.setFont(new Font("Arial", Font.BOLD, 20));
        botonJugar.setBackground(Color.BLACK);
        botonJugar.setForeground(Color.WHITE);
        botonJugar.setBounds(300,500,200,50);
        botonJugar.addActionListener(this);
        add(botonJugar);
        
        // Botón Salir
        botonSalir = new JButton("Salir");
        botonSalir.setFont(new Font("Arial", Font.BOLD, 20));
        botonSalir.setBackground(Color.BLACK);
        botonSalir.setForeground(Color.WHITE);
        botonSalir.setBounds(300,600,200,50);
        botonSalir.addActionListener(this);
        add(botonSalir);
     
        // Agregar panel de fondo
        add(panelFondo, BorderLayout.CENTER);
        
        //Llamo al método
        initHovers();
        
        // Mostrar JFrame
        setVisible(true);
        
    }
    
    /**
     * Reproduce la música de fondo del juego.
     */
    private void reproducirMusicaDeFondo() {
        try {
            //Cargar el archivo de audio
            AudioInputStream audioInicio = AudioSystem.getAudioInputStream(getClass().getResource("/sonido/iniciofondo.wav"));
            //Convertir el AudioInputStream en un Clip
            musicaDeFondo = AudioSystem.getClip();
            //Abrir el clip y cargar los datos del audio
            musicaDeFondo.open(audioInicio);
            //Reproducir el clip en un bucle
            musicaDeFondo.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Inicializa los efectos de hover para los componentes.
     */
    private void initHovers(){ //Cuando pongo el ratón encima de los botones
    	
      titulo.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            titulo.setForeground(Color.GREEN);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            titulo.setForeground(Color.BLACK);            
        }

          });

      botonJugar.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        	botonJugar.setBackground(Color.GREEN);
        	botonJugar.setBorder(new LineBorder(Color.GREEN));
        	botonJugar.setForeground(Color.BLACK);
        }

        @Override
        public void mouseExited(MouseEvent e) {
        	botonJugar.setBackground(Color.BLACK);
        	botonJugar.setBorder(new LineBorder(Color.DARK_GRAY));
        	botonJugar.setForeground(Color.WHITE);            
        }

      });

      botonSalir.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        	botonSalir.setBackground(Color.GREEN);
        	botonSalir.setBorder(new LineBorder(Color.GREEN));
        	botonSalir.setForeground(Color.BLACK);
        }

        @Override
        public void mouseExited(MouseEvent e) {
        	botonSalir.setBackground(Color.BLACK);
        	botonSalir.setBorder(new LineBorder(Color.DARK_GRAY));
        	botonSalir.setForeground(Color.WHITE);            
        }

      });
      
    }

    /**
     * Maneja los eventos de los botones.
     * 
     * @param e El evento que ha ocurrido.
     */
    public void actionPerformed(ActionEvent e) {  //Cuando pulso el botón
    	
        if (e.getSource() == botonJugar) {// Iniciar el juego
        	
        	if(musicaDeFondo != null) {
        		 musicaDeFondo.stop(); // Detener la música de fondo
                 musicaDeFondo.close(); // Liberar recursos
        	}
        	
            HijoJFrame hijoframe = new HijoJFrame(); //Se abre el juego
            setVisible(false); // Deja de ser visible la ventana del inicio
            
        } else if (e.getSource() == botonSalir) {// Salir del programa
        	
        	if (musicaDeFondo != null) {
                musicaDeFondo.stop(); // Detener la música de fondo
                musicaDeFondo.close(); // Liberar recursos
            }
        	
            System.exit(0);
        }
    }

    /**
     * Método principal que crea una instancia de la clase Inicio para mostrar la ventana del menú principal (Jugar y Salir).
     * 
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        new Inicio();
    }
}