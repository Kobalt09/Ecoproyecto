/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;
import Entidades.Jugador;
import casillas.ManejadorCasillas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import objetos.Objetosclase;

/**
 *
 * @author Cris
 */
public class PanelJuego extends JPanel implements Runnable{
    //configuracion de pantalla
    
    final int tamanioCasillaOrig= 32; //dimenciones por defecto del jugador, NPC o mapa title 32x32
    final int escala = 2; //escala los sprites de 32x32 a 64x64
    
    public final int tamanioCasilla= tamanioCasillaOrig*escala; //64x64 tlie
    public final int maxColumnas= 16;
    public final int maxFilas = 10;
    final int screenWidth=tamanioCasilla *maxColumnas; // 1024
    final int screenHeight=tamanioCasilla *maxFilas; // 640
    
    //Fps permitidos
    int fps=60;
    
    ManejadorCasillas manCas=new ManejadorCasillas(this);
    KeyHandler keyH= new KeyHandler();
    Thread gameThread;
    
    EmisorObjetos objeto= new EmisorObjetos(this);
    
    Jugador jugador= new Jugador(this,keyH);
    //arreglo con el total de objetos que se pueden mostrar al mismo tiempo
    Objetosclase obj[]= new Objetosclase[10];
    
    
    //Posicion por defecto del jugador
    int jugadorX=100;
    int jugadorY=100;
    int vel_jugador=8;
    
    public PanelJuego() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); //reconocer la letra precioanda
        this.setFocusable(true);
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        
    }

    @Override
    public void run() {
        double drawInterval= 1000000000/fps; //0.016666 segundos
        double nextDrawTime = System.nanoTime()+drawInterval;
        long lastTimeCheck = System.nanoTime();
        int frameCount = 0;

        while(gameThread!= null){
            //loop de actualizacion del juego
            //System.out.println("game runing");   

            long currentTime = System.nanoTime();

            //actualiza la informacion como la posicion del personaje
            update();
            //muestra la pantalla y actuliza la informacion en pantalla
            repaint();

            // Incrementa el contador de frames
            frameCount++;

            // mediante esto imrpmimos el numero de cuadros que se han hecho en 1 segundo
            if (currentTime - lastTimeCheck >= 1000000000) {
                System.out.println("FPS: " + frameCount);
                frameCount = 0;
                lastTimeCheck = System.nanoTime();
            }

            //de esta forma el programa se queda en espera tras que se cumpla una actualizacion 
            try{
                double remaningTime= nextDrawTime - System.nanoTime();
                remaningTime=remaningTime /1000000;

                if(remaningTime<0){
                    remaningTime=0;
                }
                Thread.sleep((long) remaningTime);
                nextDrawTime+= drawInterval;

            } catch (InterruptedException ex) {
                Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void update(){
       
        jugador.update();
        manCas.actualizar(jugador,screenWidth, screenHeight);
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g; // estas dos clases son similares pero graphis2D tiene mas funciones para dibujar 

        manCas.dibujar(g2);
        
        jugador.draw(g2);
        
        objeto.establecerObj();
        
        objeto.draw(g2);
        
        
        g2.dispose();
    }
    
}
