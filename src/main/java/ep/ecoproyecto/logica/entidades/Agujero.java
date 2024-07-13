package ep.ecoproyecto.logica.entidades;

import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 * Asigna un comportamiento para las casillas para el minijugo de plantar arboles
 * @author C-A-F
 */

public final class Agujero extends Entidad{
    public String estado;
    
    public Agujero(PanelJuego pJuego, int x, int y) {
        super(pJuego);
        this.xMapa=x*pJuego.tamanioCasilla;
        this.yMapa=y*pJuego.tamanioCasilla;
        this.vel=0;
        this.hitBox=new Rectangle(0,0,pJuego.tamanioCasilla,pJuego.tamanioCasilla);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        estado="Agujerovacio";
        
        this.mensaje="Es un agujero, perfecto para plantar un Arbol";
        
        getImage();
    }
    
    
    public void getImage(){
        right1=right2=up1=up2=left1=left2=down2=down1=this.configuracion("/objetos/agujero/"+estado);
    }
    
}
