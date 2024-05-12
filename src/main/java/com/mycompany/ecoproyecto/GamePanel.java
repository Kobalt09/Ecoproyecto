/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecoproyecto;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class GamePanel extends JPanel implements Runnable{
    //configuracion de pantalla
    
    final int originalTileSize= 32; //dimenciones por defecto del jugador, NPC o mapa title 32x32
    final int scale = 2; //escala los sprites de 32x32 a 64x64
    
    final int tileSize= originalTileSize *scale; //64x64 tlie
    final int maxScreenCol= 16;
    final int maxScreenRow = 12;
    final int screenWidth=tileSize *maxScreenCol; // 1024
    final int screenHeight=tileSize *maxScreenRow; // 768
    
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
    
    public void startGameTrhread(){
        gameThread = new Thread(this);
        gameThread.start();
        
    }

    @Override
    public void run() {
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
