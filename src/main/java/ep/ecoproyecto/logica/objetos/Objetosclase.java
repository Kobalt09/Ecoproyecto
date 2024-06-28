package ep.ecoproyecto.logica.objetos;
import ep.ecoproyecto.logica.Herramientas;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Entidad;
import java.awt.Rectangle;

/**
 *
 * @author C-A-F
 */
public class Objetosclase extends Entidad{

    public int precio;
            
    public Objetosclase(PanelJuego gp) {
        super(gp);
    }
      
    
/*
    public void draw(Graphics2D g2) {
        
        image = null;
  
       g2.drawImage(image,this.posicionX, this.posicionY, gp.tamanioCasilla,gp.tamanioCasilla,null); 
    
    }*/

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

}

