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
public class Papelera extends Entidad{
    
    public Papelera(PanelJuego pJuego, int x, int y) {
        super(pJuego);
        this.xMapa=x*pJuego.tamanioCasilla;
        this.yMapa=y*pJuego.tamanioCasilla;
        this.vel=0;
        this.hitBox=new Rectangle(0,0,pJuego.tamanioCasilla,pJuego.tamanioCasilla);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        this.mensaje="Una papelera, aqui podria dejar la basura";
        
        getImage();
    }
    
    
    public void getImage(){
        right1=right2=up1=up2=left1=left2=down2=down1=this.configuracion("/objetos/papelera/Casillapapelera");
    }
    
}