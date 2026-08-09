/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communication.Operacija;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import controller.Controller;
import server.ServerState;
import java.io.EOFException;
import java.net.SocketException;
import model.Bibliotekar;
/**
 *
 * @author lukaa
 */
public class HandleClient extends Thread {
    private Sender sender;
    private Receiver receiver;
    private ServerThread serverThread;
    private volatile boolean running = true;
    private String loggedUsername;

    public HandleClient(Socket socket, ServerThread serverThread) {
        try {
            this.serverThread = serverThread;
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (IOException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try{
            while(running){
            
                // primi zahtev
                Request request = (Request) receiver.receive();

                // obradi zahtev
                Response response = null;
                if(request.getOp() == Operacija.PRIJAVI_BIBLIOTEKARA){
                    response = Controller.getInstance().prijaviBibliotekara(request);
                    Bibliotekar b = (Bibliotekar) response.getRezultat();
                    if (b != null) {
                        loggedUsername = b.getKorisnickoIme();
                    }
                } else if(request.getOp() == Operacija.VRATI_SVE_KATEGORIJE){
                    response = Controller.getInstance().vratiSveKategorije(request);
                } else if(request.getOp() == Operacija.KREIRAJ_CITAOCA){
                    response = Controller.getInstance().kreirajCitaoca(request);
                } else if(request.getOp() == Operacija.PRETRAZI_CITAOCA){
                    response = Controller.getInstance().pretraziCitaoca(request);
                } else if(request.getOp() == Operacija.OBRISI_CITAOCA){
                    response = Controller.getInstance().obrisiCitaoca(request);
                } else if(request.getOp() == Operacija.PROMENI_CITAOCA){
                    response = Controller.getInstance().promeniCitaoca(request);
                } else if(request.getOp() == Operacija.PRETRAZI_KNJIGU){
                    response = Controller.getInstance().pretraziKnjigu(request);
                } else if(request.getOp() == Operacija.KREIRAJ_IZNAJMLJIVANJE){
                    response = Controller.getInstance().kreirajIznajmljivanje(request);
                } else if(request.getOp() == Operacija.VRATI_SVE_CITAOCE){
                    response = Controller.getInstance().vratiSveCitaoce(request);
                } else if(request.getOp() == Operacija.VRATI_SVE_BIBLIOTEKARE){
                    response = Controller.getInstance().vratiSveBibliotekare(request);
                } else if(request.getOp() == Operacija.PRETRAZI_IZNAJMLJIVANJE){
                    response = Controller.getInstance().pretraziIznajmljivanje(request);
                } else if(request.getOp() == Operacija.VRATI_SVE_STAVKE_IZNAJMLJIVANJA){
                    response = Controller.getInstance().vratiSveStavkeIznajmljivanja(request);
                } else if(request.getOp() == Operacija.PROMENI_IZNAJMLJIVANJE){
                    response = Controller.getInstance().promeniIznajmljivanje(request);
                } else if(request.getOp() == Operacija.UBACI_RADNU_SMENU){
                    response = Controller.getInstance().ubaciRadnuSmenu(request);
                } else if(request.getOp() == Operacija.VRATI_SVE_KNJIGE){
                    response = Controller.getInstance().vratiSveKnjige(request);
                } else if(request.getOp() == Operacija.ODJAVI_BIBLIOTEKARA){
                    response = Controller.getInstance().odjaviBibliotekara(request);
                } else if(request.getOp() == Operacija.VRATI_SVE_AUTORE){
                    response = Controller.getInstance().vratiSveAutore(request);
                }

                // posalji odgovor
                sender.send(response);
            }
        } catch (SocketException ex) {
            System.out.println("Klijent je prekinuo konekciju.");
        } catch (EOFException ex) {
            System.out.println("Klijent je zatvorio aplikaciju.");
        } catch (Exception ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            stopClient();
            serverThread.ukloniKlijenta(this);
            ServerState.getInstance().clientDisconnected();
            
            if (loggedUsername != null) {
                Controller.getInstance().odjavi(loggedUsername);
            }
        }
    }
    
    public void stopClient() {
        try {
            running = false;
            receiver.close();
            sender.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
