/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
//
package ep.ecoproyecto;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class Ecoproyecto {

    public static void main(String[] args) {
        JFrame ventana= new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("ECOPROYECTO");
     
        PanelJuego panelDeJuego= new PanelJuego();
        ventana.add(panelDeJuego);
        
        ventana.pack();
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
        panelDeJuego.startGameThread();
    }
}
