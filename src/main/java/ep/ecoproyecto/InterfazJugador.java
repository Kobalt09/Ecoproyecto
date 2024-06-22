/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;

import Interfaces.Dibujado;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import objetos.ObjetoRecogible;

/**
 *
 * @author Cris
 */
public class InterfazJugador implements Dibujado{
    public PanelJuego gp;
    public Font fuente;
    public Graphics2D g2;
    //BufferedImage llaveimagen;
    public boolean mensajeOn=false;
    public String mensaje="";
    int contmensajes=0;
    public boolean victoriamensaje=false;
    

    public InterfazJugador(PanelJuego gp) {
        this.gp = gp;
        fuente=new Font("Arial",Font.PLAIN,40);
       // ObjetoRecogible llave= new ObjetoRecogible("llave",0,0,gp);
       // llaveimagen = llave.image;
    }
    
    public void mostrarmensaje(String texto) {
        
        mensaje=texto;
        mensajeOn= true;
        
    }
    
    
    public void dibujado(Graphics2D g2){
        
        g2.setFont(fuente);
        g2.setColor(Color.white);
        g2.drawString("posicion X:"+(gp.jugador.xMapa/64)+" Y "+(gp.jugador.yMapa/64), gp.tamanioCasilla*2, gp.tamanioCasilla);
        
        
        //estado de dialogo
        if(gp.estadoDialogo==gp.estadodelJuego){
            
        }
        
        
        
        g2.setFont(fuente);
        g2.setColor(Color.white);
       // g2.drawImage(llaveimagen, gp.tamanioCasilla/2, gp.tamanioCasilla/2, gp.tamanioCasilla,gp.tamanioCasilla,null);
        g2.drawString("x = "+gp.jugador.llaves, gp.tamanioCasilla*2, gp.tamanioCasilla);
        
        
        dibujadoinventario();
        
        if(mensajeOn==true){
            int tarjeta=mensaje.length();
            g2.setColor(Color.blue);
            g2.fillRect(gp.screenWidth/3-8, gp.tamanioCasilla/6-5, tarjeta*15+8, gp.tamanioCasilla-6);
            
            g2.setColor(Color.black);
            g2.fillRect(gp.screenWidth/3-5, gp.tamanioCasilla/6, tarjeta*15, gp.tamanioCasilla-16);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(30F));
            
            g2.drawString(mensaje, gp.screenWidth/3, gp.tamanioCasilla/2+5);
            
            contmensajes++;
            
            if(contmensajes>120){
                contmensajes=0;
                mensajeOn=false;
            }
        }
    }
    
    public void dibujadoinventario(){
        int MarcoX=gp.tamanioCasilla*4;
        int MarcoY=gp.tamanioCasilla*4;
        int MarcoAncho=gp.tamanioCasilla/2;
        int MarcoAlto=gp.tamanioCasilla/2;
        subventanas(MarcoX,MarcoY,MarcoAncho,MarcoAlto);
    }

    public void subventanas(int posX,int posY, int Ancho, int Largo){
        
        //establecemos el color de la sudventana
        Color c= new Color(82,183,136);
        g2.setColor(Color.GREEN);
        g2.fillRect(posX, posY, Largo, Largo);
        
    }
}
