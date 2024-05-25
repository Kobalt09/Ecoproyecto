
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hp
 */
public class Cliente implements Runnable {
    private int puerto;
    private String mensaje;

    public Cliente(int puerto, String mensaje) {
        this.puerto = puerto;
        this.mensaje = mensaje;
    }
    
   
    @Override
    public void run() {
        
    final String HOST="2620:9b::193e:cc9f";
    DataInputStream in;
    DataOutputStream out;
        
        try {
            Socket sc = new Socket(HOST, puerto);
            
            
            out=new DataOutputStream(sc.getOutputStream());
            
            out.writeUTF(mensaje);
            
            sc.close();
            
                
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
