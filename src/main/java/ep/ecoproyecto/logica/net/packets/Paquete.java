package ep.ecoproyecto.logica.net.packets;
import ep.ecoproyecto.logica.net.*;
/**
 *
 * @author C-A-F
 */
public abstract class Paquete {
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
    
    //Buscar un paquete en especifico//
    public static PacketTypes lookupPacket(String packetId){
        try{
            return lookupPacket(Integer.parseInt(packetId));
        }catch(NumberFormatException e){
            return PacketTypes.INVALID;
        }
    }
    
    public static PacketTypes lookupPacket(int id){
        for (PacketTypes p:PacketTypes.values()){
            if (p.getPacketid() == id){
                return p;
            }
        }
        return PacketTypes.INVALID;
    }
}
