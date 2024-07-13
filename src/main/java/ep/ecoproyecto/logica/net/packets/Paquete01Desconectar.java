package ep.ecoproyecto.logica.net.packets;

import ep.ecoproyecto.logica.net.Cliente;
import ep.ecoproyecto.logica.net.Servidor;

/**
 * Paquete de datos que se encarga de los usuarios desconectados
 * @author C-A-F
 */

public class Paquete01Desconectar extends Paquete{
    
    private String usuario;
    
    public Paquete01Desconectar(byte[] data) {
        super(01);
        this.usuario = readData(data);
    }

    public Paquete01Desconectar(String usuario) {
        super(01);
        this.usuario = usuario;
    }

    public String getUsername() {
        return usuario;
    }

    public void setUsername(String usuario) {
        this.usuario = usuario;
    }
      /**
    * Escribe su informacion para el cliente conectado
    * @param cliente persona conectada al servidor
    */ 
    @Override
    public void writeData(Cliente cliente) {
        cliente.enviarData(getData());
    }
 /**
     * envía la informacion de un cliente al servidor
     * @param server servidor donde se envia la informacion
     */
    @Override
    public void writeData(Servidor server) {
        server.enviarDataClientes(getData());
    }
 /**
     * reparte los datos en bytes
     * @return bytes de datos 
     */
    @Override
    public byte[] getData() {
        return ("01" + this.usuario).getBytes();
    }
    
}
