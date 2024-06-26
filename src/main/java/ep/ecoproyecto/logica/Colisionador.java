package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Entidad;

/**
 *
 * @author C-A-F
 */
public class Colisionador {

    private final PanelJuego gp;

    public Colisionador(PanelJuego gp) {
        this.gp=gp;
    }
    
    public void revisarColision(Entidad entidad){
            int posiciojugadorizquierda=entidad.xMapa+entidad.hitBox.x;
            int posiciojugadorderecha=entidad.xMapa+entidad.hitBox.x+entidad.hitBox.width;
            int posiciojugadorarriba=entidad.yMapa+entidad.hitBox.y;
            int posiciojugadorabajo=entidad.yMapa+entidad.hitBox.y+entidad.hitBox.height;
            
            int columizquierda=posiciojugadorizquierda/gp.tamanioCasilla;
            int columderecha=posiciojugadorderecha/gp.tamanioCasilla;
            int filaarriba=posiciojugadorarriba/gp.tamanioCasilla;
            int filaabajo=posiciojugadorabajo/gp.tamanioCasilla;
           
            int casilla1, casilla2;
                switch (entidad.direction) {
                        case "up" -> {  
                            filaarriba=(posiciojugadorarriba-entidad.vel)/gp.tamanioCasilla;
                            casilla1=gp.manCas.numCasillaMapa[columizquierda][filaarriba];
                            casilla2=gp.manCas.numCasillaMapa[columderecha][filaarriba];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
            }
                        case "left" -> {
                            columizquierda=(posiciojugadorizquierda-entidad.vel)/gp.tamanioCasilla;
                            casilla1=gp.manCas.numCasillaMapa[columizquierda][filaarriba];
                            casilla2=gp.manCas.numCasillaMapa[columizquierda][filaabajo];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
            }
                        case "down" -> {
                            filaabajo=(posiciojugadorabajo+entidad.vel)/gp.tamanioCasilla;
                            casilla1=gp.manCas.numCasillaMapa[columizquierda][filaabajo];
                            casilla2=gp.manCas.numCasillaMapa[columderecha][filaabajo];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
            }
                        case "right" -> {
                            columderecha=(posiciojugadorderecha+entidad.vel)/gp.tamanioCasilla;
                            casilla1=gp.manCas.numCasillaMapa[columderecha][filaarriba];
                            casilla2=gp.manCas.numCasillaMapa[columderecha][filaabajo];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
            }
                    }
    }

    //chequeo de colision con objetos
    
    
    public int chequeoObjetos(Entidad entidad, boolean jugador){
            int id=999;
            
            for(int i=0;i<gp.obj[0].length;i++){
                if(gp.obj[gp.mapaActual][i]!=null){
                    
                    entidad.hitBox.x=entidad.xMapa+entidad.hitBox.x;
                    entidad.hitBox.y=entidad.yMapa+entidad.hitBox.y;
                    
                    gp.obj[gp.mapaActual][i].hitBox.x=gp.obj[gp.mapaActual][i].xMapa+gp.obj[gp.mapaActual][i].hitBox.x;
                    gp.obj[gp.mapaActual][i].hitBox.y=gp.obj[gp.mapaActual][i].yMapa+gp.obj[gp.mapaActual][i].hitBox.y;

                    switch (entidad.direction) {
                        case "up" -> {
                            entidad.hitBox.y-=entidad.vel;
                            if(entidad.hitBox.intersects(gp.obj[gp.mapaActual][i].hitBox)){
                                if(gp.obj[gp.mapaActual][i].colision==true){
                                    entidad.colision=true;
                                }
                                if(jugador==true){
                                    id=i;
                                }
                            }
                        }
                        case "left" -> {
                            entidad.hitBox.x-=entidad.vel;
                            if(entidad.hitBox.intersects(gp.obj[gp.mapaActual][i].hitBox)){
                                if(gp.obj[gp.mapaActual][i].colision==true){
                                    entidad.colision=true;
                                }
                                if(jugador==true){
                                    id=i;
                                }
                            }
                        }
                        case "down" -> {
                            entidad.hitBox.y+=entidad.vel;
                            if(entidad.hitBox.intersects(gp.obj[gp.mapaActual][i].hitBox)){
                                if(gp.obj[gp.mapaActual][i].colision==true){
                                    entidad.colision=true;
                                }
                                if(jugador==true){
                                    id=i;
                                }
                            }
                        }
                        case "right" -> {
                            entidad.hitBox.x+=entidad.vel;
                            if(entidad.hitBox.intersects(gp.obj[gp.mapaActual][i].hitBox)){
                                if(gp.obj[gp.mapaActual][i].colision==true){
                                    entidad.colision=true;
                                }
                                if(jugador==true){
                                    id=i;
                                }
                            }
                        }
                    }
                    entidad.hitBox.x=entidad.areadefectoX;
                    entidad.hitBox.y=entidad.areadefectoY;
                    gp.obj[gp.mapaActual][i].hitBox.x=gp.obj[gp.mapaActual][i].areadefectoX;
                    gp.obj[gp.mapaActual][i].hitBox.y=gp.obj[gp.mapaActual][i].areadefectoY;
                }
                
            }
            
            return id;
        }
    
