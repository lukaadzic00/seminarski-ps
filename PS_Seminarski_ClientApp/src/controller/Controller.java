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
import model.RadnaSmena;
import model.StavkaIznajmljivanja;

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
        try {
            Request request = new Request(Operacija.PRIJAVI_BIBLIOTEKARA, bibliotekar);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (Bibliotekar) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji prijavi_bibliotekara: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public List<KategorijaCitaoca> vratiSveKategorije() {
        try {
            Request request = new Request(Operacija.VRATI_SVE_KATEGORIJE, null);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<KategorijaCitaoca>) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji vrati_sve_kategorije: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public int kreirajCitaoca(Citalac citalac) {
        try {
            Request request = new Request(Operacija.KREIRAJ_CITAOCA, citalac);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (int) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji kreiraj_citaoca: " + ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }

    public List<Citalac> pretraziCitaoca(Citalac citalac) {
        try {
            Request request = new Request(Operacija.PRETRAZI_CITAOCA, citalac);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<Citalac>) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji pretrazi_citaoca po filteru: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public int obrisiCitaoca(Citalac citalac) {
        try {
            Request request = new Request(Operacija.OBRISI_CITAOCA, citalac);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (int) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji obrisi_citaoca: " + ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }

    public int promeniCitaoca(Citalac citalac) {
        try {
            Request request = new Request(Operacija.PROMENI_CITAOCA, citalac);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (int) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji promeni_citaoca: " + ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }

    public List<Knjiga> pretraziKnjigu(Knjiga filter) {
        try {
            Request request = new Request(Operacija.PRETRAZI_KNJIGU, filter);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<Knjiga>) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji pretrazi_knjigu po filteru: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public int kreirajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        try {
            Request request = new Request(Operacija.KREIRAJ_IZNAJMLJIVANJE, iznajmljivanje);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (int) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji kreiraj_iznajmljivanje: " + ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }

    public List<Citalac> vratiSveCitaoce() {
        try {
            Request request = new Request(Operacija.VRATI_SVE_CITAOCE, null);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<Citalac>) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji vrati_sve_citaoce: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public List<Bibliotekar> vratiSveBibliotekare() {
        try {
            Request request = new Request(Operacija.VRATI_SVE_BIBLIOTEKARE, null);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<Bibliotekar>) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji vrati_sve_bibliotekare: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public List<Iznajmljivanje> pretraziIznajmljivanje(Iznajmljivanje filterIzn) {
        try {
            Request request = new Request(Operacija.PRETRAZI_IZNAJMLJIVANJE, filterIzn);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<Iznajmljivanje>) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji pretrazi_iznajmljivanje po filteru: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public List<StavkaIznajmljivanja> vratiSveStavkeIznajmljivanja(Iznajmljivanje iznajmljivanje) {
        try {
            Request request = new Request(Operacija.VRATI_SVE_STAVKE_IZNAJMLJIVANJA, iznajmljivanje);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<StavkaIznajmljivanja>) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji vrati_sve_stavke_iznajmljivanja: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public boolean promeniIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        try {
            Request request = new Request(Operacija.PROMENI_IZNAJMLJIVANJE, iznajmljivanje);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (boolean) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji promeni_iznajmljivanje: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public int obrisiStavku(StavkaIznajmljivanja selektovanaStavka) {
        try {
            Request request = new Request(Operacija.OBRISI_STAVKU, selektovanaStavka);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (int) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji obrisi_stavku: " + ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }

    public int promeniStavku(StavkaIznajmljivanja stavka) {
        try {
            Request request = new Request(Operacija.PROMENI_STAVKU, stavka);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (int) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji promeni_stavku: " + ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }

    public int dodajStavke(Iznajmljivanje iznajmljivanje) {
        try {
            Request request = new Request(Operacija.DODAJ_STAVKE, iznajmljivanje);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (int) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji dodaj_stavke: " + ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }

    public int ubaciRadnuSmenu(RadnaSmena radnaSmena) {
        try {
            Request request = new Request(Operacija.UBACI_RADNU_SMENU, radnaSmena);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (int) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji ubaci_radnu_smenu: " + ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }

    public List<Knjiga> vratiSveKnjige() {
        try {
            Request request = new Request(Operacija.VRATI_SVE_KNJIGE, null);
            sender.send(request);
            
            Response response = (Response) receiver.receive();
            return (List<Knjiga>) response.getRezultat();
        } catch (Exception ex) {
            System.out.println("[CLIENT - Controller] Greška pri operaciji vrati_sve_knjige: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}