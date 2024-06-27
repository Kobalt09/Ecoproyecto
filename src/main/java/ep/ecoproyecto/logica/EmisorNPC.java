
package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Interfaces.Actualizar;
import ep.ecoproyecto.logica.Interfaces.Dibujado;
import ep.ecoproyecto.logica.entidades.Tienda;
import ep.ecoproyecto.logica.entidades.chiguire;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author C-A-F
 */

public class EmisorNPC  implements Dibujado, Actualizar{
    PanelJuego gp;

    public EmisorNPC(PanelJuego gp) {
        this.gp = gp;
    }
    
    
    public void establecernpcs(){
        
        //----MUNDO 1:-----//
        int NumMap = 0;
        gp.NPC[NumMap][0]= new chiguire(gp,10,7);
        gp.NPC[NumMap][1]= new Tienda(gp,9,9);
        
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
    
    
    @Override
    public void dibujado(Graphics2D g2){

            for(int i=0;i<gp.NPC[0].length;i++){
                
                if(gp.NPC[gp.mapaActual][i]!=null){
                    BufferedImage image =null;  
                    int PantallaX=gp.NPC[gp.mapaActual][i].xMapa- gp.jugador.xMapa+gp.jugador.pantallaX;
                    int PantallaY=gp.NPC[gp.mapaActual][i].yMapa- gp.jugador.yMapa+gp.jugador.pantallaY;
                
                    if((gp.NPC[gp.mapaActual][i].xMapa+gp.tamanioCasilla > gp.jugador.xMapa-gp.jugador.pantallaX)&&(gp.NPC[gp.mapaActual][i].xMapa-gp.tamanioCasilla < gp.jugador.xMapa+gp.jugador.pantallaX)&&
                       (gp.NPC[gp.mapaActual][i].yMapa+gp.tamanioCasilla > gp.jugador.yMapa-gp.jugador.pantallaY)&&(gp.NPC[gp.mapaActual][i].yMapa-gp.tamanioCasilla < gp.jugador.yMapa+gp.jugador.pantallaY)){     

                                switch(gp.NPC[gp.mapaActual][i].direction){
                                    case "up" -> {     
                                        if (gp.NPC[gp.mapaActual][i].spriteNum==1){
                                            image=gp.NPC[gp.mapaActual][i].up1;
                                        }
                                          if (gp.NPC[gp.mapaActual][i].spriteNum==2){
                                            image=gp.NPC[gp.mapaActual][i].up2;
                                        }
                                    break;
                                    }
                                    case "down" -> {
                                        if (gp.NPC[gp.mapaActual][i].spriteNum==1)
                                            image=gp.NPC[gp.mapaActual][i].down1;
                                        if (gp.NPC[gp.mapaActual][i].spriteNum==2)
                                            image=gp.NPC[gp.mapaActual][i].down2;                              
                                        break;
                                    }
                                    case "left" ->  {     
                                        if (gp.NPC[gp.mapaActual][i].spriteNum==1)
                                            image=gp.NPC[gp.mapaActual][i].left1;
                                        if (gp.NPC[gp.mapaActual][i].spriteNum==2)
                                            image=gp.NPC[gp.mapaActual][i].left2;
                                        break;
                                    }
                                    case "right" -> {      
                                        if (gp.NPC[gp.mapaActual][i].spriteNum==1)   
                                            image=gp.NPC[gp.mapaActual][i].right1;
                                        if (gp.NPC[gp.mapaActual][i].spriteNum==2)   
                                            image=gp.NPC[gp.mapaActual][i].right2;
                                        break;
                                    }
                                }
                        g2.drawImage(image, PantallaX, PantallaY, gp.tamanioCasilla,gp.tamanioCasilla,null);
                    }
               }
            }
    }
    
    @Override
    public void actualizar(){

            for(int i=0;i<gp.NPC[0].length;i++){ 
                if(gp.NPC[gp.mapaActual][i]!=null){
                    gp.NPC[gp.mapaActual][i].actualizar();
               }
            }
            
    }

}
