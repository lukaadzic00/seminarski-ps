/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukaa
 */
public class ServerThread extends Thread {
    private ServerSocket serverSocket;

    public ServerThread() {
        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Server je pokrenut.");
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket socket = serverSocket.accept();
                
                HandleClient clientThread = new HandleClient(socket);
                clientThread.start();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
