/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.net.packets;

import ep.ecoproyecto.logica.net.Cliente;
import ep.ecoproyecto.logica.net.Servidor;

/**
 *
 * @author C-A-F
 */
public class Paquete03Mapa extends Paquete {
    private String username;
    private int mapa;

    public Paquete03Mapa(byte[] data) {
        super(03);
        String[] datos=readData(data).split(",");
        this.mapa=Integer.parseInt(datos[0]);
        this.username=datos[1];
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Paquete03Mapa(int mapa,String username) {
        super(03);
        this.mapa = mapa;
        this.username=username;
    }
    
    public int getMapa() {
        return mapa;
    }

    public void setMapa(int mapa) {
        this.mapa = mapa;
    }

    @Override
    public void writeData(Cliente cliente) {
        cliente.enviarData(getData());
    }

    @Override
    public void writeData(Servidor server) {
        server.enviarDataClientes(getData());
    }

    @Override
    public byte[] getData() {
          return ("03" + mapa+","+username).getBytes();
    }
    
    
    
}
