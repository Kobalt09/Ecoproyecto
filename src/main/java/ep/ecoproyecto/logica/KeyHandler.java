package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author C-A-F
 */
public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed,PPressed,escPressed,ePressed, enterPressed;
    public PanelJuego gp;
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    public void establecerPanel(PanelJuego gp){
        this.gp=gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        
        if (!gp.pause){
            juegoState(code);
        }else{
            opcionesState(code);
        }
        
        if(code==KeyEvent.VK_ESCAPE || code==KeyEvent.VK_P){
            gp.pause = !gp.pause;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
                //reconocer si una tecla se ha dejado de precionar
        if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP){
            upPressed=false;
        }
        if(code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT){
            leftPressed=false;
        }
        if(code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT){
            rightPressed=false;
        }
        if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN){
            downPressed=false;
        }
        if(code==KeyEvent.VK_E){
            ePressed=false;
        }
    }
    
    public void juegoState(int code){
        //reconocer si una tecla esta siendo precionada
        if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP){
            upPressed=true;
        }
        if(code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT){
            leftPressed=true;
        }
        if(code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT){
            rightPressed=true;
        }
        if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN){
            downPressed=true;
        }
        if(code==KeyEvent.VK_E){
            ePressed=true;
        }
    }
    
    public void opcionesState(int code){
        
        if (code==KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        
        int maxOpcionMenu = 0;
        switch(gp.hud.subState){
            case 0->maxOpcionMenu=4;
            case 3->maxOpcionMenu=1;
        }
        
        if (code==KeyEvent.VK_W || code==KeyEvent.VK_UP){
            gp.hud.opcionMenu--;
            gp.efectos(2);
            if (gp.hud.opcionMenu<0)
                gp.hud.opcionMenu = maxOpcionMenu;
        }
        
        if (code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN){
            gp.hud.opcionMenu++;
            gp.efectos(2);
            if (gp.hud.opcionMenu>maxOpcionMenu)
                gp.hud.opcionMenu = 0;
        }
        
        if (code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT){
            if (gp.hud.subState == 0){
                if (gp.hud.opcionMenu == 1 && gp.controlmusica.escalaVolumen>0){
                    gp.controlmusica.escalaVolumen--;
                    gp.controlmusica.chequearVolumen();
                    gp.efectos(2);
                }
                if (gp.hud.opcionMenu == 2 && gp.controlsonido.escalaVolumen>0){
                    gp.controlsonido.escalaVolumen--;
                    gp.efectos(2);
                }
            }
        }
        
        if (code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT){
            if (gp.hud.subState == 0){
                if (gp.hud.opcionMenu == 1 && gp.controlmusica.escalaVolumen<5){
                    gp.controlmusica.escalaVolumen++;
                    gp.controlmusica.chequearVolumen();
                    gp.efectos(2);
                }
                if (gp.hud.opcionMenu == 2 && gp.controlsonido.escalaVolumen<5){
                    gp.controlsonido.escalaVolumen++;
                    gp.efectos(2);
                }
            }
        }
    }
    
}
