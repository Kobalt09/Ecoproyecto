/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.minijuegos;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Aguaconbasura;
import ep.ecoproyecto.logica.entidades.Agujero;
import ep.ecoproyecto.logica.entidades.Papelera;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;

/**
 *
 * @author Cris
 */
public class Minijuegoagua extends Minijuego{
    
    public Minijuegoagua(PanelJuego gp) {
        super(gp);
    }
    
    @Override
    public void interaccion(){
        if(terminado==false && empezado==false){
            empezado=true;
            //generarobjetos();
        }else if(empezado==true){
            comprobante();
            if(terminado==true){
                empezado=false;
            }
        }     
    }
   
    
    @Override
    public void generarobjetos(){
        //Npc

    }
    
    @Override
    public void comprobante(){    
        terminado=true;
        for(int i=5;i<8;i++){
            if(gp.NPC[gp.mapaActual][i] instanceof Agujero Aux){
                if(Aux.estado.equals("Aguasucia")){
                    terminado=false;
                }
            }
        }
    }
}
