package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.minijuegos.Minijuegoagua;
import ep.ecoproyecto.logica.minijuegos.Minijuegoarboles;
import ep.ecoproyecto.logica.minijuegos.Minijuegobasura;

/**
 * Establece los minijuegos para cada mapa
 * @author C-A-F
 */
public class ControladorMinijuegos {
    //public Minijuego minijuegos;
    public PanelJuego pJuego;
    
    /**
     * Constructor de la clase
     * @param pJuego Panel donde se ubicará la clase
     */
    public ControladorMinijuegos(PanelJuego pJuego) {
        this.pJuego = pJuego;
    }
    /**
     * pone los minijuegos en cada mapa
     */
    public void establecerminijuegos(){
        
        
        //----MUNDO 1:-----//
        int NumMap = 0;
       
        
        //----MUNDO 2:-----//
        NumMap = 1;
       
        //----MUNDO 3:-----//
              
        NumMap = 2;
        //----MUNDO 4:-----derecha//
        NumMap = 3;
        pJuego.minijuego[NumMap][0]= new Minijuegoagua(pJuego);
        
        
        //----MUNDO 5:-----arriba// 
        NumMap = 4;
        pJuego.minijuego[NumMap][0]= new Minijuegobasura(pJuego);
        //----MUNDO 6:-----izquierda//
            NumMap = 5;
        pJuego.minijuego[NumMap][0]= new Minijuegoarboles(pJuego);
                  
    }
    /**
     * Activa un minijuego
     * @param mapa mapa del minijuego
     * @param num numero del minijuego
     */
    public void activarMini(int mapa, int num){
        pJuego.minijuego[mapa][num].interaccion();
    }
    
    
}
