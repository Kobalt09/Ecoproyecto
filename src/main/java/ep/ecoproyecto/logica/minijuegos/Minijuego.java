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
