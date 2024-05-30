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
    
    
    public void dibujar(Graphics2D g2, PanelJuego gp){
        int tamanio = 64;
        
        int columna=0;
        int fila=0;
        int x=0;
        int y=0;
        
        while (columna < gp.maxColumnas && fila <gp.maxFilas){
            
            int numCasilla= numCasillaMapa[columna][fila];
            
            
            g2.drawImage(casilla[numCasilla].imagen, x, y, gp.tamanioCasilla,gp.tamanioCasilla,null);
            columna++;
            x+=gp.tamanioCasilla;
            if(columna==gp.maxColumnas){
                columna=0;
                x=0;
                fila++;
                y+=gp.tamanioCasilla;
            }
        } 
    }
}
