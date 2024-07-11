package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.net.packets.Packet03Mapa;
/**
 *
 * @author C-A-F
 */

public class ControladorEventos {
    PanelJuego gp;
    
    public ControladorEventos(PanelJuego gp){
        this.gp=gp;   
    }

    
    //efectos de sonido
    public void eventSonido(int i){
        gp.efectos(2);
    }
    
 
    public void musicatienda(){
        gp.musica= gp.reproducirmusicatienda(gp.musica);
    }
    
    public void mensaje(){
        gp.hud.mostrarmensaje(gp.NPC[0][0].mensaje);
    }
    
    //desplazar al jugador
    public void tpcasilla(int x,int y, int mapa){
        gp.jugador.xMapa=(x*gp.tamanioCasilla)+gp.tamanioCasilla;
        gp.jugador.yMapa=(y*gp.tamanioCasilla)+gp.tamanioCasilla;
        gp.mapaActual=mapa;
        gp.mini.activarmini(1, 1);

        gp.jugador.setMapa(mapa);
        gp.manCas.actualizar(gp.jugador, gp.screenWidth, gp.screenHeight);
        
        Packet03Mapa packet =new Packet03Mapa(mapa, gp.jugador.getUsername());
        packet.writeData(gp.socketcliente);
        //rectanguloEvento[col][fil][mapacasilla].Activado=true;
    }
    
    public void tpinteractuar(int x,int y, int mapa){
        
        gp.jugador.xMapa=(x*gp.tamanioCasilla)+gp.tamanioCasilla;
        gp.jugador.yMapa=(y*gp.tamanioCasilla)+gp.tamanioCasilla;
        gp.mapaActual=mapa;       
        gp.jugador.setMapa(mapa);
        gp.manCas.actualizar(gp.jugador, gp.screenWidth, gp.screenHeight);
        
        
        Packet03Mapa packet =new Packet03Mapa(mapa, gp.jugador.getUsername());
        packet.writeData(gp.socketcliente);
        
    }
 
}