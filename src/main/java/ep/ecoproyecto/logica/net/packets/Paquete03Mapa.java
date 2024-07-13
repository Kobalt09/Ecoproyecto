package ep.ecoproyecto.logica.net.packets;

import ep.ecoproyecto.logica.net.Cliente;
import ep.ecoproyecto.logica.net.Servidor;

/**
 * Paquete de informacion para el multijugador
 * @author C-A-F
 */

public class Paquete03Mapa extends Paquete {
    private String usuario;
    private int mapa;
    
    /**
     * constructor del paquete
     * @param data bites de datos que se usa para construir un archivo
     */
    public Paquete03Mapa(byte[] data) {
        super(03);
        String[] datos=readData(data).split(",");
        this.mapa=Integer.parseInt(datos[0]);
        this.usuario=datos[1];
        
    }
    public Paquete03Mapa(int mapa,String usuario) {
        super(03);
        this.mapa = mapa;
        this.usuario=usuario;
    }
    public String getUsername() {
        return usuario;
    }

    public void setUsername(String usuario) {
        this.usuario = usuario;
    }

  
    public int getMapa() {
        return mapa;
    }

    public void setMapa(int mapa) {
        this.mapa = mapa;
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
          return ("03" + mapa+","+usuario).getBytes();
    }
    
    
    
}
