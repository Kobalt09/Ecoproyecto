/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;

import Entidades.Entidad;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author hp
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
                        case "up":  
                            filaarriba=(posiciojugadorarriba-entidad.vel)/gp.tamanioCasilla;
                            casilla1=gp.manCas.numCasillaMapa[gp.Mapaactual][columizquierda][filaarriba];
                            casilla2=gp.manCas.numCasillaMapa[gp.Mapaactual][columderecha][filaarriba];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
                            break;
                        case "left":
                            columizquierda=(posiciojugadorizquierda-entidad.vel)/gp.tamanioCasilla;
                            casilla1=gp.manCas.numCasillaMapa[gp.Mapaactual][columizquierda][filaarriba];
                            casilla2=gp.manCas.numCasillaMapa[gp.Mapaactual][columizquierda][filaabajo];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
                        break;
                        case "down":
                            filaabajo=(posiciojugadorabajo+entidad.vel)/gp.tamanioCasilla;
                            casilla1=gp.manCas.numCasillaMapa[gp.Mapaactual][columizquierda][filaabajo];
                            casilla2=gp.manCas.numCasillaMapa[gp.Mapaactual][columderecha][filaabajo];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
                        break;
                        case "right":
                            columderecha=(posiciojugadorderecha+entidad.vel)/gp.tamanioCasilla;
                            casilla1=gp.manCas.numCasillaMapa[gp.Mapaactual][columderecha][filaarriba];
                            casilla2=gp.manCas.numCasillaMapa[gp.Mapaactual][columderecha][filaabajo];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
                        break;
                    }
    }

    //chequeo de colision con objetos
    
    
    public int chequeoObjetos(Entidad entidad, boolean jugador){ //MODIFICADO
            int id=999;
            
            for(int i=0;i<gp.obj[0].length;i++){
                if(gp.obj[gp.Mapaactual][i]!=null){
                    
                    entidad.hitBox.x=entidad.xMapa+entidad.hitBox.x;
                    entidad.hitBox.y=entidad.yMapa+entidad.hitBox.y;
                    
                    gp.obj[gp.Mapaactual][i].hitBox.x=gp.obj[gp.Mapaactual][i].posicionX+gp.obj[gp.Mapaactual][i].hitBox.x;
                    gp.obj[gp.Mapaactual][i].hitBox.y=gp.obj[gp.Mapaactual][i].posicionY+gp.obj[gp.Mapaactual][i].hitBox.y;

                
                    
                    switch (entidad.direction) {
                        case "up":  
                                entidad.hitBox.y-=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[gp.Mapaactual][i].hitBox)){
                                    if(gp.obj[gp.Mapaactual][i].colision==true){
                                        entidad.colision=true;
                                    }
                                    if(jugador==true){
                                        id=i;
                                    }
                                }
                                
                        break;
                        case "left":
                                entidad.hitBox.x-=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[gp.Mapaactual][i].hitBox)){
                                    if(gp.obj[gp.Mapaactual][i].colision==true){
                                        entidad.colision=true;
                                    }
                                    if(jugador==true){
                                        id=i;
                                    }
                                }
                        break;
                        case "down":
                                entidad.hitBox.y+=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[gp.Mapaactual][i].hitBox)){
                                    if(gp.obj[gp.Mapaactual][i].colision==true){
                                        entidad.colision=true;
                                    }
                                    if(jugador==true){
                                        id=i;
                                    }
                                }
                        break;
                        case "right":
                                entidad.hitBox.x+=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[gp.Mapaactual][i].hitBox)){
                                    if(gp.obj[gp.Mapaactual][i].colision==true){
                                        entidad.colision=true;
                                    }
                                    if(jugador==true){
                                        id=i;
                                    }
                                }
                        break;
                    }
                    entidad.hitBox.x=entidad.areadefectoX;
                    entidad.hitBox.y=entidad.areadefectoY;
                    gp.obj[gp.Mapaactual][i].hitBox.x=gp.obj[gp.Mapaactual][i].areadefectoX;
                    gp.obj[gp.Mapaactual][i].hitBox.y=gp.obj[gp.Mapaactual][i].areadefectoY;
                }
                
            }
            
            return id;
        }
    
    public int chequeoEntidades(Entidad entidad, Entidad[][] objetivos){  //MODIFICADO
                    int id=999;
            
            for(int i=0;i<objetivos[0].length;i++){
                if(objetivos[gp.Mapaactual][i]!=null){
                    
                    entidad.hitBox.x=entidad.xMapa+entidad.hitBox.x;
                    entidad.hitBox.y=entidad.yMapa+entidad.hitBox.y;
                    
                    objetivos[gp.Mapaactual][i].hitBox.x=objetivos[gp.Mapaactual][i].xMapa+objetivos[gp.Mapaactual][i].hitBox.x;
                    objetivos[gp.Mapaactual][i].hitBox.y=objetivos[gp.Mapaactual][i].yMapa+objetivos[gp.Mapaactual][i].hitBox.y;
                
                    
                    switch (entidad.direction) {
                        case "up":  
                                entidad.hitBox.y-=entidad.vel;
                                if(entidad.hitBox.intersects(objetivos[gp.Mapaactual][i].hitBox)){
                                    entidad.colision=true;
                                    id=i;
                                }
                                
                        break;
                        case "left":
                                entidad.hitBox.x-=entidad.vel;
                                if(entidad.hitBox.intersects(objetivos[gp.Mapaactual][i].hitBox)){
                                    entidad.colision=true;
                                    id=i;
                                }
                        break;
                        case "down":
                                entidad.hitBox.y+=entidad.vel;
                                if(entidad.hitBox.intersects(objetivos[gp.Mapaactual][i].hitBox)){
                                    entidad.colision=true;
                                    id=i;
                                }
                        break;
                        case "right":
                                entidad.hitBox.x+=entidad.vel;
                                if(entidad.hitBox.intersects(objetivos[gp.Mapaactual][i].hitBox)){
                                    entidad.colision=true;
                                    id=i;
                                }
                        break;
                    }
                    entidad.hitBox.x=entidad.areadefectoX;
                    entidad.hitBox.y=entidad.areadefectoY;
                    objetivos[gp.Mapaactual][i].hitBox.x=objetivos[gp.Mapaactual][i].areadefectoX;
                    objetivos[gp.Mapaactual][i].hitBox.y=objetivos[gp.Mapaactual][i].areadefectoY;
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
                        case "up":  
                                entidad.hitBox.y-=entidad.vel;
                                if(entidad.hitBox.intersects(gp.jugador.hitBox)){
                                    entidad.colision=true;
                                }
                                
                        break;
                        case "left":
                                entidad.hitBox.x-=entidad.vel;
                                if(entidad.hitBox.intersects(gp.jugador.hitBox)){
                                    entidad.colision=true;
                                }
                        break;
                        case "down":
                                entidad.hitBox.y+=entidad.vel;
                                if(entidad.hitBox.intersects(gp.jugador.hitBox)){
                                    entidad.colision=true;
                                }
                        break;
                        case "right":
                                entidad.hitBox.x+=entidad.vel;
                                if(entidad.hitBox.intersects(gp.jugador.hitBox)){
                                    entidad.colision=true;
                                }
                        break;
                    }
                    entidad.hitBox.x=entidad.areadefectoX;
                    entidad.hitBox.y=entidad.areadefectoY;
                    gp.jugador.hitBox.x=gp.jugador.areadefectoX;
                    gp.jugador.hitBox.y=gp.jugador.areadefectoY;

    }
}