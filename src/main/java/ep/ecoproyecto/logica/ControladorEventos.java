package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.net.packets.Paquete03Mapa;
/**
 *
 * @author C-A-F
 */

public class ControladorEventos {
    PanelJuego pJuego;
    
    public ControladorEventos(PanelJuego pJuego){
        this.pJuego=pJuego;   
    }

    
    //efectos de sonido
    public void eventSonido(int i){
        pJuego.efectos(2);
    }
    
 
    public void musicaTienda(){
        pJuego.musica= pJuego.reproducirmusicatienda(pJuego.musica);
    }
    
    //desplazar al jugador
    public void tpCasilla(int x,int y, int mapa){
        pJuego.jugador.xMapa=(x*pJuego.tamanioCasilla)+pJuego.tamanioCasilla;
        pJuego.jugador.yMapa=(y*pJuego.tamanioCasilla)+pJuego.tamanioCasilla;
        pJuego.mapaActual=mapa;
        pJuego.mini.activarMini(1, 1);

        pJuego.jugador.setMapa(mapa);
        pJuego.manCas.actualizar(pJuego.jugador, pJuego.screenWidth, pJuego.screenHeight);
        
        Paquete03Mapa packet =new Paquete03Mapa(mapa, pJuego.jugador.getUsername());
        packet.writeData(pJuego.socketCliente);
       
    }
    
    public void tpInteractuar(int x,int y, int mapa){
        
        pJuego.jugador.xMapa=(x*pJuego.tamanioCasilla)+pJuego.tamanioCasilla;
        pJuego.jugador.yMapa=(y*pJuego.tamanioCasilla)+pJuego.tamanioCasilla;
        pJuego.mapaActual=mapa;       
        pJuego.jugador.setMapa(mapa);
        pJuego.manCas.actualizar(pJuego.jugador, pJuego.screenWidth, pJuego.screenHeight);
        
        
        Paquete03Mapa packet =new Paquete03Mapa(mapa, pJuego.jugador.getUsername());
        packet.writeData(pJuego.socketCliente);
        
    }
 
}