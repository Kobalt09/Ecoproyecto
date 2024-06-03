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
}
