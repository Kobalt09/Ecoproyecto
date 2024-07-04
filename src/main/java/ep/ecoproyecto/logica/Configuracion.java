package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author C-A-F
 */
public class Configuracion {
    
    PanelJuego gp;

    public Configuracion(PanelJuego gp) {
        this.gp = gp;
    }
    
    public void guardarConfig(){
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"))) {
                //PANTALLA COMPLETA//
                if (gp.pantallaCompleta)
                    bw.write("True");
                else
                    bw.write("False");
                bw.newLine();
                
                //MUSICA VOLUMEN//
                bw.write(String.valueOf(gp.controlmusica.escalaVolumen));
                bw.newLine();
                
                //EFECTOS DE SONIDO VOLUMEN//
                bw.write(String.valueOf(gp.efectossonido.escalaVolumen));
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
                    gp.pantallaCompleta = true;
                else if(s.equals("False"))
                    gp.pantallaCompleta = false;
                
                //MUSICA VOLUMEN//
                s = br.readLine();
                gp.controlmusica.escalaVolumen = Integer.parseInt(s);
                
                //EFECTOS DE SONIDO VOLUMEN//
                s = br.readLine();
                gp.efectossonido.escalaVolumen = Integer.parseInt(s);
            }
        } catch (Exception e) {
        }
    }
}
