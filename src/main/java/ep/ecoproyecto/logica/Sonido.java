package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author C-A-F
 */
public class Sonido {
    //los archivos deben ser WAP de 16bits
    Clip clip;
    URL sonidoURL[]= new URL[30];
    int sonidoanterior;
    
    public Sonido(){
        
        sonidoURL[0]=getClass().getResource("/musica/paseo.wav");
        sonidoURL[1]=getClass().getResource("/musica/tiendajazz.wav");
        sonidoURL[2]=getClass().getResource("/efectos/moneda.wav");
        sonidoURL[3]=getClass().getResource("/efectos/caminar.wav");
        sonidoURL[4]=getClass().getResource("/efectos/equipo.wav");
        sonidoURL[5]=getClass().getResource("/efectos/powerup.wav");
    }
    
    /*
    public void establecerArchivo(int i) {
        try{
            AudioInputStream ais= AudioSystem.getAudioInputStream(sonidoURL[i]);
            clip= AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(Exception e){
        }
    }
    */
    
    public int musicatienda(int i) {
        int actual=9999;
        try{
            if(i!=1){
                clip.stop();
                AudioInputStream ais= AudioSystem.getAudioInputStream(sonidoURL[1]);
                clip= AudioSystem.getClip();
                clip.open(ais);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                sonidoanterior=i;
                actual= 1;
            }else {
                clip.stop();
                AudioInputStream ais= AudioSystem.getAudioInputStream(sonidoURL[sonidoanterior]);
                clip= AudioSystem.getClip();
                clip.open(ais);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                actual= sonidoanterior;
            }
        }catch(Exception e){}
        return actual;
    }
    
    public void reproducirmusica(int i) {
            try{
                    AudioInputStream ais= AudioSystem.getAudioInputStream(sonidoURL[i]);
                    clip= AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
            }catch(Exception e){}
    }
    
    public void reproducirefecto(int i) {
        try{
            AudioInputStream ais= AudioSystem.getAudioInputStream(sonidoURL[i]);
            clip= AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }catch(Exception e){}
    }
    
    
    /*
    public void reproducir() {
        clip.start();
    }

    public void bucle() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void parar() {
        clip.stop();
    }*/
    
}
