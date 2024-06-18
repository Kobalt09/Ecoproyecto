/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;

import Entidades.Jugador;
import Entidades.chiguire;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cris
 */
public class EmisorNPC {
    PanelJuego gp;

    public EmisorNPC(PanelJuego gp) {
        this.gp = gp;
    }
    
    
    public void establecernpcs(){
        
        gp.NPC[0]= new chiguire(gp,7,7);
        
    }
    
    
    public void draw(Graphics2D g2){

            for(int i=0;i<gp.NPC.length;i++){
                
                if(gp.NPC[i]!=null){
                    BufferedImage image =null;  
                    int PantallaX=gp.NPC[i].xMapa- gp.jugador.xMapa+gp.jugador.pantallaX;
                    int PantallaY=gp.NPC[i].yMapa- gp.jugador.yMapa+gp.jugador.pantallaY;
                
                    if((gp.NPC[i].xMapa+gp.tamanioCasilla > gp.jugador.xMapa-gp.jugador.pantallaX)&&(gp.NPC[i].xMapa-gp.tamanioCasilla < gp.jugador.xMapa+gp.jugador.pantallaX)&&
                       (gp.NPC[i].yMapa+gp.tamanioCasilla > gp.jugador.yMapa-gp.jugador.pantallaY)&&(gp.NPC[i].yMapa-gp.tamanioCasilla < gp.jugador.yMapa+gp.jugador.pantallaY)){     

                                switch(gp.NPC[i].direction){
                                    case "up" -> {     
                                        if (gp.NPC[i].spriteNum==1){
                                            image=gp.NPC[i].up1;
                                        }
                                          if (gp.NPC[i].spriteNum==2){
                                            image=gp.NPC[i].up2;
                                        }
                                    break;
                                    }
                                    case "down" -> {
                                        if (gp.NPC[i].spriteNum==1)
                                            image=gp.NPC[i].down1;
                                        if (gp.NPC[i].spriteNum==2)
                                            image=gp.NPC[i].down2;                              
                                        break;
                                    }
                                    case "left" ->  {     
                                        if (gp.NPC[i].spriteNum==1)
                                            image=gp.NPC[i].left1;
                                        if (gp.NPC[i].spriteNum==2)
                                            image=gp.NPC[i].left2;
                                        break;
                                    }
                                    case "right" -> {      
                                        if (gp.NPC[i].spriteNum==1)   
                                            image=gp.NPC[i].right1;
                                        if (gp.NPC[i].spriteNum==2)   
                                            image=gp.NPC[i].right2;
                                        break;
                                    }

                                }
                        
                        g2.drawImage(image, PantallaX, PantallaY, gp.tamanioCasilla,gp.tamanioCasilla,null);
                    }
               }
            }
            
    }
    
    public void actualizacion(){

            for(int i=0;i<gp.NPC.length;i++){
                
                if(gp.NPC[i]!=null){
                    gp.NPC[i].actualizar();
               }
            }
            
    }

}
