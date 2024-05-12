/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ecoproyecto;

import javax.swing.JFrame;

/**
 *
 * @author Cris
 */
public class Ecoproyecto {

    public static void main(String[] args) {
        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Ecoproyecto");
        
        GamePanel gamepanel= new GamePanel();
        window.add(gamepanel);
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
