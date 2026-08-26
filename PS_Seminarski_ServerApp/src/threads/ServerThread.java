/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import database.DatabaseBroker;

/**
 *
 * @author lukaa
 */
public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private volatile boolean running = true;
    private int maxBrojKlijenata;
    private List<HandleClient> klijenti = new java.util.concurrent.CopyOnWriteArrayList<>();
    
    public ServerThread(int maxBrojKlijenata) throws Exception {
        this.maxBrojKlijenata = maxBrojKlijenata;
        // connect radimo samo kao proveru, da li moze da se poveze sa trenutnom konfiguracijom
        DatabaseBroker dbb = DatabaseBroker.getInstance();
        dbb.connect();
        dbb.disconnect();
        
        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Server je pokrenut.");
        } catch (IOException ex) {
            throw new Exception("Server je vec pokrenut.");
        }
    }

    public int getMaxBrojKlijenata() {
        return maxBrojKlijenata;
    }
    
    public ServerSocket getServerskiSoket() {
        return serverSocket;
    }

    @Override
    public void run() {
        while(running){
            try {
                Socket socket = serverSocket.accept();
                // limit klijenata
                /*if(klijenti.size() >= maxBrojKlijenata){
                    System.out.println("Server je pun.");
                    socket.close();
                    continue;
                }*/

                HandleClient clientThread = new HandleClient(socket, this);
                klijenti.add(clientThread);
                clientThread.start();

            } catch (IOException ex) {
                if(running){
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void stopServer() {
        running = false;
        // zatvori socket (prekida accept loop)
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ugasi klijente
        for (HandleClient c : klijenti) {
            c.stopClient();
        }
        
        klijenti.clear();
        System.out.println("Server je ugasen.");
    }
    
    public void ukloniKlijenta(HandleClient klijent){
        klijenti.remove(klijent);
    }
}