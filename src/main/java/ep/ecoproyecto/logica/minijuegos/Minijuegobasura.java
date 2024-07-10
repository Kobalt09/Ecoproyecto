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
public class Minijuegobasura extends Minijuego{
    
    public Minijuegobasura(PanelJuego gp) {
        super(gp);
    }
    
    @Override
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
        gp.obj[5][9]=new ObjetoRecogible("basura",  20, 6, gp);
        gp.obj[5][10]=new ObjetoRecogible("basura", 16,12, gp);
        gp.obj[5][11]=new ObjetoRecogible("basura", 13,11, gp);
        gp.obj[5][12]=new ObjetoRecogible("basura", 14,13, gp);
        gp.obj[5][13]=new ObjetoRecogible("basura", 9,9, gp);
        gp.obj[5][14]=new ObjetoRecogible("basura", 8,17, gp);
        gp.obj[5][15]=new ObjetoRecogible("basura", 13,19, gp);
        gp.obj[5][16]=new ObjetoRecogible("basura", 10,22, gp);
        gp.obj[5][17]=new ObjetoRecogible("basura", 15,23, gp);
        //Npc
        
        //gp.NPC[5][5]=new Papelera(gp,15,20);
    }
    
    @Override
    public void comprobante(){    
        Terminado=true;
       
        
        for(int i=9;i<=17;i++){
            if(gp.obj[gp.mapaActual][i] !=null){
                Terminado=false;
            }
        }
    }
    
}
