package ep.ecoproyecto.logica;

import ep.ecoproyecto.gui.PanelJuego;
import ep.ecoproyecto.logica.net.packets.Paquete01Desconectar;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Se usa para ver si se cerró el panel con el que se trabajo
 * @author C-A-F
 */

public class WindowHandler implements WindowListener{
    
    private final PanelJuego pJuego;

    public WindowHandler(PanelJuego pJuego) {
        this.pJuego = pJuego;
        this.pJuego.frame.addWindowListener(this);
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
    }

    /**
     * Escribe un paquete de desconectar cuando se cierra la ventana
     * @param e notificador de evento
     */
    @Override
    public void windowClosing(WindowEvent e) {
        Paquete01Desconectar packet = new Paquete01Desconectar(this.pJuego.jugador.getUsername());
        packet.writeData(this.pJuego.socketCliente);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
}
