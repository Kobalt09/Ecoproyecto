/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minijuegos;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Agujero;
import ep.ecoproyecto.logica.objetos.ObjetoEquipo;
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
        gp.obj[5][5]=new ObjetoRecogible("semilla", 10, 16, gp);
        gp.obj[5][6]=new ObjetoRecogible("semilla", 9, 20, gp);
        gp.obj[5][7]=new ObjetoRecogible("semilla", 15, 22, gp);
        gp.obj[5][8]=new ObjetoRecogible("semilla", 15, 5, gp);
        //Npc
        
        gp.NPC[5][5]=new Agujero(gp,10,6);
        gp.NPC[5][6]=new Agujero(gp,14,9);
        gp.NPC[5][7]=new Agujero(gp,18,20);
        gp.NPC[5][8]=new Agujero(gp,21,10);
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
