package ep.ecoproyecto.logica.objetos;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 * Clase que defina el objeto con el sprite de mesa
 * @author C-A-F
 */
public class ObjetoMesa extends Objetosclase{
     /**
     * constructor de objetos para mesa
     * @param nombre nombre de la mesa
     * @param posicionX posicion en horizontal
     * @param posicionY posicion en vertical
     * @param pJuego panel donde se dibujará
     */
    public ObjetoMesa(String nombre, int posicionX, int posicionY, PanelJuego pJuego) {
        super(pJuego);
        this.nombre=nombre;
        this.xMapa=posicionX*pJuego.tamanioCasilla;
        this.yMapa=posicionY*pJuego.tamanioCasilla;
        down1= this.configuracion("/objetos/mesa");
        colision=true;
        hitBox= new Rectangle(0,0,64,64);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
    }
    
}
