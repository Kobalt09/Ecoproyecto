package ep.ecoproyecto.logica.net;

import ep.ecoproyecto.gui.PanelJuego;
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
            System.out.println("SERVER > "+ new String(packet.getData()));
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
