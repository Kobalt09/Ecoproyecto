/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import ep.ecoproyecto.Herramientas;
import ep.ecoproyecto.PanelJuego;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cris
 */
public class Objetosclase {
      
    public int posicionX, posicionY;
    public PanelJuego gp;
    public Rectangle hitBox= new Rectangle(0,0,64,64);
    public int areadefectoX=hitBox.x;
    public int  areadefectoY=hitBox.y;
    Herramientas herramienta= new Herramientas();
    private final String nombre;


    public Objetosclase( String nombre, int posicionX, int posicionY, PanelJuego gp) {
        
        this.nombre = nombre;

        this.posicionX = posicionX*gp.tamanioCasilla;
        this.posicionY = posicionY*gp.tamanioCasilla;

        this.gp = gp;
        
    }
    
/*
    public void draw(Graphics2D g2) {
        
        image = null;
  
       g2.drawImage(image,this.posicionX, this.posicionY, gp.tamanioCasilla,gp.tamanioCasilla,null); 
    
    }*/

}

