package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Jugador;
import ep.ecoproyecto.logica.tipografia.Fuentes;
import ep.ecoproyecto.logica.objetos.Objetosclase;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Maeja los elementos de informacion para el jugador
 * @author C-A-F
 */

public class InterfazJugador{
    public PanelJuego pJuego;
    public Fuentes fuente= new Fuentes();
    public Graphics2D g2;
    public BufferedImage imagen;
    public boolean mensajeOn=false;
    public boolean tiendaOn=false;
    public boolean victoriaMensaje=false;
    public String mensaje="";
    int contMensajes=0;
    public int opcion=0;
    public int opcionMenu=0;
    public int subState = 0;
    
    public InterfazJugador(PanelJuego pJuego) {
        this.pJuego = pJuego;
        
    }
    
    public void mostrarmensaje(String texto) {
        mensaje=texto;
        mensajeOn= true;
    }
    
    public void mostrartienda() {
        if(tiendaOn==false){
            tiendaOn=true;
        }
    }
    
    public void guardartienda() {
        if(tiendaOn==true){
            tiendaOn=false;
        }
    }

    
    public void dibujado(Graphics2D g2,Jugador jugador){
        this.g2 = g2;
        
        dibujadoLetras("Posicion:  X:"+(pJuego.jugador.xMapa/64)+"  Y: "+(pJuego.jugador.yMapa/64), pJuego.screenWidth-800, pJuego.screenHeight-50, 2);
        
        //g2.setFont(fuente);
        dibujadoLetras("Unidades de credito: "+(pJuego.jugador.cantInventario[4]), 50, 50, 2);
        
        if (pJuego.pause){
            dibujadoFondo();
            dibujadoMenuPausa();
        }
        
        //mostrar mensajes de NPC
        if(mensajeOn==true){
            int tarjeta=mensaje.length();
            g2.setColor(Color.blue);
            g2.fillRect(pJuego.screenWidth/3-8, pJuego.tamanioCasilla/6-5, tarjeta*15+8, pJuego.tamanioCasilla-6);
            
            g2.setColor(Color.black);
            g2.fillRect(pJuego.screenWidth/3-5, pJuego.tamanioCasilla/6, tarjeta*15, pJuego.tamanioCasilla-16);
            g2.setColor(Color.white);
            //g2.setFont(g2.getFont().deriveFont(30F));
            
            g2.drawString(mensaje, pJuego.screenWidth/3, pJuego.tamanioCasilla/2+5);   
            contMensajes++;
            if(contMensajes>60){
                contMensajes=0;
                mensajeOn=false;
            }
        }

        //dibujado de inventario
        if(mensajeOn==false){
            if(pJuego.jugador==jugador)
                dibujadoinventario(g2,jugador); 
            
            if(tiendaOn==true ){
                tienda(g2);
            }
        }
    }
    
    public void dibujadoFondo(){
        Color colorOscuro = new Color(0,0,0,150);
        g2.setColor(colorOscuro);
        g2.fillRect(0, 0, pJuego.screenWidth, pJuego.screenHeight);
    }
    
    public void dibujadoMenuPausa(){
        
        int frameX = getXcentrado("Opciones:") - pJuego.tamanioCasilla;
        int frameY = pJuego.tamanioCasilla;
    
        switch(subState){
            case 0-> opciones_Top(frameX,frameY);
            case 1-> pantallaCompletaNotif(frameX,frameY);
            case 2-> controlesMenu(frameX,frameY);
            case 3-> cerrarJuegoConfirm(frameX,frameY);
        }
        
        pJuego.keyH.enterPressed = false;
    }
    
