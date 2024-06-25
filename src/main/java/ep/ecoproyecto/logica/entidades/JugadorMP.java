package ep.ecoproyecto.logica.entidades;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.KeyHandler;
import java.net.InetAddress;

public class JugadorMP extends Jugador{
    public InetAddress direccionIP;
    public int puerto;

    public JugadorMP(InetAddress direccionIP, int puerto, PanelJuego gp, KeyHandler keyH, String username) {
        super(gp, keyH, username);
        
        this.direccionIP = direccionIP;
        this.puerto = puerto;
    }
    
    public JugadorMP(InetAddress direccionIP, int puerto, PanelJuego gp,String username) {
        
        super(gp, null, username);
        this.direccionIP = direccionIP;
        this.puerto = puerto;
    }
    
    
    @Override
    public void update(){
        super.update();
    }
    
    
}
