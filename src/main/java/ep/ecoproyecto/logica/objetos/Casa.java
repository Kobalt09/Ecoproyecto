package ep.ecoproyecto.logica.objetos;

import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 * Casa con las dimensiones de 3x2
 * @author C-A-F
 */

public class Casa extends Objetosclase {
      /**
     * constructor de clase casa
     * @param nombre nombre de la casa
     * @param posicionX posicion en horizontal
     * @param posicionY posicion en vertical
     * @param pJuego panel donde se dibujará
     */
    
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
