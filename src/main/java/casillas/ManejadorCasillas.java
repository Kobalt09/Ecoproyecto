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
    Casilla[] casilla;
    int numCasillaMapa[][];

    public ManejadorCasillas(PanelJuego gp) {
        
        this.gp = gp;
        
        casilla = new Casilla[10];
        
        numCasillaMapa= new int[gp.maxColumnas+2][gp.maxFilas+2];
        cargarMapa("/mapas/mapaprueba.txt",0,0);
        getImagenCasilla();
        
        
    }
    
    public void getImagenCasilla(){
        try{
            casilla[0]=new Casilla();
            casilla[0].imagen= ImageIO.read(getClass().getResource("/casillas/pasto.png"));
            
            casilla[1]=new Casilla();
            casilla[1].imagen= ImageIO.read(getClass().getResource("/casillas/agua.png"));
            
            casilla[2]=new Casilla();
            casilla[2].imagen= ImageIO.read(getClass().getResource("/casillas/pared.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    
    }
    public void cargarMapa(String direccion,int dx,int dy){
        try{
            
            
            InputStream is = getClass().getResourceAsStream(direccion);
            BufferedReader br =new BufferedReader(new InputStreamReader(is));
        
            int colum=0+dx+5; //el 5 es un numero random para mover el mapa
            int fila=0;
            
            int auxfila=dy;
            
            while(colum < gp.maxColumnas+dx+5 && fila < gp.maxFilas){
            
       
                String line= br.readLine();
                
                while(colum < gp.maxColumnas+dx+5 ){
                    String[] numeros = line.split(" ");
                
                    int num = Integer.parseInt(numeros[colum]);
                    
                    if (auxfila==0) numCasillaMapa[colum-dx-5][fila]=num;                       
                    
                    colum++;    
                }
                  if (colum==gp.maxColumnas+dx+5){
                        colum=0+dx+5;
                        if(auxfila==0)
                        fila++;
                    }
                
                if (auxfila>0)
                    auxfila--;
                
              
                
                
            }
        br.close();
        }
        catch (IOException e) {
     
        } 
    }
    
    public void actualizar(Jugador jugador,int dimX,int dimY){
          
        int i=jugador.xMapa/64; 
        int j=jugador.yMapa/64;
        
        if (jugador.x>dimX*2/3){
            jugador.vel=0;
            if ("left".equals(jugador.getDirection())) jugador.vel=4;
            
        } 
        if (jugador.x<dimX*1/3){
            jugador.vel=0;    
            if ("right".equals(jugador.getDirection())) jugador.vel=4;
        }
          
        
        if (jugador.y>dimY*2/3){
            jugador.vel=0;
            if ("up".equals(jugador.getDirection())) jugador.vel=4;
            
        } 
        if (jugador.y<dimY*1/3){
            jugador.vel=0;    
            if ("down".equals(jugador.getDirection())) jugador.vel=4;
        }
        
        //BORDES DEL MAPA   
        if (jugador.xMapa<0-(6*64)+5)jugador.xMapa+=16;
        if (jugador.xMapa>dimX-(2*64)-5)jugador.xMapa-=16;        
        if (jugador.yMapa<0)jugador.yMapa+=16;
        if (jugador.yMapa>dimY-5)jugador.yMapa-=16;
       
        cargarMapa("/mapas/mapaprueba.txt",i,j);  
        
    
    }
    
    public void dibujar(Graphics2D g2){
        int tamanio = 64;
        
        int columna=0;
        int fila=0;
        int x=0;
        int y=0;
        
        while (columna < gp.maxColumnas && fila <gp.maxFilas){
            
            int numCasilla= numCasillaMapa[columna][fila];
            
            
            g2.drawImage(casilla[numCasilla].imagen, x, y, gp.tamanioCasilla,gp.tamanioCasilla,null);
            columna++;
            x+=gp.tamanioCasilla;
            if(columna==gp.maxColumnas){
                columna=0;
                x=0;
                fila++;
                y+=gp.tamanioCasilla;
            }
        }      
        
    }
    
}
