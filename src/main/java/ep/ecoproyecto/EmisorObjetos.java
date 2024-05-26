/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;

import java.awt.Graphics2D;
import objetos.ObjetoRecogible;

/**
 *
 * @author Cris
 */
public class EmisorObjetos {
    
    PanelJuego gp;
    
    public EmisorObjetos(PanelJuego gp){
        this.gp=gp;
    }
    
    public void establecerObj(){
        gp.obj[0]=new ObjetoRecogible();
        gp.obj[0].posicioX=30;
        gp.obj[0].posicionY=30;
    }
    
    public void draw(Graphics2D g2){
    
        g2.drawImage(gp.obj[0].image,gp.obj[0].posicioX, gp.obj[0].posicionY, gp.tamanioCasilla,gp.tamanioCasilla,null); 
    
    }
}
