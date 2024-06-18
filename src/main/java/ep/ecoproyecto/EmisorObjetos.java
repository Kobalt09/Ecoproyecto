/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;

import Entidades.Jugador;
import java.awt.Color;
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
        
        //MAPA 1://
        int MapNum = 0;
        gp.obj[MapNum][0]=new ObjetoRecogible("llave",20,9,gp);
        gp.obj[MapNum][1]=new ObjetoRecogible("llave",5,7,gp);
        gp.obj[MapNum][2]=new ObjetoPuerta("puerta",7,8,gp);
        gp.obj[MapNum][3]=new ObjetoCofre("cofre",3,7,gp);
        gp.obj[MapNum][4]=new ObjetoEquipo("botas",6,6,gp);
        
        //MAPA 2://
        MapNum = 1;
        
        //MAPA 3://
        MapNum = 2;
        
        //MAPA 4://
        MapNum = 3;
        
        //MAPA 5://
        MapNum = 4;
    }
    
    public void draw(Graphics2D g2){
        
            for(int i=0;i<gp.obj[0].length;i++){
                //if((gp.obj[i]!=null)&&(gp.obj[i].posicionX>jugador.xMapa && gp.obj[i].posicionX <jugador.xMapa+this.gp.getWidth())){
                if(gp.obj[gp.Mapaactual][i]!=null){
                int PantallaX=gp.obj[gp.Mapaactual][i].posicionX- gp.jugador.xMapa+gp.jugador.pantallaX;
                int PantallaY=gp.obj[gp.Mapaactual][i].posicionY- gp.jugador.yMapa+gp.jugador.pantallaY;
                
                    if((gp.obj[gp.Mapaactual][i].posicionX+gp.tamanioCasilla > gp.jugador.xMapa-gp.jugador.pantallaX)&&(gp.obj[gp.Mapaactual][i].posicionX-gp.tamanioCasilla < gp.jugador.xMapa+gp.jugador.pantallaX)&&
                       (gp.obj[gp.Mapaactual][i].posicionY+gp.tamanioCasilla > gp.jugador.yMapa-gp.jugador.pantallaY)&&(gp.obj[gp.Mapaactual][i].posicionY-gp.tamanioCasilla < gp.jugador.yMapa+gp.jugador.pantallaY)){     


                        g2.drawImage(gp.obj[gp.Mapaactual][i].image, PantallaX, PantallaY, gp.tamanioCasilla,gp.tamanioCasilla,null);
                    }
                    /*
                    g2.setColor(Color.red);
                    g2.fillRect(gp.obj[i].posicionX, gp.obj[i].posicionY, gp.obj[i].AreaobjX, gp.obj[i].AreaobjdefectoY);*/
               }
                
               
                
            }
            
    }
    
    
        
}

