/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Panel;
import ep.ecoproyecto.logica.minijuegos.Minijuegoagua;
import ep.ecoproyecto.logica.minijuegos.Minijuegoarboles;
import ep.ecoproyecto.logica.minijuegos.Minijuegobasura;

/**
 *
 * @author fabif
 */
public class ControladorMinijuegos {
    //public Minijuego minijuegos;
    public PanelJuego gp;

    public ControladorMinijuegos(PanelJuego gp) {
        this.gp = gp;
    }
    
    public void establecerminijuegos(){
        
        
                //----MUNDO 1:-----//
        int NumMap = 0;
        /*
        gp.Minijuego[NumMap][0];= new chiguire(gp,10,7);
        */
        
        //----MUNDO 2:-----//
        NumMap = 1;
        //gp.Minijuego[NumMap][0]= new Minijuegoarboles(gp);
        //gp.Minijuego[NumMap][1]= new Minijuegobasura(gp);
        /*
        //----MUNDO 3:-----//
        
        
        NumMap = 2;

         */
        //----MUNDO 4:-----derecha//
        NumMap = 3;
        gp.minijuego[NumMap][0]= new Minijuegoagua(gp);
        
        
        //----MUNDO 5:-----arriba// 
        NumMap = 4;
        gp.minijuego[NumMap][0]= new Minijuegobasura(gp);
        //----MUNDO 6:-----izquierda//
            NumMap = 5;
        gp.minijuego[NumMap][0]= new Minijuegoarboles(gp);
                  
    }
    
    public void activarmini(int mapa, int num){
        gp.minijuego[mapa][num].interacion();
    }
    
    
}
