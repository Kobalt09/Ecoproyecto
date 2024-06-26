package ep.ecoproyecto.logica.entidades;
import ep.ecoproyecto.logica.Herramientas;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Interfaces.Actualizar;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author C-A-F
 */

public class Entidad implements Actualizar{
    //
    public BufferedImage image;
    public String nombre;
    public boolean colision=false;
    //
    PanelJuego gp;
    public int xMapa,yMapa;
    public int vel;
    
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction= "down";
    
    public int spriteCounter = 0 ;
    public int spriteNum = 1;
    public Rectangle hitBox;

    public int areadefectoX, areadefectoY;
    
    public int contadordeaccion=0;
    public Entidad inventario[]= new Entidad[10];
    
    //variables de dialogo
    public boolean dialogo=false;
    public int veloriginal;
    public String directionoriginal;
    
    public String Mensaje;
    public boolean movimiento;
    
    
    public Entidad(PanelJuego gp){
        this.gp=gp;
    }
    
    
    public void actualizar(){
        
        colision=false;
        gp.colisiones.revisarColision(this);
        gp.colisiones.chequeoObjetos(this, colision);
        
        gp.colisiones.chequeojugador(this);
        
        if(colision==false){
                switch (direction) {
                        case "up" -> yMapa-=vel;
                        case "left" -> xMapa-=vel;
                        case "down" -> yMapa+=vel;
                        case "right" -> xMapa+=vel;
                    }
            }

            spriteCounter++;
            if (spriteCounter>10){
                if (spriteNum == 2 )
                {spriteNum=1;}
                else{
                if (spriteNum == 1)
                spriteNum=2;
                }
                spriteCounter = 0;
            }
    }
    
    public BufferedImage configuracion(String nombre){
        
        Herramientas herramienta = new Herramientas();
        BufferedImage imagen= null;
        
        try{
            imagen=ImageIO.read(getClass().getResourceAsStream(nombre+".png"));
            imagen= herramienta.imagenEscalada(imagen, gp.tamanioCasilla, gp.tamanioCasilla);
            
        }catch(IOException e){
        }
        
        return imagen;
    }
    
    public void dialogo(){
        if(dialogo==false){
            dialogo=true;
            veloriginal=vel;
            vel=0;
            directionoriginal=direction;
        }else{
            dialogo=false;
            vel=veloriginal;
            direction=directionoriginal;
        }
    }
}
