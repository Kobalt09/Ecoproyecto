/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Cris
 */
public class ObjetoRecogible extends Objetosclase{

    public ObjetoRecogible() {
        this.nombre= "llave";
        
        try{
            image =ImageIO.read(getClass().getResourceAsStream("/objetos/llave1.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
}
