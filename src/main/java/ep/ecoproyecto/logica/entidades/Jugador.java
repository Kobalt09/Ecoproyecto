package ep.ecoproyecto.logica.entidades;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Herramientas;
import ep.ecoproyecto.logica.KeyHandler;
import ep.ecoproyecto.logica.net.packets.Paquete02Movimiento;
import ep.ecoproyecto.logica.objetos.ObjetoEquipo;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;
import ep.ecoproyecto.logica.objetos.Objetosclase;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import ep.ecoproyecto.logica.tipografia.Fuentes;
import java.awt.Color;
import java.awt.RenderingHints;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Clase del personaje jugable, para movimiento e interacciones
 * @author C-A-F
 */

public class Jugador extends Entidad{

    KeyHandler keyH;
    public final int pantallaX;
    public final int pantallaY;
    protected String usuario;
    protected int mapa;
    
    public String sombreros[]= new String[10];
    // 1 llaves
    // 2 basura
    // 7 semillas
    // 9 dinero                                                                     
    // 8 red
    public int cantInventario[]= new int[5];
    public boolean interactuar;
    
    public int estado;
    public final int estadojuego=1;
    public final int estadotienda=2;
    
    //contador de culdaun en teclas
    public int contador;
    public boolean tecla=true;
    
    public BufferedImage sombrero;
    public String sombreroactual;

    /**
     * Constructor de la clase para inicializar antes de permitir movimiento
     * @param pJuego panel de juego del jugador
     */
    public Jugador(PanelJuego pJuego) {
        super(pJuego);
        this.keyH=new KeyHandler();
        this.usuario="";
        
        pantallaX=pJuego.screenWidth/2-(pJuego.tamanioCasilla/2);
        pantallaY=pJuego.screenHeight/2-(pJuego.tamanioCasilla/2);
        
        hitBox=new Rectangle();
        //donde empiza la hitbox en relacion a la esquina superior
        hitBox.x=16;
        hitBox.y=16;
        //tamanio de la hitbox
        hitBox.height=52;
        hitBox.width=48;
        
        //area de colision
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        
        estado=estadojuego;
        valoresporDefecto();
        getPlayerImage();
    }
    /**
     * Constructoe con keyH y usuario
     * @param pJuego panel del jugador 
     * @param keyH manejador de teclas
     * @param usuario nombre a asignar para el usuario
     */
    public Jugador(PanelJuego pJuego, KeyHandler keyH, String usuario){
        
        super(pJuego);
        
        this.keyH=keyH;
        this.usuario=usuario;
        this.mapa=0;
        pantallaX=pJuego.screenWidth/2-(pJuego.tamanioCasilla/2);
        pantallaY=pJuego.screenHeight/2-(pJuego.tamanioCasilla/2);
        
        hitBox=new Rectangle();
        //donde empiza la hitbox en relacion a la esquina superior
        hitBox.x=8;
        hitBox.y=4;
        //tamanio de la hitbox
        hitBox.height=56;
        hitBox.width=48;
        
        //area de colision
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        sombreroactual="ninguno";
        
        
        valoresporDefecto();
        getPlayerImage();
        getsombre();
    }

    public int getMapa() {
        return mapa;
    }

    public void setMapa(int mapa) {
        this.mapa = mapa;
    }
    /**
     * Da valores por defecto a los jugadores para iniciar
     */
    private void valoresporDefecto(){
        //posicion del jugador en el arreglo +1,
        xMapa=15*pJuego.tamanioCasilla+pJuego.tamanioCasilla;
        yMapa=10*pJuego.tamanioCasilla+pJuego.tamanioCasilla;
        
        estado=estadojuego;
        
        vel=6;
        veloriginal=vel;
        direction ="down"; 
        
        if(pJuego.socketserver!=null){
        Paquete02Movimiento packet=new Paquete02Movimiento(usuario, this.xMapa, this.yMapa,this.direction);
        packet.writeData(PanelJuego.juego.socketCliente);
        }
    }

    public String getDirection() {
        return direction;
    }

