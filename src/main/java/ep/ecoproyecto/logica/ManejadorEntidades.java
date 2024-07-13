package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Interfaces.Actualizar;
import ep.ecoproyecto.logica.Interfaces.Dibujado;
import ep.ecoproyecto.logica.entidades.Aguaconbasura;
import ep.ecoproyecto.logica.entidades.Agujero;
import ep.ecoproyecto.logica.entidades.Papelera;
import ep.ecoproyecto.logica.entidades.PuertaInteractuable;
import ep.ecoproyecto.logica.entidades.Tienda;
import ep.ecoproyecto.logica.entidades.Chiguire;
import ep.ecoproyecto.logica.entidades.Mono;
import ep.ecoproyecto.logica.entidades.Tonina;
import ep.ecoproyecto.logica.entidades.Turpial;
import ep.ecoproyecto.logica.entidades.Zamuro;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Asigna, dibuja y actualiza a las entidades
 * @author C-A-F
 */

public class ManejadorEntidades  implements Dibujado, Actualizar{
    PanelJuego pJuego;

    public ManejadorEntidades(PanelJuego pJuego) {
        this.pJuego = pJuego;
    }
    
    
    public void establecerEntidades(){
        
        //----MUNDO 1:-----//
        int NumMap = 0;
        pJuego.NPC[NumMap][0]= new Chiguire(pJuego,20,15);
        
        //posicion de la puerta(x,y,z), luego posiciona a la que hara el tp(xyz)
        
        //prueba
        pJuego.NPC[NumMap][7]= new PuertaInteractuable(pJuego,"Ar",20,35,0  ,15, 15, 1);
        //tienda
        pJuego.NPC[NumMap][2]= new PuertaInteractuable(pJuego,"puerta",13,9,0   ,4, 8, 2);
        
        //der
        pJuego.NPC[NumMap][6]= new PuertaInteractuable(pJuego,"De",32,19,0  ,8, 13, 3);
        pJuego.NPC[NumMap][10]= new PuertaInteractuable(pJuego,"De",32,20,0  ,8, 14, 3);
        //arr
        pJuego.NPC[NumMap][3]= new PuertaInteractuable(pJuego,"Ar",20,5,0   ,13, 23, 4);
        pJuego.NPC[NumMap][4]= new PuertaInteractuable(pJuego,"Ar",21,5,0   ,14, 23, 4);
        //iz
        pJuego.NPC[NumMap][5]= new PuertaInteractuable(pJuego,"Iz",7,20,0   ,20, 14, 5); 
        pJuego.NPC[NumMap][9]= new PuertaInteractuable(pJuego,"Iz",7,19,0   ,20, 13, 5); 
        
        //----MUNDO 2:----- pruebas//
        NumMap = 1;
        pJuego.NPC[NumMap][0]= new Agujero(pJuego,10, 10);
        pJuego.NPC[NumMap][1]= new Agujero(pJuego,1, 5);
        pJuego.NPC[NumMap][2]= new Papelera(pJuego,1, 1);
        pJuego.NPC[NumMap][3]= new PuertaInteractuable(pJuego,"Iz",2, 1, 1, 14,9,0);
        
        
        
        //----MUNDO 3:-----  tienda//   
            NumMap = 2;
            pJuego.NPC[NumMap][2]= new Mono(pJuego, 5, 5);
            pJuego.NPC[NumMap][1]= new Tienda(pJuego,5,1);
            pJuego.NPC[NumMap][0]= new PuertaInteractuable(pJuego,"Ab",5, 10, 2,   12,11,0);
        
        //----MUNDO 4:-----derecha//
            NumMap = 3;
            pJuego.NPC[NumMap][0]= new PuertaInteractuable(pJuego,"Iz",8, 14, 3,  30,19,0);
            pJuego.NPC[NumMap][1]= new PuertaInteractuable(pJuego,"Iz",8, 15, 3,  30,20,0);
            pJuego.NPC[NumMap][2]= new Tonina(pJuego,17,14);
            
            pJuego.NPC[NumMap][5]=new Aguaconbasura(pJuego,10,6);
            pJuego.NPC[NumMap][6]=new Aguaconbasura(pJuego,14,9);
            pJuego.NPC[NumMap][7]=new Aguaconbasura(pJuego,18,20);
            pJuego.NPC[NumMap][8]=new Aguaconbasura(pJuego,21,10);
        
        //----MUNDO 5:-----arriba// 
            NumMap = 4;
            pJuego.NPC[NumMap][0]= new PuertaInteractuable(pJuego,"Ab",14, 25, 4,   20,5,0);
            pJuego.NPC[NumMap][1]= new PuertaInteractuable(pJuego,"Ab",15, 25, 4,   21,5,0);
            pJuego.NPC[NumMap][2]= new Turpial(pJuego,13,23);
            
            pJuego.NPC[NumMap][5]=new Agujero(pJuego,19,20);
            pJuego.NPC[NumMap][6]=new Agujero(pJuego,11,19);
            pJuego.NPC[NumMap][7]=new Agujero(pJuego,15,18);
            pJuego.NPC[NumMap][8]=new Agujero(pJuego,11,16);
            pJuego.NPC[NumMap][9]=new Agujero(pJuego,20,12);
            pJuego.NPC[NumMap][10]=new Agujero(pJuego,14,10);
            
            
        //----MUNDO 6:-----izquierda//
            NumMap = 5;
            pJuego.NPC[NumMap][0]= new PuertaInteractuable(pJuego,"De",22, 15, 5,   8,19,0);
            pJuego.NPC[NumMap][1]= new PuertaInteractuable(pJuego,"De",22, 14, 5,   8,18,0);
            pJuego.NPC[NumMap][3]= new Zamuro(pJuego,18,14);
            pJuego.NPC[NumMap][5]=new Papelera(pJuego,21,16);
    }
    
    
    @Override
    public void dibujado(Graphics2D g2){

            for(int i=0;i<pJuego.NPC[0].length;i++){
                
                if(pJuego.NPC[pJuego.mapaActual][i]!=null){
                    BufferedImage image =null;  
                    int PantallaX=pJuego.NPC[pJuego.mapaActual][i].xMapa- pJuego.jugador.xMapa+pJuego.jugador.pantallaX;
                    int PantallaY=pJuego.NPC[pJuego.mapaActual][i].yMapa- pJuego.jugador.yMapa+pJuego.jugador.pantallaY;
                
                    if((pJuego.NPC[pJuego.mapaActual][i].xMapa+pJuego.tamanioCasilla > pJuego.jugador.xMapa-pJuego.jugador.pantallaX)&&(pJuego.NPC[pJuego.mapaActual][i].xMapa-pJuego.tamanioCasilla < pJuego.jugador.xMapa+pJuego.jugador.pantallaX)&&
                       (pJuego.NPC[pJuego.mapaActual][i].yMapa+pJuego.tamanioCasilla > pJuego.jugador.yMapa-pJuego.jugador.pantallaY)&&(pJuego.NPC[pJuego.mapaActual][i].yMapa-pJuego.tamanioCasilla < pJuego.jugador.yMapa+pJuego.jugador.pantallaY)){     

                                switch(pJuego.NPC[pJuego.mapaActual][i].direction){
                                    case "up" -> {     
                                        if (pJuego.NPC[pJuego.mapaActual][i].spriteNum==1){
                                            image=pJuego.NPC[pJuego.mapaActual][i].up1;
                                        }
                                          if (pJuego.NPC[pJuego.mapaActual][i].spriteNum==2){
                                            image=pJuego.NPC[pJuego.mapaActual][i].up2;
                                        }
                                    break;
                                    }
                                    case "down" -> {
                                        if (pJuego.NPC[pJuego.mapaActual][i].spriteNum==1)
                                            image=pJuego.NPC[pJuego.mapaActual][i].down1;
                                        if (pJuego.NPC[pJuego.mapaActual][i].spriteNum==2)
                                            image=pJuego.NPC[pJuego.mapaActual][i].down2;                              
                                        break;
                                    }
                                    case "left" ->  {     
                                        if (pJuego.NPC[pJuego.mapaActual][i].spriteNum==1)
                                            image=pJuego.NPC[pJuego.mapaActual][i].left1;
                                        if (pJuego.NPC[pJuego.mapaActual][i].spriteNum==2)
                                            image=pJuego.NPC[pJuego.mapaActual][i].left2;
                                        break;
                                    }
                                    case "right" -> {      
                                        if (pJuego.NPC[pJuego.mapaActual][i].spriteNum==1)   
                                            image=pJuego.NPC[pJuego.mapaActual][i].right1;
                                        if (pJuego.NPC[pJuego.mapaActual][i].spriteNum==2)   
                                            image=pJuego.NPC[pJuego.mapaActual][i].right2;
                                        break;
                                    }
                                }
                        g2.drawImage(image, PantallaX, PantallaY, pJuego.tamanioCasilla,pJuego.tamanioCasilla,null);
                    }
               }
            }
    }
    
    @Override
    public void actualizar(){

            for(int i=0;i<pJuego.NPC[0].length;i++){ 
                if(pJuego.NPC[pJuego.mapaActual][i]!=null){
                    pJuego.NPC[pJuego.mapaActual][i].actualizar();
               }
            }
            
    }

}