    public void opciones_Top(int frameX, int frameY){
        int textoX, textoY;
        
        //TITULO//
        textoX = frameX;
        textoY = frameY + pJuego.tamanioCasilla;
        dibujadoLetras("Opciones:",textoX,textoY,1);
        
        //PANTALLA COMPLETA//
        textoX = textoX - pJuego.tamanioCasilla*2;
        textoY += pJuego.tamanioCasilla;
        dibujadoLetras("Pantalla Completa",textoX,textoY,2);
        if (opcionMenu == 0){
            dibujadoLetras("+",textoX-25,textoY,2);
            if (pJuego.keyH.enterPressed){
                pJuego.pantallaCompleta = !pJuego.pantallaCompleta;
                subState = 1;
            }
        }
        
        //MUSICA//
        textoY += pJuego.tamanioCasilla;
        dibujadoLetras("Musica",textoX,textoY,2);
        if (opcionMenu == 1)
            dibujadoLetras("+",textoX-25,textoY,2);
        
        //EFECTOS DE SONIDO//
        textoY += pJuego.tamanioCasilla;
        dibujadoLetras("Efectos de Sonido",textoX,textoY,2);
        if (opcionMenu == 2)
            dibujadoLetras("+",textoX-25,textoY,2);
        
        //CONTROLES//
        textoY += pJuego.tamanioCasilla;
        dibujadoLetras("Controles",textoX,textoY,2);
        if (opcionMenu == 3){
            dibujadoLetras("+",textoX-25,textoY,2);
            if (pJuego.keyH.enterPressed){
                subState = 2;
                opcionMenu = 0;
            }
        }
    
        //CERRAR JUEGO//
        textoX = getXcentrado("Cerrar Juego");
        textoY += pJuego.tamanioCasilla;
        dibujadoLetras("Cerrar Juego",textoX,textoY,2);
        if (opcionMenu == 4){
            dibujadoLetras("+",textoX-25,textoY,2);
            if (pJuego.keyH.enterPressed){
                subState = 3;
                opcionMenu = 0;
            }
        }
    
        //CHECK BOX PANTALLA COMPLETA//
        textoX = getXcentrado("Opciones:") + pJuego.tamanioCasilla*4;
        textoY = frameY + pJuego.tamanioCasilla + 35;
        g2.setStroke(new BasicStroke(3));
        
        dibujadoRect(textoX,textoY,false,pJuego.pantallaCompleta ? 1 : 0);
    
        //VOLUMEN DE LA MUSICA //
        textoY += pJuego.tamanioCasilla;
        textoX -= pJuego.tamanioCasilla + 33;
        
        dibujadoRect(textoX,textoY,true,pJuego.controlmusica.escalaVolumen);
        
        //VOLUMEN DE LOS EFECTOS DE SONIDO//
        textoY += pJuego.tamanioCasilla;
        dibujadoRect(textoX,textoY,true,pJuego.controlsonido.escalaVolumen);
    
        //GUARDADO//
        pJuego.config.guardarConfig();
    }
    
    private int getXcentrado(String texto){
        int length = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        return pJuego.screenWidth/2 - length/2;
    }
    
    private void dibujadoRect(int x,int y, boolean rect, int cantidad){
        int dimX = 36, dimY = 36;
        if (rect)
            dimX += 96;
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, dimX, dimY);
        g2.drawRect(x+6, y+6, dimX-12, dimY-12);
    
        g2.setColor(Color.WHITE);
        g2.drawRect(x+3, y+3, dimX-6, dimY-6);
        
