/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import threads.ServerThread;

/**
 *
 * @author lukaa
 */
public class ServerManager {
    
    private ServerThread serverThread;
    
    public ServerThread getServerThread() {
        return serverThread;
    }

    public void startServer(int maxKlijenata) throws Exception {
        serverThread = new ServerThread(maxKlijenata);
        serverThread.start();
    }

    public void stopServer() {
        serverThread.stopServer();
    }
}