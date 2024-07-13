package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Entidad;

/**
 * Revisa si colisiona el jugador con alguna entidad
 * @author C-A-F
 */
public class Colisionador {

    private final PanelJuego pJuego;

    /**
     * Constructor de la clase
     * @param pJuego panel en que se ubican las colisiones
     */
    public Colisionador(PanelJuego pJuego) {
        this.pJuego=pJuego;
    }
    /**
     * Revisa las colisiones de entidades
     * @param entidad entidad con la que se revisa la colision
     */
    public void revisarColision(Entidad entidad){
        int posiciojugadorizquierda=entidad.xMapa+entidad.hitBox.x;
        int posiciojugadorderecha=entidad.xMapa+entidad.hitBox.x+entidad.hitBox.width;
        int posiciojugadorarriba=entidad.yMapa+entidad.hitBox.y;
        int posiciojugadorabajo=entidad.yMapa+entidad.hitBox.y+entidad.hitBox.height;

        int columizquierda=posiciojugadorizquierda/pJuego.tamanioCasilla;
        int columderecha=posiciojugadorderecha/pJuego.tamanioCasilla;
        int filaarriba=posiciojugadorarriba/pJuego.tamanioCasilla;
        int filaabajo=posiciojugadorabajo/pJuego.tamanioCasilla;
        
        
        int casilla1, casilla2;
        switch (entidad.direction) {
            case "up" -> {  
                filaarriba=(posiciojugadorarriba-entidad.vel)/pJuego.tamanioCasilla;
                casilla1=pJuego.manCas.numCasillaMapa[columizquierda][filaarriba];
                casilla2=pJuego.manCas.numCasillaMapa[columderecha][filaarriba];
                if (pJuego.manCas.casilla[casilla1].colision==true ||pJuego.manCas.casilla[casilla2].colision==true){
                    entidad.colision=true;
                }
            }
            case "left" -> {
                columizquierda=(posiciojugadorizquierda-entidad.vel)/pJuego.tamanioCasilla;
                casilla1=pJuego.manCas.numCasillaMapa[columizquierda][filaarriba];
                casilla2=pJuego.manCas.numCasillaMapa[columizquierda][filaabajo];
                if (pJuego.manCas.casilla[casilla1].colision==true ||pJuego.manCas.casilla[casilla2].colision==true){
                    entidad.colision=true;
                }
            }
            case "down" -> {
                filaabajo=(posiciojugadorabajo+entidad.vel)/pJuego.tamanioCasilla;
                casilla1=pJuego.manCas.numCasillaMapa[columizquierda][filaabajo];
                casilla2=pJuego.manCas.numCasillaMapa[columderecha][filaabajo];
                if (pJuego.manCas.casilla[casilla1].colision==true ||pJuego.manCas.casilla[casilla2].colision==true){
                    entidad.colision=true;
                }
            }
            case "right" -> {
                columderecha=(posiciojugadorderecha+entidad.vel)/pJuego.tamanioCasilla;
                casilla1=pJuego.manCas.numCasillaMapa[columderecha][filaarriba];
                casilla2=pJuego.manCas.numCasillaMapa[columderecha][filaabajo];
                if (pJuego.manCas.casilla[casilla1].colision==true ||pJuego.manCas.casilla[casilla2].colision==true){
                    entidad.colision=true;
                }
            }
            
        }
    }
 
    /**
     * Revisa las colisiones con objetos en las casilla alrededor del jugador
     * @param entidad entidad del mapa
     * @param jugador jugador principal de la instancia
     * @return 
     */
    
