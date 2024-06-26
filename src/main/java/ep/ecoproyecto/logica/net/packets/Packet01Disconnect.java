package ep.ecoproyecto.logica.net.packets;
import ep.ecoproyecto.logica.net.Cliente;
import ep.ecoproyecto.logica.net.Server;
/**
 *
 * @author C-A-F
 */
public class Packet01Disconnect extends Packet{
    
    private String username;
    
    public Packet01Disconnect(byte[] data) {
        super(01);
        this.username = readData(data);
    }

    public Packet01Disconnect(String username) {
        super(01);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void writeData(Cliente cliente) {
        cliente.enviarData(getData());
    }

    @Override
    public void writeData(Server server) {
        server.enviarDataClientes(getData());
    }

    @Override
    public byte[] getData() {
        return ("01" + this.username).getBytes();
    }
    
}
