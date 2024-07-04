/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minijuegos;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Agujero;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;

/**
 *
 * @author fabif
 */
public class Minijuegoarboles extends Minijuego{

    public Minijuegoarboles(PanelJuego gp) {
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
        //objetos
        gp.obj[gp.mapaActual][5]=new ObjetoRecogible("semilla", 3, 3, gp);
        gp.obj[gp.mapaActual][6]=new ObjetoRecogible("semilla", 4, 3, gp);
        gp.obj[gp.mapaActual][7]=new ObjetoRecogible("semilla", 3, 3, gp);
        gp.obj[gp.mapaActual][8]=new ObjetoRecogible("semilla", 4, 3, gp);
        
        //Npc
        
        gp.NPC[gp.mapaActual][5]=new Agujero(gp,4,5);
        gp.NPC[gp.mapaActual][6]=new Agujero(gp,5,5);
        gp.NPC[gp.mapaActual][7]=new Agujero(gp,6,5);
        gp.NPC[gp.mapaActual][8]=new Agujero(gp,7,5);
    }
    
    @Override
    public void comprobante(){    
        Terminado=true;
       
        
        for(int i=5;i<8;i++){
            if(gp.NPC[gp.mapaActual][i] instanceof Agujero Aux){
                if(Aux.estado.equals("Agujerovacio")){
                    Terminado=false;
                }
            }
        }
    }
    
}
