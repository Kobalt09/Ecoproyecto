package ep.ecoproyecto.logica.entidades;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;
import java.util.Random;
/**
 *
 * @author C-A-F
 */
public class Zamuro extends Entidad {
    
    public boolean misioncumplida=false;

    public Zamuro(PanelJuego gp, int x, int y) {
        super(gp);
        this.xMapa=x*gp.tamanioCasilla;
        this.yMapa=y*gp.tamanioCasilla;
        this.direction="down";
        this.vel=0;
        this.hitBox=new Rectangle(-1,-1,gp.tamanioCasilla+2,gp.tamanioCasilla+2);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        movimiento=true;
        this.Mensaje="Hola, soy un Zamuro";
        
        getImage();
    }
    
    public void getImage(){
        right1=up1=left1=down1=this.configuracion("/NPC/zamuro");
        right2=up2=left2=down2=this.configuracion("/NPC/zamuro1");
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

    public void interactuar(){
        
    }
}
