
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
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
public class Servidor extends Observable implements Runnable{
    
    private int puerto;
    public Servidor(int puerto){
        this.puerto=puerto;
    }    
    
    
    @Override
    public void run() {
        ServerSocket servidor = null;
        Socket sc =null;
        DataInputStream in;
        DataOutputStream out;
       
   
        try {
            servidor=new ServerSocket(puerto);
            System.out.println("Servidor iniciado");
            
            while (true){
                
                sc = servidor.accept();
                
                System.out.println("cliente conectado");
                in= new DataInputStream(sc.getInputStream());
                
                
                String mensaje =in.readUTF();
                
                System.out.println(mensaje);
                
                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();
                
                sc.close();
                System.out.println("cliente desconectado");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
        

