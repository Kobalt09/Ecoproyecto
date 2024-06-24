/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import ep.ecoproyecto.PanelJuego;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author fabif
 */
public class ObjetoCofre extends Objetosclase{
    
    public Rectangle areasolida = new Rectangle(0,0,64,64);

    public ObjetoCofre(String nombre, int posicionX, int posicionY, PanelJuego gp) {
        super(nombre, posicionX, posicionY, gp);

        try{
            image =ImageIO.read(getClass().getResourceAsStream("/objetos/cofre1.png"));
        }catch(IOException e){
        
        }
        
        colision=true;

    }
}