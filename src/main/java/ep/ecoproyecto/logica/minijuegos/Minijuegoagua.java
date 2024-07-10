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
    
        public void interacion(){
        if(Terminado==false && Empezado==false){
            Empezado=true;
            generarobjetos();
        }else if(Empezado==true){
            comprobante();
            if(Terminado==true){
                Empezado=false;
            }
        }     
    }
   
    
    
    
    @Override
    public void generarobjetos(){
        //Npc
        gp.NPC[3][5]=new Aguaconbasura(gp,10,6);
        gp.NPC[3][6]=new Aguaconbasura(gp,14,9);
        gp.NPC[3][7]=new Aguaconbasura(gp,18,20);
        gp.NPC[3][8]=new Aguaconbasura(gp,21,10);
    }
    
    @Override
    public void comprobante(){    
        Terminado=true;
        for(int i=5;i<8;i++){
            if(gp.NPC[gp.mapaActual][i] instanceof Agujero Aux){
                if(Aux.estado.equals("Aguasucia")){
                    Terminado=false;
                }
            }
        }
    }
}
