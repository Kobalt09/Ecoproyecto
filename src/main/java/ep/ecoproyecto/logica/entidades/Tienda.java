package ep.ecoproyecto.logica.entidades;
import ep.ecoproyecto.gui.PanelJuego;

import ep.ecoproyecto.logica.entidades.Entidad;
import ep.ecoproyecto.logica.objetos.ObjetoEquipo;
import ep.ecoproyecto.logica.objetos.ObjetoMoneda;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author C-A-F
 */
public class Tienda extends Entidad {
    
    public Tienda(PanelJuego gp, int x, int y) {
        super(gp);
        this.xMapa=x*gp.tamanioCasilla;
        this.yMapa=y*gp.tamanioCasilla;
        this.direction="down";
        this.vel=0;

        right1=right2=up1=up2=left1=left2=down2=down1= this.configuracion("/objetos/CajaRegistradora");
        colision=true;
        
        hitBox= new Rectangle(0,0,64,64);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        movimiento=false;
        EstablecerTienda();
    }
    
    
    public void EstablecerTienda(){
        this.inventario[0]=new ObjetoEquipo("botas",16,6,gp);
        this.inventario[0].setPrecio(10);
        this.inventario[1]=new ObjetoMoneda("coin",10,10,gp);
        this.inventario[1].setPrecio(0);
        this.inventario[2]=new ObjetoRecogible("llave",10,10,gp);
        this.inventario[2].setPrecio(1);
    }
    
}
