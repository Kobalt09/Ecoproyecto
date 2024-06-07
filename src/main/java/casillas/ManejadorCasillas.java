/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casillas;

import Entidades.Jugador;
import ep.ecoproyecto.PanelJuego;
import java.awt.Graphics2D;
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

    public ManejadorCasillas(PanelJuego gp) {
        
        this.gp = gp;
        
        casilla = new Casilla[10];
        
        numCasillaMapa= new int[gp.Maximocolumnas][gp.Maximofilas];
        
        cargarMapa("/mapas/mapaprueba.txt",0,0);
        getImagenCasilla();
        
        
    }
    
    public void getImagenCasilla(){
        try{
            casilla[0]=new Casilla();
            casilla[0].imagen= ImageIO.read(getClass().getResource("/casillas/pasto.png"));
            casilla[0].colision=false;
            casilla[1]=new Casilla();
            casilla[1].imagen= ImageIO.read(getClass().getResource("/casillas/agua.png"));
            casilla[1].colision=false;
            casilla[2]=new Casilla();
            casilla[2].imagen= ImageIO.read(getClass().getResource("/casillas/pared.png"));
            casilla[2].colision=true;
            casilla[3]=new Casilla();
            casilla[3].imagen= ImageIO.read(getClass().getResource("/casillas/arbol.png"));
            casilla[3].colision=true;
            casilla[4]=new Casilla();
            casilla[4].imagen= ImageIO.read(getClass().getResource("/casillas/arena.png"));
            casilla[4].colision=false;
        }
        catch(IOException e){
            e.printStackTrace();
        }
    
    }
    public void cargarMapa(String direccion,int dx,int dy){
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

        cargarMapa("/mapas/mapaprueba.txt",i,j);  
        
    
    }
    
    public void dibujar(Graphics2D g2){
        
        int columna=0;
        int fila=0;

    
        while (columna < gp.Maximocolumnas && fila <gp.Maximofilas){
        
            int numCasilla= numCasillaMapa[columna][fila];
                
                int PosicionX=columna*gp.tamanioCasilla;
                int PosicionY=fila*gp.tamanioCasilla;
                int PantallaX=PosicionX- gp.jugador.xMapa+gp.jugador.pantallaX;
                int PantallaY=PosicionY- gp.jugador.yMapa+gp.jugador.pantallaY;
                
                if((PosicionX+gp.tamanioCasilla > gp.jugador.xMapa-gp.jugador.pantallaX)&&(PosicionX-gp.tamanioCasilla < gp.jugador.xMapa+gp.jugador.pantallaX)&&
                   (PosicionY+gp.tamanioCasilla > gp.jugador.yMapa-gp.jugador.pantallaY)&&(PosicionY-gp.tamanioCasilla < gp.jugador.yMapa+gp.jugador.pantallaY)){     
                
                    g2.drawImage(casilla[numCasilla].imagen, PantallaX, PantallaY, gp.tamanioCasilla,gp.tamanioCasilla,null);
                }
                columna++;


                if(columna==gp.Maximofilas){
                    columna=0;
                    fila++;
                }
       }      
    }        
}
    

