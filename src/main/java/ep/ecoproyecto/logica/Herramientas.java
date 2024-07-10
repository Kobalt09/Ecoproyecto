
package ep.ecoproyecto.logica;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author C-A-F
 */
public class Herramientas {
    public BufferedImage imagenEscalada(BufferedImage original, int ancho, int alto){
        
        BufferedImage imagenescalada= new BufferedImage(ancho,alto, original.getType());
        Graphics2D g2 = imagenescalada.createGraphics();
        g2.drawImage(original,0,0,ancho,alto,null);
        g2.dispose();
        
        return imagenescalada;
    }
}
