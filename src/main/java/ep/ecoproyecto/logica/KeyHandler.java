package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Recibe los comandos del teclado y los interpreta para saber 
 * que tecla se está presionando
 * @author C-A-F
 */

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed,PPressed,escPressed,ePressed, enterPressed;
    public PanelJuego pJuego;
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    public void establecerPanel(PanelJuego pJuego){
        this.pJuego=pJuego;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        
        if (!pJuego.pause){
            juegoState(code);
        }else{
            opcionesState(code);
        }
        
        if(code==KeyEvent.VK_ESCAPE || code==KeyEvent.VK_P){
            if (!pJuego.hud.tiendaOn)
                pJuego.pause = !pJuego.pause;
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
        switch(pJuego.hud.subState){
            case 0->maxOpcionMenu=4;
            case 3->maxOpcionMenu=1;
        }
        
        if (code==KeyEvent.VK_W || code==KeyEvent.VK_UP){
            pJuego.hud.opcionMenu--;
            pJuego.efectos(2);
            if (pJuego.hud.opcionMenu<0)
                pJuego.hud.opcionMenu = maxOpcionMenu;
        }
        
        if (code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN){
            pJuego.hud.opcionMenu++;
            pJuego.efectos(2);
            if (pJuego.hud.opcionMenu>maxOpcionMenu)
                pJuego.hud.opcionMenu = 0;
        }
        
        if (code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT){
            if (pJuego.hud.subState == 0){
                if (pJuego.hud.opcionMenu == 1 && pJuego.controlmusica.escalaVolumen>0){
                    pJuego.controlmusica.escalaVolumen--;
                    pJuego.controlmusica.chequearVolumen();
                    pJuego.efectos(2);
                }
                if (pJuego.hud.opcionMenu == 2 && pJuego.controlsonido.escalaVolumen>0){
                    pJuego.controlsonido.escalaVolumen--;
                    pJuego.efectos(2);
                }
            }
        }
        
        if (code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT){
            if (pJuego.hud.subState == 0){
                if (pJuego.hud.opcionMenu == 1 && pJuego.controlmusica.escalaVolumen<5){
                    pJuego.controlmusica.escalaVolumen++;
                    pJuego.controlmusica.chequearVolumen();
                    pJuego.efectos(2);
                }
                if (pJuego.hud.opcionMenu == 2 && pJuego.controlsonido.escalaVolumen<5){
                    pJuego.controlsonido.escalaVolumen++;
                    pJuego.efectos(2);
                }
            }
        }
    }
    
}
