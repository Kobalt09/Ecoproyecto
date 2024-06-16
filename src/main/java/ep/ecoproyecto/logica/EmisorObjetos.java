/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Jugador;
import java.awt.Color;
import java.awt.Graphics2D;
import ep.ecoproyecto.logica.objetos.ObjetoCofre;
import ep.ecoproyecto.logica.objetos.ObjetoEquipo;
import ep.ecoproyecto.logica.objetos.ObjetoPuerta;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;
import ep.ecoproyecto.logica.objetos.Objetosclase;

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

        gp.obj[0]=new ObjetoRecogible("llave",20,9,gp);
        gp.obj[1]=new ObjetoRecogible("llave",5,7,gp);
        gp.obj[2]=new ObjetoPuerta("puerta",7,8,gp);
        gp.obj[3]=new ObjetoCofre("cofre",3,7,gp);
        gp.obj[4]=new ObjetoEquipo("botas",6,6,gp);

    }
    
    public void draw(Graphics2D g2){
        
            for(int i=0;i<gp.obj.length;i++){
                //if((gp.obj[i]!=null)&&(gp.obj[i].posicionX>jugador.xMapa && gp.obj[i].posicionX <jugador.xMapa+this.gp.getWidth())){
                if(gp.obj[i]!=null){
                int PantallaX=gp.obj[i].posicionX- gp.jugador.xMapa+gp.jugador.pantallaX;
                int PantallaY=gp.obj[i].posicionY- gp.jugador.yMapa+gp.jugador.pantallaY;
                
                    if((gp.obj[i].posicionX+gp.tamanioCasilla > gp.jugador.xMapa-gp.jugador.pantallaX)&&(gp.obj[i].posicionX-gp.tamanioCasilla < gp.jugador.xMapa+gp.jugador.pantallaX)&&
                       (gp.obj[i].posicionY+gp.tamanioCasilla > gp.jugador.yMapa-gp.jugador.pantallaY)&&(gp.obj[i].posicionY-gp.tamanioCasilla < gp.jugador.yMapa+gp.jugador.pantallaY)){     


                        g2.drawImage(gp.obj[i].image, PantallaX, PantallaY, gp.tamanioCasilla,gp.tamanioCasilla,null);
                    }
                    /*
                    g2.setColor(Color.red);
                    g2.fillRect(gp.obj[i].posicionX, gp.obj[i].posicionY, gp.obj[i].AreaobjX, gp.obj[i].AreaobjdefectoY);*/
               }
                
               
                
            }
            
    }
    
    
        
}

