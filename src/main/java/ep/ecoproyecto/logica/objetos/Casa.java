/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.objetos;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Entidad;
import java.awt.Rectangle;

/**
 *
 * @author hp
 */
public class Casa extends Objetosclase {
    
      public Casa(String nombre, int posicionX, int posicionY, PanelJuego gp) {
        super(gp);
        this.nombre=nombre;
        this.xMapa=posicionX*gp.tamanioCasilla;
        this.yMapa=posicionY*gp.tamanioCasilla;
        down1= this.configuracion("/objetos/casa");
        colision=true;
        hitBox= new Rectangle(0,0,256,128);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
    }
}
