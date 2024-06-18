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
                            casilla1=gp.manCas.numCasillaMapa[gp.MapaActual][columizquierda][filaarriba];
                            casilla2=gp.manCas.numCasillaMapa[gp.MapaActual][columderecha][filaarriba];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
                            break;
                        case "left":
                            columizquierda=(posiciojugadorizquierda-entidad.vel)/gp.tamanioCasilla;
                            casilla1=gp.manCas.numCasillaMapa[gp.MapaActual][columizquierda][filaarriba];
                            casilla2=gp.manCas.numCasillaMapa[gp.MapaActual][columizquierda][filaabajo];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
                        break;
                        case "down":
                            filaabajo=(posiciojugadorabajo+entidad.vel)/gp.tamanioCasilla;
                            casilla1=gp.manCas.numCasillaMapa[gp.MapaActual][columizquierda][filaabajo];
                            casilla2=gp.manCas.numCasillaMapa[gp.MapaActual][columderecha][filaabajo];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
                        break;
                        case "right":
                            columderecha=(posiciojugadorderecha+entidad.vel)/gp.tamanioCasilla;
                            casilla1=gp.manCas.numCasillaMapa[gp.MapaActual][columderecha][filaarriba];
                            casilla2=gp.manCas.numCasillaMapa[gp.MapaActual][columderecha][filaabajo];
                            if (gp.manCas.casilla[casilla1].colision==true ||gp.manCas.casilla[casilla2].colision==true){
                                entidad.colision=true;
                            }
                        break;
                    }
    }

    //chequeo de colision con objetos
    
    
    public int chequeoObjetos(Entidad entidad, boolean jugador){
            int id=999;
            
            for(int i=0;i<gp.obj[0].length;i++){
                if(gp.obj[gp.MapaActual][i]!=null){
                    
                    entidad.hitBox.x=entidad.xMapa+entidad.hitBox.x;
                    entidad.hitBox.y=entidad.yMapa+entidad.hitBox.y;
                    
                    gp.obj[gp.MapaActual][i].hitBox.x=gp.obj[gp.MapaActual][i].posicionX+gp.obj[gp.MapaActual][i].hitBox.x;
                    gp.obj[gp.MapaActual][i].hitBox.y=gp.obj[gp.MapaActual][i].posicionY+gp.obj[gp.MapaActual][i].hitBox.y;

                
                    
                    switch (entidad.direction) {
                        case "up":  
                                entidad.hitBox.y-=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[gp.MapaActual][i].hitBox)){
                                    if(gp.obj[gp.MapaActual][i].colision==true){
                                        entidad.colision=true;
                                    }
                                    if(jugador==true){
                                        id=i;
                                    }
                                }
                                
                        break;
                        case "left":
                                entidad.hitBox.x-=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[gp.MapaActual][i].hitBox)){
                                    if(gp.obj[gp.MapaActual][i].colision==true){
                                        entidad.colision=true;
                                    }
                                    if(jugador==true){
                                        id=i;
                                    }
                                }
                        break;
                        case "down":
                                entidad.hitBox.y+=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[gp.MapaActual][i].hitBox)){
                                    if(gp.obj[gp.MapaActual][i].colision==true){
                                        entidad.colision=true;
                                    }
                                    if(jugador==true){
                                        id=i;
                                    }
                                }
                        break;
                        case "right":
                                entidad.hitBox.x+=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[gp.MapaActual][i].hitBox)){
                                    if(gp.obj[gp.MapaActual][i].colision==true){
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
                    gp.obj[gp.MapaActual][i].hitBox.x=gp.obj[gp.MapaActual][i].areadefectoX;
                    gp.obj[gp.MapaActual][i].hitBox.y=gp.obj[gp.MapaActual][i].areadefectoY;
                }
                
            }
            
            return id;
        }
    
    public int chequeoEntidades(Entidad entidad, Entidad[][] objetivos){
                    int id=999;
            
            for(int i=0;i<objetivos[0].length;i++){
                if(objetivos[gp.MapaActual][i]!=null){
                    
                    entidad.hitBox.x=entidad.xMapa+entidad.hitBox.x;
                    entidad.hitBox.y=entidad.yMapa+entidad.hitBox.y;
                    
                    objetivos[gp.MapaActual][i].hitBox.x=objetivos[gp.MapaActual][i].xMapa+objetivos[gp.MapaActual][i].hitBox.x;
                    objetivos[gp.MapaActual][i].hitBox.y=objetivos[gp.MapaActual][i].yMapa+objetivos[gp.MapaActual][i].hitBox.y;
                
                    
                    switch (entidad.direction) {
                        case "up":  
                                entidad.hitBox.y-=entidad.vel;
                                if(entidad.hitBox.intersects(objetivos[gp.MapaActual][i].hitBox)){
                                    entidad.colision=true;
                                    id=i;
                                }
                                
                        break;
                        case "left":
                                entidad.hitBox.x-=entidad.vel;
                                if(entidad.hitBox.intersects(objetivos[gp.MapaActual][i].hitBox)){
                                    entidad.colision=true;
                                    id=i;
                                }
                        break;
                        case "down":
                                entidad.hitBox.y+=entidad.vel;
                                if(entidad.hitBox.intersects(objetivos[gp.MapaActual][i].hitBox)){
                                    entidad.colision=true;
                                    id=i;
                                }
                        break;
                        case "right":
                                entidad.hitBox.x+=entidad.vel;
                                if(entidad.hitBox.intersects(objetivos[gp.MapaActual][i].hitBox)){
                                    entidad.colision=true;
                                    id=i;
                                }
                        break;
                    }
                    entidad.hitBox.x=entidad.areadefectoX;
                    entidad.hitBox.y=entidad.areadefectoY;
                    objetivos[gp.MapaActual][i].hitBox.x=objetivos[gp.MapaActual][i].areadefectoX;
                    objetivos[gp.MapaActual][i].hitBox.y=objetivos[gp.MapaActual][i].areadefectoY;
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