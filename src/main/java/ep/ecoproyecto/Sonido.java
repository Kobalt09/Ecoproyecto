/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {
    //los archivos deben ser WAP de 16bits
    Clip clip;
    URL sonidoURL[];

    public Sonido() {
        
        sonidoURL=new URL[30];
        
        sonidoURL[0]=getClass().getResource("/musica/paseo.wav");
        sonidoURL[1]=getClass().getResource("/musica/tiendajazz.wav");
        sonidoURL[2]=getClass().getResource("/efectosdesonido/caminar.wav");
        sonidoURL[3]=getClass().getResource("/efectosdesonido/moneda.wav");
        sonidoURL[4]=getClass().getResource("/efectosdesonido/powerup.wav");
        sonidoURL[5]=getClass().getResource("/efectosdesonido/equipo.wav");
    }

    void establecerArchivo(int i) {
        try{
        AudioInputStream ais= AudioSystem.getAudioInputStream(sonidoURL[i]);
        clip= AudioSystem.getClip();
        clip.open(ais);
        }catch(Exception e){
    
        }
    }

    void reproducir() {
        clip.start();
    }

    void bucle() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    void parar() {
        clip.stop();
    }
}