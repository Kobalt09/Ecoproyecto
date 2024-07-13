package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Carga la configuracion de la ultima partida
 * @author C-A-F
 */
public class Configuracion {
    
    private final PanelJuego pJuego;

    public Configuracion(PanelJuego pJuego) {
        this.pJuego = pJuego;
    }
    
    public void guardarConfig(){
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"))) {
                //PANTALLA COMPLETA//
                if (pJuego.pantallaCompleta)
                    bw.write("True");
                else
                    bw.write("False");
                bw.newLine();
                
                //MUSICA VOLUMEN//
                bw.write(String.valueOf(pJuego.controlmusica.escalaVolumen));
                bw.newLine();
                
                //EFECTOS DE SONIDO VOLUMEN//
                bw.write(String.valueOf(pJuego.controlsonido.escalaVolumen));
                bw.newLine();
            }
            
        } catch (IOException ex) {
        }
    }
    
    public void cargarConfig(){
        try {
            try (BufferedReader br = new BufferedReader(new FileReader("config.txt"))) {
                String s = br.readLine();
                
                //PANTALLA COMPLETA//
                if (s.equals("True"))
                    pJuego.pantallaCompleta = true;
                else if(s.equals("False"))
                    pJuego.pantallaCompleta = false;
                
                //MUSICA VOLUMEN//
                s = br.readLine();
                pJuego.controlmusica.escalaVolumen = Integer.parseInt(s);
                
                //EFECTOS DE SONIDO VOLUMEN//
                s = br.readLine();
                pJuego.controlsonido.escalaVolumen = Integer.parseInt(s);
            }
        } catch (Exception e) {
        }
    }
}
