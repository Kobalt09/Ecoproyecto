/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.entidades;


import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.entidades.Entidad;
import ep.ecoproyecto.logica.objetos.ObjetoEquipo;
import ep.ecoproyecto.logica.objetos.ObjetoMoneda;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Cris
 */
public class Tienda extends Entidad {
    
    public Tienda(PanelJuego gp, int x, int y) {
        super(gp);
        this.xMapa=x*gp.tamanioCasilla;
        this.yMapa=y*gp.tamanioCasilla;
        this.direction="down";
        this.vel=0;

        right1=right2=up1=up2=left1=left2=down2=down1= this.configuracion("/objetos/CajaRegistradora");
        colision=true;
        
        hitBox= new Rectangle(16,16,32,32);
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        movimiento=false;
    }
    
    public void estableceraccion(){
        if(movimiento==true){
        contadordeaccion++;
            if(contadordeaccion==120){
                Random random = new Random();
                int i= random.nextInt(100)+1;

                if(i<=25){
                    direction="up";
                }
                if((i<=50)&&(i>25)){
                    direction="down";
                }
                if((i<=75)&&(i>50)){
                    direction="left";
                }
                if((i<=100)&&(i>75)){
                    direction="right";
                }
                
                contadordeaccion=0;
            }
        }
    }
    
    public void EstablecerTienda(){
        this.inventario[0]=new ObjetoEquipo("botas",16,6,gp);;
        this.inventario[0]=new ObjetoMoneda("coin",10,10,gp);
        this.inventario[0]=new ObjetoRecogible("llave",10,10,gp);
    }
    
}
