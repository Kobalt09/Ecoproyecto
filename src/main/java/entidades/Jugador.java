/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//
package Entidades;
import ep.ecoproyecto.PanelJuego;
import ep.ecoproyecto.KeyHandler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import objetos.Objetosclase;

/**
 *
 * @author Cris
 */
public class Jugador extends Entidad{
    
    PanelJuego gp;
    KeyHandler keyH;
    public final int pantallaX;
    public final int pantallaY;
    
    Objetosclase inventario[]= new Objetosclase[10];
    int llaves=0;
    
    
    public Jugador(PanelJuego gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;
        
        pantallaX=gp.screenWidth/2-(gp.tamanioCasilla/2);
        pantallaY=gp.screenHeight/2-(gp.tamanioCasilla/2);
        
        hitBox=new Rectangle();
        //donde empiza la hitbox en relacion a la esquina superior
        hitBox.x=8;
        hitBox.y=8;
        //tamanio de la hitbox
        hitBox.height=48;
        hitBox.width=48;
        
        //area de colision
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        
        
        valoresporDefecto();
        getPlayerImage();
    }
    
    public void valoresporDefecto(){
        
        //posicion del jugador en el arreglo +1,
        xMapa=3*gp.tamanioCasilla+gp.tamanioCasilla;
        yMapa=3*gp.tamanioCasilla+gp.tamanioCasilla;
        vel=4;
        direction ="down"; 
    }

    public String getDirection() {
        return direction;
    }
    
    public void getPlayerImage(){
        try{
            up1= ImageIO.read(getClass().getResourceAsStream("/player/jg_arr_01.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/player/jg_arr_02.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/player/jg_abj_01.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/player/jg_abj_02.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/player/jg_izq_01.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/jg_izq_02.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/jg_der_01.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/jg_der_02.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    public void update(){
        if (keyH.upPressed==true||keyH.leftPressed==true||keyH.downPressed==true||keyH.rightPressed==true){
            //actualizamos la posicion del jugador sumando o restando su velocidad
            if(keyH.upPressed==true){
                direction="up";
            }else if(keyH.leftPressed==true){
                direction="left";
            }else if(keyH.downPressed==true){
                direction="down";
            }else if(keyH.rightPressed==true){
                direction="right";
            }
            
            colision=false;
            gp.colisiones.revisarColision(this);
            
            int objID=gp.colisiones.chequeoObjetos(this, true);
            recogerobjetos(objID);
            
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
    }
    
    public void recogerobjetos(int id){
        
        if(id!=999){
            //usa el nombre del objeto para saber con cual objeto colisiona 
            String objnombre=gp.obj[id].nombre;
            
            //switch para el nombre
            //nota se puede usar un getclass para saber el tipo o usar 
            System.out.println(gp.obj[id].nombre);
            switch(objnombre){
                case "llave":
                        llaves++;
                        gp.efectos(2);
                        gp.obj[id]=null;
                        System.out.println("llaves: "+llaves);
                    break;
                case "puerta":
                        if(llaves>0){
                            llaves--;
                            gp.efectos(5);
                            gp.obj[id]=null;
                            System.out.println("llaves: "+llaves);
                        }else{
                            System.out.println("no tienes llaves para esta puerta");
                        }
                    break;    
                case "botas":
                        if(this.inventario[0]==null){
                            gp.efectos(4);
                            vel=vel+2;
                            this.inventario[0]=gp.obj[id];
                            
                            gp.obj[id]=null;
                        }
                    break;
            }
            
        }
        
    }
        
        
    public void draw(Graphics2D g2){
        
       BufferedImage image = null;  
       
           
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
        
       //    g2.drawImage(image,gp.tamanioCasilla*xMapa,gp.tamanioCasilla*yMapa, gp.tamanioCasilla,gp.tamanioCasilla,null); 
    
       g2.drawImage(image,pantallaX,pantallaY, gp.tamanioCasilla,gp.tamanioCasilla,null); 
    }
    
    //los efectos de sonido deben estar en 32 bits 
    
}
