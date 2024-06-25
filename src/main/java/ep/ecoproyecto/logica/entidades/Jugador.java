

package ep.ecoproyecto.logica.entidades;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.KeyHandler;
import ep.ecoproyecto.logica.net.packets.Packet02Mov;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import ep.ecoproyecto.logica.objetos.Objetosclase;
import ep.ecoproyecto.logica.tipografia.Fuentes;
import java.awt.Color;

/**
 *
 * @author Cris
 */

public class Jugador extends Entidad{

    KeyHandler keyH;
    public final int pantallaX;
    public final int pantallaY;
    protected String username;
    
    Objetosclase inventario[]= new Objetosclase[10];
    public int llaves=0;

    public Jugador(PanelJuego gp) {
        super(gp);
        this.keyH=new KeyHandler();
        this.username="";
        
        pantallaX=gp.screenWidth/2-(gp.tamanioCasilla/2);
        pantallaY=gp.screenHeight/2-(gp.tamanioCasilla/2);
        
        hitBox=new Rectangle();
        //donde empiza la hitbox en relacion a la esquina superior
        hitBox.x=8;
        hitBox.y=8;
        //tamanio de la hitbox
        hitBox.height=48;
        hitBox.width=48;
        
        //area de colision
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        
        valoresporDefecto();
        getPlayerImage();
    }
    
   
    
    public Jugador(PanelJuego gp, KeyHandler keyH, String username){
        
        super(gp);
        
        this.keyH=keyH;
        this.username=username;
        
        pantallaX=gp.screenWidth/2-(gp.tamanioCasilla/2);
        pantallaY=gp.screenHeight/2-(gp.tamanioCasilla/2);
        
        hitBox=new Rectangle();
        //donde empiza la hitbox en relacion a la esquina superior
        hitBox.x=8;
        hitBox.y=8;
        //tamanio de la hitbox
        hitBox.height=48;
        hitBox.width=48;
        
        //area de colision
        areadefectoX=hitBox.x;
        areadefectoY=hitBox.y;
        
        valoresporDefecto();
        getPlayerImage();
    }
    
    public void valoresporDefecto(){

        
        //posicion del jugador en el arreglo +1,
        xMapa=10*gp.tamanioCasilla+gp.tamanioCasilla;
        yMapa=10*gp.tamanioCasilla+gp.tamanioCasilla;

        vel=4;
        direction ="down"; 
    }

    public String getDirection() {
        return direction;
    }

    public String getUsername() {
        return username;
    }
    
    public void getPlayerImage(){
        
        up1=configuracion("/player/jg_arr_01");
        up2=configuracion("/player/jg_arr_02");
        down1=configuracion("/player/jg_abj_01");
        down2=configuracion("/player/jg_abj_02");
        left1=configuracion("/player/jg_izq_01");
        left2=configuracion("/player/jg_izq_02");
        right1=configuracion("/player/jg_der_01");
        right2=configuracion("/player/jg_der_02");
        
    }
    
    public void update(){
        if(keyH != null){
            if (keyH.upPressed==true||keyH.leftPressed==true||keyH.downPressed==true||keyH.rightPressed==true){
            //actualizamos la posicion del jugador sumando o restando su velocidad
            
                if(keyH.upPressed==true){
                    direction="up";
                }else if(keyH.leftPressed==true){
                    direction="left";
                }else if(keyH.downPressed==true){
                    direction="down";
                }else if(keyH.rightPressed==true){
                    direction="right";
                }
                
                Packet02Mov packet=new Packet02Mov(username, this.xMapa, this.yMapa,this.direction);
                packet.writeData(PanelJuego.juego.socketcliente);
                //colision Casillas
                colision=false;
                gp.colisiones.revisarColision(this);
            
             //colision NPC
                int entidadID=gp.colisiones.chequeoEntidades(this, gp.NPC);
                intereaccionNCP(entidadID);
            
            //colision objetos
                int objID=gp.colisiones.chequeoObjetos(this, true);
                recogerobjetos(objID);
            
            //colision eventos
                gp.ControlEventos.chequeoEvento();
            
            
                if(colision==false){
                    switch (direction) {
                            case "up":
                                yMapa-=vel;
                                break;
                            case "left":
                                xMapa-=vel;
                            break;
                            case "down":
                                yMapa+=vel;
                            break;
                             case "right":
                                xMapa+=vel;
                            break;
                    }
                }

                spriteCounter++;
                if (spriteCounter>10){
                    if (spriteNum == 2 )
                    {spriteNum=1;}
                    else{
                    if (spriteNum == 1)
                    spriteNum=2;
                    }
                    spriteCounter = 0;
                }
            }
        } 
    }
        /*interaccion
        if (key2.ePressed==true){
            
        }*/

