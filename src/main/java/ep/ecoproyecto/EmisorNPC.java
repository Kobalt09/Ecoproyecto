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
        
        //MAPA 1://
        int MapNum = 0;
        gp.NPC[MapNum][0]= new chiguire(gp,7,7);
        
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

            for(int i=0;i<gp.NPC[0].length;i++){
                
                if(gp.NPC[gp.Mapaactual][i]!=null){
                    BufferedImage image =null;  
                    int PantallaX=gp.NPC[gp.Mapaactual][i].xMapa- gp.jugador.xMapa+gp.jugador.pantallaX;
                    int PantallaY=gp.NPC[gp.Mapaactual][i].yMapa- gp.jugador.yMapa+gp.jugador.pantallaY;
                
                    if((gp.NPC[gp.Mapaactual][i].xMapa+gp.tamanioCasilla > gp.jugador.xMapa-gp.jugador.pantallaX)&&(gp.NPC[gp.Mapaactual][i].xMapa-gp.tamanioCasilla < gp.jugador.xMapa+gp.jugador.pantallaX)&&
                       (gp.NPC[gp.Mapaactual][i].yMapa+gp.tamanioCasilla > gp.jugador.yMapa-gp.jugador.pantallaY)&&(gp.NPC[gp.Mapaactual][i].yMapa-gp.tamanioCasilla < gp.jugador.yMapa+gp.jugador.pantallaY)){     

                                switch(gp.NPC[gp.Mapaactual][i].direction){
                                    case "up" -> {     
                                        if (gp.NPC[gp.Mapaactual][i].spriteNum==1){
                                            image=gp.NPC[gp.Mapaactual][i].up1;
                                        }
                                          if (gp.NPC[gp.Mapaactual][i].spriteNum==2){
                                            image=gp.NPC[gp.Mapaactual][i].up2;
                                        }
                                    break;
                                    }
                                    case "down" -> {
                                        if (gp.NPC[gp.Mapaactual][i].spriteNum==1)
                                            image=gp.NPC[gp.Mapaactual][i].down1;
                                        if (gp.NPC[gp.Mapaactual][i].spriteNum==2)
                                            image=gp.NPC[gp.Mapaactual][i].down2;                              
                                        break;
                                    }
                                    case "left" ->  {     
                                        if (gp.NPC[gp.Mapaactual][i].spriteNum==1)
                                            image=gp.NPC[gp.Mapaactual][i].left1;
                                        if (gp.NPC[gp.Mapaactual][i].spriteNum==2)
                                            image=gp.NPC[gp.Mapaactual][i].left2;
                                        break;
                                    }
                                    case "right" -> {      
                                        if (gp.NPC[gp.Mapaactual][i].spriteNum==1)   
                                            image=gp.NPC[gp.Mapaactual][i].right1;
                                        if (gp.NPC[gp.Mapaactual][i].spriteNum==2)   
                                            image=gp.NPC[gp.Mapaactual][i].right2;
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
                
                if(gp.NPC[gp.Mapaactual][i]!=null){
                    gp.NPC[gp.Mapaactual][i].actualizar();
               }
            }
            
    }

}
