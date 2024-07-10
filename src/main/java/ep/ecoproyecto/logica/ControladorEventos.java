package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.net.packets.Paquete03Mapa;
/**
 *
 * @author C-A-F
 */

public class ControladorEventos {
    PanelJuego gp;
    CasillaEventos rectanguloEvento[][][];
    CasillaEventos eventoprevio;
    
    public ControladorEventos(PanelJuego gp){
        this.gp=gp;
        
        rectanguloEvento = new CasillaEventos[gp.maximocolumnas][gp.maximofilas][gp.maximomundos];
        int columna = 0, fila = 0,  mapa=0;
        
        while(columna < gp.maximocolumnas && fila < gp.maximofilas && mapa < gp.maximomundos){
            
            rectanguloEvento[columna][fila][mapa] = new CasillaEventos();
            rectanguloEvento[columna][fila][mapa].x=0;
            rectanguloEvento[columna][fila][mapa].y=0;
            
            rectanguloEvento[columna][fila][mapa].width=gp.tamanioCasilla;
            rectanguloEvento[columna][fila][mapa].height=gp.tamanioCasilla;
            rectanguloEvento[columna][fila][mapa].posicionDefectoX=rectanguloEvento[columna][fila][mapa].x;
            rectanguloEvento[columna][fila][mapa].posicionDefectoY=rectanguloEvento[columna][fila][mapa].y;

            
            columna++;
            if (columna == gp.maximocolumnas){
                columna = 0;
                //System.out.println(fila);
                fila++;
            }
            if (columna == gp.maximocolumnas - 1 &&fila == gp.maximofilas-1){
                columna = 0;
                fila = 0;
                mapa++;
            }
        }
    }
    
    public void chequeoEvento(){
        //NOTA LOS EVENTOS SOLO SE ACTIVAN 1 VEZ LUEGO ES NECESARIO ACTIVAR OTRO EVENTO
        
        //evento sonido
        //if(colision(27,16,0,"right")==true){ eventSonido(2); }
        //if(colision(15,15,0,"any")==true){ musicatienda(); }
        
        //Se le entrega a tp (posicionX,PosicionY, mapa)para el jugador y (X,Y,mapa) de la casilla que se activo
       // if(colision(9,6,0,"any")==true){ tpcasilla(11, 15, 1); } 
        //if(colision(10,10,0,"any")==true){ mensaje();}
        /*
        if (gp.mapaActual==2){
            if(colision(5,5,2,"any")==true)
            {mensaje();}
        }*/
    }
    
    public boolean colision(int columna, int fila,int mapa ,String regdireccion){
        boolean hit= false;
        
        gp.jugador.hitBox.x=gp.jugador.xMapa+gp.jugador.hitBox.width;
        gp.jugador.hitBox.y=gp.jugador.yMapa+gp.jugador.hitBox.height;
        
        rectanguloEvento[columna][fila][mapa].x=columna*gp.tamanioCasilla+rectanguloEvento[columna][fila][mapa].x;
        rectanguloEvento[columna][fila][mapa].y=fila*gp.tamanioCasilla+rectanguloEvento[columna][fila][mapa].y;
        
        if((gp.jugador.hitBox.intersects(rectanguloEvento[columna][fila][mapa]))&&(rectanguloEvento[columna][fila][mapa].Activado==false)&&(eventoprevio!=rectanguloEvento[columna][fila][mapa])&&(gp.mapaActual==mapa)){
            if((gp.jugador.direction.equals(regdireccion)||regdireccion.contentEquals("any"))){
                hit=true;
                eventoprevio=rectanguloEvento[columna][fila][mapa];
            }
        }
        
        gp.jugador.hitBox.x=gp.jugador.areadefectoX;
        gp.jugador.hitBox.y=gp.jugador.areadefectoY;
        
        rectanguloEvento[columna][fila][mapa].x=rectanguloEvento[columna][fila][mapa].posicionDefectoX;
        rectanguloEvento[columna][fila][mapa].y=rectanguloEvento[columna][fila][mapa].posicionDefectoY;
        
        return hit;
    }
    
    //efectos de sonido
    public void eventSonido(int i){
        gp.efectos(2);
    }
    
    //cambiar la musica
    /*
    public void eventMusica(int i){
        gp.efectos(1);
    }
    */
    
    public void musicatienda(){
        gp.musica= gp.reproducirmusicatienda(gp.musica);
    }
    
    public void mensaje(){
        gp.hud.mostrarmensaje(gp.NPC[0][0].mensaje);
    }
    
    //desplazar al jugador
    public void tpcasilla(int x,int y, int mapa){
        gp.jugador.xMapa=(x*gp.tamanioCasilla)+gp.tamanioCasilla;
        gp.jugador.yMapa=(y*gp.tamanioCasilla)+gp.tamanioCasilla;
        gp.mapaActual=mapa;
        gp.mini.activarmini(1, 1);

        gp.jugador.setMapa(mapa);
        gp.manCas.actualizar(gp.jugador, gp.screenWidth, gp.screenHeight);
        
        Paquete03Mapa packet =new Paquete03Mapa(mapa, gp.jugador.getUsername());
        packet.writeData(gp.socketcliente);
        //rectanguloEvento[col][fil][mapacasilla].Activado=true;
    }
    
    public void tpinteractuar(int x,int y, int mapa){
        
        gp.jugador.xMapa=(x*gp.tamanioCasilla)+gp.tamanioCasilla;
        gp.jugador.yMapa=(y*gp.tamanioCasilla)+gp.tamanioCasilla;
        gp.mapaActual=mapa;       
        gp.jugador.setMapa(mapa);
        gp.manCas.actualizar(gp.jugador, gp.screenWidth, gp.screenHeight);
        
        
        Paquete03Mapa packet =new Paquete03Mapa(mapa, gp.jugador.getUsername());
        packet.writeData(gp.socketcliente);
        
    }
    /*
    public void interacturarEvento(int i){
        gp.Minijuego[gp.mapaActual][i].interacion();
    }
    */
    
}