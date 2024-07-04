package ep.ecoproyecto.gui;
import ep.ecoproyecto.logica.ControladorEventos;
import ep.ecoproyecto.logica.InterfazJugador;
import ep.ecoproyecto.logica.EmisorObjetos;
import ep.ecoproyecto.logica.EmisorNPC;
import ep.ecoproyecto.logica.Sonido;
import ep.ecoproyecto.logica.Colisionador;
import ep.ecoproyecto.logica.ControladorMinijuegos;
import ep.ecoproyecto.logica.KeyHandler;
import ep.ecoproyecto.logica.WindowHandler;
import ep.ecoproyecto.logica.entidades.Entidad;
import ep.ecoproyecto.logica.casillas.ManejadorCasillas;
import ep.ecoproyecto.logica.entidades.Jugador;
import ep.ecoproyecto.logica.entidades.JugadorMP;
import ep.ecoproyecto.logica.net.*;
import ep.ecoproyecto.logica.net.packets.Packet00Login;
import ep.ecoproyecto.logica.net.packets.Packet01Disconnect;
import ep.ecoproyecto.logica.objetos.Objetosclase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import minijuegos.Minijuego;

/**
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
    public final int screenWidth=tamanioCasilla *maxColumnasPantalla; // resoluciom en x 1024
    public final int screenHeight=tamanioCasilla *maxFilasPantalla; //  resoluciom en x  640
    
    //configuracion de mapa
    public final int Maximocolumnas=40;
    public final int Maximofilas=40;
    public final int Maximomundos=6;
    public int mapaActual=0;
    public static PanelJuego juego;
     
    //indica la cancion que esta sonando actualmente
    public int musica=0;
    
    //Fps permitidos
    int fps=60;
    


    public ManejadorCasillas manCas=new ManejadorCasillas(this); // maneja los mapas 
    public KeyHandler keyH= new KeyHandler();                    // detecta el teclado
    public Sonido controlmusica = new Sonido();
    public Sonido efectossonido = new Sonido();

    public WindowHandler winH;
    public JFrame frame;
    public boolean pantallaCompleta = false;

    public Colisionador colisiones =new Colisionador(this);
    public EmisorObjetos objeto= new EmisorObjetos(this);
    public EmisorNPC npcs= new EmisorNPC(this);
    public ControladorMinijuegos mini= new ControladorMinijuegos(this);
    public InterfazJugador hud = new InterfazJugador(this);
    public ControladorEventos ControlEventos= new ControladorEventos(this);
    Thread gameThread;

    //manejador de efectos de sonido
    

    
    //Jugador, objetos y NPC
    public Jugador jugador;
    public LinkedList<JugadorMP> jugadores = new LinkedList<>(); 
    public Objetosclase obj[][]= new Objetosclase[Maximomundos][20];
    public Entidad NPC[][]= new Entidad[Maximomundos][20];
    public Minijuego Minijuego[][]=new Minijuego[Maximomundos][10];
    //ArrayList<Entidad> Entidadlista= new ArrayList<>();


    
    //-----ESTADOS DE JUEGO------------//
    public boolean pause;
    
    //----ONLINE-----//
    public Cliente socketcliente;
    public Server socketserver;
    

    
    //Inicializar el panel
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
    public void configuraciondejuego(){
        juego=this;
        
        keyH.establecerPanel(this);

        objeto.establecerObj();     //envia los objetos definidos a un arreglo
        npcs.establecernpcs();      //igual pero con npcs
        mini.establecerminijuegos();
        this.reproducirmusica(musica);
        
        this.jugador=new Jugador(this); //inicializa un jugador en blanco para las funciones que lo necesitan antes de darle valores
        //estado de juego
        
        pause=false;
    }
    
    public void inicioJugador (){
        
        jugador = new JugadorMP(null,-1,this,keyH,JOptionPane.showInputDialog(this, "Por favor, introduzca su nombre de usuario:"));
        jugadores.add((JugadorMP)jugador);
        
        Packet00Login loginpacket = new Packet00Login(jugador.getUsername(),jugador.xMapa,jugador.yMapa,jugador.direction,jugador.getMapa());
        if (socketserver!=null){
            socketserver.addConnection((JugadorMP)jugador, loginpacket);
        }
        loginpacket.writeData(socketcliente);
        
    }
        
    public void removePlayerMP(String username) {
        for (JugadorMP j:jugadores){
            if (j.getUsername().equals(username)){
                jugadores.remove(j);
            }
        }
    }
    
    public void startGameThread(){
        
        //Se puede adaptar como una funcion dentro del MENU//
        if(JOptionPane.showConfirmDialog(this, "Quieres iniciar el server?") == JOptionPane.YES_OPTION){
            socketserver = new Server(this);
            socketserver.start();
        }
        
        //-------------------------------------------------//
        
        socketcliente = new Cliente("localhost",this);
        socketcliente.start();
        
        inicioJugador();
        
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
            actualizar();
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
    
    public void regresarAlMenuIni(){
        Packet01Disconnect packet = new Packet01Disconnect(this.jugador.getUsername());
        packet.writeData(this.socketcliente);
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
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g; // estas dos clases son similares pero graphis2D tiene mas funciones para dibujar 

        //casillas
        
        manCas.dibujado(g2);
        
        //objetos
        objeto.dibujado(g2);
        
        //npc
        npcs.dibujado(g2);
        

        //jugadores
        for (JugadorMP jug : jugadores) {
            jug.dibujado(g2);
            hud.dibujado(g2,jug);
        }

        g2.dispose();
    }
    
    public void reproducirmusica(int i){
        controlmusica.reproducirmusica(i);
    }
    
    public int reproducirmusicatienda(int i){
        return controlmusica.musicatienda(i);
    }
    
    public void efectos(int i){
        efectossonido.reproducirefecto(i);
    }
    
    private int getIndiceJugador(String user){
        int indice=0;
        for (Jugador jug:jugadores){
            if(jug.getUsername() == null ? (user) == null : jug.getUsername().equals(user)){
                break;
            }indice++;
        }
        return indice;
    }
    
    public void moverJugadores(String user,int x, int y,String dir){
        int indice=getIndiceJugador(user);
        
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

    public void cambiarMapa(String username, int mapa) {
        int indice=getIndiceJugador(username);
        this.jugadores.get(indice).setMapa(mapa);
        
        
    }
}
