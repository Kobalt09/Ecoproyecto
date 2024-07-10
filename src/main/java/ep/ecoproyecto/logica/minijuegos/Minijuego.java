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
    public boolean Empezado, Terminado;
    
    
    public Minijuego(PanelJuego gp) {
        this.gp = gp;
        Empezado=false;
        Terminado=false;
    }
    
    
    
    public abstract void interacion();
    
    public abstract void generarobjetos();
    
    public abstract void comprobante();
}
