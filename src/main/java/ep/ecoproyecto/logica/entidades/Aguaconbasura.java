package ep.ecoproyecto.logica.entidades;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Herramientas;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Asigna una casilla especial para el minijuego de recoger basura del agua
 * @author C-A-F
 */

public final class Aguaconbasura extends Entidad{

    public String estado;

    public Aguaconbasura(PanelJuego pJuego, int x, int y) {
        super(pJuego);
        this.xMapa=x*pJuego.tamanioCasilla;
        this.yMapa=y*pJuego.tamanioCasilla;
        this.vel=0;
        this.hitBox=new Rectangle(0,0,pJuego.tamanioCasilla,pJuego.tamanioCasilla);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        estado="Aguasucia";
        
        this.mensaje="Cuanta basura en el agua";
        
        getImage();
    }
    
    
    public void getImage(){
        right1=right2=up1=up2=left1=left2=down2=down1=this.configuracion("/objetos/agua/"+estado);
    }
    
    @Override
    public BufferedImage configuracion(String nombre){
        
        Herramientas herramienta = new Herramientas();
        BufferedImage imagen= null;
        
        try{
            imagen=ImageIO.read(getClass().getResourceAsStream(nombre+".jpg"));
            imagen= herramienta.imagenEscalada(imagen, pJuego.tamanioCasilla, pJuego.tamanioCasilla);
            
        }catch(IOException e){
        }
        
        return imagen;
    }
}