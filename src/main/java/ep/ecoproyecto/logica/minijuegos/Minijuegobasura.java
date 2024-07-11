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
    
    public Minijuegobasura(PanelJuego pJuego) {
        super(pJuego);
    }
    
    @Override
    public void interaccion(){
        if(terminado==false && empezado==false){
            empezado=true;
            generarobjetos();
        }else if(empezado==true){
            comprobante();
            if(terminado==true){
                empezado=false;
            }
        }     
    }
    
    @Override
    public void generarobjetos(){
        //objetos
        pJuego.obj[5][9]=new ObjetoRecogible("basura",  20, 6, pJuego);
        pJuego.obj[5][10]=new ObjetoRecogible("basura", 16,12, pJuego);
        pJuego.obj[5][11]=new ObjetoRecogible("basura", 13,11, pJuego);
        pJuego.obj[5][12]=new ObjetoRecogible("basura", 14,13, pJuego);
        pJuego.obj[5][13]=new ObjetoRecogible("basura", 9,9, pJuego);
        pJuego.obj[5][14]=new ObjetoRecogible("basura", 8,17, pJuego);
        pJuego.obj[5][15]=new ObjetoRecogible("basura", 13,19, pJuego);
        pJuego.obj[5][16]=new ObjetoRecogible("basura", 10,22, pJuego);
        pJuego.obj[5][17]=new ObjetoRecogible("basura", 15,23, pJuego);
        //Npc
        
        //pJuego.NPC[5][5]=new Papelera(pJuego,15,20);
    }
    
    @Override
    public void comprobante(){    
        terminado=true;
       
        
        for(int i=9;i<=17;i++){
            if(pJuego.obj[pJuego.mapaActual][i] !=null){
                terminado=false;
            }
        }
    }
    
}