    public String getUsername() {
        return usuario;
    }
    /**
     * asigna los sprites al jugador
     */
    private void getPlayerImage(){
        
        up1=configuracion("/player/jg_arr_01");
        up2=configuracion("/player/jg_arr_02");
        down1=configuracion("/player/jg_abj_01");
        down2=configuracion("/player/jg_abj_02");
        left1=configuracion("/player/jg_izq_01");
        left2=configuracion("/player/jg_izq_02");
        right1=configuracion("/player/jg_der_01");
        right2=configuracion("/player/jg_der_02");
        
    }
    /**
     * verifica si el jugador cuenta con un sombrero
     */
    private void getsombre(){
        if(sombreroactual!="ninguno"){
            sombrero=configuracionSombrero("/gorros/"+sombreroactual);
        }
    }
    /**
     * Devuele la imagen de un sombrero
     * @param nombre nombre del sombrero
     * @return imagen del sombrero actual
     */
    public BufferedImage configuracionSombrero(String nombre){
        
        Herramientas herramienta = new Herramientas();
        BufferedImage imagen= null;
        
        try{
            imagen=ImageIO.read(getClass().getResourceAsStream(nombre+".png"));
            imagen= herramienta.imagenEscalada(imagen, pJuego.tamanioCasilla, pJuego.tamanioCasilla);
            
        }catch(IOException e){
        }
        
        return imagen;
    }
    
    /**
     * actualiza la posicion y estado del jugador
     */
    public void update(){
        if(keyH != null){        
            interactuar=false;
            if (keyH.upPressed==true||keyH.leftPressed==true||keyH.downPressed==true||keyH.rightPressed==true||keyH.ePressed==true){
                //actualizamos la posicion del jugador sumando o restando su velocidad
                
                switch(estado){
                    case 1 -> estadoJuego();
                    case 2 -> estadoTienda();
                }
                
                if(tecla==false){
                    aumentarcontador();
                }
            }    
        } 
    }
    
    /**
     * aumenta un contador para el tiempo de presion de las teclas
     */
    public void aumentarcontador(){
        contador++;
        if(contador>5){
            tecla=true;
            contador=0;
        }
    }
    /**
     * Mueve al jugador y comprueba los eventos relacionados a el
     */
    
    public void estadoJuego(){
        pJuego.hud.guardartienda();
        if(tecla==true){
                if(keyH.upPressed==true){
                    direction="up";
                }else if(keyH.leftPressed==true){
                    direction="left";
                }else if(keyH.downPressed==true){
                    direction="down";
                }else if(keyH.rightPressed==true){
                    direction="right";
                }else if(keyH.ePressed==true){
                    tecla=false;
                    interactuar=true;
                }
        }
        
        //colision Casillas
        colision=false;
        pJuego.colisiones.revisarColision(this);
    
                //colision entidades
                intereaccionNCP(pJuego.colisiones.chequeoEntidades(this, pJuego.entidades));
                
                //colision objetos
                recogerObjetos(pJuego.colisiones.chequeoObjetos(this, true));

                if(colision==false && interactuar==false){
                    switch (direction) {
                            case "up" -> {yMapa-=vel;
                                hitBox.x=16;
                                hitBox.width=30;
                            }
                            case "left" -> {
                                xMapa-=vel;
                                hitBox.x=14;
                                hitBox.width=36;
                            }
                                
                            case "down" ->{ 
                                yMapa+=vel;
                                hitBox.x=14;                                                            
                                hitBox.width=36;}
                            
                            case "right" -> {
                                xMapa+=vel;
                                hitBox.x=16;                              
                                hitBox.width=30;
                            }
                    }
                }
                
                Paquete02Movimiento packet=new Paquete02Movimiento(usuario, this.xMapa, this.yMapa,this.direction);
                packet.writeData(PanelJuego.juego.socketCliente);
                
                spriteCounter++;
                if (spriteCounter>10){
                    if (spriteNum == 2 ){
                        spriteNum=1;
                    }else{
                        if (spriteNum == 1)
                        spriteNum=2;
                    }
                    spriteCounter = 0;
                }
    }

