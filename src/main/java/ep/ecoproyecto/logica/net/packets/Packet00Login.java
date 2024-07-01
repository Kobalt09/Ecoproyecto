package ep.ecoproyecto.logica.net.packets;
import ep.ecoproyecto.logica.net.Cliente;
import ep.ecoproyecto.logica.net.Server;
/**
 *
 * @author C-A-F
 */
public class Packet00Login extends Packet{
    
    private String username,dir;
    private int x,y,mapa;
    
    
    public Packet00Login(byte[] data) {
        super(00);
        String[] datos = readData(data).split(",");
        this.username = datos[0];
        this.x=Integer.parseInt(datos[1]);
        this.y=Integer.parseInt(datos[2]);
        this.dir=datos[3];
        this.mapa=Integer.parseInt(datos[4]);
    }


    public Packet00Login(String username,int x,int y,String dir,int mapa) {
        super(00);
        this.username = username;
        this.x=x;
        this.y=y;
        this.dir=dir;
        this.mapa=mapa;
    }

    public String getUsername() {
        return username;
    }
    
    public int getX() {
        return x;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setX(int x) {
        this.x = x;
    }

    
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
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
        return ("00" + this.username+","+x+","+ y+","+dir+","+mapa ).getBytes();
    }

    public int getMapa() {
        return mapa;
    }

    public void setMapa(int mapa) {
        this.mapa = mapa;
    }
    
}
