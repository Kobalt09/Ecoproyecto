
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.objetos;


import ep.ecoproyecto.logica.entidades.Entidad;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Cris
 */
public class ObjetoRecogible extends Entidad{

    public ObjetoRecogible(String nombre, int posicionX, int posicionY, PanelJuego gp) {
        super(gp);
        this.nombre=nombre;
        this.xMapa=posicionX*gp.tamanioCasilla;
        this.yMapa=posicionY*gp.tamanioCasilla;
        down1= this.configuracion("/objetos/llave1");
        colision=true;
        
        hitBox= new Rectangle(0,0,64,64);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
    }
    
    
    
}