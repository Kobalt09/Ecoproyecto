package ep.ecoproyecto.gui;
import javax.swing.JFrame;

/**
 *
 * @author C-A-F
 */
public class Ecoproyecto {
    
    public void iniciarJuego() {
        JFrame ventana= new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("ECOPROYECTO");
     
        PanelJuego panelDeJuego= new PanelJuego(ventana);
        ventana.add(panelDeJuego);        
        ventana.pack();
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
        panelDeJuego.configuraciondejuego();
        panelDeJuego.startGameThread();
    }
}
