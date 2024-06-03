/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cris
 */
public class Entidad {
    public int xMapa,yMapa;
    //vel= velocidad,
    public int vel;
    
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;
    
    public int spriteCounter = 0 ;
    public int spriteNum = 1;
    public Rectangle hitBox;
    public boolean colision;
    
}
