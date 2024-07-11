package ep.ecoproyecto.logica;
import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.Interfaces.Dibujado;
import ep.ecoproyecto.logica.objetos.Casa;
import ep.ecoproyecto.logica.objetos.ObjetoEquipo;
import ep.ecoproyecto.logica.objetos.ObjetoMesa;
import ep.ecoproyecto.logica.objetos.ObjetoRecogible;
import java.awt.Graphics2D;

/**
 *
 * @author C-A-F
 */
public class EmisorObjetos  implements Dibujado{
    PanelJuego pJuego;
    
    public EmisorObjetos(PanelJuego pJuego){
        this.pJuego=pJuego;
    }
    
    public void establecerObj(){
        
        //----MUNDO 1:-----//
        int NumMap = 0;
        pJuego.obj[NumMap][4]=new ObjetoEquipo("botas",16,6,pJuego);
        pJuego.obj[NumMap][7]=new Casa("casa",11,8,pJuego);
        //----MUNDO 2:----- pruebas//
        NumMap = 1;
        
        pJuego.obj[NumMap][0]=new ObjetoRecogible("semilla",20,9,pJuego);
        pJuego.obj[NumMap][1]=new ObjetoRecogible("semilla",5,7,pJuego);
        pJuego.obj[NumMap][2]=new ObjetoRecogible("semilla",6,10,pJuego);
        
        pJuego.obj[NumMap][3]=new ObjetoRecogible("basura",8,8,pJuego);
        
        //----MUNDO 3:-----  tienda//   
        NumMap = 2;
        pJuego.obj[NumMap][1]=new ObjetoMesa("mesa",7,1,pJuego);
        pJuego.obj[NumMap][2]=new ObjetoMesa("mesa",6,1,pJuego);
        pJuego.obj[NumMap][3]=new ObjetoMesa("mesa",5,1,pJuego);
        pJuego.obj[NumMap][4]=new ObjetoMesa("mesa",4,1,pJuego);
        pJuego.obj[NumMap][5]=new ObjetoMesa("mesa",3,1,pJuego);
        pJuego.obj[NumMap][6]=new ObjetoRecogible("coin",3,3,pJuego);
        pJuego.obj[NumMap][6]=new ObjetoRecogible("coin",2,5,pJuego);
        
        /*
        //----MUNDO 4:-----derecha//
        NumMap = 3;
        
        //----MUNDO 5:-----arriba// 
        NumMap = 4;
        */
        
    }
    @Override
    public void dibujado(Graphics2D g2){
        
            for(int i=0;i<pJuego.obj[0].length;i++){
                if(pJuego.obj[pJuego.mapaActual][i]!=null){
                    int PantallaX=pJuego.obj[pJuego.mapaActual][i].xMapa- pJuego.jugador.xMapa+pJuego.jugador.pantallaX;
                    int PantallaY=pJuego.obj[pJuego.mapaActual][i].yMapa- pJuego.jugador.yMapa+pJuego.jugador.pantallaY;
                    
                    if((pJuego.obj[pJuego.mapaActual][i].xMapa+pJuego.tamanioCasilla+pJuego.obj[pJuego.mapaActual][i].hitBox.width > pJuego.jugador.xMapa-pJuego.jugador.pantallaX)&&(pJuego.obj[pJuego.mapaActual][i].xMapa-pJuego.tamanioCasilla < pJuego.jugador.xMapa+pJuego.jugador.pantallaX)&&
                       (pJuego.obj[pJuego.mapaActual][i].yMapa+pJuego.tamanioCasilla+pJuego.obj[pJuego.mapaActual][i].hitBox.height > pJuego.jugador.yMapa-pJuego.jugador.pantallaY)&&(pJuego.obj[pJuego.mapaActual][i].yMapa-pJuego.tamanioCasilla < pJuego.jugador.yMapa+pJuego.jugador.pantallaY)){     
                        g2.drawImage(pJuego.obj[pJuego.mapaActual][i].down1, PantallaX, PantallaY, pJuego.obj[pJuego.mapaActual][i].hitBox.width,pJuego.obj[pJuego.mapaActual][i].hitBox.height,null);
                    }
                    
                    
                }
            }
            
    }
    
    
        
}

