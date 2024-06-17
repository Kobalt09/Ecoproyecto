/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.entidades;

import ep.ecoproyecto.logica.Interfaces.Dibujado;
import ep.ecoproyecto.logica.Herramientas;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Cris
 */
public class Entidad{
    PanelJuego gp;
    public int xMapa,yMapa;
    public int vel;
    
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;
    
    public int spriteCounter = 0 ;
    public int spriteNum = 1;
    public Rectangle hitBox;

    public int areadefectoX, areadefectoY;
    public boolean colision;
    public int contadordeaccion=0;
    
    public Entidad(PanelJuego gp){
        this.gp=gp;
    }
    /*
    public void draw(Graphics2D g2){
        BufferedImage image =null;  
        int PantallaX=xMapa- gp.jugador.xMapa+gp.jugador.pantallaX;
        int PantallaY=yMapa- gp.jugador.yMapa+gp.jugador.pantallaY;
                
                    if((xMapa+gp.tamanioCasilla > gp.jugador.xMapa-gp.jugador.pantallaX)&&(xMapa-gp.tamanioCasilla < gp.jugador.xMapa+gp.jugador.pantallaX)&&
                       (yMapa+gp.tamanioCasilla > gp.jugador.yMapa-gp.jugador.pantallaY)&&(yMapa-gp.tamanioCasilla < gp.jugador.yMapa+gp.jugador.pantallaY)){     

                                switch(direction){
                                    case "up" -> {     
                                        if (spriteNum==1){
                                            image=up1;
                                        }
                                          if (spriteNum==2){
                                            image=up2;
                                        }
                                    break;
                                    }
                                    case "down" -> {
                                        if (spriteNum==1)
                                            image=down1;
                                        if (spriteNum==2)
                                            image=down2;                              
                                        break;
                                    }
                                    case "left" ->  {     
                                        if (spriteNum==1)
                                            image=left1;
                                        if (spriteNum==2)
                                            image=left2;
                                        break;
                                    }
                                    case "right" -> {      
                                        if (spriteNum==1)   
                                            image=right1;
                                        if (spriteNum==2)   
                                            image=right2;
                                        break;
                                    }

                                }
                        
                        g2.drawImage(image, PantallaX, PantallaY, gp.tamanioCasilla,gp.tamanioCasilla,null);
                    }
    }
    */
    
    public void estableceraccion(){}
    
    public void actualizar(){
        estableceraccion();
        
        colision=false;
        gp.colisiones.revisarColision(this);
        gp.colisiones.chequeoObjetos(this, colision);
        gp.colisiones.chequeojugador(this);
        
        if(colision==false){
                switch (direction) {
                        case "up":
                            yMapa-=vel;
                            break;
                        case "left":
                            xMapa-=vel;
                        break;
                        case "down":
                            yMapa+=vel;
                        break;
                        case "right":
                            xMapa+=vel;
                        break;
                    }
            }

            spriteCounter++;
            if (spriteCounter>10){
                if (spriteNum == 2 )
                {spriteNum=1;}
                else{
                if (spriteNum == 1)
                spriteNum=2;
                }
                spriteCounter = 0;
            }
    }
    
    public BufferedImage configuracion(String nombre){
        
        Herramientas herramienta = new Herramientas();
        BufferedImage imagen= null;
        
        try{
            imagen=ImageIO.read(getClass().getResourceAsStream(nombre+".png"));
            imagen= herramienta.imagenEscalada(imagen, gp.tamanioCasilla, gp.tamanioCasilla);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return imagen;
    }
    

    
}
