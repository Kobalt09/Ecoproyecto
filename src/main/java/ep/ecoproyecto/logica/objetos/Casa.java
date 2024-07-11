/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.objetos;

import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 *
 * @author C-A-F
 */
public class Casa extends Objetosclase {
    
    public Casa(String nombre, int posicionX, int posicionY, PanelJuego pJuego) {
        super(pJuego);
        this.nombre=nombre;
        this.xMapa=posicionX*pJuego.tamanioCasilla;
        this.yMapa=posicionY*pJuego.tamanioCasilla;
        down1= this.configuracion("/objetos/casa");
        colision=true;
        hitBox= new Rectangle(0,0,256,128);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
    }
}
