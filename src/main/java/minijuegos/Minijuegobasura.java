/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minijuegos;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Papelera;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;

/**
 *
 * @author fabif
 */
public class Minijuegobasura extends Minijuego{
    
    public Minijuegobasura(PanelJuego gp) {
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
        gp.obj[gp.mapaActual][9]=new ObjetoRecogible("basura", 20, 20, gp);
        gp.obj[gp.mapaActual][10]=new ObjetoRecogible("basura", 23,25, gp);
        gp.obj[gp.mapaActual][11]=new ObjetoRecogible("basura", 19, 21, gp);
        gp.obj[gp.mapaActual][12]=new ObjetoRecogible("basura", 24, 28, gp);
        
        //Npc
        
        gp.NPC[gp.mapaActual][5]=new Papelera(gp,15,25);
    }
    
    @Override
    public void comprobante(){    
        Terminado=true;
       
        
        for(int i=9;i<12;i++){
            if(gp.obj[gp.mapaActual][i] !=null){
                Terminado=false;
            }
        }
    }
    
}
