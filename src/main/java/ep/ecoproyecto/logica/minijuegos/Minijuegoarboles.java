/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.minijuegos;

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
        gp.obj[4][5]=new ObjetoRecogible("semilla", 16, 21, gp);
        gp.obj[4][6]=new ObjetoRecogible("semilla", 10, 23, gp);
        gp.obj[4][7]=new ObjetoRecogible("semilla", 13, 17, gp);
        gp.obj[4][8]=new ObjetoRecogible("semilla", 18, 15, gp);
        gp.obj[4][9]=new ObjetoRecogible("semilla", 15, 13, gp);
        gp.obj[4][10]=new ObjetoRecogible("semilla",11, 8, gp);
        //Npc
        

    }
    
    @Override
    public void comprobante(){    
        terminado=true;
       
        
        for(int i=5;i<=10;i++){
            if(gp.NPC[gp.mapaActual][i] instanceof Agujero Aux){
                if(Aux.estado.equals("Agujerovacio")){
                    terminado=false;
                }
            }
        }
    }
    
}
