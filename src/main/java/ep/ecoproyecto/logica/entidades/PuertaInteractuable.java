package ep.ecoproyecto.logica.entidades;

import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 * Comportamiento de las puertas que permiten el transporte de jugador entre mapas
 * @author C-A-F
 */
public final class PuertaInteractuable extends Entidad{
    int Xtp;
    int Ytp;
    int Ztp;
    /**
     * Constructor de la clase
     * @param pJuego panel donde se ubica la entidad
     * @param nombre nombre del tipo de puerta
     * @param x posicion en horizontal
     * @param y posicion en vertica
     * @param z mapa en el que se encuentra
     * @param Xtp posicion en horizontal a la que transporta
     * @param Ytp posicion en vertical a la que transporta
     * @param Ztp mapa al que transporta
     */
    public PuertaInteractuable(PanelJuego pJuego,String nombre, int x, int y,int z ,int Xtp, int Ytp, int Ztp) {
        super(pJuego);
        this.nombre=nombre;
        this.xMapa=x*pJuego.tamanioCasilla;
        this.yMapa=y*pJuego.tamanioCasilla;
        this.direction="down";
        this.vel=0;
        colision=true;
        
        hitBox= new Rectangle(-1,-1,pJuego.tamanioCasilla+2,pJuego.tamanioCasilla+2);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        
        this.Xtp=Xtp;
        this.Ytp=Ytp;
        this.Ztp=Ztp;
        getImage();
    }
     /**
     * asigna las imagenes para la clase
     */
    public void getImage(){
        if(this.nombre.equals("puerta")){
            right1=up1=left1=down1=right2=up2=left2=down2= this.configuracion("/objetos/puerta1Invis");
            
        }else{
            right1=up1=left1=down1= this.configuracion("/Flechas/Flecha"+this.nombre+"1");
            right2=up2=left2=down2= this.configuracion("/Flechas/Flecha"+this.nombre+"2");
            movimiento=true;
        }
    }   
    
    
}
