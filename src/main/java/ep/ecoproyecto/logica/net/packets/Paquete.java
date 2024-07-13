package ep.ecoproyecto.logica.net.packets;

import ep.ecoproyecto.logica.net.*;

/**
 * Clase que define los tipos de paquetes y su comportamiento base
 * @author C-A-F
 */

public abstract class Paquete {
    /**
     * Asigna tipos de paqutes a los valores
     */
    public static enum PacketTypes{
        INVALID(-1), LOGIN(00), DISCONNECT(01),MOVE(02),CAMBIO(03);
    
        private final int packetid;
        
        private PacketTypes(int packetid) {
            this.packetid = packetid;
        }

        public int getPacketid() {
            return packetid;
        }
        
    }
    
    public byte packetid;
    
    /**
     * constructor a bytes
     * @param packetid 
     */
    public Paquete(int packetid) {
        this.packetid = (byte) packetid;
    }
    
    //ESCRITURA Y RECEPCION DE DATOS//
    public abstract void writeData(Cliente cliente);
    public abstract void writeData(Servidor server);
    public String readData(byte[] data){
        String mensaje = new String(data).trim();
        return mensaje.substring(2);
    }

    public abstract byte[] getData();
    
    /**
     * Busca un paquete en especifico
     * @param packetId id del paquete en string
     * @return numero del paquete
     */
    public static PacketTypes lookupPacket(String packetId){
        try{
            return lookupPacket(Integer.parseInt(packetId));
        }catch(NumberFormatException e){
            return PacketTypes.INVALID;
        }
    }
    /**
     * Busca un paquete en especifico
     * @param id numero de id del paquete
     * @return numero del paquete
     */
    public static PacketTypes lookupPacket(int id){
        for (PacketTypes p:PacketTypes.values()){
            if (p.getPacketid() == id){
                return p;
            }
        }
        return PacketTypes.INVALID;
    }
}
