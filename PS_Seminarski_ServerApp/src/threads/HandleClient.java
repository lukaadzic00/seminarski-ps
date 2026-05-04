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
/**
 *
 * @author lukaa
 */
public class HandleClient extends Thread {
    private Sender sender;
    private Receiver receiver;

    public HandleClient(Socket socket) {
        try {
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (IOException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                // primi zahtev
                Request request = (Request) receiver.receive();
                
                // obradi zahtev
                Response response = null;
                if(request.getOp() == Operacija.PRIJAVI_BIBLIOTEKARA){
                    response = Controller.getInstance().prijaviBibliotekara(request);
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
                } else if(request.getOp() == Operacija.OBRISI_STAVKU){
                    response = Controller.getInstance().obrisiStavku(request);
                } else if(request.getOp() == Operacija.PROMENI_STAVKU){
                    response = Controller.getInstance().promeniStavku(request);
                } else if(request.getOp() == Operacija.DODAJ_STAVKE){
                    response = Controller.getInstance().dodajStavke(request);
                } else if(request.getOp() == Operacija.UBACI_RADNU_SMENU){
                    response = Controller.getInstance().ubaciRadnuSmenu(request);
                }
                
                // posalji odgovor
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
}