    public void recogerobjetos(int id){
        
        if(id!=999){
            //usa el nombre del objeto para saber con cual objeto colisiona 
            String objnombre=gp.obj[gp.mapaActual][id].nombre;
            
            //switch para el nombre
            //nota se puede usar un getclass para saber el tipo o usar 
            System.out.println(gp.obj[gp.mapaActual][id].nombre);
            switch(objnombre){
                case "llave":
                        llaves++;
                        gp.efectos(2);
                        gp.hud.mostrarmensaje("conseguiste una llave");
                        gp.obj[gp.mapaActual][id]=null;
                        System.out.println("llaves: "+llaves);
                    break;
                case "puerta":
                        if(llaves>0){
                            llaves--;
                            gp.efectos(5);
                            gp.hud.mostrarmensaje("puerta abierta");
                            gp.obj[gp.mapaActual][id]=null;
                            System.out.println("llaves: "+llaves);
                        }else{
                            gp.hud.mostrarmensaje("no tienes llaves para esta puerta");
                        }
                    break;    
                case "botas":
                        if(this.inventario[0]==null){
                            gp.efectos(4);
                            gp.hud.mostrarmensaje("conseguiste "+objnombre);
                            vel=vel+2;
                            this.inventario[0]=gp.obj[gp.mapaActual][id];
                            gp.obj[gp.mapaActual][id]=null;
                        }
                        
                    break;
                case "cofre":
                            gp.hud.victoriamensaje=true;
                            gp.obj[gp.mapaActual][id]=null;
                    break;
            }
            
        }
        
    }
    
    public void RecogerObjeto(int i){
        if(i!=999){
            gp.obj[gp.mapaActual][i]=null;
        }
    }
        
        
    public void intereaccionNCP(int id) {
        if(id!=999){
                System.out.println("tocas un npc");
        }
    }
    
    public void draw(Graphics2D g2){

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
       
        
        if(gp.jugador.equals(this)) {
            g2.drawImage(image,pantallaX,pantallaY,null);   
 
            if (username != null){
                Fuentes tipoFuente=new Fuentes();
                g2.setFont((tipoFuente.fuente(tipoFuente.upheaval,0,20)));
                int textX = pantallaX + (image.getWidth() - g2.getFontMetrics().stringWidth(username))/2 ;
                int textY = pantallaY - 5;

                //Bordes Negros//
                 g2.setColor(Color.BLACK);
                 g2.drawString(username, textX - 2, textY - 2);
                 g2.drawString(username, textX - 2, textY + 2);
                 g2.drawString(username, textX + 2, textY - 2);
                 g2.drawString(username, textX + 2, textY + 2);
                 g2.drawString(username, textX, textY - 2);
                 g2.drawString(username, textX, textY + 2);
                 g2.drawString(username, textX - 2, textY);
                 g2.drawString(username, textX + 2, textY);

                 //Letras Blancas//
                 g2.setColor(Color.WHITE);
                 g2.drawString(username, textX, textY);

            
            
            
            }
        }else
        {
            int dibX=gp.jugador.pantallaX+this.xMapa-gp.jugador.xMapa;
            int dibY=gp.jugador.pantallaY+this.yMapa-gp.jugador.yMapa;
           // g2.drawImage(image,xMapa,yMapa,null);
            g2.drawImage(image,dibX,dibY,null);
        }
    }


}