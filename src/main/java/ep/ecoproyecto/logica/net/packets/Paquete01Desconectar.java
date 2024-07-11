package ep.ecoproyecto.logica.net.packets;
import ep.ecoproyecto.logica.net.Cliente;
import ep.ecoproyecto.logica.net.Servidor;
/**
 *
 * @author C-A-F
 */
public class Paquete01Desconectar extends Paquete{
    
    private String username;
    
    public Paquete01Desconectar(byte[] data) {
        super(01);
        this.username = readData(data);
    }

    public Paquete01Desconectar(String username) {
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
    public void writeData(Servidor server) {
        server.enviarDataClientes(getData());
    }

    @Override
    public byte[] getData() {
        return ("01" + this.username).getBytes();
    }
    
}
