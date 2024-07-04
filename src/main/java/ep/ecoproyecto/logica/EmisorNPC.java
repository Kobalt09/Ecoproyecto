
package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Interfaces.Actualizar;
import ep.ecoproyecto.logica.Interfaces.Dibujado;
import ep.ecoproyecto.logica.entidades.Agujero;
import ep.ecoproyecto.logica.entidades.Papelera;
import ep.ecoproyecto.logica.entidades.PuertaInteractuable;
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
        
        //posicion de la puerta(x,y,z), luego posiciona a la que hara el tp(xyz)
        gp.NPC[NumMap][2]= new PuertaInteractuable(gp,13,9,0,3, 1, 1);
        
        //prueba
        gp.NPC[NumMap][5]= new PuertaInteractuable(gp,19,34,0  ,15, 15, 1);
        
        //tienda
        gp.NPC[NumMap][2]= new PuertaInteractuable(gp,13,9,0   ,6, 7, 2);
        
        //der
        gp.NPC[NumMap][6]= new PuertaInteractuable(gp,32,19,0  ,8, 14, 3);
        
        //arr
        gp.NPC[NumMap][3]= new PuertaInteractuable(gp,20,6,0   ,14, 24, 4);
        
        //iz
        gp.NPC[NumMap][4]= new PuertaInteractuable(gp,7,20,0   ,20, 14, 5); 
        
        
        //----MUNDO 2:----- pruebas//
        NumMap = 1;
        gp.NPC[NumMap][0]= new Agujero(gp,10, 10);
        gp.NPC[NumMap][1]= new Agujero(gp,1, 5);
        gp.NPC[NumMap][2]= new Papelera(gp,1, 1);
        gp.NPC[NumMap][3]= new PuertaInteractuable(gp,2, 1, 1, 14,9,0);
        
        
        //----MUNDO 3:-----  tienda//   
            NumMap = 2;
            gp.NPC[NumMap][1]= new Tienda(gp,9,1);
            gp.NPC[NumMap][0]= new PuertaInteractuable(gp,8, 8, 2,   9,10,0);
        
        //----MUNDO 4:-----derecha//
            NumMap = 3;
            gp.NPC[NumMap][0]= new PuertaInteractuable(gp,8, 14, 3,   30,18,0);
        
        //----MUNDO 5:-----arriba// 
            NumMap = 4;
            gp.NPC[NumMap][0]= new PuertaInteractuable(gp,15, 25, 4,   20,9,0);
        
        //----MUNDO 6:-----izquierda//
            NumMap = 5;
            gp.NPC[NumMap][0]= new PuertaInteractuable(gp,22, 15, 5,   8,19,0);
        
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
