/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.minijuegos;

import ep.ecoproyecto.gui.PanelJuego;

/**
 *
 * @author fabif
 */
public abstract class Minijuego {
    PanelJuego gp;
    public boolean empezado, terminado;
    
    
    public Minijuego(PanelJuego gp) {
        this.gp = gp;
        empezado=false;
        terminado=false;
    }
    
    
    
    public abstract void interaccion();
    
    public abstract void generarobjetos();
    
    public abstract void comprobante();
}
