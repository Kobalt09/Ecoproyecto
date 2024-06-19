/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.tipografia;

import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author hp
 */
public class Fuentes {
    
    Font fuente=null;
    
    public String upheaval="/fuente/upheavtt.ttf";
     public String Roboto="/fuente/Roboto-Regular.ttf";
    
    public Font fuente(String nFuente, int estilo, float tamanio){
        
        try {
            
            InputStream is = Fuentes.class.getResourceAsStream(nFuente);
            fuente = Font.createFont(Font.TRUETYPE_FONT, is);
        
        } catch (Exception e) {          
            System.err.println(nFuente + " no se cargo la fuente");
            fuente=new Font("Arial", Font.PLAIN, 14);
        }
        
        
        Font tfont = fuente.deriveFont(estilo,tamanio);
        return tfont;
    } 
    
    
    
}
