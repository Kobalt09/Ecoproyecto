/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import ep.ecoproyecto.PanelJuego;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cris
 */
public class Objetosclase {
    
    public BufferedImage image;
    public String nombre;
    public boolean colision=false;
    public int posicioX, posicionY;
    public PanelJuego gp;

    public Objetosclase( String nombre, int posicioX, int posicionY, PanelJuego gp) {
        
        this.nombre = nombre;
        this.posicioX = posicioX;
        this.posicionY = posicionY;
        this.gp = gp;
    }
    
    
 
    public void draw(Graphics2D g2) {
       // g2.setColor(Color.white);
       // g2.fillRect(x, y, gp.titleSize, gp.titleSize);
        image = null;  
  
       g2.drawImage(image,this.posicioX, this.posicionY, gp.tamanioCasilla,gp.tamanioCasilla,null); 
    
    }
}

