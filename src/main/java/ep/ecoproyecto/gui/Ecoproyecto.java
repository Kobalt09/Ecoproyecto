package ep.ecoproyecto.gui;
import javax.swing.ImageIcon;
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
        ventana.setIconImage((new ImageIcon(getClass().getResource("/player/jg_abj_01.png"))).getImage());
     
        PanelJuego panelDeJuego= new PanelJuego(ventana);
        ventana.add(panelDeJuego); 

        panelDeJuego.config.cargarConfig();
        if (panelDeJuego.pantallaCompleta){
            ventana.setUndecorated(true);
        }
        
        ventana.pack();
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
        panelDeJuego.configuraciondejuego();
        
        panelDeJuego.startGameThread();
    }
}
