/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author lukaa
 */
public class ServerState {
    private static ServerState instance;
    private int maxKlijenata;
    private int active;
    
    public static ServerState getInstance() {
        if (instance == null) {
            instance = new ServerState();
        }
        return instance;
    }

    private ServerState() {}

    public synchronized boolean canAccept() {
        return active < maxKlijenata;
    }

    public synchronized void clientConnected() {
        active++;
    }

    public synchronized void clientDisconnected() {
        active--;
    }

    public int getMaxKlijenata() {
        return maxKlijenata;
    }

    public void setMaxKlijenata(int maxKlijenata) {
        this.maxKlijenata = maxKlijenata;
    }
}