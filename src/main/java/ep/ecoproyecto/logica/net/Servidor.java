package ep.ecoproyecto.logica.net;

import ep.ecoproyecto.logica.net.packets.Paquete03Mapa;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.JugadorMP;
import ep.ecoproyecto.logica.net.packets.Paquete;
import ep.ecoproyecto.logica.net.packets.Paquete.PacketTypes;
import ep.ecoproyecto.logica.net.packets.Paquete00Login;
import ep.ecoproyecto.logica.net.packets.Paquete02Movimiento;
import ep.ecoproyecto.logica.net.packets.Paquete01Desconectar;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.LinkedList;

/**
 * Jugador que aporta un servidor para jugar en linea
 * @author C-A-F
 */

public class Servidor extends Thread{
    private DatagramSocket socket;
    private PanelJuego pJuego;
    private LinkedList<JugadorMP> jugadoresConectados = new LinkedList<>();
    public static int refresco=0;
    
    
    public Servidor(PanelJuego pJuego) {
        this.pJuego = pJuego;
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
        PacketTypes type = Paquete.lookupPacket(mensaje.substring(0,2));
        Paquete packet;
        switch(type){
            case INVALID->{
                System.out.println("Paquete invalido");
            }
            case LOGIN->{
                packet = new Paquete00Login(data); 
                System.out.println("["+direccion.getHostAddress()+":"+puerto+"] "+((Paquete00Login)packet).getUsername()+" se ha conectado.");
                JugadorMP jugador = new JugadorMP(direccion,puerto,pJuego,((Paquete00Login)packet).getUsername(),((Paquete00Login)packet).getX(),((Paquete00Login)packet).getY(),((Paquete00Login)packet).getDir(),((Paquete00Login)packet).getMapa());
                this.addConnection(jugador,((Paquete00Login)packet));
            }
            case DISCONNECT->{
                packet = new Paquete01Desconectar(data); 
                System.out.println("["+direccion.getHostAddress()+":"+puerto+"] "+((Paquete01Desconectar)packet).getUsername()+" se ha desconectado.");
                this.removeConnection((Paquete01Desconectar)packet);
            }
            case MOVE->{
                packet =new Paquete02Movimiento(data);
                if (refresco++ % 10==0)
                    //System.out.println(((Paquete02Movimiento)packet).getUsername()+" movido a x "+((Paquete02Movimiento)packet).getX()+" y "+((Paquete02Movimiento)packet).getY()+" mirando a "+((Paquete02Movimiento)packet).getDir());
                this.manejarMov((Paquete02Movimiento)packet);
            }
            
            case CAMBIO->{
                packet =new Paquete03Mapa(data);
                System.out.println(((Paquete03Mapa)packet).getUsername()+" cambiado a mapa "+((Paquete03Mapa)packet).getMapa());
                this.cambioMapa((Paquete03Mapa)packet);
            }
                       
            default->{
                System.out.println("Paquete desconocido");
            }
        }
    }
    
    public void addConnection(JugadorMP jugador, Paquete00Login packet) {
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
                packet = new Paquete00Login(j.getUsername(),j.xMapa,j.yMapa,j.direction,j.getMapa());
                enviarData(packet.getData(),jugador.direccionIP,jugador.puerto);
            }
        }
        if (!yaConectado){
            this.jugadoresConectados.add(jugador);
        }
    }
    
    
    private void removeConnection(Paquete01Desconectar packet) {
        this.jugadoresConectados.remove(getJugadorMPindex(packet.getUsername()));
        packet.writeData(this);
    }
    
    public JugadorMP getJugadorMP(String usuario){
        for (JugadorMP jugador:jugadoresConectados){
            if (jugador.getUsername().equals(usuario)){
                return jugador;
            }
        }
        return null;
    }
    
    public int getJugadorMPindex(String usuario){
        int index = 0;
        for (JugadorMP jugador:jugadoresConectados){
            if (jugador.getUsername().equals(usuario)){
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
   
    private void manejarMov(Paquete02Movimiento packet) {
        
        if(getJugadorMP(packet.getUsername())!=null){
            
            int indice=this.getJugadorMPindex(packet.getUsername());
            
            this.jugadoresConectados.get(indice).xMapa=packet.getX();
            this.jugadoresConectados.get(indice).yMapa=packet.getY();
            this.jugadoresConectados.get(indice).direction=packet.getDir();
            packet.writeData(this);
        }
    }

    private void cambioMapa(Paquete03Mapa packet) {
        if(getJugadorMP(packet.getUsername())!=null){
            
            int indice=this.getJugadorMPindex(packet.getUsername());
            this.jugadoresConectados.get(indice).setMapa(packet.getMapa());
            
            packet.writeData(this);
        }
    }

}
