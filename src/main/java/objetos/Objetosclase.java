/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import ep.ecoproyecto.PanelJuego;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cris
 */
public class Objetosclase {
    
    public BufferedImage image;
    public String nombre;
    public boolean colision=false;
    public int posicionX, posicionY;
    public Rectangle Areasolida = new Rectangle(0,0,32,32);

    public int AreaobjdefectoX=Areasolida.x;
    public int AreaobjdefectoY=Areasolida.y;
    public PanelJuego gp;

    public Objetosclase( String nombre, int posicionX, int posicionY, PanelJuego gp) {
        
        this.nombre = nombre;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.gp = gp;
    }
    
    
}

