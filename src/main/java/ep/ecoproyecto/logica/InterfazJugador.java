
package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Jugador;
import ep.ecoproyecto.logica.tipografia.Fuentes;
import ep.ecoproyecto.logica.objetos.Objetosclase;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author C-A-F
 */
public class InterfazJugador{
    public PanelJuego gp;
    public Fuentes fuente= new Fuentes();
    public Graphics2D g2;
    public BufferedImage imagen;
    public boolean mensajeOn=false;
    public boolean tiendaOn=false;
    public boolean victoriamensaje=false;
    public String mensaje="";
    int contmensajes=0;
    public int opcion=0;
    public int opcionMenu=0;
    public int subState = 0;
    
            //titulo.setFont(new java.awt.Font("Arial", 0, 40)); // NOI18N

    public InterfazJugador(PanelJuego gp) {
        this.gp = gp;
        
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
        
        g2.setFont(fuente.fuente(fuente.upheaval,0,25));
        g2.setColor(Color.WHITE);
        g2.drawString("posicion X:"+(gp.jugador.xMapa/64)+" Y "+(gp.jugador.yMapa/64), gp.screenWidth-800, gp.screenHeight-50);

        //g2.setFont(fuente);
        g2.drawString("Unidades de credito: "+(gp.jugador.cantInventario[4]),50, 50);
        
        
        if (gp.pause){
            dibujadoFondo();
            dibujadoMenuPausa();
        }
        
        //mostrar mensajes de NPC
        if(mensajeOn==true){
            int tarjeta=mensaje.length();
            g2.setColor(Color.blue);
            g2.fillRect(gp.screenWidth/3-8, gp.tamanioCasilla/6-5, tarjeta*15+8, gp.tamanioCasilla-6);
            
            g2.setColor(Color.black);
            g2.fillRect(gp.screenWidth/3-5, gp.tamanioCasilla/6, tarjeta*15, gp.tamanioCasilla-16);
            g2.setColor(Color.white);
            //g2.setFont(g2.getFont().deriveFont(30F));
            
            g2.drawString(mensaje, gp.screenWidth/3, gp.tamanioCasilla/2+5);   
            contmensajes++;
            if(contmensajes>60){
                contmensajes=0;
                mensajeOn=false;
            }
        }

        //dibujado de inventario
        if(mensajeOn==false){
            dibujadoinventario(g2,jugador); 
            
            if(tiendaOn==true ){
                tienda(g2);
            }
        }
    }
    
    public void dibujadoFondo(){
        Color colorOscuro = new Color(0,0,0,150);
        g2.setColor(colorOscuro);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    }
    
    public void dibujadoMenuPausa(){
        
        int frameX = getXcentrado("Opciones:") - gp.tamanioCasilla;
        int frameY = gp.tamanioCasilla;
    
        switch(subState){
            case 0-> opciones_Top(frameX,frameY);
            case 1-> pantallaCompletaNotif(frameX,frameY);
            case 2-> controlesMenu(frameX,frameY);
            case 3-> cerrarJuegoConfirm(frameX,frameY);
        }
        
        gp.keyH.enterPressed = false;
    }
    
    public void opciones_Top(int frameX, int frameY){
        int textoX, textoY;
        
        //TITULO//
        textoX = frameX;
        textoY = frameY + gp.tamanioCasilla;
        dibujadoLetras("Opciones:",textoX,textoY,true);
        
        //PANTALLA COMPLETA//
        textoX = textoX - gp.tamanioCasilla*2;
        textoY += gp.tamanioCasilla;
        dibujadoLetras("Pantalla Completa",textoX,textoY,false);
        if (opcionMenu == 0){
            dibujadoLetras("+",textoX-25,textoY,false);
            if (gp.keyH.enterPressed){
                gp.pantallaCompleta = !gp.pantallaCompleta;
                subState = 1;
            }
        }
        
        //MUSICA//
        textoY += gp.tamanioCasilla;
        dibujadoLetras("Musica",textoX,textoY,false);
        if (opcionMenu == 1)
            dibujadoLetras("+",textoX-25,textoY,false);
        
        //EFECTOS DE SONIDO//
        textoY += gp.tamanioCasilla;
        dibujadoLetras("Efectos de Sonido",textoX,textoY,false);
        if (opcionMenu == 2)
            dibujadoLetras("+",textoX-25,textoY,false);
        
        //CONTROLES//
        textoY += gp.tamanioCasilla;
        dibujadoLetras("Controles",textoX,textoY,false);
        if (opcionMenu == 3){
            dibujadoLetras("+",textoX-25,textoY,false);
            if (gp.keyH.enterPressed){
                subState = 2;
                opcionMenu = 0;
            }
        }
    
        //CERRAR JUEGO//
        textoX = getXcentrado("Cerrar Juego");
        textoY += gp.tamanioCasilla;
        dibujadoLetras("Cerrar Juego",textoX,textoY,false);
        if (opcionMenu == 4){
            dibujadoLetras("+",textoX-25,textoY,false);
            if (gp.keyH.enterPressed){
                subState = 3;
                opcionMenu = 0;
            }
        }
    
        //CHECK BOX PANTALLA COMPLETA//
        textoX = getXcentrado("Opciones:") + gp.tamanioCasilla*4;
        textoY = frameY + gp.tamanioCasilla + 35;
        g2.setStroke(new BasicStroke(3));
        
        dibujadoRect(textoX,textoY,false,gp.pantallaCompleta ? 1 : 0);
    
        //VOLUMEN DE LA MUSICA //
        textoY += gp.tamanioCasilla;
        textoX -= gp.tamanioCasilla + 33;
        
        dibujadoRect(textoX,textoY,true,gp.controlmusica.escalaVolumen);
        
        //VOLUMEN DE LOS EFECTOS DE SONIDO//
        textoY += gp.tamanioCasilla;
        dibujadoRect(textoX,textoY,true,gp.controlsonido.escalaVolumen);
    
        //GUARDADO//
        gp.config.guardarConfig();
    }
    
