package ep.ecoproyecto.logica.minijuegos;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;

/**
 * Maneja el evento del minijuego de recoger basura
 * @author C-A-F
 */
public class Minijuegobasura extends Minijuego{
    /**
     * Constructor de la clase
     * @param pJuego Panel donde se ubicará la clase
     */
    public Minijuegobasura(PanelJuego pJuego) {
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
        
        //pJuego.entidades[5][5]=new Papelera(pJuego,15,20);
    }
    /**
     * comprueba que el evento haya terminado
     */
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
