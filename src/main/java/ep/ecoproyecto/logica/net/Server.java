package ep.ecoproyecto.logica.net;

import ep.ecoproyecto.gui.PanelJuego;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends Thread{
    private DatagramSocket socket;
    private PanelJuego juego;

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
            } catch (IOException ex) {
            }
            String mensaje = new String(packet.getData());
            System.out.println("CLIENT > "+ mensaje);
            if (mensaje.equalsIgnoreCase("ping")){
                enviarData("pong".getBytes(), packet.getAddress(), packet.getPort());
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
}
