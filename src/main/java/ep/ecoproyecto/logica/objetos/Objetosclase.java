package ep.ecoproyecto.logica.objetos;
import ep.ecoproyecto.logica.Herramientas;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Entidad;
import java.awt.Rectangle;

/**
 *
 * @author C-A-F
 */
public class Objetosclase extends Entidad{

    public int precio;
            
    public Objetosclase(PanelJuego pJuego) {
        super(pJuego);
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

}

