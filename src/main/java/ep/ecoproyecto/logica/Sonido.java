package ep.ecoproyecto.logica;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Asigna y permite la reproduccion de efectos de sonido y musica
 * @author C-A-F
 */

public class Sonido {
    //los archivos deben ser WAP de 16bits
    Clip clip;
    URL sonidoURL[]= new URL[30];
    FloatControl fc;
    int sonidoanterior, escalaVolumen = 3;
    float volumen;
    /**
     * Inicializa los archivos para su uso
     */
    public Sonido(){
        sonidoURL[0]=getClass().getResource("/musica/paseo.wav");
        sonidoURL[1]=getClass().getResource("/musica/tiendajazz.wav");
        sonidoURL[2]=getClass().getResource("/efectos/moneda.wav");
        sonidoURL[3]=getClass().getResource("/efectos/caminar.wav");
        sonidoURL[4]=getClass().getResource("/efectos/equipo.wav");
        sonidoURL[5]=getClass().getResource("/efectos/powerup.wav");
        sonidoURL[6]=getClass().getResource("/efectos/Recoger.wav");
        sonidoURL[7]=getClass().getResource("/efectos/Select.wav");
        sonidoURL[8]=getClass().getResource("/efectos/Victoria.wav");
    }
    /**
     * detiene un clip e audio
     */
    public void detenerMusica(){
        if(clip!=null && clip.isRunning())
            clip.stop();
    }
    /**
     * reproduce una cancion especificada y devuelve
     * @param i indice de la cancion
     * @return cancion cambiada
     */
    public int musicatienda(int i) {
        int actual=9999;
        try{
            if(i!=1){
                clip.stop();
                AudioInputStream ais= AudioSystem.getAudioInputStream(sonidoURL[1]);
                clip= AudioSystem.getClip();
                clip.open(ais);
                fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                chequearVolumen();
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                sonidoanterior=i;
                actual= 1;
            }else {
                clip.stop();
                AudioInputStream ais= AudioSystem.getAudioInputStream(sonidoURL[sonidoanterior]);
                clip= AudioSystem.getClip();
                clip.open(ais);
                fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                chequearVolumen();
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                actual= sonidoanterior;
            }
        }catch(Exception e){}
        return actual;
    }
    /**
     * reproduce un archivo de musica
     * @param i indice de el archivo
     */
    public void reproducirmusica(int i) {
        try{
            AudioInputStream ais= AudioSystem.getAudioInputStream(sonidoURL[i]);
            clip= AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            chequearVolumen();
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(Exception e){}
    }
    /**
     * reproduce un efecto de sonido
     * @param i indice del archivo
     */
    public void reproducirefecto(int i) {
        try{
            AudioInputStream ais= AudioSystem.getAudioInputStream(sonidoURL[i]);
            clip= AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            chequearVolumen();
            clip.start();
        }catch(Exception e){}
    }
    /**
     * cambia el volumen segun corresponsa
     */
    public void chequearVolumen(){
        switch(escalaVolumen){
            case 0-> volumen = -80f;
            case 1-> volumen = -20f;
            case 2-> volumen = -12f;
            case 3-> volumen = -5f;
            case 4-> volumen = 1f;
            case 5-> volumen = 6f;
        }
        fc.setValue(volumen);
    }
    
  
}
