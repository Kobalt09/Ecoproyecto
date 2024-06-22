/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.casillas;

import ep.ecoproyecto.logica.entidades.Jugador;
import ep.ecoproyecto.logica.Herramientas;
import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author hp
 */
public class ManejadorCasillas {
    
    PanelJuego gp;
    public Casilla[] casilla;
    public int numCasillaMapa[][];
    public String mapa[]= new String[5];

    public ManejadorCasillas(PanelJuego gp) {
        
        this.gp = gp;
        
        casilla = new Casilla[10];
        
        numCasillaMapa= new int[gp.Maximocolumnas][gp.Maximofilas];
        
        
        
        
        //-----AGREGAR MUNDOS AQUI----------//
        
        mapa[0]="/mapas/mapaprueba.txt";
        mapa[1]="/mapas/mapaprueba_1.txt";
        
        
        cargarMapa(mapa[gp.mapaActual],0,0);
        getImagenCasilla();
        
    }
    
    public void getImagenCasilla(){

            configutacion(0, "pasto", false);
            configutacion(1, "agua", true);
            configutacion(2, "pared", false);
            configutacion(3, "arbol", true);
            configutacion(4, "arena", false);
            configutacion(5, "asfalto", true);
            
    }
    
    public void configutacion(int id,String ruta, boolean colision){
        
        Herramientas herramienta = new Herramientas();
        
        try{

            casilla[id] = new Casilla();
            casilla[id].imagen= ImageIO.read(getClass().getResource("/casillas/"+ruta+".png"));
            casilla[id].imagen = herramienta.imagenEscalada(casilla[id].imagen, gp.tamanioCasilla, gp.tamanioCasilla);
            casilla[id].colision = colision;
            
        }catch(IOException e){

        }
        
    }
    
    public void cargarMapa(String direccion, int dx,int dy){
        try{
            
            
            InputStream is = getClass().getResourceAsStream(direccion);
            BufferedReader br =new BufferedReader(new InputStreamReader(is));
        
            int colum=0; 
            int fila=0;
            
          
            while(colum < gp.Maximocolumnas && fila < gp.Maximofilas){
            
       
                String line= br.readLine();
                
                while(colum < gp.Maximocolumnas){
                    
                    String numeros[] = line.split(" ");
                    
                    int num = Integer.parseInt(numeros[colum]);
                    
                    numCasillaMapa[colum][fila]=num;
                    colum++;
                }
                if(colum==gp.Maximocolumnas){
                    colum=0;
                    fila++;
                    
                }
            }
            br.close();
        }catch (IOException e) {
            
        } 
    }
    
    public void actualizar(Jugador jugador,int dimX,int dimY){
          
        int i=jugador.xMapa/64; 
        int j=jugador.yMapa/64;

        cargarMapa(mapa[gp.mapaActual],i,j);  
        
    
    }
    
    public void dibujar(Graphics2D g2){
        
        int columna=0;
        int fila=0;

    
        while (columna < gp.Maximocolumnas && fila <gp.Maximofilas){
        
            int numCasilla = numCasillaMapa[columna][fila];
                
                int PosicionX=columna*gp.tamanioCasilla;
                int PosicionY=fila*gp.tamanioCasilla;
                int PantallaX=PosicionX- gp.jugador.xMapa+gp.jugador.pantallaX;
                int PantallaY=PosicionY- gp.jugador.yMapa+gp.jugador.pantallaY;
                
                if((PosicionX+gp.tamanioCasilla > gp.jugador.xMapa-gp.jugador.pantallaX)&&(PosicionX-gp.tamanioCasilla < gp.jugador.xMapa+gp.jugador.pantallaX)&&
                   (PosicionY+gp.tamanioCasilla > gp.jugador.yMapa-gp.jugador.pantallaY)&&(PosicionY-gp.tamanioCasilla < gp.jugador.yMapa+gp.jugador.pantallaY)){     
                
                    g2.drawImage(casilla[numCasilla].imagen, PantallaX, PantallaY,null);
                }
                columna++;


                if(columna==gp.Maximofilas){
                    columna=0;
                    fila++;
                }
       }      
    }        
}
    

