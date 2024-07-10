/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.entidades;

import ep.ecoproyecto.gui.PanelJuego;
import java.awt.Rectangle;

/**
 *
 * @author Cris
 */
public final class PuertaInteractuable extends Entidad{
    int Xtp;
    int Ytp;
    int Ztp;
    
    public PuertaInteractuable(PanelJuego gp,String nombre, int x, int y,int z ,int Xtp, int Ytp, int Ztp) {
        super(gp);
        this.nombre=nombre;
        this.xMapa=x*gp.tamanioCasilla;
        this.yMapa=y*gp.tamanioCasilla;
        this.direction="down";
        this.vel=0;
        colision=true;
        
        hitBox= new Rectangle(-1,-1,gp.tamanioCasilla+2,gp.tamanioCasilla+2);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        
        this.Xtp=Xtp;
        this.Ytp=Ytp;
        this.Ztp=Ztp;
        getImage();
    }

    public void getImage(){
        if(this.nombre.equals("puerta")){
            right1=up1=left1=down1=right2=up2=left2=down2= this.configuracion("/objetos/puerta1Invis");
            
        }else{
            right1=up1=left1=down1= this.configuracion("/Flechas/Flecha"+this.nombre+"1");
            right2=up2=left2=down2= this.configuracion("/Flechas/Flecha"+this.nombre+"2");
            movimiento=true;
        }
    }   
    
    
}
