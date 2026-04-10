/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import database.DatabaseBroker;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bibliotekar;
import model.Citalac;
import model.Iznajmljivanje;
import model.Kategorija;
import model.KategorijaCitaoca;
import model.Knjiga;
import so.AbstractSO;
import so.SOLogin;
import so.SOVratiListuSveKategorijeCitaoca;

/**
 *
 * @author lukaa
 */
public class Controller {
    private static Controller instance;
    private DatabaseBroker dbb;
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    private Controller(){
        dbb = new DatabaseBroker();
    }

    public Response prijaviBibliotekara(Request request) throws Exception {
        try {
            Bibliotekar bibliotekar = (Bibliotekar) request.getParam();
            
            SOLogin login = new SOLogin();
            login.execute(bibliotekar);
            Bibliotekar ulogovani = login.getUlogovani();
            
            String poruka;
            if(ulogovani != null){
                poruka = "Bibliotekar uspesno prijavljen";
            } else {
                poruka = "Bibliotekar nije uspesno prijavljen";
            }
            
            return new Response(ulogovani, poruka);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Response(null, "Greska pri login operaciji");
        }
    }

    public Response vratiSveKategorije(Request request) throws Exception {
        try {
            List<KategorijaCitaoca> listaKategorija = new ArrayList<>();
            
            SOVratiListuSveKategorijeCitaoca vratiSveKategorijeCitaoca = new SOVratiListuSveKategorijeCitaoca();
            vratiSveKategorijeCitaoca.execute(null);
            listaKategorija = vratiSveKategorijeCitaoca.getListaKategorija();
            
            Response response = new Response(listaKategorija, "Lista svih kategorija");
            return response;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Response(null, "Greska pri vratiSveKategorije operaciji");
        }
    }

    public Response kreirajCitaoca(Request request) {
        try {
            Citalac citalac = (Citalac) request.getParam();
            
            dbb.connect();
            boolean uspesnoDodat = dbb.kreirajCitaoca(citalac);
            dbb.commit();
            dbb.disconnect();
            
            Response response = new Response(uspesnoDodat, "Citalac uspesno dodat");
            return response;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Response pretraziCitaoca(Request request) {
        try {
            Citalac citalac = (Citalac) request.getParam();
            
            dbb.connect();
            List<Citalac> lista = dbb.pretraziCitaoca(citalac);
            dbb.disconnect();
            
            Response response = new Response(lista, "Filtrirani citaoci");
            return response;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Response obrisiCitaoca(Request request) {
        try {
            Citalac citalac = (Citalac) request.getParam();
            
            dbb.connect();
            boolean obrisan = dbb.obrisiCitaoca(citalac);
            dbb.commit();
            dbb.disconnect();
            
            Response response;
            if(obrisan){
                response = new Response(true, "Citalac uspesno obrisan");
            } else {
                response = new Response(false, "Citalac nije uspesno obrisan");
            }
            
            return response;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Response promeniCitaoca(Request request) {
        try {
            Citalac citalac = (Citalac) request.getParam();
            
            dbb.connect();
            boolean uspesnoPromenjen = dbb.promeniCitaoca(citalac);
            dbb.commit();
            dbb.disconnect();
            
            Response response;
            if(uspesnoPromenjen){
                response = new Response(true, "Uspesno promenjen citalac");
            } else {
                response = new Response(false, "Neuspesno promenjen citalac");
            }
            
            return response;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Response pretraziKnjigu(Request request) {
        try {
            Knjiga filter = (Knjiga) request.getParam();
            
            dbb.connect();
            List<Knjiga> listaKnjiga = dbb.pretraziKnjigu(filter);
            dbb.disconnect();
            
            Response response = new Response(listaKnjiga, "Vracena lista knjiga");
            return response;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Response kreirajIznajmljivanje(Request request) {
        System.out.println("STIGLA OPERACIJA: " + request.getOp());
        try {
            Iznajmljivanje iznajmljivanje = (Iznajmljivanje) request.getParam();
            
            dbb.connect();
            boolean uspeh = false;
            
            uspeh = dbb.kreirajIznajmljivanje(iznajmljivanje);
            
            dbb.commit();
            dbb.disconnect();
            
            return new Response(uspeh, null);
        } catch (Exception ex) {
            try {
                dbb.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return new Response(false, "Greska pri kreiranju iznajmljivanja");
        }
    }
}
