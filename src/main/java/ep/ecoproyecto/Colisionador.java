/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;

import Entidades.Entidad;

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
        
        int izqEntidad = entidad.xMapa+entidad.hitBox.x;
        int derEntidad = entidad.xMapa+entidad.hitBox.x + entidad.hitBox.width;
        int arrEntidad = entidad.yMapa+ entidad.hitBox.y;
        int abjEntidad = entidad.yMapa+ entidad.hitBox.y+entidad.hitBox.height;
    
        int izqEntidadCol = izqEntidad/gp.tamanioCasilla;
        int derEntidadCol = derEntidad/gp.tamanioCasilla;
        int arrEntidadFila = arrEntidad/gp.tamanioCasilla;
        int abjEntidadFila = abjEntidad/gp.tamanioCasilla;
    
        int numCas1,numCas2;
        
        switch(entidad.direction){
            
            case "up" -> {     
                
                arrEntidadFila =(arrEntidad + entidad.vel)/gp.tamanioCasilla;
                numCas1 = gp.manCas.numCasillaMapa[izqEntidadCol][arrEntidadFila];
                numCas2 = gp.manCas.numCasillaMapa[derEntidadCol][arrEntidadFila];
                
                if(gp.manCas.casilla[numCas1].colision == true ||gp.manCas.casilla[numCas2].colision == true){
                    entidad.colision=true;
                }
                
                break;
            }
            
            case "down" -> {      
                
                abjEntidadFila =(abjEntidad + entidad.vel)/gp.tamanioCasilla;
                numCas1 = gp.manCas.numCasillaMapa[izqEntidadCol][abjEntidadFila];
                numCas2 = gp.manCas.numCasillaMapa[derEntidadCol][abjEntidadFila];
               
                if(gp.manCas.casilla[numCas1].colision == true ||gp.manCas.casilla[numCas2].colision == true){
                    entidad.colision=true;
                }
                                         
                break;
            }
            
            case "left" ->  {     
                
                izqEntidadCol =(izqEntidad - entidad.vel)/gp.tamanioCasilla;
                numCas1 = gp.manCas.numCasillaMapa[izqEntidadCol][arrEntidadFila];
                numCas2 = gp.manCas.numCasillaMapa[izqEntidadCol][abjEntidadFila];
               
                if(gp.manCas.casilla[numCas1].colision == true ||gp.manCas.casilla[numCas2].colision == true){
                    entidad.colision=true;
                }
                
                break;
            }
            
            case "right" -> {  
                
                derEntidadCol =(derEntidad - entidad.vel)/gp.tamanioCasilla;
                numCas1 = gp.manCas.numCasillaMapa[derEntidadCol][arrEntidadFila];
                numCas2 = gp.manCas.numCasillaMapa[derEntidadCol][abjEntidadFila];
               
                if(gp.manCas.casilla[numCas1].colision == true ||gp.manCas.casilla[numCas2].colision == true){
                    entidad.colision=true;
                }
                
                break;
            }
         
        }
        

    }
    
    //chequeo de colision con objetos
            public int chequeoObjetos(Entidad entidad, boolean jugador){
            int id=999;
            
            for(int i=0;i<gp.obj.length;i++){
                if(gp.obj[i]!=null){
                    entidad.hitBox.x=entidad.xMapa+entidad.hitBox.x;
                    entidad.hitBox.y=entidad.yMapa+entidad.hitBox.y;
                    
                    gp.obj[i].hitBox.x=gp.obj[i].posicionX+gp.obj[i].hitBox.x;
                    gp.obj[i].hitBox.y=gp.obj[i].posicionY+gp.obj[i].hitBox.y;
                
                    
                    switch (entidad.direction) {
                        case "up":  
                                entidad.hitBox.y-=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[i].hitBox)){
                                    if(gp.obj[i].colision==true){
                                        entidad.colision=true;
                                    }
                                    if(jugador==true){
                                        id=i;
                                    }
                                    System.out.println("colision arriba");
                                }
                                
                        break;
                        case "left":
                                entidad.hitBox.x-=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[i].hitBox)){
                                    if(gp.obj[i].colision==true){
                                        entidad.colision=true;
                                    }
                                    if(jugador==true){
                                        id=i;
                                    }
                                    System.out.println("colision izquierda");
                                }
                        break;
                        case "down":
                                entidad.hitBox.y+=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[i].hitBox)){
                                    if(gp.obj[i].colision==true){
                                        entidad.colision=true;
                                    }
                                    if(jugador==true){
                                        id=i;
                                    }
                                    System.out.println("colision abajo");
                                }
                        break;
                        case "right":
                                entidad.hitBox.x+=entidad.vel;
                                if(entidad.hitBox.intersects(gp.obj[i].hitBox)){
                                    if(gp.obj[i].colision==true){
                                        entidad.colision=true;
                                    }
                                    if(jugador==true){
                                        id=i;
                                    }
                                    System.out.println("colision derecha");
                                }
                        break;
                    }
                    entidad.hitBox.x=entidad.areadefectoX;
                    entidad.hitBox.y=entidad.areadefectoY;
                    gp.obj[i].hitBox.x=gp.obj[i].areadefectoX;
                    gp.obj[i].hitBox.y=gp.obj[i].areadefectoY;
                }
                
            }
            
            return id;
        }
}
