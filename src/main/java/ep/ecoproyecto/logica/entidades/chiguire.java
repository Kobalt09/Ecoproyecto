package ep.ecoproyecto.logica.entidades;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;
import java.util.Random;
/**
 *
 * @author C-A-F
 */
public class chiguire extends Entidad {

    public chiguire(PanelJuego gp, int x, int y) {
        super(gp);
        this.xMapa=x*gp.tamanioCasilla;
        this.yMapa=y*gp.tamanioCasilla;
        this.direction="down";
        this.vel=0;
        this.hitBox=new Rectangle(0,0,gp.tamanioCasilla,gp.tamanioCasilla);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        movimiento=true;
        
        this.Mensaje="hola soy un chiguire";
        
        getImage();
    }
    
    private void getImage(){
        
        up1=configuracion("/player/jg_arr_01");
        up2=configuracion("/player/jg_arr_02");
        down1=configuracion("/player/jg_abj_01");
        down2=configuracion("/player/jg_abj_02");
        left1=configuracion("/player/jg_izq_01");
        left2=configuracion("/player/jg_izq_02");
        right1=configuracion("/player/jg_der_01");
        right2=configuracion("/player/jg_der_02");
        
    }
    
    public void estableceraccion(){
        if(movimiento==true){
        contadordeaccion++;
            if(contadordeaccion==120){
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
