package ep.ecoproyecto.logica.minijuegos;

import ep.ecoproyecto.gui.PanelJuego;

/**
 * Define el comportamiento basico para minijuegos
 * @author C-A-F
 */

public abstract class Minijuego {
    PanelJuego pJuego;
    public boolean empezado, terminado;
    
    
    public Minijuego(PanelJuego pJuego) {
        this.pJuego = pJuego;
        empezado=false;
        terminado=false;
    }
    
    
    
    public abstract void interaccion();
    
    public abstract void generarobjetos();
    
    public abstract void comprobante();
}