        dimX=36;
        for (int i=0;i<cantidad;i++){
            g2.setColor(Color.BLACK);
            g2.drawRect(x+6+i*24, y+6, dimX-12, dimY-12);
            g2.setColor(Color.WHITE);
            g2.fillRect(x+8+i*24, y+8, dimX-15, dimY-15);
        }
    }
    
    private void dibujadoLetras(String texto, int x, int y, int tipo){
        Fuentes tipoFuente=new Fuentes();
        int b = 0;
        switch (tipo){
            case 1 ->{
                g2.setFont((tipoFuente.fuente(tipoFuente.upheaval,0,50)));
                b=3;
            }
            case 2 ->{
                g2.setFont((tipoFuente.fuente(tipoFuente.pokemon,0,40)));
                b=2;
            }
            case 3 ->{
                g2.setFont((tipoFuente.fuente(tipoFuente.pokemon2,0,30)));
                b=2;
            }
        }
        
        //ANTI-LIASING
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        //BORDES NEGROS//
        g2.setColor(Color.BLACK);
        for (int i = -b; i <= b; i++) {
            for (int j = -b; j <= b; j++) {
                if (i != 0 || j != 0) {
                    g2.drawString(texto, x + i, y + j);
                }
            }
        }
        
        //LETRAS BLANCAS//
        g2.setColor(Color.WHITE);
        g2.drawString(texto, x, y);
    }
    
    public void pantallaCompletaNotif(int frameX, int frameY){
        int textoX = frameX - pJuego.tamanioCasilla*2;
        int textoY = frameY + pJuego.tamanioCasilla*3;
    
        mensaje = "El cambio tendra efecto cuando \nreinicies el juego";
        
        for (String linea:mensaje.split("\n")){
            dibujadoLetras(linea,textoX,textoY,2);
            textoY += 40;
        }
        
        //REGRESAR//
        textoX += pJuego.tamanioCasilla; 
        textoY = pJuego.tamanioCasilla*6;
        dibujadoLetras("Regresar", textoX, textoY,2);
        if (opcionMenu == 0){
            dibujadoLetras("+", textoX-25, textoY,2);
            if (pJuego.keyH.enterPressed == true){
                subState = 0;
            }
        }
    }
    
    public void controlesMenu(int frameX, int frameY){
        int textoX = getXcentrado("CONTROLES:") - pJuego.tamanioCasilla,
        textoY = frameY + pJuego.tamanioCasilla;
        
        //TITULO//
        dibujadoLetras("CONTROLES:",textoX,textoY,2);
    
        //ACCIONES//
        textoX = frameX - pJuego.tamanioCasilla*2;
        textoY += pJuego.tamanioCasilla;
        dibujadoLetras("Movimiento",textoX,textoY,2);textoY+=pJuego.tamanioCasilla;
        dibujadoLetras("Interactuar",textoX,textoY,2);textoY+=pJuego.tamanioCasilla;
        dibujadoLetras("Confirmar",textoX,textoY,2);textoY+=pJuego.tamanioCasilla;
        dibujadoLetras("Abrir/Cerrar Menu",textoX,textoY,2);
        
        //CONTROLES//
        textoX = frameX + pJuego.tamanioCasilla*3;
        textoY = frameY + pJuego.tamanioCasilla*2;
        dibujadoLetras("WASD/FLECHITAS",textoX,textoY,2);textoY+=pJuego.tamanioCasilla;
        dibujadoLetras("E",textoX,textoY,2);textoY+=pJuego.tamanioCasilla;
        dibujadoLetras("ENTER",textoX,textoY,2);textoY+=pJuego.tamanioCasilla;
        dibujadoLetras("ESC/P",textoX,textoY,2);
        
        //REGRESAR//
        textoX = frameX + pJuego.tamanioCasilla; 
        textoY = pJuego.tamanioCasilla*7;
        dibujadoLetras("Regresar", textoX, textoY,2);
        if (opcionMenu == 0){
            dibujadoLetras("+", textoX-25, textoY,2);
            if (pJuego.keyH.enterPressed == true){
                subState = 0;
                opcionMenu = 3;
            }
        }
    }
    
    public void cerrarJuegoConfirm(int frameX, int frameY){
        int textoX = frameX - pJuego.tamanioCasilla*2;
        int textoY = frameY + pJuego.tamanioCasilla*3;
    
        mensaje = "Deseas salir del juego y \nregresar a la pantalla de titulo?";
        
        for (String linea:mensaje.split("\n")){
            dibujadoLetras(linea,textoX,textoY,2);
            textoY += 40;
        }
        
        //SI//
        textoX += pJuego.tamanioCasilla; 
        textoY = pJuego.tamanioCasilla*6;
        dibujadoLetras("SI", textoX, textoY,2);
        if (opcionMenu == 0){
            dibujadoLetras("+", textoX-25, textoY,2);
            if (pJuego.keyH.enterPressed == true){
                pJuego.regresarAlMenuIni();
            }
        }
        
        //NO//
        textoY += pJuego.tamanioCasilla;
        dibujadoLetras("NO", textoX, textoY,2);
        if (opcionMenu == 1){
            dibujadoLetras("+", textoX-25, textoY,2);
            if (pJuego.keyH.enterPressed == true){
                subState = 0;
                opcionMenu = 4;
            }
        }
    }
    
    public void dibujadoinventario(Graphics2D g2,Jugador jugador){
            int MarcoX=(pJuego.screenWidth/2)-128;
            int MarcoY=pJuego.tamanioCasilla/6;
            int MarcoAncho=pJuego.tamanioCasilla;
            
            BufferedImage casilla=this.configuracion("/objetos/casilla");
            int cont=0;

            
            for(Objetosclase obj:jugador.inventario){
                
                if(cont<4*pJuego.tamanioCasilla){
                    g2.drawImage(casilla, MarcoX+(cont), MarcoY, MarcoAncho,MarcoAncho,null);
                }
                if(obj!=null){
                    g2.drawImage(obj.down1, MarcoX+(cont), MarcoY, MarcoAncho,MarcoAncho,null);
                
                    //g2.drawImage(obj.down1, pJuego.tamanioCasilla*4+cont, pJuego.tamanioCasilla/4, pJuego.tamanioCasilla,pJuego.tamanioCasilla,null);
                }
                cont=cont+pJuego.tamanioCasilla;
            }    
    }
    
    public void tienda(Graphics2D g2){
            int MarcoX=(pJuego.screenWidth/2)-128;
            int MarcoY=pJuego.tamanioCasilla/6+124;
            int MarcoAncho=pJuego.tamanioCasilla;
            int MarcoAlto=pJuego.tamanioCasilla;
            BufferedImage casilla=this.configuracion("/objetos/casilla");
            int cont=0;

                
        dibujadoFondo();
        for(Objetosclase obj:pJuego.NPC[2][1].inventario){
            if(cont<pJuego.tamanioCasilla*4){
                g2.drawImage(casilla, MarcoX+(cont), MarcoY, MarcoAncho,MarcoAncho,null);

                if(obj!=null){
                    if(obj.nombre.equals("botas")){
                        g2.drawImage(obj.down1, MarcoX+(cont), MarcoY, MarcoAncho,MarcoAncho,null);
                    }else{
                        g2.drawImage(obj.down1, MarcoX+(cont), MarcoY+10, MarcoAncho,MarcoAncho+10,null);
                    }
                }
                }
                cont=cont+pJuego.tamanioCasilla;
            }
            //casilla de informacion
            
            g2.drawImage(casilla, 60, MarcoY, pJuego.tamanioCasilla*4,pJuego.tamanioCasilla*3,null);
                
            dibujadoinfoTienda(g2,opcion,MarcoY+10);
                
                if(opcion>0){
                    dibujadoLetras("^", MarcoX+25+((opcion-1)*64), MarcoY+MarcoAlto+35,3);
                }else{
                    dibujadoLetras("^", 100+15, MarcoY+pJuego.tamanioCasilla*2+45,3);

                }
               
    }
    
    public void dibujadoinfoTienda(Graphics2D g2,int objselect ,int MarcoY){
        int cont=0;
        
        for(Objetosclase obj:pJuego.NPC[2][1].inventario){
            cont++;
            if(obj!=null && cont==objselect){
                dibujadoLetras(obj.nombre,100, MarcoY+pJuego.tamanioCasilla/2+10, 2);
                dibujadoLetras("Precio: "+obj.getPrecio(), 100, MarcoY+pJuego.tamanioCasilla+20, 2);
            }
        }
        dibujadoLetras("Salir",100, MarcoY+pJuego.tamanioCasilla*2, 2);
    }
    
    public BufferedImage configuracion(String nombre){
        
        Herramientas herramienta = new Herramientas();
        BufferedImage imagen= null;
        
        try{
            imagen=ImageIO.read(getClass().getResourceAsStream(nombre+".png"));
            imagen= herramienta.imagenEscalada(imagen, pJuego.tamanioCasilla, pJuego.tamanioCasilla);
            
        }catch(IOException e){
        }
        
        return imagen;
    }
}
