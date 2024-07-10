/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.entidades;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Herramientas;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Cris
 */
public class Aguaconbasura extends Entidad{

    public String estado;

    public Aguaconbasura(PanelJuego gp, int x, int y) {
        super(gp);
        this.xMapa=x*gp.tamanioCasilla;
        this.yMapa=y*gp.tamanioCasilla;
        this.vel=0;
        this.hitBox=new Rectangle(0,0,gp.tamanioCasilla,gp.tamanioCasilla);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        estado="Aguasucia";
        
        this.Mensaje="Cuanta basura en el agua";
        
        getImage();
    }
    
    
    public void getImage(){
        right1=right2=up1=up2=left1=left2=down2=down1=this.configuracion("/objetos/agua/"+estado);
    }
    
    public BufferedImage configuracion(String nombre){
        
        Herramientas herramienta = new Herramientas();
        BufferedImage imagen= null;
        
        try{
            imagen=ImageIO.read(getClass().getResourceAsStream(nombre+".jpg"));
            imagen= herramienta.imagenEscalada(imagen, gp.tamanioCasilla, gp.tamanioCasilla);
            
        }catch(IOException e){
        }
        
        return imagen;
    }
}