package ep.ecoproyecto.logica.objetos;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 *
 * @author C-A-F
 */
public class ObjetoMoneda extends Objetosclase{
    
    public ObjetoMoneda(String nombre, int posicionX, int posicionY, PanelJuego pJuego) {
        super(pJuego);
        this.nombre=nombre;
        this.xMapa=posicionX*pJuego.tamanioCasilla;
        this.yMapa=posicionY*pJuego.tamanioCasilla;
        down1= this.configuracion("/objetos/coin");
        colision=false;
        hitBox= new Rectangle(16,16,32,32);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
    }

    @Override
    public int getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
}
