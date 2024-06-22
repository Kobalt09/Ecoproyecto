package ep.ecoproyecto.logica.net;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.casillas.ManejadorCasillas;
import ep.ecoproyecto.logica.entidades.JugadorMP;
import ep.ecoproyecto.logica.net.packets.Packet;
import ep.ecoproyecto.logica.net.packets.Packet.PacketTypes;
import ep.ecoproyecto.logica.net.packets.Packet00Login;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.LinkedList;

public class Server extends Thread{
    private DatagramSocket socket;
    private PanelJuego juego;
    private LinkedList<JugadorMP> jugadoresConectados = new LinkedList<>();

    public Server(PanelJuego juego) {
        this.juego = juego;
        try {
            this.socket = new DatagramSocket(1234);
        } catch (SocketException ex) {
        }
    }
    
    @Override
    public void run(){
        while(true){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
                parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
            } catch (IOException ex) {
            }
//            String mensaje = new String(packet.getData());
//            System.out.println("CLIENT > "+ mensaje);
//            if (mensaje.equalsIgnoreCase("ping")){
//                enviarData("pong".getBytes(), packet.getAddress(), packet.getPort());
//            }
        }
    }
    
    private void parsePacket(byte[] data, InetAddress direccion, int puerto) {
        String mensaje = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(mensaje.substring(0,2));
        switch(type){
            case INVALID->{
                System.out.println("Paquete invalido");
            }
            case LOGIN->{
                Packet00Login packet = new Packet00Login(data); 
                System.out.println("["+direccion.getHostAddress()+":"+puerto+"] "+packet.getUsername()+" se ha conectado.");
                JugadorMP jugador;
                if (direccion.getHostAddress().equalsIgnoreCase("127.0.0.1")){
                    jugador = new JugadorMP(direccion,puerto,juego,juego.keyH,packet.getUsername());
                }else{
                    jugador = new JugadorMP(direccion,puerto,juego,packet.getUsername());
                }
                jugadoresConectados.add(jugador);
                juego.jugador = jugador;
            }
            case DISCONNECT->{
                
            }
            default->{
                System.out.println("Paquete desconocido");
            }
        }
    }
    
    public void enviarData(byte[] data, InetAddress direccionIP, int puerto){
        DatagramPacket packet = new DatagramPacket(data, data.length, direccionIP, puerto);
        try {
            socket.send(packet);
        } catch (IOException ex) {
        }
    }

    public void enviarDataClientes(byte[] data) {
        for (JugadorMP j:jugadoresConectados){
            enviarData(data,j.direccionIP,j.puerto);
        }
    }

}
