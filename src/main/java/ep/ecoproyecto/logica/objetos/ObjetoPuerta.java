package ep.ecoproyecto.logica.objetos;
import ep.ecoproyecto.logica.entidades.Entidad;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;
/**
 *
 * @author C-A-F
 */
public class ObjetoPuerta extends Objetosclase{
    
    public ObjetoPuerta(String nombre, int posicionX, int posicionY, PanelJuego pJuego) {
        super(pJuego);
        this.nombre=nombre;
        this.xMapa=posicionX*pJuego.tamanioCasilla;
        this.yMapa=posicionY*pJuego.tamanioCasilla;
        down1= this.configuracion("/objetos/puerta1");
        colision=true;
        hitBox= new Rectangle(0,0,64,64);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
    }
    
}