package ep.ecoproyecto.logica.tipografia;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
/**
 * Asigna archivos de fuentes a variables para ser usadas sin instalarlas
 * @author C-A-F
 */
public class Fuentes {
    
    Font fuente=null;
   
    /**
     * Fuentes pre asignadas
     */
    public String upheaval="/fuente/upheavtt.ttf";
    public String roboto="/fuente/Roboto-Regular.ttf";
    public String pokemon="/fuente/pokemon_pixel_font.ttf";
    public String pokemon2="/fuente/Pokemon_Classic.ttf";
    
    /**
     * Método que permite elegir entre las fuentes de las variables, su estilo y tamaño
     * @param nFuente nombre de la fuente
     * @param estilo  tipo de escritura (negrita, light, etc)
     * @param tamanio (tamaño de la fuente)
     * @return una fuente nueva con el tipo especificado
     */
    public Font fuente(String nFuente, int estilo, float tamanio){
        
        try {
            
            InputStream is = Fuentes.class.getResourceAsStream(nFuente);
            fuente = Font.createFont(Font.TRUETYPE_FONT, is);
        
        } catch (FontFormatException | IOException e) {          
            System.err.println(nFuente + " no se cargo la fuente");
            fuente=new Font("Arial", Font.PLAIN, 14);
        }
        
        
        Font tfont = fuente.deriveFont(estilo,tamanio);
        return tfont;
    }
    
    
    
}