    /**
     * mueve el hud de la tienda
     */
    public void estadoTienda(){
        if(tecla==true){
            if(keyH.upPressed==true){

            }else if(keyH.downPressed==true){

            }
            if(keyH.rightPressed==true){
                pJuego.efectos(7);
                pJuego.hud.opcion++;
                tecla=false;
                if( pJuego.hud.opcion>4){
                    pJuego.hud.opcion=0;
                }
            }else if(keyH.leftPressed==true){
                pJuego.efectos(7);
                pJuego.hud.opcion--;
                tecla=false;
                if( pJuego.hud.opcion<0){
                    pJuego.hud.opcion=4;
                }
            }else if(keyH.ePressed==true){
                pJuego.efectos(7);
                tecla=false;
                if(pJuego.hud.opcion==0){
                    estado=estadojuego;
                }else{
                    int cont=0;
                    
                    for(Objetosclase obj:pJuego.entidades[2][1].inventario){
                        if(obj!=null && cont==(pJuego.hud.opcion-1) ){
                            if(!(obj.getPrecio()>this.cantInventario[4])&&(pJuego.entidades[2][1].inventario[cont]!=null)){
                                
                                this.cantInventario[4]=this.cantInventario[4]-obj.getPrecio();
                                switch(obj.nombre){
                                    case "gGorro" -> {
                                        this.sombreros[0]= "gGorro";
                                        pJuego.entidades[2][1].inventario[cont]=null;
                                        this.sombreroactual=this.sombreros[0];
                                        getsombre();
                                    }
                                    case "gCopa" -> {
                                        this.sombreros[1]="gCopa";
                                        pJuego.entidades[2][1].inventario[cont]=null;
                                        this.sombreroactual=this.sombreros[1];
                                        getsombre();
                                    }
                                    case "gPlaya" -> {
                                        this.sombreros[2]="gPlaya";
                                        pJuego.entidades[2][1].inventario[cont]=null;
                                        this.sombreroactual=this.sombreros[2];
                                        getsombre();
                                    }
                                    case "Botas" -> {
                                        if(this.inventario[0]==null){
                                            pJuego.efectos(4);
                                            pJuego.hud.mostrarmensaje("conseguiste "+obj.nombre);
                                            vel=vel+2;
                                            this.inventario[0]=new ObjetoEquipo("Botas", 0, 0, pJuego);
                                            pJuego.entidades[2][1].inventario[cont]=null;
                                        }
                                    }
                                }
                            }else{
                                
                                if(pJuego.entidades[2][1].inventario[cont]==null){
                                    pJuego.hud.mostrarmensaje("Ya compraste esto");
                                }else{
                                    pJuego.hud.mostrarmensaje("Es muy caro");
                                }
                            }    
                        }
                        cont++;
                    }
                }    
            }
        }    
        pJuego.hud.mostrartienda();
    }
    /**
     * Comprueba la recoleccion de objetos
     * @param id id del objeto a recoger
     */        
    public void recogerObjetos(int id){
        if(id!=999){
            //usa el nombre del objeto para saber con cual objeto colisiona 
            String objnombre=pJuego.obj[pJuego.mapaActual][id].nombre;
            
            //switch para el nombre
            //nota se puede usar un getclass para saber el tipo o usar 
            switch(objnombre){
              
                case "Botas" -> {
                    if(this.inventario[0]==null){
                        pJuego.efectos(4);
                        pJuego.hud.mostrarmensaje("conseguiste "+objnombre);
                        vel=vel+2;
                        this.inventario[0]=pJuego.obj[pJuego.mapaActual][id];
                        pJuego.obj[pJuego.mapaActual][id]=null;
                    }
                }
                case "cofre" -> {
                    pJuego.hud.victoriaMensaje=true;
                    pJuego.obj[pJuego.mapaActual][id]=null;
                }
                case "coin" -> {
                    this.cantInventario[4]++;
                    pJuego.efectos(2);
                    pJuego.hud.victoriaMensaje=true;
                    pJuego.obj[pJuego.mapaActual][id]=null;
                }
                case "semilla" -> {
                    this.cantInventario[1]++;
                    pJuego.efectos(6);
                    this.inventario[1]=pJuego.obj[pJuego.mapaActual][id];
                    pJuego.hud.victoriaMensaje=true;
                    pJuego.obj[pJuego.mapaActual][id]=null;
                }
                case "basura" -> {
                    this.cantInventario[2]++;
                    pJuego.efectos(6);
                    this.inventario[2]=pJuego.obj[pJuego.mapaActual][id];
                    pJuego.hud.victoriaMensaje=true;
                    pJuego.obj[pJuego.mapaActual][id]=null;
                }
            }
        }
    }
    /**
     * Elimina el objeto recogido
     * @param i indice del objeto 
     */
    public void recogerObjeto(int i){
        if(i!=999){
            pJuego.obj[pJuego.mapaActual][i]=null;
        }
    }
          