    public int chequeoObjetos(Entidad entidad, boolean jugador){
            int id=999;
            
            for(int i=0;i<pJuego.obj[0].length;i++){
                if(pJuego.obj[pJuego.mapaActual][i]!=null){
                    
                    entidad.hitBox.x=entidad.xMapa+entidad.hitBox.x;
                    entidad.hitBox.y=entidad.yMapa+entidad.hitBox.y;
                    
                    pJuego.obj[pJuego.mapaActual][i].hitBox.x=pJuego.obj[pJuego.mapaActual][i].xMapa+pJuego.obj[pJuego.mapaActual][i].hitBox.x;
                    pJuego.obj[pJuego.mapaActual][i].hitBox.y=pJuego.obj[pJuego.mapaActual][i].yMapa+pJuego.obj[pJuego.mapaActual][i].hitBox.y;
                    
                    switch (entidad.direction) {
                        case "up" -> {
                            entidad.hitBox.y-=entidad.vel;
                            if(entidad.hitBox.intersects(pJuego.obj[pJuego.mapaActual][i].hitBox)){
                                if(pJuego.obj[pJuego.mapaActual][i].colision==true){
                                    entidad.colision=true;
                                }
                                if(jugador==true){
                                    id=i;
                                }
                            }
                        }
                        case "left" -> {
                            entidad.hitBox.x-=entidad.vel;
                            if(entidad.hitBox.intersects(pJuego.obj[pJuego.mapaActual][i].hitBox)){
                                if(pJuego.obj[pJuego.mapaActual][i].colision==true){
                                    entidad.colision=true;
                                }
                                if(jugador==true){
                                    id=i;
                                }
                            }
                        }
                        case "down" -> {
                            entidad.hitBox.y+=entidad.vel;
                            if(entidad.hitBox.intersects(pJuego.obj[pJuego.mapaActual][i].hitBox)){
                                if(pJuego.obj[pJuego.mapaActual][i].colision==true){
                                    entidad.colision=true;
                                }
                                if(jugador==true){
                                    id=i;
                                }
                            }
                        }
                        case "right" -> {
                            entidad.hitBox.x+=entidad.vel;
                            if(entidad.hitBox.intersects(pJuego.obj[pJuego.mapaActual][i].hitBox)){
                                if(pJuego.obj[pJuego.mapaActual][i].colision==true){
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
                    pJuego.obj[pJuego.mapaActual][i].hitBox.x=pJuego.obj[pJuego.mapaActual][i].areadefectoX;
                    pJuego.obj[pJuego.mapaActual][i].hitBox.y=pJuego.obj[pJuego.mapaActual][i].areadefectoY;
                }
                
            }
            
            return id;
        }
    
    /**
     * Revisa las colisiones con entidades en las casillas alrededor de una entidad
     * @param entidad entidad para revisar la colision
     * @param objetivos arreglo de varias entidades
     * @return id de la entidad con la que se colisiona
     */
    public int chequeoEntidades(Entidad entidad, Entidad[][] objetivos){
                    int id=999;
            
            for(int i=0;i<objetivos[0].length;i++){
                if(objetivos[pJuego.mapaActual][i]!=null){
                    
                    entidad.hitBox.x=entidad.xMapa+entidad.hitBox.x;
                    entidad.hitBox.y=entidad.yMapa+entidad.hitBox.y;
                    
                    objetivos[pJuego.mapaActual][i].hitBox.x=objetivos[pJuego.mapaActual][i].xMapa+objetivos[pJuego.mapaActual][i].hitBox.x;
                    objetivos[pJuego.mapaActual][i].hitBox.y=objetivos[pJuego.mapaActual][i].yMapa+objetivos[pJuego.mapaActual][i].hitBox.y;
                    
                    switch (entidad.direction) {
                        case "up" -> {
                            entidad.hitBox.y-=entidad.vel;
                            if(entidad.hitBox.intersects(objetivos[pJuego.mapaActual][i].hitBox)){
                                entidad.colision=true;
                                id=i;
                            }
                        }
                        case "left" -> {
                            entidad.hitBox.x-=entidad.vel;
                            if(entidad.hitBox.intersects(objetivos[pJuego.mapaActual][i].hitBox)){
                                entidad.colision=true;
                                id=i;
                            }
                        }
                        case "down" -> {
                            entidad.hitBox.y+=entidad.vel;
                            if(entidad.hitBox.intersects(objetivos[pJuego.mapaActual][i].hitBox)){
                                entidad.colision=true;
                                id=i;
                            }
                        }
                        case "right" -> {
                            entidad.hitBox.x+=entidad.vel;
                            if(entidad.hitBox.intersects(objetivos[pJuego.mapaActual][i].hitBox)){
                                entidad.colision=true;
                                id=i;
                            }
                        }
                    }
                    entidad.hitBox.x=entidad.areadefectoX;
                    entidad.hitBox.y=entidad.areadefectoY;
                    objetivos[pJuego.mapaActual][i].hitBox.x=objetivos[pJuego.mapaActual][i].areadefectoX;
                    objetivos[pJuego.mapaActual][i].hitBox.y=objetivos[pJuego.mapaActual][i].areadefectoY;
                }
            }
            return id;
    }
   
    /**
     * Revisa las colisiones con entidades en las casillas alrededor del jugador
     * @param entidad entidad del mapa
     * @return 
     */
    
    public void chequeojugador(Entidad entidad){
                    entidad.hitBox.x=entidad.xMapa+entidad.hitBox.x;
                    entidad.hitBox.y=entidad.yMapa+entidad.hitBox.y;
                    
                    pJuego.jugador.hitBox.x=pJuego.jugador.xMapa+pJuego.jugador.hitBox.x;
                    pJuego.jugador.hitBox.y=pJuego.jugador.yMapa+pJuego.jugador.hitBox.y;
                
                    
                    switch (entidad.direction) {
                        case "up" -> {
                            entidad.hitBox.y-=entidad.vel;
                            if(entidad.hitBox.intersects(pJuego.jugador.hitBox)){
                                entidad.colision=true;
                            }
            }
                        case "left" -> {
                            entidad.hitBox.x-=entidad.vel;
                            if(entidad.hitBox.intersects(pJuego.jugador.hitBox)){
                                entidad.colision=true;
                            }
            }
                        case "down" -> {
                            entidad.hitBox.y+=entidad.vel;
                            if(entidad.hitBox.intersects(pJuego.jugador.hitBox)){
                                entidad.colision=true;
                            }
            }
                        case "right" -> {
                            entidad.hitBox.x+=entidad.vel;
                            if(entidad.hitBox.intersects(pJuego.jugador.hitBox)){
                                entidad.colision=true;
                            }
            }
                    }
                    entidad.hitBox.x=entidad.areadefectoX;
                    entidad.hitBox.y=entidad.areadefectoY;
                    pJuego.jugador.hitBox.x=pJuego.jugador.areadefectoX;
                    pJuego.jugador.hitBox.y=pJuego.jugador.areadefectoY;

    }
}