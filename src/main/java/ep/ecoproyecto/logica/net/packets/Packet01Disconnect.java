/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ep.ecoproyecto.logica.net.packets;

import ep.ecoproyecto.logica.net.Cliente;
import ep.ecoproyecto.logica.net.Server;

public class Packet01Disconnect extends Packet{
    
    private String username;
    
    public Packet01Disconnect(byte[] data) {
        super(01);
        this.username = readData(data);
    }

    public Packet01Disconnect(String username) {
        super(01);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void writeData(Cliente cliente) {
        cliente.enviarData(getData());
    }

    @Override
    public void writeData(Server server) {
        server.enviarDataClientes(getData());
    }

    @Override
    public byte[] getData() {
        return ("01" + this.username).getBytes();
    }
    
}
