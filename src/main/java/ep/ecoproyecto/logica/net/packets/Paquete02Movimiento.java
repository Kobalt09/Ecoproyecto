package ep.ecoproyecto.logica.net.packets;
import ep.ecoproyecto.logica.net.Cliente;
import ep.ecoproyecto.logica.net.Servidor;

/**
 * Paquete de datos para el movimiento de los jugadores
 * @author C-A-F
 */

public class Paquete02Movimiento extends Paquete{
    
    private String usuario,dir;
    private int x,y;
    
    
    public Paquete02Movimiento(byte[] data) {
        super(02);
        String[] dataArray=readData(data).split(",");
        
        this.usuario = dataArray[0];   
        this.x=Integer.parseInt(dataArray[1]);
        this.y=Integer.parseInt(dataArray[2]);
        this.dir = dataArray[3];
        
    }

    public Paquete02Movimiento(String usuario,int x,int y,String dir) {
        super(02);
        this.usuario = usuario;
        this.x=x;
        this.y=y;
        this.dir=dir;
    }

    public String getDir() {
        return dir;
    }
    
    public String getUsername() {
        return usuario;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

   
    @Override
    public void writeData(Cliente cliente) {
        cliente.enviarData(getData());
    }

    @Override
    public void writeData(Servidor server) {
        server.enviarDataClientes(getData());
    }

    //Aquí se agregan los datos con coma para separar en paquetes y despues hacer el split
    
    @Override
    public byte[] getData() {
        return ("02" + this.usuario+","+this.x+","+this.y+","+this.dir).getBytes();
    }
    
}
