package ep.ecoproyecto.logica.objetos;
import ep.ecoproyecto.logica.entidades.Entidad;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Herramientas;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
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
        if(nombre.equals("calvo")||nombre.equals("gCopa")||nombre.equals("gPlaya")){
            down1= this.configuracion("/gorros/"+nombre);
        }else {
            down1= this.configuracion("/objetos/"+nombre);
        }
        colision=false;
        
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