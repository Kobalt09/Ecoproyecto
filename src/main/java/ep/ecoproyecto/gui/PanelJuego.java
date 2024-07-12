package ep.ecoproyecto.gui;
import ep.ecoproyecto.logica.ControladorEventos;
import ep.ecoproyecto.logica.InterfazJugador;
import ep.ecoproyecto.logica.EmisorObjetos;
import ep.ecoproyecto.logica.ManejadorEntidades;
import ep.ecoproyecto.logica.Sonido;
import ep.ecoproyecto.logica.Colisionador;
import ep.ecoproyecto.logica.Configuracion;
import ep.ecoproyecto.logica.ControladorMinijuegos;
import ep.ecoproyecto.logica.KeyHandler;
import ep.ecoproyecto.logica.WindowHandler;
import ep.ecoproyecto.logica.entidades.Entidad;
import ep.ecoproyecto.logica.casillas.ManejadorCasillas;
import ep.ecoproyecto.logica.entidades.Jugador;
import ep.ecoproyecto.logica.entidades.JugadorMP;
import ep.ecoproyecto.logica.net.*;
import ep.ecoproyecto.logica.net.packets.Paquete00Login;
import ep.ecoproyecto.logica.net.packets.Paquete01Desconectar;
import ep.ecoproyecto.logica.objetos.Objetosclase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ep.ecoproyecto.logica.minijuegos.Minijuego;

/**
 * Clase que contiene en si toda la reproduccion del juego
 * @author C-A-F
 */

public class PanelJuego extends JPanel implements Runnable{
    //configuracion de pantalla
    
    final int tamanioCasillaOrig= 32; //dimenciones por defecto del jugador, NPC o mapa title 32x32
    final int escala = 2; //escala los sprites de 32x32 a 64x64
    
    public final int tamanioCasilla= tamanioCasillaOrig*escala; //casillas 64x64 
    
    public final int maxColumnasPantalla= 16;
    //public final int maxColumnasPantalla= 10;
    public final int maxFilasPantalla = 10;
    public int screenWidth=tamanioCasilla *maxColumnasPantalla; // resoluciom en x 1024
    public int screenHeight=tamanioCasilla *maxFilasPantalla; //  resoluciom en x  640
    
    
    //configuracion de mapa
    public final int maximoColumnas=40;
    public final int maximoFilas=40;
    public final int maximoMundos=6;
    public int mapaActual=0;
    public static PanelJuego juego;
     
    //indica la cancion que esta sonando actualmente
    public int musica=0;
    
    //Fps permitidos
    int fps=60;
    


    public ManejadorCasillas manCas=new ManejadorCasillas(this); // maneja los mapas 
    public KeyHandler keyH= new KeyHandler();                    // detecta el teclado
    public Sonido controlmusica = new Sonido();
    public Sonido controlsonido = new Sonido();
    public Configuracion config = new Configuracion(this);
    public WindowHandler winH;
    public JFrame frame;
    public boolean pantallaCompleta = false;

    public Colisionador colisiones =new Colisionador(this);
    public EmisorObjetos objetos= new EmisorObjetos(this);
    public ManejadorEntidades npcs= new ManejadorEntidades(this);
    public ControladorMinijuegos mini= new ControladorMinijuegos(this);
    public InterfazJugador hud = new InterfazJugador(this);
    public ControladorEventos controlEventos= new ControladorEventos(this);
    Thread gameThread;

   
    
    //Jugador, objetos y NPC
    public Jugador jugador;
    public LinkedList<JugadorMP> jugadores = new LinkedList<>(); 
    public Objetosclase obj[][]= new Objetosclase[maximoMundos][20];
    public Entidad NPC[][]= new Entidad[maximoMundos][20];
    public Minijuego[][] minijuego=new Minijuego[maximoMundos][10];


    
    //-----ESTADOS DE JUEGO------------//
    public boolean pause;
    
    //----ONLINE-----//
    public Cliente socketCliente;
    public Servidor socketserver;
    

