package ep.ecoproyecto.logica;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author C-A-F
 */
public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed,PPressed,escPressed,ePressed;
 
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        
        //reconocer si una tecla esta siendo precionada
        if(code==KeyEvent.VK_W){
            upPressed=true;
        }
        if(code==KeyEvent.VK_A){
            leftPressed=true;
        }
        if(code==KeyEvent.VK_D){
            rightPressed=true;
        }
        if(code==KeyEvent.VK_S){
            downPressed=true;
        }

        if(code==KeyEvent.VK_E){
            ePressed=true;
        
        if(code==KeyEvent.VK_ESCAPE){
            escPressed=true;

        }
        
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
                //reconocer si una tecla se ha dejado de precionar
        if(code==KeyEvent.VK_W){
            upPressed=false;
        }
        if(code==KeyEvent.VK_A){
            leftPressed=false;
        }
        if(code==KeyEvent.VK_D){
            rightPressed=false;
        }
        if(code==KeyEvent.VK_S){
            downPressed=false;
        }
        if(code==KeyEvent.VK_E){
            ePressed=false;
        }
    }
    
}
