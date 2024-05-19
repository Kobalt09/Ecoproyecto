/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casillas;

import ep.ecoproyecto.PanelJuego;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author hp
 */
public class ManejadorCasillas {
    
    PanelJuego gp;
    Casilla[] casilla;

    public ManejadorCasillas(PanelJuego gp) {
        this.gp = gp;
        casilla = new Casilla[10];
        getImagenCasilla();
        
        
    }
    
    public void getImagenCasilla(){
        try{
            casilla[0]=new Casilla();
            casilla[0].imagen= ImageIO.read(getClass().getResource("/casillas/pasto.png"));
            
            casilla[1]=new Casilla();
            casilla[1].imagen= ImageIO.read(getClass().getResource("/casillas/agua.png"));
            
            casilla[2]=new Casilla();
            casilla[2].imagen= ImageIO.read(getClass().getResource("/casillas/pared.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    
    }
    
    public void dibujar(Graphics2D g2){
        int tamanio = 64;
        
        g2.drawImage(casilla[0].imagen, 0, 0, gp.tamanioCasilla,gp.tamanioCasilla,null);
        g2.drawImage(casilla[1].imagen, 1*tamanio, 0, gp.tamanioCasilla,gp.tamanioCasilla,null);
        g2.drawImage(casilla[2].imagen, 2*tamanio, 0, gp.tamanioCasilla,gp.tamanioCasilla,null);
        g2.drawImage(casilla[0].imagen, 3*tamanio, 0, gp.tamanioCasilla,gp.tamanioCasilla,null);
        g2.drawImage(casilla[1].imagen, 4*tamanio, 0, gp.tamanioCasilla,gp.tamanioCasilla,null);
        g2.drawImage(casilla[2].imagen, 5*tamanio, 0, gp.tamanioCasilla,gp.tamanioCasilla,null);
        
        
        
        
        
        
        
    }
}
