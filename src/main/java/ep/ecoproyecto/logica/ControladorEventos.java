package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;

/**
 *
 * @author C-A-F
 */

public class ControladorEventos {
    PanelJuego gp;
    EventoRect rectanguloEvento[][];
    
    public ControladorEventos(PanelJuego gp){
        this.gp=gp;
        rectanguloEvento = new EventoRect[gp.Maximocolumnas][gp.Maximofilas];
        
        int columna = 0, fila = 0;
        while(columna < gp.Maximocolumnas && fila < gp.Maximofilas){
            rectanguloEvento[columna][fila] = new EventoRect();
            rectanguloEvento[columna][fila].x=0;
            rectanguloEvento[columna][fila].y=0;
            rectanguloEvento[columna][fila].width=gp.tamanioCasilla;
            rectanguloEvento[columna][fila].height=gp.tamanioCasilla;
            rectanguloEvento[columna][fila].rectanguloEventoDefaultX=rectanguloEvento[columna][fila].x;
            rectanguloEvento[columna][fila].rectanguloEventoDefaultY=rectanguloEvento[columna][fila].y;
            
            columna++;
            if (columna == gp.Maximocolumnas){
                columna = 0;
                fila++;
            }
        }
    }
    
    public void chequeoEvento(){
        
        //evento sonido
        if(colision(27,16,"right")==true){ eventSonido(2); }
        if(colision(15,15,"any")==true){ eventMusica(1); }
        if(colision(5,1,"any")==true){ tp(48, 48); }
        
    }
    
    public boolean colision(int columna, int fila, String regdirecion){
        boolean hit= false;
        
        gp.jugador.hitBox.x=gp.jugador.xMapa+gp.jugador.hitBox.width;
        gp.jugador.hitBox.y=gp.jugador.yMapa+gp.jugador.hitBox.height;
        rectanguloEvento[columna][fila].x=columna*gp.tamanioCasilla+rectanguloEvento[columna][fila].x;
        rectanguloEvento[columna][fila].y=fila*gp.tamanioCasilla+rectanguloEvento[columna][fila].y;
        
        if(gp.jugador.hitBox.intersects(rectanguloEvento[columna][fila])){
            if(gp.jugador.direction.equals(regdirecion)||regdirecion.contentEquals("any")){
                hit=true;
            }
        }
        
        gp.jugador.hitBox.x=gp.jugador.areadefectoX;
        gp.jugador.hitBox.y=gp.jugador.areadefectoY;
        rectanguloEvento[columna][fila].x=rectanguloEvento[columna][fila].rectanguloEventoDefaultX;
        rectanguloEvento[columna][fila].y=rectanguloEvento[columna][fila].rectanguloEventoDefaultY;
        
        
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
        gp.mapaActual=1;
    }
    
}
