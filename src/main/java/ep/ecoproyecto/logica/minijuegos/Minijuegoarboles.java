package ep.ecoproyecto.logica.minijuegos;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Agujero;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;

/**
 * Maneja el evento del minijuego de siembra
 * @author C-A-F
 */
public class Minijuegoarboles extends Minijuego{
    /**
     * Constructor de la clase
     * @param pJuego Panel donde se ubicará la clase
     */
    public Minijuegoarboles(PanelJuego pJuego) {
        super(pJuego);
    }
    
    /**
     * comprueba el estado del evento
     */
    
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
   
    
    /**
     * genera los objetos del evento
     */
    
    @Override
    public void generarobjetos(){
        
        //objetos
        pJuego.obj[4][5]=new ObjetoRecogible("semilla", 16, 21, pJuego);
        pJuego.obj[4][6]=new ObjetoRecogible("semilla", 10, 23, pJuego);
        pJuego.obj[4][7]=new ObjetoRecogible("semilla", 13, 17, pJuego);
        pJuego.obj[4][8]=new ObjetoRecogible("semilla", 18, 15, pJuego);
        pJuego.obj[4][9]=new ObjetoRecogible("semilla", 15, 13, pJuego);
        pJuego.obj[4][10]=new ObjetoRecogible("semilla",11, 8, pJuego);
        //Npc
        

    }
    /**
     * comprueba que el evento haya teminado
     */
    @Override
    public void comprobante(){    
        terminado=true;
       
        
        for(int i=5;i<=10;i++){
            if(pJuego.entidades[pJuego.mapaActual][i] instanceof Agujero Aux){
                if(Aux.estado.equals("Agujerovacio")){
                    terminado=false;
                }
            }
        }
    }
    
}