    /**
     * comprueba la interaccin con un npc
     * @param id id del npc
     */
    public void intereaccionNCP(int id) {
        if(id!=999){
            if (interactuar==true){
                pJuego.efectos(7);
                if(pJuego.entidades[pJuego.mapaActual][id].movimiento==true){
                    if("right".equals(direction)){
                        pJuego.entidades[pJuego.mapaActual][id].direction="left";
                    }
                    if("left".equals(direction)){
                        pJuego.entidades[pJuego.mapaActual][id].direction="right";
                    }
                    if("up".equals(direction)){
                        pJuego.entidades[pJuego.mapaActual][id].direction="down";
                    }
                    if("down".equals(direction)){
                        pJuego.entidades[pJuego.mapaActual][id].direction="up";
                    }
                }
                switch (pJuego.entidades[pJuego.mapaActual][id]) {
                    case Tienda aux -> {
                        pJuego.hud.mostrarmensaje("Tienda");
                        estado=estadotienda;
                    }
                    case PuertaInteractuable  aux -> {
                            if(aux.Ztp==2){
                                pJuego.controlEventos.musicaTienda();
                            }else if(pJuego.mapaActual==2){
                                pJuego.controlEventos.musicaTienda();
                            }
                            pJuego.controlEventos.tpInteractuar(aux.Xtp,aux.Ytp, aux.Ztp);
                        }
                    case Papelera  aux -> {
                            if(this.cantInventario[2]!=0){
                                pJuego.hud.mostrarmensaje("depositaste: "+this.cantInventario[2]+" bolsas de basura");
                                this.inventario[2]=null;
                                this.cantInventario[2]=0;
                            }else{
                                pJuego.hud.mostrarmensaje(pJuego.entidades[pJuego.mapaActual][id].mensaje);
                            }
                        }
                    case Agujero  aux -> {
                            if(aux.estado=="Agujerovacio"){
                                if(this.cantInventario[1]!=0){
                                    this.cantInventario[1]--;
                                    if(this.cantInventario[1]==0){
                                        this.inventario[1]=null;
                                    }
                                    pJuego.hud.mostrarmensaje("Plantaste un Arbol");
                                    aux.estado="Agujerolleno";
                                    aux.getImage();
                                }else{
                                    pJuego.hud.mostrarmensaje(aux.mensaje);
                                }
                            }else{
                                pJuego.hud.mostrarmensaje("Espero que este Arbol cresca ");
                            }
                        }
                    case Tonina  aux -> {
                            if(pJuego.minijuego[3][0].terminado==true){
                                if(aux.misioncumplida==false){
                                    pJuego.hud.mostrarmensaje("Muchas gracias, ten estas U.C.");
                                    aux.misioncumplida=true;
                                    this.cantInventario[4]=this.cantInventario[4]+10;
                                    pJuego.hud.ocultarODS();
                                }else{
                                    pJuego.hud.mostrarmensaje("Muchas gracias de nuevo");
                                }   
                            }else{
                                if(pJuego.minijuego[3][0].empezado==false){
                                    pJuego.hud.mostrarmensaje("Hola, me ayudas a limpiar el agua? Usa esta red");
                                    this.inventario[3]= new ObjetoRecogible("Red", 1, 1, pJuego);
                                    pJuego.mini.activarMini(3, 0);
                                    pJuego.hud.mostrarODS("ODS14");
                                }else if(pJuego.minijuego[3][0].empezado==true){
                                    pJuego.minijuego[3][0].interaccion();
                                }
                            }
                        }
                    case Turpial  aux -> {
                            if(pJuego.minijuego[5][0].terminado==true){
                                if(aux.misioncumplida==false){
                                    pJuego.hud.mostrarmensaje("Gracias, toma estas U.C.");
                                    aux.misioncumplida=true;
                                    this.cantInventario[4]=this.cantInventario[4]+10;
                                     pJuego.hud.ocultarODS();
                                }else{
                                    pJuego.hud.mostrarmensaje("Gracias de nuevo");
                                }
                            }else{
                                if(pJuego.minijuego[5][0].empezado==false){
                                    pJuego.hud.mostrarmensaje("Hola, Podrias sembrar algunos arboles?");
                                    pJuego.mini.activarMini(5, 0);
                                    pJuego.hud.mostrarODS("ODS15");
                                }else if(pJuego.minijuego[5][0].empezado==true){
                                    pJuego.minijuego[5][0].interaccion();
                                }
                            }
                        }
                    case Zamuro  aux -> {
                            if(pJuego.minijuego[4][0].terminado==true){
                                if(aux.misioncumplida==false){
                                    pJuego.hud.mostrarmensaje("Toma estas U.C.");
                                    aux.misioncumplida=true;
                                    this.cantInventario[4]=this.cantInventario[4]+10;
                                     pJuego.hud.ocultarODS();
                                }else{
                                    pJuego.hud.mostrarmensaje("Ahora esa basura es toda mia");
                                }
                            }else{
                                if(pJuego.minijuego[4][0].empezado==false){
                                    pJuego.hud.mostrarmensaje("Oye, recoge esa basura por mi te dare U.C.");
                                    pJuego.mini.activarMini(4, 0);
                                    pJuego.hud.mostrarODS("ODS13");
                                }else if(pJuego.minijuego[4][0].empezado==true){
                                    pJuego.minijuego[4][0].interaccion();
                                }
                            }
                        }
                    case Aguaconbasura aux ->{
                        if("Aguasucia".equals(aux.estado) && this.inventario[3]!=null){
                            pJuego.hud.mostrarmensaje("Recogiste la basura");
                            aux.estado="agua";
                            aux.getImage();
                        }else{
                                pJuego.hud.mostrarmensaje(aux.mensaje);
                        }
                    }
                    case Chiguire aux ->{
                        if((pJuego.minijuego[4][0].terminado==true&&pJuego.minijuego[3][0].terminado==true&&pJuego.minijuego[5][0].terminado==true)){
                            pJuego.efectos(8);            
                            pJuego.hud.mostrarmensaje("FELICIDADES AYUDASTE A TODA LA ISLA");
                        }else{
                            pJuego.hud.mostrarmensaje(pJuego.entidades[pJuego.mapaActual][id].mensaje);
                        }
                        tecla=false;
                    }
                    default -> {
                        pJuego.hud.mostrarmensaje(pJuego.entidades[pJuego.mapaActual][id].mensaje);
                    }
                }
            }
        }
    }
    /**
     * dibuja el personaje 
     * @param g2 Elemento basico para dibujar en la pantalla
     */
    public void dibujado(Graphics2D g2){

        BufferedImage image = null;  
        
        switch(direction){
            case "up" -> {     
                if (spriteNum==1){
                    image=up1;
                }
                  if (spriteNum==2){
                    image=up2;
                }
            break;
            }
            case "down" -> {
                if (spriteNum==1)
                    image=down1;
                if (spriteNum==2)
                    image=down2;                              
                break;
            }
            case "left" ->  {     
                if (spriteNum==1)
                    image=left1;
                if (spriteNum==2)
                    image=left2;
                break;
            }
            case "right" -> {      
                if (spriteNum==1)   
                    image=right1;
                if (spriteNum==2)   
                    image=right2;
                break;
            }
            
        }
       
        if (pJuego.jugador.getMapa()==this.mapa) {   
            if(pJuego.jugador.equals(this)) {
                g2.drawImage(image,pantallaX,pantallaY,null);  
                if(sombreroactual!="ninguno"){
                    g2.drawImage(sombrero,pantallaX,pantallaY,null);
                }
                
                if (usuario != null){
                    Fuentes tipoFuente=new Fuentes();
                    g2.setFont((tipoFuente.fuente(tipoFuente.upheaval,0,20)));
                    int textX = pantallaX + (image.getWidth() - g2.getFontMetrics().stringWidth(usuario))/2 ;
                    int textY = pantallaY - 5;
                    
                    //ANTI-LIASING
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    
                    //Bordes Negros//
                    g2.setColor(Color.BLACK);
                    for (int i = -2; i <= 2; i++) {
                        for (int j = -2; j <= 2; j++) {
                            if (i != 0 || j != 0) {
                                g2.drawString(usuario, textX + i, textY + j);
                            }
                        }
                    }

                    //Letras Blancas//
                    g2.setColor(Color.WHITE);
                    g2.drawString(usuario, textX, textY);




                }
            }else{
                int dibX=pJuego.jugador.pantallaX+this.xMapa-pJuego.jugador.xMapa;
                int dibY=pJuego.jugador.pantallaY+this.yMapa-pJuego.jugador.yMapa;
               // g2.drawImage(image,xMapa,yMapa,null);
                g2.drawImage(image,dibX,dibY,null);


                if (usuario != null){
                    Fuentes tipoFuente=new Fuentes();
                    g2.setFont((tipoFuente.fuente(tipoFuente.upheaval,0,20)));
                    int textX = dibX + (image.getWidth() - g2.getFontMetrics().stringWidth(usuario))/2 ;
                    int textY = dibY - 5;
                    
                    //ANTI-LIASING
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                        
                    //Bordes Negros//
                    g2.setColor(Color.BLACK);
                    for (int i = -2; i <= 2; i++) {
                        for (int j = -2; j <= 2; j++) {
                            if (i != 0 || j != 0) {
                                g2.drawString(usuario, textX + i, textY + j);
                            }
                        }
                    }
                    
                    //Letras Blancas//
                    g2.setColor(Color.WHITE);
                    g2.drawString(usuario, textX, textY);
                }

            }
        }

    }
}
