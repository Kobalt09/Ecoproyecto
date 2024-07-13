package ep.ecoproyecto.logica.net.packets;

import ep.ecoproyecto.logica.net.Cliente;
import ep.ecoproyecto.logica.net.Servidor;

/**
 * Paquete que recibe la informacion de inicio de seciones de jugadores
 * @author C-A-F
 */

public class Paquete00Login extends Paquete{
    
    private String usuario,dir;
    private int x,y,mapa;
    
    
    public Paquete00Login(byte[] data) {
        super(00);
        String[] datos = readData(data).split(",");
        this.usuario = datos[0];
        this.x=Integer.parseInt(datos[1]);
        this.y=Integer.parseInt(datos[2]);
        this.dir=datos[3];
        this.mapa=Integer.parseInt(datos[4]);
    }

    public Paquete00Login(String usuario,int x,int y,String dir,int mapa) {
        super(00);
        this.usuario = usuario;
        this.x=x;
        this.y=y;
        this.dir=dir;
        this.mapa=mapa;
    }

    public void setUsername(String usuario) {
        this.usuario = usuario;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMapa(int mapa) {
        this.mapa = mapa;
    }

    public String getUsername() {
        return usuario;
    }

    public String getDir() {
        return dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMapa() {
        return mapa;
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
     * reparte los datos en bytes separados por comas
     * @return bytes de datos 
     */
    @Override
    public byte[] getData() {
        return ("00" + this.usuario+","+x+","+ y+","+dir+","+mapa ).getBytes();
    }
    
}
