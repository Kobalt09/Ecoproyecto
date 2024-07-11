package ep.ecoproyecto.logica.net.packets;
import ep.ecoproyecto.logica.net.Cliente;
import ep.ecoproyecto.logica.net.Servidor;
/**
 *
 * @author C-A-F
 */
public class Paquete00Login extends Paquete{
    
    private String username,dir;
    private int x,y,mapa;
    
    
    public Paquete00Login(byte[] data) {
        super(00);
        String[] datos = readData(data).split(",");
        this.username = datos[0];
        this.x=Integer.parseInt(datos[1]);
        this.y=Integer.parseInt(datos[2]);
        this.dir=datos[3];
        this.mapa=Integer.parseInt(datos[4]);
    }

    public Paquete00Login(String username,int x,int y,String dir,int mapa) {
        super(00);
        this.username = username;
        this.x=x;
        this.y=y;
        this.dir=dir;
        this.mapa=mapa;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return username;
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
        return ("00" + this.username+","+x+","+ y+","+dir+","+mapa ).getBytes();
    }
    
}
