/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import com.mycompany.ecoproyecto.GamePanel;
import com.mycompany.ecoproyecto.KeyHandler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    }
    public  void getPlayerImage(){
        try{
            //C:\Users\Cris\Documents\NetBeansProjects\Ecoproyecto\src\main\java\Player\jg_abj_01.png
            up1= ImageIO.read(getClass().getResourceAsStream("/Player/jg_abj_01.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/Player/jg_arr_02.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/Player/jg_abj_01.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/Player/jg_abj_02.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/Player/jg_der_01.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/Player/jg_der_02.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/Player/jg_izq_01.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/Player/jg_izq_02.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    public void update(){
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
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.titleSize, gp.titleSize);
    }
}
