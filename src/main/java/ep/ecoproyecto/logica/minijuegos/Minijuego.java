/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.minijuegos;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;

/**
 *
 * @author fabif
 */
public class Minijuego {
    PanelJuego gp;
    public boolean Empezado, Terminado;
    
    
    public Minijuego(PanelJuego gp) {
        this.gp = gp;
        Empezado=false;
        Terminado=false;
    }
    
    
    
    public void interacion(){}
    
    public void generarobjetos(){}
    
    public void comprobante(){}
}
