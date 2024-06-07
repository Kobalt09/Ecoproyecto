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
public class ObjetoPuerta extends Objetosclase{
    
    public ObjetoPuerta(String nombre, int posicionX, int posicionY, PanelJuego gp) {
        super(nombre, posicionX, posicionY, gp);
        
        try{
            image =ImageIO.read(getClass().getResourceAsStream("/objetos/puerta1.png"));
        }catch(IOException e){
            
        }
        
        colision=true;
        
    }
    
}