/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import ep.ecoproyecto.PanelJuego;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author fabif
 */
public class ObjetoEquipo extends Objetosclase{
    
    public ObjetoEquipo(String nombre, int posicionX, int posicionY, PanelJuego gp) {
        super(nombre, posicionX, posicionY, gp);
                
        try{
            image =ImageIO.read(getClass().getResourceAsStream("/objetos/llave1.png"));
        }catch(IOException e){
        
        }
    }
    
    
}
