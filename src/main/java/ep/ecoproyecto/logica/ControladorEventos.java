package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.net.packets.Paquete03Mapa;

/**
 * Se encarga de eventos de interacciones
 * @author C-A-F
 */

public class ControladorEventos {
    PanelJuego pJuego;
    /**
     * Constructor de la clase
     * @param pJuego Panel donde se ubicará la clase
     */
    public ControladorEventos(PanelJuego pJuego){
        this.pJuego=pJuego;   
    }

    /**
     * reproduce un efecto de sonido
     * @param i indice del efecto de sonido
     */
    
    public void eventSonido(int i){
        pJuego.efectos(2);
    }
    
    /**
    * Reproduce la musica de tienda 
    */
    public void musicaTienda(){
        pJuego.musica= pJuego.reproducirmusicatienda(pJuego.musica);
    }
    
    /**
     * Teletransporta a un jugador a una posicion despues de interactuar con algo
     * @param x posicion horizontal
     * @param y posicion vertical
     * @param mapa mapa al que se transportará
     */
    
    public void tpInteractuar(int x,int y, int mapa){
        
        pJuego.jugador.xMapa=(x*pJuego.tamanioCasilla)+pJuego.tamanioCasilla;
        pJuego.jugador.yMapa=(y*pJuego.tamanioCasilla)+pJuego.tamanioCasilla;
        pJuego.mapaActual=mapa;       
        pJuego.jugador.setMapa(mapa);
        pJuego.manCas.actualizar();
        
        
        Paquete03Mapa packet =new Paquete03Mapa(mapa, pJuego.jugador.getUsername());
        packet.writeData(pJuego.socketCliente);
        
    }
 
}