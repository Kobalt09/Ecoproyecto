package ep.ecoproyecto.logica.objetos;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 * Objetos que agregan algo al personaje en jugabilidad
 * @author C-A-F
 */

public class ObjetoEquipo extends Objetosclase{
      
    /**
     * constructor para el equipo
     * @param nombre nombre del objeto
     * @param posicionX posicion en horizontal
     * @param posicionY posicion en vertical
     * @param pJuego panel donde se dibujará
     */
    
    public ObjetoEquipo(String nombre, int posicionX, int posicionY, PanelJuego pJuego) {
        super(pJuego);
        this.nombre=nombre;
        this.xMapa=posicionX*pJuego.tamanioCasilla;
        this.yMapa=posicionY*pJuego.tamanioCasilla;
        down1= this.configuracion("/objetos/bota1");
        colision=true;
        hitBox= new Rectangle(0,0,64,64);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
    }
    /**
     * metodo get del precio
     * @return retorna el precio del objeto
     */
    @Override
    public int getPrecio() {
        return precio;
    }

    /**
     * metodo set del precio
     * @param precio precio del objeto
     */
    @Override
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
}
