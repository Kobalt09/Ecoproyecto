/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;

import Entidades.Jugador;
import Interfaces.Dibujado;
import java.awt.Color;
import java.awt.Graphics2D;
import objetos.ObjetoCofre;
import objetos.ObjetoEquipo;
import objetos.ObjetoMesa;
import objetos.ObjetoMoneda;
import objetos.ObjetoPuerta;
import objetos.ObjetoRecogible;
import objetos.Objetosclase;

/**
 *
 * @author Cris
 */
public class EmisorObjetos  implements Dibujado{
    
    PanelJuego gp;
    
    public EmisorObjetos(PanelJuego gp){
        this.gp=gp;
    }
    
    public void establecerObj(){
        
        //----MUNDO 1:-----//
        int NumMap = 0;
        gp.obj[NumMap][0]=new ObjetoRecogible("llave",20,9,gp);
        gp.obj[NumMap][1]=new ObjetoRecogible("llave",5,7,gp);
        gp.obj[NumMap][2]=new ObjetoPuerta("puerta",7,8,gp);
        gp.obj[NumMap][3]=new ObjetoCofre("cofre",3,7,gp);
        gp.obj[NumMap][4]=new ObjetoEquipo("botas",6,6,gp);
        gp.obj[NumMap][5]=new ObjetoMesa("mesa",9,9,gp);
        gp.obj[NumMap][6]=new ObjetoMoneda("coin",10,10,gp);
        /*
        //----MUNDO 2:-----//
        NumMap = 1;
        
        //----MUNDO 3:-----//
        NumMap = 2;
        
        //----MUNDO 4:-----//
        NumMap = 3;
        
        //----MUNDO 5:-----//
        NumMap = 4;
        */
    }
    /*
    public void Agregar(){
            for(int i=0;i<gp.obj[0].length;i++){
                    gp.Entidadlista.add(gp.obj[0][i]);
            }
        
    }*/
    
    
    public void dibujado(Graphics2D g2){
        
            for(int i=0;i<gp.obj[0].length;i++){
                //if((gp.obj[i]!=null)&&(gp.obj[i].posicionX>jugador.xMapa && gp.obj[i].posicionX <jugador.xMapa+this.gp.getWidth())){
                if(gp.obj[gp.MapaActual][i]!=null){
                int PantallaX=gp.obj[gp.MapaActual][i].xMapa- gp.jugador.xMapa+gp.jugador.pantallaX;
                int PantallaY=gp.obj[gp.MapaActual][i].yMapa- gp.jugador.yMapa+gp.jugador.pantallaY;
                
                    if((gp.obj[gp.MapaActual][i].xMapa+gp.tamanioCasilla > gp.jugador.xMapa-gp.jugador.pantallaX)&&(gp.obj[gp.MapaActual][i].xMapa-gp.tamanioCasilla < gp.jugador.xMapa+gp.jugador.pantallaX)&&
                       (gp.obj[gp.MapaActual][i].yMapa+gp.tamanioCasilla > gp.jugador.yMapa-gp.jugador.pantallaY)&&(gp.obj[gp.MapaActual][i].yMapa-gp.tamanioCasilla < gp.jugador.yMapa+gp.jugador.pantallaY)){     

                        g2.drawImage(gp.obj[gp.MapaActual][i].down1, PantallaX, PantallaY, gp.tamanioCasilla,gp.tamanioCasilla,null);
                    }
                }
            }
    }
    
    
        
}

