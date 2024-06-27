package ep.ecoproyecto.logica.net;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.JugadorMP;
import ep.ecoproyecto.logica.net.packets.Packet;
import ep.ecoproyecto.logica.net.packets.Packet.PacketTypes;
import ep.ecoproyecto.logica.net.packets.Packet00Login;
import ep.ecoproyecto.logica.net.packets.Packet02Mov;
import ep.ecoproyecto.logica.net.packets.Packet01Disconnect;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.LinkedList;
/**
 *
 * @author C-A-F
 */
public class Server extends Thread{
    private DatagramSocket socket;
    private PanelJuego juego;
    private LinkedList<JugadorMP> jugadoresConectados = new LinkedList<>();
    public static int refresco=0;
    
    
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
            parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
//            String mensaje = new String(packet.getData());
//            System.out.println("CLIENT > "+ mensaje);
//            if (mensaje.equalsIgnoreCase("ping")){
//                enviarData("pong".getBytes(), packet.getAddress(), packet.getPort());
//            }
        }
    }
    
    public void parsePacket(byte[] data, InetAddress direccion, int puerto) {
        String mensaje = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(mensaje.substring(0,2));
        Packet packet;
        switch(type){
            case INVALID->{
                System.out.println("Paquete invalido");
            }
            case LOGIN->{
                packet = new Packet00Login(data); 
                System.out.println("["+direccion.getHostAddress()+":"+puerto+"] "+((Packet00Login)packet).getUsername()+" se ha conectado.");
                JugadorMP jugador = new JugadorMP(direccion,puerto,juego,((Packet00Login)packet).getUsername(),((Packet00Login)packet).getX(),((Packet00Login)packet).getY(),((Packet00Login)packet).getDir());
                this.addConnection(jugador,((Packet00Login)packet));
            }
            case DISCONNECT->{
                packet = new Packet01Disconnect(data); 
                System.out.println("["+direccion.getHostAddress()+":"+puerto+"] "+((Packet01Disconnect)packet).getUsername()+" se ha desconectado.");
                this.removeConnection((Packet01Disconnect)packet);
            }
            case MOVE->{
                packet =new Packet02Mov(data);
                if (refresco++ % 10==0)
                    System.out.println(((Packet02Mov)packet).getUsername()+" movido a x "+((Packet02Mov)packet).getX()+" y "+((Packet02Mov)packet).getY()+" mirando a "+((Packet02Mov)packet).getDir());
                this.manejarMov((Packet02Mov)packet);
            }
            
            default->{
                System.out.println("Paquete desconocido");
            }
        }
    }
    
    public void addConnection(JugadorMP jugador, Packet00Login packet) {
        boolean yaConectado = false;
        for (JugadorMP j:jugadoresConectados){
            if (jugador.getUsername().equalsIgnoreCase(j.getUsername())){
                if (j.direccionIP == null){
                    j.direccionIP = jugador.direccionIP;
                }
                if (j.puerto == -1){
                    j.puerto = jugador.puerto;
                }
                yaConectado = true;
            }else{
                enviarData(packet.getData(),j.direccionIP,j.puerto);
                packet = new Packet00Login(j.getUsername(),j.xMapa,j.yMapa,j.direction);
                enviarData(packet.getData(),jugador.direccionIP,jugador.puerto);
            }
        }
        if (!yaConectado){
            this.jugadoresConectados.add(jugador);
        }
    }
    
    
    private void removeConnection(Packet01Disconnect packet) {
        this.jugadoresConectados.remove(getJugadorMPindex(packet.getUsername()));
        packet.writeData(this);
    }
    
    public JugadorMP getJugadorMP(String username){
        for (JugadorMP jugador:jugadoresConectados){
            if (jugador.getUsername().equals(username)){
                return jugador;
            }
        }
        return null;
    }
    
    public int getJugadorMPindex(String username){
        int index = 0;
        for (JugadorMP jugador:jugadoresConectados){
            if (jugador.getUsername().equals(username)){
                break;
            }
            index++;
        }
        return index;
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
   
    public int getIndiceJugador(String user){
        int indice=0;
        for (JugadorMP jug:jugadoresConectados){
            if (jug.getUsername().equals(user)){
                break;
            }
        indice++;
        } 
        return indice;
    }
    
    private void manejarMov(Packet02Mov packet) {
        
        if(getJugadorMP(packet.getUsername())!=null){
            
            int indice=this.getIndiceJugador(packet.getUsername());
            
            this.jugadoresConectados.get(indice).xMapa=packet.getX();
            this.jugadoresConectados.get(indice).yMapa=packet.getY();
            this.jugadoresConectados.get(indice).direction=packet.getDir();
            packet.writeData(this);
        }
    }

}