    public int chequeoEntidades(Entidad entidad, Entidad[][] objetivos){
                    int id=999;
            
            for(int i=0;i<objetivos[0].length;i++){
                if(objetivos[gp.mapaActual][i]!=null){
                    
                    entidad.hitBox.x=entidad.xMapa+entidad.hitBox.x;
                    entidad.hitBox.y=entidad.yMapa+entidad.hitBox.y;
                    
                    objetivos[gp.mapaActual][i].hitBox.x=objetivos[gp.mapaActual][i].xMapa+objetivos[gp.mapaActual][i].hitBox.x;
                    objetivos[gp.mapaActual][i].hitBox.y=objetivos[gp.mapaActual][i].yMapa+objetivos[gp.mapaActual][i].hitBox.y;
                    
                    switch (entidad.direction) {
                        case "up" -> {
                            entidad.hitBox.y-=entidad.vel;
                            if(entidad.hitBox.intersects(objetivos[gp.mapaActual][i].hitBox)){
                                entidad.colision=true;
                                id=i;
                            }
                        }
                        case "left" -> {
                            entidad.hitBox.x-=entidad.vel;
                            if(entidad.hitBox.intersects(objetivos[gp.mapaActual][i].hitBox)){
                                entidad.colision=true;
                                id=i;
                            }
                        }
                        case "down" -> {
                            entidad.hitBox.y+=entidad.vel;
                            if(entidad.hitBox.intersects(objetivos[gp.mapaActual][i].hitBox)){
                                entidad.colision=true;
                                id=i;
                            }
                        }
                        case "right" -> {
                            entidad.hitBox.x+=entidad.vel;
                            if(entidad.hitBox.intersects(objetivos[gp.mapaActual][i].hitBox)){
                                entidad.colision=true;
                                id=i;
                            }
                        }
                    }
                    entidad.hitBox.x=entidad.areadefectoX;
                    entidad.hitBox.y=entidad.areadefectoY;
                    objetivos[gp.mapaActual][i].hitBox.x=objetivos[gp.mapaActual][i].areadefectoX;
                    objetivos[gp.mapaActual][i].hitBox.y=objetivos[gp.mapaActual][i].areadefectoY;
                }
            }
            
            return id;
    }

    public void chequeojugador(Entidad entidad){
                    entidad.hitBox.x=entidad.xMapa+entidad.hitBox.x;
                    entidad.hitBox.y=entidad.yMapa+entidad.hitBox.y;
                    
                    gp.jugador.hitBox.x=gp.jugador.xMapa+gp.jugador.hitBox.x;
                    gp.jugador.hitBox.y=gp.jugador.yMapa+gp.jugador.hitBox.y;
                
                    
                    switch (entidad.direction) {
                        case "up" -> {
                            entidad.hitBox.y-=entidad.vel;
                            if(entidad.hitBox.intersects(gp.jugador.hitBox)){
                                entidad.colision=true;
                            }
            }
                        case "left" -> {
                            entidad.hitBox.x-=entidad.vel;
                            if(entidad.hitBox.intersects(gp.jugador.hitBox)){
                                entidad.colision=true;
                            }
            }
                        case "down" -> {
                            entidad.hitBox.y+=entidad.vel;
                            if(entidad.hitBox.intersects(gp.jugador.hitBox)){
                                entidad.colision=true;
                            }
            }
                        case "right" -> {
                            entidad.hitBox.x+=entidad.vel;
                            if(entidad.hitBox.intersects(gp.jugador.hitBox)){
                                entidad.colision=true;
                            }
            }
                    }
                    entidad.hitBox.x=entidad.areadefectoX;
                    entidad.hitBox.y=entidad.areadefectoY;
                    gp.jugador.hitBox.x=gp.jugador.areadefectoX;
                    gp.jugador.hitBox.y=gp.jugador.areadefectoY;

    }
}