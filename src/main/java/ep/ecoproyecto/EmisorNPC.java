/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;

import Entidades.Jugador;
import Entidades.chiguire;
import Interfaces.Dibujado;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cris
 */
public class EmisorNPC  implements Dibujado{
    PanelJuego gp;

    public EmisorNPC(PanelJuego gp) {
        this.gp = gp;
    }
    
    
    public void establecernpcs(){
        
        //----MUNDO 1:-----//
        int NumMap = 0;
        gp.NPC[NumMap][0]= new chiguire(gp,7,7);
        
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
            for(int i=0;i<gp.NPC[0].length;i++){
                    gp.Entidadlista.add(gp.NPC[0][i]);
            }
        
    }*/
    
    
    public void dibujado(Graphics2D g2){

            for(int i=0;i<gp.NPC[0].length;i++){
                
                if(gp.NPC[gp.MapaActual][i]!=null){
                    BufferedImage image =null;  
                    int PantallaX=gp.NPC[gp.MapaActual][i].xMapa- gp.jugador.xMapa+gp.jugador.pantallaX;
                    int PantallaY=gp.NPC[gp.MapaActual][i].yMapa- gp.jugador.yMapa+gp.jugador.pantallaY;
                
                    if((gp.NPC[gp.MapaActual][i].xMapa+gp.tamanioCasilla > gp.jugador.xMapa-gp.jugador.pantallaX)&&(gp.NPC[gp.MapaActual][i].xMapa-gp.tamanioCasilla < gp.jugador.xMapa+gp.jugador.pantallaX)&&
                       (gp.NPC[gp.MapaActual][i].yMapa+gp.tamanioCasilla > gp.jugador.yMapa-gp.jugador.pantallaY)&&(gp.NPC[gp.MapaActual][i].yMapa-gp.tamanioCasilla < gp.jugador.yMapa+gp.jugador.pantallaY)){     

                                switch(gp.NPC[gp.MapaActual][i].direction){
                                    case "up" -> {     
                                        if (gp.NPC[gp.MapaActual][i].spriteNum==1){
                                            image=gp.NPC[gp.MapaActual][i].up1;
                                        }
                                          if (gp.NPC[gp.MapaActual][i].spriteNum==2){
                                            image=gp.NPC[gp.MapaActual][i].up2;
                                        }
                                    break;
                                    }
                                    case "down" -> {
                                        if (gp.NPC[gp.MapaActual][i].spriteNum==1)
                                            image=gp.NPC[gp.MapaActual][i].down1;
                                        if (gp.NPC[gp.MapaActual][i].spriteNum==2)
                                            image=gp.NPC[gp.MapaActual][i].down2;                              
                                        break;
                                    }
                                    case "left" ->  {     
                                        if (gp.NPC[gp.MapaActual][i].spriteNum==1)
                                            image=gp.NPC[gp.MapaActual][i].left1;
                                        if (gp.NPC[gp.MapaActual][i].spriteNum==2)
                                            image=gp.NPC[gp.MapaActual][i].left2;
                                        break;
                                    }
                                    case "right" -> {      
                                        if (gp.NPC[gp.MapaActual][i].spriteNum==1)   
                                            image=gp.NPC[gp.MapaActual][i].right1;
                                        if (gp.NPC[gp.MapaActual][i].spriteNum==2)   
                                            image=gp.NPC[gp.MapaActual][i].right2;
                                        break;
                                    }
                                }
                        g2.drawImage(image, PantallaX, PantallaY, gp.tamanioCasilla,gp.tamanioCasilla,null);
                    }
               }
            }
    }
    
    public void actualizacion(){

            for(int i=0;i<gp.NPC[0].length;i++){ 
                if(gp.NPC[gp.MapaActual][i]!=null){
                    gp.NPC[gp.MapaActual][i].actualizar();
               }
            }
            
    }

}
