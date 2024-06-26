package ep.ecoproyecto.logica.objetos;
import ep.ecoproyecto.logica.Herramientas;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 *
 * @author C-A-F
 */
public class Objetosclase {
      
    public int posicionX, posicionY;
    public PanelJuego gp;
    public Rectangle hitBox= new Rectangle(0,0,64,64);
    public int areadefectoX=hitBox.x;
    public int  areadefectoY=hitBox.y;
    Herramientas herramienta= new Herramientas();
    private final String nombre;


    public Objetosclase( String nombre, int posicionX, int posicionY, PanelJuego gp) {
        
        this.nombre = nombre;

        this.posicionX = posicionX*gp.tamanioCasilla;
        this.posicionY = posicionY*gp.tamanioCasilla;

        this.gp = gp;
        
    }
    
/*
    public void draw(Graphics2D g2) {
        
        image = null;
  
       g2.drawImage(image,this.posicionX, this.posicionY, gp.tamanioCasilla,gp.tamanioCasilla,null); 
    
    }*/

}

