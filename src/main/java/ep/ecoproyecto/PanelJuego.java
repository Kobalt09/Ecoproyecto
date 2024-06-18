/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;
import Entidades.Entidad;
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
 * @author Cris
 */

public class PanelJuego extends JPanel implements Runnable{
    //configuracion de pantalla
    
    final int tamanioCasillaOrig= 32; //dimenciones por defecto del jugador, NPC o mapa title 32x32
    final int escala = 2; //escala los sprites de 32x32 a 64x64
    
    public final int tamanioCasilla= tamanioCasillaOrig*escala; //64x64 tlie
    public final int maxColumnasPantalla= 16;
    public final int maxFilasPantalla = 10;
    public final int screenWidth=tamanioCasilla *maxColumnasPantalla; // 1024
    public final int screenHeight=tamanioCasilla *maxFilasPantalla; // 640
    
    //configuracion de mapa
    public final int Maximocolumnas=50;
    public final int Maximofilas=50;
    public final int Maximomundos=5;
    public int Mapaactual = 0;
    
    //indica la cancion que esta sonando actualmente
    public int musica=0;
    
    
    //Fps permitidos
    int fps=60;
    
    public ManejadorCasillas manCas=new ManejadorCasillas(this);
   
    KeyHandler keyH= new KeyHandler();
    KeyHandler key2= new KeyHandler();
    
    public Sonido controlmusica = new Sonido();
    public Sonido efectossonido = new Sonido();
    public Colisionador colisiones =new Colisionador(this);
    EmisorObjetos objeto= new EmisorObjetos(this);
    EmisorNPC npcs= new EmisorNPC(this);
    public InterfazJugador hud = new InterfazJugador(this);
    public ControladorEventos ControlEventos= new ControladorEventos(this);
    Thread gameThread;
    //manejador de efectos de sonido
    

    
    //Jugador, objetos y NPC
    public Jugador jugador= new Jugador(this,keyH,key2);
    public Objetosclase obj[][]= new Objetosclase[Maximomundos][10];
    public Entidad NPC[][]= new Entidad[Maximomundos][10];
    
    //Estado de Juego
    public int estadodelJuego;
    public final int estadoJuego=1;
    public final int estadoDialogo=3;
    
    //interfaz
    public boolean pause;
    
    public PanelJuego() {
        
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); //reconocer la letra precioanda
        this.setFocusable(true);
        this.addKeyListener(key2);
    }
    
    public void configuraciondejuego(){
        objeto.establecerObj();
        npcs.establecernpcs();
        
        this.reproducirmusica(musica);
        
        //estado de juego
        estadodelJuego=estadoJuego;
        pause=true;
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
                //System.out.println("FPS: " + frameCount);
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
       
        if(estadodelJuego==1){
            
        }
        jugador.update();
        npcs.actualizacion();
        manCas.actualizar(jugador,screenWidth, screenHeight);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g; // estas dos clases son similares pero graphis2D tiene mas funciones para dibujar 

        //casillas
        manCas.dibujar(g2);
        
        //objetos
        objeto.draw(g2);
        
        //npc
        npcs.draw(g2);
        
        //jugador
        jugador.draw(g2);
        
        //interfaz
        hud.dibujar(g2);
        


        g2.dispose();
    }
    
    public void reproducirmusica(int i){
        controlmusica.reproducirmusica(i);
        //controlmusica.reproducir();
        //controlmusica.bucle();
    }
    /*
    public void paramusica(){
        controlmusica.parar();
    }*/
    
    public void efectos(int i){
        efectossonido.reproducirefecto(i);
    }
    
}
