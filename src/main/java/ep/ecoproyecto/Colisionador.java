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
    
    public int checkObjeto(Entidad entidad, boolean jugador){
        int index=9999;
        
        for(int i=0;i<gp.obj.length;i++){
            if(gp.obj[i]!=null){
                //conseguir la posicion de la entidad
                entidad.AreaX=entidad.xMapa+entidad.AreadefectoX;
                entidad.AreaY=entidad.yMapa+entidad.AreadefectoY;
                
                
                //conseguir el area
                gp.obj[i].AreaobjX=gp.obj[i].posicionX+gp.obj[i].AreaobjX;
                gp.obj[i].AreaobjY=gp.obj[i].posicionY+gp.obj[i].AreaobjY;
                
                switch(entidad.direction){
                    case"up":
                        entidad.AreaY-=entidad.vel;
                        if(entidad.hitBox.intersects(gp.obj[i].Areasolida)){
                            if(gp.obj[i].colision==true){
                                //entidad.colision=true;
                            }
                            if(jugador ==true){
                                index=i;
                                
                            }
                        }
                        break;
                    case"down":
                        entidad.AreaY+=entidad.vel;
                        if(entidad.hitBox.intersects(gp.obj[i].Areasolida)){
                            if(gp.obj[i].colision==true){
                                //entidad.colision=true;
                            }
                            if(jugador ==true){
                                index=i;
                            }
                        }
                        break;
                     case"left":
                        entidad.AreaX-=entidad.vel;
                        if(entidad.hitBox.intersects(gp.obj[i].Areasolida)){
                            if(gp.obj[i].colision==true){
                                //entidad.colision=true;
                            }
                            if(jugador == true){
                               // index=i;
                            }
                        }
                        break;
                    case"right":
                        entidad.AreaX+=entidad.vel;
                        if(entidad.hitBox.intersects(gp.obj[i].Areasolida)){
                            if(gp.obj[i].colision==true){
                               //a entidad.colision=true;
                            }
                            if(jugador == true){
                                index=i;
                            }

                        }
                        break; 
                }
                /*
                entidad.AreaX=entidad.AreadefectoX;
                entidad.AreaY=entidad.AreadefectoY;
                
                gp.obj[i].AreaobjX=gp.obj[i].AreaobjdefectoX;
                gp.obj[i].AreaobjY=gp.obj[i].AreaobjdefectoY;*/
            }
        }
        
        return index;
    }
}
