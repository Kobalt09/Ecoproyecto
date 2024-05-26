
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hp
 */
public class Mandar implements Observer{
    
    
    public static void main (String[] args){

    Cliente c=new Cliente(5000,"huevo");
    Thread t=new Thread(c);
    t.run();
    }

    public Mandar() {
        
    Servidor s=new Servidor(6000);
    s.addObserver(this);
    Thread t= new Thread();
    t.start();
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        
    }


}
