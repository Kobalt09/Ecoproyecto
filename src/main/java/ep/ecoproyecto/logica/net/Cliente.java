package ep.ecoproyecto.logica.net;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.JugadorMP;
import ep.ecoproyecto.logica.net.packets.Paquete;
import static ep.ecoproyecto.logica.net.packets.Paquete.PacketTypes.DISCONNECT;
import static ep.ecoproyecto.logica.net.packets.Paquete.PacketTypes.INVALID;
import static ep.ecoproyecto.logica.net.packets.Paquete.PacketTypes.LOGIN;
import ep.ecoproyecto.logica.net.packets.Paquete00Login;
import ep.ecoproyecto.logica.net.packets.Paquete02Movimiento;
import ep.ecoproyecto.logica.net.packets.Paquete01Desconectar;
import ep.ecoproyecto.logica.net.packets.Paquete03Mapa;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Jugador que establece conexion con el servidor
 * @author C-A-F
 */

public class Cliente extends Thread{
    private InetAddress direccionIP;
    private DatagramSocket socket;
    private PanelJuego pJuego;

    public Cliente(String direccionIP, PanelJuego pJuego) {
        this.pJuego = pJuego;
        try {
            this.socket = new DatagramSocket();
            this.direccionIP = InetAddress.getByName(direccionIP);
        } catch (SocketException | UnknownHostException ex) {
        }
    }
    /**
     * Ejecucion constante de llamadas
     */
    @Override
    public void run(){
        while(true){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException ex) {
            }
//            System.out.println("SERVER > "+ new String(packet.getData()));
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }
    }
      /**
     * Determina que paquete se recibe y que se hace con el
     * @param data data de un paquete
     * @param direccion ip de donde llega
     * @param puerto puerto del que llega
     */
    private void parsePacket(byte[] data, InetAddress direccion, int puerto) {
        String mensaje = new String(data).trim();
        Paquete.PacketTypes type = Paquete.lookupPacket(mensaje.substring(0,2));
        Paquete packet;
        switch(type){
            case INVALID->{
                System.out.println("Paquete invalido");
            }
            case LOGIN->{
                packet = new Paquete00Login(data); 
                System.out.println("["+direccion.getHostAddress()+":"+puerto+"] "+((Paquete00Login)packet).getUsername()+" se ha unido al juego.");
                JugadorMP jugador = new JugadorMP(direccion,puerto,pJuego,((Paquete00Login)packet).getUsername(),((Paquete00Login)packet).getX(),((Paquete00Login)packet).getY(),((Paquete00Login)packet).getDir(),((Paquete00Login)packet).getMapa());
                pJuego.jugadores.add(jugador);
            }
            case DISCONNECT->{
                packet = new Paquete01Desconectar(data); 
                System.out.println("["+direccion.getHostAddress()+":"+puerto+"] "+((Paquete01Desconectar)packet).getUsername()+" se ha ido del juego.");
                pJuego.removePlayerMP(((Paquete01Desconectar)packet).getUsername());
            }
            case MOVE->{
                packet = new Paquete02Movimiento(data);
                this.manejarMov((Paquete02Movimiento)packet);
            }
             case CAMBIO->{
                packet =new Paquete03Mapa(data);
                this.cambioMapa((Paquete03Mapa)packet);
            }
            default->{
                System.out.println("Paquete desconocido");
            }
        }
    }
    /**
     * obtiene la ip del servidor
     * @return string de la direccion
     */
    public String getServerIP() {
        return direccionIP.getHostAddress();
    }
    /**
     * envia datos empaquetados
     * @param data datos a empaquetar
     */
    public void enviarData(byte[] data){
        DatagramPacket packet = new DatagramPacket(data, data.length, direccionIP, 1234);
        try {
            socket.send(packet);
        } catch (IOException ex) {
        }
    }
    /**
     * mueve a los otros jugadores 
     * @param packet paquete que llega con la informacion de los otros jugadores
     */
    private void manejarMov(Paquete02Movimiento packet) {
        this.pJuego.moverJugadores(packet.getUsername(), packet.getX(), packet.getY(),packet.getDir());          
    }

    /**
     * cambia de mapa a los jugadores
     * @param packet paquete que llega con la informacion de los otros jugadores
     */
    private void cambioMapa(Paquete03Mapa packet) {
       pJuego.cambiarMapa(packet.getUsername(),packet.getMapa());
    }
    
}
