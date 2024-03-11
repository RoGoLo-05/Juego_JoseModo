package principal;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * La clase PanelImagen representa un panel que muestra una imagen de fondo.
 */
public class PanelImagen extends JPanel{
	
	@Override
	/**
	 * Sobrescribe el método paint para dibujar la imagen de fondo en el panel.
	 * 
	 * @param g Los gráficos para que se dibuje la imagen.
	 */
	public void paint(Graphics g){
        Dimension dimension = this.getSize();
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/ilerna.jpg"));
        g.drawImage(icon.getImage(), 0, 0, dimension.width, dimension.height, null);
        setOpaque(false);
        super.paintChildren(g);
    }
}
