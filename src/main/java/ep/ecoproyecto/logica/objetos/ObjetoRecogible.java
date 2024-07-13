package ep.ecoproyecto.logica.objetos;

import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 * Es la clase que define los objetos recogibles por el jugador
 * @author C-A-F
 */

public class ObjetoRecogible extends Objetosclase{
    
    /**
     * constructor que revisa si el objeto es un sombrero
     * @param nombre nombre del objeto
     * @param posicionX posicion en horizontal
     * @param posicionY posicion en vertical
     * @param pJuego panel donde se dibujará
     */
    
    public ObjetoRecogible(String nombre, int posicionX, int posicionY, PanelJuego pJuego) {
        super(pJuego);
        this.nombre=nombre;
        this.xMapa=posicionX*pJuego.tamanioCasilla;
        this.yMapa=posicionY*pJuego.tamanioCasilla;
        if(nombre.equals("gGorro")||nombre.equals("gCopa")||nombre.equals("gPlaya")){
            down1= this.configuracion("/gorros/"+nombre);
        }else {
            down1= this.configuracion("/objetos/"+nombre);
        }
        colision=false;
        
        hitBox= new Rectangle(0,0,64,64);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
    }
    
    /**
     * 
     * @return retorna el precio del objeto
     */
    @Override
    public int getPrecio() {
        return precio;
    }
    /**
    * 
    * @param precio Precio a introducir en el objeto
    */
    @Override
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    

    
}