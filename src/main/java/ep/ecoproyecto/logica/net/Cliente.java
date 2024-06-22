package ep.ecoproyecto.logica.net;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.JugadorMP;
import ep.ecoproyecto.logica.net.packets.Packet;
import static ep.ecoproyecto.logica.net.packets.Packet.PacketTypes.DISCONNECT;
import static ep.ecoproyecto.logica.net.packets.Packet.PacketTypes.INVALID;
import static ep.ecoproyecto.logica.net.packets.Packet.PacketTypes.LOGIN;
import ep.ecoproyecto.logica.net.packets.Packet00Login;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente extends Thread{
    private InetAddress direccionIP;
    private DatagramSocket socket;
    private PanelJuego juego;

    public Cliente(String direccionIP, PanelJuego juego) {
        this.juego = juego;
        try {
            this.socket = new DatagramSocket();
            this.direccionIP = InetAddress.getByName(direccionIP);
        } catch (SocketException | UnknownHostException ex) {
        }
    }
    
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
            parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }
    }
    
    private void parsePacket(byte[] data, InetAddress direccion, int puerto) {
        String mensaje = new String(data).trim();
        Packet.PacketTypes type = Packet.lookupPacket(mensaje.substring(0,2));
        Packet packet;
        switch(type){
            case INVALID->{
                System.out.println("Paquete invalido");
            }
            case LOGIN->{
                packet = new Packet00Login(data); 
                System.out.println("["+direccion.getHostAddress()+":"+puerto+"] "+((Packet00Login)packet).getUsername()+" se ha unido al juego.");
                JugadorMP jugador = new JugadorMP(direccion,puerto,juego,((Packet00Login)packet).getUsername());
                //REVISAR//
                juego.jugadores.add(jugador);
            }
            case DISCONNECT->{
                
            }
            default->{
                System.out.println("Paquete desconocido");
            }
        }
    }

    public void enviarData(byte[] data){
        DatagramPacket packet = new DatagramPacket(data, data.length, direccionIP, 1234);
        try {
            socket.send(packet);
        } catch (IOException ex) {
        }
    }
}