    /**
     *inicia el panel en un frame
    *@param frame marco donde se dibujará el panel

    */
    public PanelJuego(JFrame frame) {
        
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); //reconocer la letra precioanda
        this.setFocusable(true);
        this.frame = frame;
        winH = new WindowHandler(this);
    }
    
    //prepara todo para antes de dibujar y comenzar el juego
    public void configuracionDeJuego(){
        juego=this;
        
        keyH.establecerPanel(this);

        objetos.establecerObj();     //envia los objetos definidos a un arreglo
        npcs.establecerEntidades();      //igual pero con npcs
        mini.establecerminijuegos();
        
        
        this.jugador=new Jugador(this); //inicializa un jugador en blanco para las funciones que lo necesitan antes de darle valores
        
        //ESTADO DE JUEGO//
        pause=false;
        
    }
    
    public void setPantallaCompleta(){
        //OBTENER DISPOSITIVO DE PANTALLA LOCAL//
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);
        
        //CONSEGUIR LOS DATOS DE LA NUEVA RESOLUCION//
        screenWidth = frame.getWidth();
        screenHeight = frame.getHeight();
    }
    
    public void inicioJugador (String nomb){
        if (pantallaCompleta){
            setPantallaCompleta();
        }
        jugador = new JugadorMP(null,-1,this,keyH,nomb);
        jugadores.add((JugadorMP)jugador);
        
        Paquete00Login loginpacket = new Paquete00Login(jugador.getUsername(),jugador.xMapa,jugador.yMapa,jugador.direction,jugador.getMapa());
        if (socketserver!=null){
            socketserver.addConnection((JugadorMP)jugador, loginpacket);
        }
        
        loginpacket.writeData(socketCliente);
        
    }
        
    public void removePlayerMP(String usuarioname) {
        for (JugadorMP j:jugadores){
            if (j.getUsername().equals(usuarioname)){
                jugadores.remove(j);
            }
        }
    }
   
    /**
     * maneja los fps del juego
     */
    @Override
    public void run() {
        double drawInterval= 2000000000/fps; //0.016666 segundos
        double nextDrawTime = System.nanoTime()+drawInterval;
        long lastTimeCheck = System.nanoTime();
        int frameCount = 0;

        while(gameThread!= null){
            //loop de actualizacion del juego
            //System.out.println("game runing");   

            long currentTime = System.nanoTime();

            //actualiza la informacion como la posicion del personaje
            actualizar();
            //muestra la pantalla y actuliza la informacion en pantalla
            repaint();

            // Incrementa el contador de frames
            frameCount++;

            // mediante esto imrpmimos el numero de cuadros que se han hecho en 1 segundo
            if (currentTime - lastTimeCheck >= 2000000000) {
                //System.out.println("FPS: " + frameCount);
                frameCount = 0;
                lastTimeCheck = System.nanoTime();
            }
          
            //de esta forma el programa se queda en espera tras que se cumpla una actualizacion 
            try{
                double remaningTime= nextDrawTime - System.nanoTime();
                remaningTime=remaningTime /2000000;
               
                if(keyH.escPressed==true){
                              gameThread.interrupt();
                            
                          }
                
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
    /**
     * devuelve a MenuIni
     */
    public void regresarAlMenuIni(){
        //DETENER MUSICA//
        controlmusica.detenerMusica();
        
        //PAQUETE DE DESCONEXION//
        Paquete01Desconectar packet = new Paquete01Desconectar(this.jugador.getUsername());
        packet.writeData(this.socketCliente);
        
        //CERRAR JUEGO//
        if (gameThread!=null){
            gameThread.interrupt();
        }
        frame.setVisible(false);
        frame.dispose();
        
        //MOSTRAR MENU INICIAL//
        java.awt.EventQueue.invokeLater(() -> {
            new MenuIni().setVisible(true);
        });
    }
    /**
     * actualiza la informacion de las variables
     */
    public void actualizar(){
        
        if (!pause){
            jugador.update();
            npcs.actualizar();
        }
        
        for (JugadorMP jug : jugadores) {
            if (jugador!=jug)jug.update();
        }
        

        manCas.actualizar(jugador,screenWidth, screenHeight);
      
    }
    /**
     * dibuja todo lo que se ve en pantalla
     * @param g clase base para el dibujado
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g; // estas dos clases son similares pero graphis2D tiene mas funciones para dibujar 

        //casillas
        
        manCas.dibujado(g2);
        
        //objetos
        objetos.dibujado(g2);
        
        //npc
        npcs.dibujado(g2);
        

        //jugadores
        for (JugadorMP jug : jugadores.reversed()) {
            jug.dibujado(g2);
            hud.dibujado(g2,jug);
        }

        g2.dispose();
    }
    
    public void reproducirMusica(int i){
        controlmusica.reproducirmusica(i);
    }
    
    public int reproducirmusicatienda(int i){
        return controlmusica.musicatienda(i);
    }
    
    public void efectos(int i){
        controlsonido.reproducirefecto(i);
    }
    /**
     * Devuelve el lugar en el indice del jugador deseado
     * @param usuario nombre del usuario
     * @return indice del jugador
     */
    private int getIndiceJugador(String usuario){
        int indice=0;
        for (Jugador jug:jugadores){
            if(jug.getUsername() == null ? (usuario) == null : jug.getUsername().equals(usuario)){
                break;
            }indice++;
        }
        return indice;
    }
    
    /**
     * Mueve a los jugadores que no son el principal
     * @param usuario nombre de usuario
     * @param x posicion en x a mover
     * @param y posicion en y a mover
     * @param dir direccion a donde se mueve el jugador
     */
    public void moverJugadores(String usuario,int x, int y,String dir){
        int indice=getIndiceJugador(usuario);
        
        this.jugadores.get(indice).xMapa=x;
        this.jugadores.get(indice).yMapa=y;
        this.jugadores.get(indice).direction=dir;
        
        this.jugadores.get(indice).spriteCounter++;
                if (this.jugadores.get(indice).spriteCounter>15){
                    if (this.jugadores.get(indice).spriteNum == 2 )
                    {this.jugadores.get(indice).spriteNum=1;}
                    else{
                    if (this.jugadores.get(indice).spriteNum == 1)
                        this.jugadores.get(indice).spriteNum=2;
                    }
                    this.jugadores.get(indice).spriteCounter = 0;
                }
    }
 
    /**
    *cambia e mapa a un usuario
    *@param usuarioname nombre del usuario que cambia de mapa
    *@param mapa mapa al que se cambiará
    */
    public void cambiarMapa(String usuarioname, int mapa) {
        int indice=getIndiceJugador(usuarioname);
        this.jugadores.get(indice).setMapa(mapa);
     
    }
}
