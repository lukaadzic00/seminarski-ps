/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Operacija;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bibliotekar;
import model.Citalac;
import model.Iznajmljivanje;
import model.Kategorija;
import model.KategorijaCitaoca;
import model.Knjiga;

/**
 *
 * @author lukaa
 */
public class Controller {
    private static Controller instance;
    private Sender sender;
    private Receiver receiver;
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    private Controller(){
        try {
            Socket socket = new Socket("localhost", 9000);
            System.out.println("Klijent se povezao na server");
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Bibliotekar prijaviBibliotekara(Bibliotekar bibliotekar) {
        Request request = new Request(Operacija.PRIJAVI_BIBLIOTEKARA, bibliotekar);
        try {
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            bibliotekar = (Bibliotekar) response.getRezultat();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bibliotekar;
    }

    public List<KategorijaCitaoca> vratiSveKategorije() {
        try {
            Request request = new Request(Operacija.VRATI_SVE_KATEGORIJE, null);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<KategorijaCitaoca>) response.getRezultat();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean kreirajCitaoca(Citalac citalac) {
        try {
            Request request = new Request(Operacija.KREIRAJ_CITAOCA, citalac);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            int id = (int) response.getRezultat();
            if(id != -1){
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Citalac> pretraziCitaoca(Citalac citalac) {
        try {
            Request request = new Request(Operacija.PRETRAZI_CITAOCA, citalac);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<Citalac>) response.getRezultat();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int obrisiCitaoca(Citalac citalac) {
        try {
            Request request = new Request(Operacija.OBRISI_CITAOCA, citalac);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (int) response.getRezultat();
        } catch (Exception ex) {
            return 0;
        }
    }

    public int promeniCitaoca(Citalac citalac) {
        try {
            Request request = new Request(Operacija.PROMENI_CITAOCA, citalac);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (int) response.getRezultat();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Knjiga> pretraziKnjigu(Knjiga filter) {
        try {
            Request request = new Request(Operacija.PRETRAZI_KNJIGU, filter);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<Knjiga>) response.getRezultat();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int kreirajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        try {
            Request request = new Request(Operacija.KREIRAJ_IZNAJMLJIVANJE, iznajmljivanje);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (int) response.getRezultat();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public List<Citalac> vratiSveCitaoce() {
        try {
            Request request = new Request(Operacija.VRATI_SVE_CITAOCE, null);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<Citalac>) response.getRezultat();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Bibliotekar> vratiSveBibliotekare() {
        try {
            Request request = new Request(Operacija.VRATI_SVE_BIBLIOTEKARE, null);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<Bibliotekar>) response.getRezultat();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Iznajmljivanje> pretraziIznajmljivanje(Iznajmljivanje filterIzn) {
        try {
            Request request = new Request(Operacija.PRETRAZI_IZNAJMLJIVANJE, filterIzn);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<Iznajmljivanje>) response.getRezultat();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