    private int getXcentrado(String texto){
        int length = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        return gp.screenWidth/2 - length/2;
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
    
    private void dibujadoLetras(String texto, int x, int y, boolean titulo){
        Fuentes tipoFuente=new Fuentes();
        int b;
        if (titulo){
            g2.setFont((tipoFuente.fuente(tipoFuente.upheaval,0,50)));
            b=3;
        }else{
            g2.setFont((tipoFuente.fuente(tipoFuente.pokemon,0,40)));
            b=2;
        }
        
        //Bordes Negros//
        g2.setColor(Color.BLACK);
        g2.drawString(texto, x - b, y - b);
        g2.drawString(texto, x + b, y + b);
        g2.drawString(texto, x + b, y - b);
        g2.drawString(texto, x - b, y + b);
        g2.drawString(texto, x, y + b);
        g2.drawString(texto, x, y - b);
        g2.drawString(texto, x + b, y);
        g2.drawString(texto, x - b, y);
        
        //Letras Blancas//
        g2.setColor(Color.WHITE);
        g2.drawString(texto, x, y);
    }
    
    public void pantallaCompletaNotif(int frameX, int frameY){
        int textoX = frameX - gp.tamanioCasilla*2;
        int textoY = frameY + gp.tamanioCasilla*3;
    
        mensaje = "El cambio tendra efecto cuando \nreinicies el juego";
        
        for (String linea:mensaje.split("\n")){
            dibujadoLetras(linea,textoX,textoY,false);
            textoY += 40;
        }
        
        //REGRESAR//
        textoX += gp.tamanioCasilla; 
        textoY = gp.tamanioCasilla*6;
        dibujadoLetras("Regresar", textoX, textoY,false);
        if (opcionMenu == 0){
            dibujadoLetras("+", textoX-25, textoY,false);
            if (gp.keyH.enterPressed == true){
                subState = 0;
            }
        }
    }
    
    public void controlesMenu(int frameX, int frameY){
        int textoX = getXcentrado("CONTROLES:") - gp.tamanioCasilla,
        textoY = frameY + gp.tamanioCasilla;
        
        //TITULO//
        dibujadoLetras("CONTROLES:",textoX,textoY,true);
    
        //ACCIONES//
        textoX = frameX - gp.tamanioCasilla*2;
        textoY += gp.tamanioCasilla;
        dibujadoLetras("Movimiento",textoX,textoY,false);textoY+=gp.tamanioCasilla;
        dibujadoLetras("Interactuar",textoX,textoY,false);textoY+=gp.tamanioCasilla;
        dibujadoLetras("Confirmar",textoX,textoY,false);textoY+=gp.tamanioCasilla;
        dibujadoLetras("Abrir/Cerrar Menu",textoX,textoY,false);
        
        //CONTROLES//
        textoX = frameX + gp.tamanioCasilla*3;
        textoY = frameY + gp.tamanioCasilla*2;
        dibujadoLetras("WASD/FLECHITAS",textoX,textoY,false);textoY+=gp.tamanioCasilla;
        dibujadoLetras("E",textoX,textoY,false);textoY+=gp.tamanioCasilla;
        dibujadoLetras("ENTER",textoX,textoY,false);textoY+=gp.tamanioCasilla;
        dibujadoLetras("ESC/P",textoX,textoY,false);
        
        //REGRESAR//
        textoX = frameX + gp.tamanioCasilla; 
        textoY = gp.tamanioCasilla*7;
        dibujadoLetras("Regresar", textoX, textoY,false);
        if (opcionMenu == 0){
            dibujadoLetras("+", textoX-25, textoY,false);
            if (gp.keyH.enterPressed == true){
                subState = 0;
                opcionMenu = 3;
            }
        }
    }
    
    public void cerrarJuegoConfirm(int frameX, int frameY){
        int textoX = frameX - gp.tamanioCasilla*2;
        int textoY = frameY + gp.tamanioCasilla*3;
    
        mensaje = "Deseas salir del juego y \nregresar a la pantalla de titulo?";
        
        for (String linea:mensaje.split("\n")){
            dibujadoLetras(linea,textoX,textoY,false);
            textoY += 40;
        }
        
        //SI//
        textoX += gp.tamanioCasilla; 
        textoY = gp.tamanioCasilla*6;
        dibujadoLetras("SI", textoX, textoY,false);
        if (opcionMenu == 0){
            dibujadoLetras("+", textoX-25, textoY,false);
            if (gp.keyH.enterPressed == true){
                gp.regresarAlMenuIni();
            }
        }
        
        //NO//
        textoY += gp.tamanioCasilla;
        dibujadoLetras("NO", textoX, textoY,false);
        if (opcionMenu == 1){
            dibujadoLetras("+", textoX-25, textoY,false);
            if (gp.keyH.enterPressed == true){
                subState = 0;
                opcionMenu = 4;
            }
        }
    }
    
    public void dibujadoinventario(Graphics2D g2,Jugador jugador){
            int MarcoX=(gp.screenWidth/2)-128;
            int MarcoY=gp.tamanioCasilla/6;
            int MarcoAncho=gp.tamanioCasilla;
            int MarcoAlto=gp.tamanioCasilla;
            
            BufferedImage casilla=this.configuracion("/objetos/casilla");
            int cont=0;

            
            for(Objetosclase obj:jugador.inventario){
                
                if(cont<4*gp.tamanioCasilla){
                    g2.drawImage(casilla, MarcoX+(cont), MarcoY, MarcoAncho,MarcoAncho,null);
                }
                if(obj!=null){
                    g2.drawImage(obj.down1, MarcoX+(cont), MarcoY, MarcoAncho,MarcoAncho,null);
                
                    //g2.drawImage(obj.down1, gp.tamanioCasilla*4+cont, gp.tamanioCasilla/4, gp.tamanioCasilla,gp.tamanioCasilla,null);
                }
                cont=cont+gp.tamanioCasilla;
            }    
    }
    
    public void tienda(Graphics2D g2){
            int MarcoX=(gp.screenWidth/2)-128;
            int MarcoY=gp.tamanioCasilla/6+124;
            int MarcoAncho=gp.tamanioCasilla;
            int MarcoAlto=gp.tamanioCasilla;
                /*
                //Color c= new Color(9,28,21);/
                g2.setColor(Color.ORANGE);
                g2.fillRect(MarcoX, MarcoY, MarcoAncho, MarcoAlto);
                */
            BufferedImage casilla=this.configuracion("/objetos/casilla");
            int cont=0;
                
            for(Objetosclase obj:gp.NPC[2][1].inventario){
                if(cont<gp.tamanioCasilla*4){
                    g2.drawImage(casilla, MarcoX+(cont), MarcoY, MarcoAncho,MarcoAncho,null);

                    if(obj!=null){
                        if(obj.nombre.equals("botas")){
                            g2.drawImage(obj.down1, MarcoX+(cont), MarcoY, MarcoAncho,MarcoAncho,null);
                        }else{
                            g2.drawImage(obj.down1, MarcoX+(cont), MarcoY+10, MarcoAncho,MarcoAncho+10,null);
                        }
                    }
                }
                cont=cont+gp.tamanioCasilla;
            }
            //casilla de informacion
            
            g2.drawImage(casilla, 60, MarcoY, gp.tamanioCasilla*4,gp.tamanioCasilla*3,null);
                
            dibujadoinfo(g2,opcion,MarcoY+10);
                
                g2.setColor(Color.WHITE);
                if(opcion>0){
                    g2.drawString("^", MarcoX+27+((opcion-1)*64), MarcoY+MarcoAlto+32);
                }else{
                    g2.drawString("^", 100+20, MarcoY+gp.tamanioCasilla*2+40);
                }
               
    }
    
    public void dibujadoinfo(Graphics2D g2,int objselect ,int MarcoY){
        int cont=0;
        /*
        g2.setColor(Color.ORANGE);
        g2.fillRect(gp.tamanioCasilla/2, MarcoY, gp.tamanioCasilla*3, gp.tamanioCasilla*3);
        */
        
        g2.setColor(Color.WHITE);
        
        for(Objetosclase obj:gp.NPC[0][1].inventario){
            cont++;
            if(obj!=null && cont==objselect){
                g2.drawString(obj.nombre, 100, MarcoY+gp.tamanioCasilla/2);
                g2.drawString("Precio: "+obj.getPrecio(), 100, MarcoY+gp.tamanioCasilla);
            }
        }
        g2.drawString("Salir",100, MarcoY+gp.tamanioCasilla*2);
    }
    
    public BufferedImage configuracion(String nombre){
        
        Herramientas herramienta = new Herramientas();
        BufferedImage imagen= null;
        
        try{
            imagen=ImageIO.read(getClass().getResourceAsStream(nombre+".png"));
            imagen= herramienta.imagenEscalada(imagen, gp.tamanioCasilla, gp.tamanioCasilla);
            
        }catch(IOException e){
        }
        
        return imagen;
    }
}
