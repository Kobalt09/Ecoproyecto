/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Panel;
import minijuegos.Minijuegoarboles;
import minijuegos.Minijuegobasura;

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
        gp.Minijuego[NumMap][0]= new Minijuegoarboles(gp);
        //gp.Minijuego[NumMap][1]= new Minijuegobasura(gp);
        /*
        //----MUNDO 3:-----//
        
        
        NumMap = 2;

         */
        //----MUNDO 4:-----//
        NumMap = 3;
        gp.Minijuego[NumMap][0]= new Minijuegoarboles(gp);
        
        /*
        //----MUNDO 5:-----//
        NumMap = 4;
        */           
    }
    
    public void activarmini(int mapa, int num){
        gp.Minijuego[mapa][num].interacion();
    }
    
    
}
