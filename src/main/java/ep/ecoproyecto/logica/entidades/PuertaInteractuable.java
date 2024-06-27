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
public class PuertaInteractuable extends Entidad{
    int zMapa;
    int Xtp;
    int Ytp;
    int Ztp;
    
    public PuertaInteractuable(PanelJuego gp, int x, int y,int z ,int Xtp, int Ytp, int Ztp) {
        super(gp);
        this.xMapa=x*gp.tamanioCasilla;
        this.yMapa=y*gp.tamanioCasilla;
        this.direction="down";
        this.vel=0;

        this.
        right1=right2=up1=up2=left1=left2=down2=down1= this.configuracion("/objetos/puerta1");
        colision=true;
        
        hitBox= new Rectangle(0,0,64,64);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        
        this.Xtp=Xtp;
        this.Ytp=Ytp;
        this.Ztp=Ztp;
        this.zMapa=z;
    }

    
    
}
