/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cris
 */
public class Herramientas {
    public  BufferedImage imagenEscalada(BufferedImage original, int ancho, int alto){
        
        BufferedImage imagenescalada= new BufferedImage(ancho,alto, original.getType());
        Graphics2D g2 = imagenescalada.createGraphics();
        g2.drawImage(original,0,0,ancho,alto,null);
        g2.dispose();
        
        return imagenescalada;
    }
}
