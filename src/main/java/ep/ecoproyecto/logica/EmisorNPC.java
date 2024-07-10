
package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Interfaces.Actualizar;
import ep.ecoproyecto.logica.Interfaces.Dibujado;
import ep.ecoproyecto.logica.entidades.Agujero;
import ep.ecoproyecto.logica.entidades.Papelera;
import ep.ecoproyecto.logica.entidades.PuertaInteractuable;
import ep.ecoproyecto.logica.entidades.Tienda;
import ep.ecoproyecto.logica.entidades.Chiguire;
import ep.ecoproyecto.logica.entidades.Tonina;
import ep.ecoproyecto.logica.entidades.Turpial;
import ep.ecoproyecto.logica.entidades.Zamuro;
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
        gp.NPC[NumMap][0]= new Chiguire(gp,10,7);
        gp.NPC[NumMap][1]= new Tienda(gp,9,9);
        
        //posicion de la puerta(x,y,z), luego posiciona a la que hara el tp(xyz)
        
        //prueba
        gp.NPC[NumMap][7]= new PuertaInteractuable(gp,"Ar",20,35,0  ,15, 15, 1);
        //tienda
        gp.NPC[NumMap][2]= new PuertaInteractuable(gp,"puerta",13,9,0   ,6, 7, 2);
        
        //der
        gp.NPC[NumMap][6]= new PuertaInteractuable(gp,"De",32,19,0  ,8, 14, 3);
        
        //arr
        gp.NPC[NumMap][3]= new PuertaInteractuable(gp,"Ar",20,5,0   ,13, 23, 4);
        gp.NPC[NumMap][4]= new PuertaInteractuable(gp,"Ar",21,5,0   ,14, 23, 4);
        //iz
        gp.NPC[NumMap][5]= new PuertaInteractuable(gp,"Iz",7,20,0   ,20, 14, 5); 
        
        //----MUNDO 2:----- pruebas//
        NumMap = 1;
        gp.NPC[NumMap][0]= new Agujero(gp,10, 10);
        gp.NPC[NumMap][1]= new Agujero(gp,1, 5);
        gp.NPC[NumMap][2]= new Papelera(gp,1, 1);
        gp.NPC[NumMap][3]= new PuertaInteractuable(gp,"Iz",2, 1, 1, 14,9,0);
        
        
        //----MUNDO 3:-----  tienda//   
            NumMap = 2;
            gp.NPC[NumMap][1]= new Tienda(gp,7,1);
            gp.NPC[NumMap][0]= new PuertaInteractuable(gp,"Ab",8, 8, 2,   9,10,0);
        
        //----MUNDO 4:-----derecha//
            NumMap = 3;
            gp.NPC[NumMap][0]= new PuertaInteractuable(gp,"Ar",8, 14, 3,  30,18,0);
            gp.NPC[NumMap][1]= new PuertaInteractuable(gp,"Ar",8, 14, 3,  30,18,0);
            gp.NPC[NumMap][2]= new Tonina(gp,17,14);
        
        //----MUNDO 5:-----arriba// 
            NumMap = 4;
            gp.NPC[NumMap][0]= new PuertaInteractuable(gp,"Ab",14, 25, 4,   20,9,0);
            gp.NPC[NumMap][1]= new PuertaInteractuable(gp,"Ab",15, 25, 4,   21,9,0);
            gp.NPC[NumMap][2]= new Turpial(gp,13,23);
            
            gp.NPC[NumMap][5]=new Agujero(gp,19,20);
            gp.NPC[NumMap][6]=new Agujero(gp,11,19);
            gp.NPC[NumMap][7]=new Agujero(gp,15,18);
            gp.NPC[NumMap][8]=new Agujero(gp,11,16);
            gp.NPC[NumMap][9]=new Agujero(gp,20,12);
            gp.NPC[NumMap][10]=new Agujero(gp,14,10);
            //gp.NPC[NumMap][11]=new Agujero(gp,18,20);
            //gp.NPC[NumMap][8]=new Agujero(gp,21,10);
            
            
        //----MUNDO 6:-----izquierda//
            NumMap = 5;
            gp.NPC[NumMap][0]= new PuertaInteractuable(gp,"Ar",22, 15, 5,   8,19,0);
            gp.NPC[NumMap][3]= new Zamuro(gp,18,14);
            gp.NPC[NumMap][5]=new Papelera(gp,21,16);
    }
    
    
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
