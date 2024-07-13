package ep.ecoproyecto.logica.minijuegos;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Agujero;

/**
 * Se encarga de el minijuego de recoger basura en el agua
 * @author C-A-F
 */
public class Minijuegoagua extends Minijuego{
    
    public Minijuegoagua(PanelJuego pJuego) {
        super(pJuego);
    }
    
    @Override
    public void interaccion(){
        if(terminado==false && empezado==false){
            empezado=true;
            //generarobjetos();
        }else if(empezado==true){
            comprobante();
            if(terminado==true){
                empezado=false;
            }
        }     
    }
   
    
    @Override
    public void generarobjetos(){
        //Npc

    }
    
    @Override
    public void comprobante(){
        terminado=true;
        for(int i=5;i<=8;i++){
            if(pJuego.entidades[pJuego.mapaActual][i] instanceof Agujero Aux){
                if(Aux.estado.equals("Aguasucia")){
                    terminado=false;
                }
            }
        }
    }
}
