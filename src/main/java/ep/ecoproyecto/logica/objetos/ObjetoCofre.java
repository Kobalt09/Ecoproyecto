/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.objetos;

import ep.ecoproyecto.gui.PanelJuego;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author fabif
 */
public class ObjetoCofre extends Objetosclase{

    public ObjetoCofre(String nombre, int posicionX, int posicionY, PanelJuego gp) {
        super(nombre, posicionX, posicionY, gp);
        try{
            image =ImageIO.read(getClass().getResourceAsStream("/objetos/cofre1.png"));
        }catch(IOException e){
        
        }
        
        colision=true;

    }
}