/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;

import Entidades.Jugador;
import java.awt.Graphics2D;
import objetos.ObjetoCofre;
import objetos.ObjetoEquipo;
import objetos.ObjetoPuerta;
import objetos.ObjetoRecogible;
import objetos.Objetosclase;

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
        gp.obj[0]=new ObjetoRecogible("llave",20*gp.tamanioCasilla,9*gp.tamanioCasilla,gp);
        gp.obj[1]=new ObjetoRecogible("llave",5*gp.tamanioCasilla,7*gp.tamanioCasilla,gp);
        gp.obj[2]=new ObjetoPuerta("puerta",7*gp.tamanioCasilla,5*gp.tamanioCasilla,gp);
        gp.obj[3]=new ObjetoCofre("cofre",3*gp.tamanioCasilla,7*gp.tamanioCasilla,gp);
        gp.obj[4]=new ObjetoEquipo("botas",6*gp.tamanioCasilla,6*gp.tamanioCasilla,gp);
    }
    
    public void draw(Graphics2D g2,Jugador jugador){
        
            for(int i=0;i<gp.obj.length;i++){
                if((gp.obj[i]!=null)&&(gp.obj[i].posicionX>jugador.xMapa && gp.obj[i].posicionX <jugador.xMapa+this.gp.getWidth())){

                    if(gp.obj[i].posicionY>jugador.yMapa && gp.obj[i].posicionY <jugador.yMapa+this.gp.getWidth()){

                        g2.drawImage(gp.obj[i].image,gp.obj[i].posicionX-jugador.xMapa,  gp.obj[i].posicionY-jugador.yMapa, gp.tamanioCasilla,gp.tamanioCasilla,null);

                    }
               }
            }
            
    }
    
    
        
}

