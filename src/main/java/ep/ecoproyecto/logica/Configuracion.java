package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author C-A-F
 */
public class Configuracion {
    
    private final PanelJuego gp;

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
                bw.write(String.valueOf(gp.controlsonido.escalaVolumen));
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
                gp.controlsonido.escalaVolumen = Integer.parseInt(s);
            }
        } catch (Exception e) {
        }
    }
}
