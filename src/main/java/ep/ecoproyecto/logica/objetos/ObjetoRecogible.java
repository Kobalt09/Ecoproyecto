package ep.ecoproyecto.logica.objetos;
import ep.ecoproyecto.logica.entidades.Entidad;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;
/**
 *
 * @author C-A-F
 */
public class ObjetoRecogible extends Objetosclase{
    public int precio;
    
    public ObjetoRecogible(String nombre, int posicionX, int posicionY, PanelJuego gp) {
        super(gp);
        this.nombre=nombre;
        this.xMapa=posicionX*gp.tamanioCasilla;
        this.yMapa=posicionY*gp.tamanioCasilla;
        down1= this.configuracion("/objetos/llave1");
        colision=true;
        
        hitBox= new Rectangle(0,0,64,64);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
    }
    
    
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
}