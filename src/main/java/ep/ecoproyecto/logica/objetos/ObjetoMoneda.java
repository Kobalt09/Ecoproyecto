/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.objetos;

import ep.ecoproyecto.logica.entidades.Entidad;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 *
 * @author Cris
 */
public class ObjetoMoneda extends Entidad{
    
    public ObjetoMoneda(String nombre, int posicionX, int posicionY, PanelJuego gp) {
        super(gp);
        this.nombre=nombre;
        this.xMapa=posicionX*gp.tamanioCasilla;
        this.yMapa=posicionY*gp.tamanioCasilla;
        down1= this.configuracion("/objetos/coin");
        colision=false;
        hitBox= new Rectangle(16,16,32,32);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
    }
}
