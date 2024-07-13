package ep.ecoproyecto.logica.entidades;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.KeyHandler;
import java.net.InetAddress;

/**
 * Define los jugadores para el multijugador
 * @author C-A-F
 */

public class JugadorMP extends Jugador{
    public InetAddress direccionIP;
    public int puerto;
/**
 * Constructor con keyhandler asignado
 * @param direccionIP direccion ip de la computadora
 * @param puerto puerto para la conexion
 * @param pJuego Panel de juego del jugador
 * @param keyH manejador de teclas
 * @param username Nombre de usuario
 */
    public JugadorMP(InetAddress direccionIP, int puerto, PanelJuego pJuego, KeyHandler keyH, String username) {
        super(pJuego, keyH, username);
        
        this.direccionIP = direccionIP;
        this.puerto = puerto;
    }
    /**
    * Constructor con ubicacion del jugador
    * @param direccionIP direccion ip de la computadora
    * @param puerto puerto para la conexion
    * @param pJuego Panel de juego del jugador
    * @param username Nombre de usuario
    * @param xMapa posicion en horizontal del jugador
    * @param yMapa posicion en vertical del jugador
    * @param dir direccion a la que mira el jugador
    * @param mapa mapa en el que esta el jugador
    */
    public JugadorMP(InetAddress direccionIP, int puerto, PanelJuego pJuego,String username,int xMapa, int yMapa,String dir,int mapa) {
        
        super(pJuego, null, username);
        this.direccionIP = direccionIP;
        this.puerto = puerto;
        this.xMapa=xMapa;
        this.yMapa=yMapa;
        this.direction=dir;
        this.mapa=mapa;
    }
     /**
      * metodo que actualiza los datos del jugador
      */
    @Override
    public void update(){
        super.update();
    }
    
    
}
