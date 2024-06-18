/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;

import java.awt.Rectangle;

/**
 *
 * @author Cris
 */
public class ControladorEventos {
    PanelJuego gp;
    Rectangle rectanguloEvento;
    int rectanguloEventoDefaultX, rectanguloEventoDefaultY;
    
    public ControladorEventos(PanelJuego gp){
        this.gp=gp;
        
        rectanguloEvento= new Rectangle();
        rectanguloEvento.x=0;
        rectanguloEvento.y=0;
        rectanguloEvento.width=gp.tamanioCasilla;
        rectanguloEvento.height=gp.tamanioCasilla;
        rectanguloEventoDefaultX=rectanguloEvento.x;
        rectanguloEventoDefaultY=rectanguloEvento.y;
        
    }
    
    public void chequeoEvento(){
        
        //evento sonido
        if(colision(27,16,"right")==true){ eventSonido(2); }
        if(colision(15,15,"any")==true){ eventMusica(1); }
        if(colision(5,1,"any")==true){ tp(48, 48); }
        
    }
    
    public boolean colision(int eventCol, int eventRow, String regdirecion){
        boolean hit= false;
        
        gp.jugador.hitBox.x=gp.jugador.xMapa+gp.jugador.hitBox.width;
        gp.jugador.hitBox.y=gp.jugador.yMapa+gp.jugador.hitBox.height;
        rectanguloEvento.x=eventCol*gp.tamanioCasilla+rectanguloEvento.x;
        rectanguloEvento.y=eventRow*gp.tamanioCasilla+rectanguloEvento.y;
        
        if(gp.jugador.hitBox.intersects(rectanguloEvento)){
            if(gp.jugador.direction.equals(regdirecion)||regdirecion.contentEquals("any")){
                hit=true;
            }
        }
        
        gp.jugador.hitBox.x=gp.jugador.areadefectoX;
        gp.jugador.hitBox.y=gp.jugador.areadefectoY;
        rectanguloEvento.x=rectanguloEventoDefaultX;
        rectanguloEvento.y=rectanguloEventoDefaultY;
        
        
        return hit;
    }
    
    //efectos de sonido
    public void eventSonido(int i){
        gp.efectos(2);
    }
    
    //cambiar la musica
    public void eventMusica(int i){
        gp.efectos(1);
    }
    
    //desplazar al jugador
    public void tp(int x,int y){
        gp.jugador.xMapa=x*gp.tamanioCasilla-gp.tamanioCasilla;
        gp.jugador.yMapa=y*gp.tamanioCasilla-gp.tamanioCasilla;
    }
    
}
