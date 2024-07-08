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
        gp.obj[4][9]=new ObjetoRecogible("basura", 20, 20, gp);
        gp.obj[4][10]=new ObjetoRecogible("basura", 15,15, gp);
        gp.obj[4][11]=new ObjetoRecogible("basura", 19, 21, gp);
        gp.obj[4][12]=new ObjetoRecogible("basura", 14, 13, gp);
        
        //Npc
        
        gp.NPC[4][5]=new Papelera(gp,15,20);
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
