package ep.ecoproyecto.logica.objetos;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Entidad;

/**
 * Define una base para los objetos
 * @author C-A-F
 */

public class Objetosclase extends Entidad{

    public int precio;
            
    /**
     * Panel para dibujar el objeto
     * @param pJuego panel de juego 
     */
    public Objetosclase(PanelJuego pJuego) {
        super(pJuego);
    }
    
    /**
     * @return precio del objeto 
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @param precio permite poner un precio externamente
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

}

