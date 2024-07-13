package ep.ecoproyecto.logica.entidades;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;
import java.util.Random;
/**
 * Comportamiento del NPC del Mono
 * @author C-A-F
 */
public class Mono extends Entidad {
    
    public boolean misioncumplida=false;

    public Mono(PanelJuego pJuego, int x, int y) {
        super(pJuego);
        this.xMapa=x*pJuego.tamanioCasilla;
        this.yMapa=y*pJuego.tamanioCasilla;
        this.direction="down";
        this.vel=0;
        this.hitBox=new Rectangle(0,0,pJuego.tamanioCasilla,pJuego.tamanioCasilla);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        movimiento=true;
        
        this.mensaje="hola bienvenido a mi tienda";
        
        getImage();
    }
    
    public void getImage(){
        right1=up1=left1=down1= this.configuracion("/NPC/mono");
        right2=up2=left2=down2=this.configuracion("/NPC/mono1");
    }

    
    public void estableceraccion(){
        if(movimiento==true){
        contadordeaccion++;
            if(contadordeaccion==500){
                Random random = new Random();
                int i= random.nextInt(100)+1;

                if(i<=25){
                    direction="up";
                }
                if((i<=50)&&(i>25)){
                    direction="down";
                }
                if((i<=75)&&(i>50)){
                    direction="left";
                }
                if((i<=100)&&(i>75)){
                    direction="right";
                }
                
                contadordeaccion=0;
            }
        }
    }    
}