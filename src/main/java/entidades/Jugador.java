/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//
package Entidades;
import ep.ecoproyecto.GamePanel;
import ep.ecoproyecto.KeyHandler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Cris
 */
public class Jugador extends Entidad{
    
    GamePanel gp;
    KeyHandler keyH;
    String direction;
    
    public Jugador(GamePanel gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;
        
        valoresporDefecto();
        getPlayerImage();
    }
    
    public void valoresporDefecto(){
        x=100;
        y=100;
        vel=4;
        direction ="up"; 
    }
    public void getPlayerImage(){
        try{
            //C:\Users\Cris\Documents\NetBeansProjects\Ecoproyecto\src\main\java\Player\jg_abj_01.png
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
            y-=vel;
        }else if(keyH.leftPressed==true){
            direction="left";
            x-=vel;
        }else if(keyH.downPressed==true){
            direction="down";
            y+=vel;
        }else if(keyH.rightPressed==true){
            direction="right";
            x+=vel;
        }
        if (spriteCounter>1){
            if (spriteNum == 2 )
            {spriteNum=1;}
            else{
            if (spriteNum == 1)
            spriteNum=2;
            }
            spriteCounter = 0;
        }
        
        } 
        spriteCounter++;
        
        
       
    }
    public void draw(Graphics2D g2){
        
       // g2.setColor(Color.white);
       // g2.fillRect(x, y, gp.titleSize, gp.titleSize);
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
        
       
        
       
       g2.drawImage(image,x, y, gp.titleSize,gp.titleSize,null); 
    }
}
