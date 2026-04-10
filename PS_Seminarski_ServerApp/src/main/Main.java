/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import threads.ServerThread;

/**
 *
 * @author lukaa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerThread server = new ServerThread();
        server.start();
    }
    
}
