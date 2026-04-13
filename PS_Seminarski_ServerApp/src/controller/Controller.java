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
import so.SOKreirajCitaoca;
import so.SOLogin;
import so.SOObrisiCitaoca;
import so.SOPretraziCitaoca;
import so.SOPretraziKnjigu;
import so.SOPromeniCitaoca;
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

    public Response kreirajCitaoca(Request request) throws Exception {
        try {
            Citalac citalac = (Citalac) request.getParam();
            
            SOKreirajCitaoca kreirajCitaoca = new SOKreirajCitaoca();
            kreirajCitaoca.execute(citalac);
            int id = kreirajCitaoca.getId();
            
            if(id != -1){
                return new Response(id, "Citalac je uspesno kreiran");
            } else {
                return new Response(id, "Citalac nije uspesno kreiran");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Response(-1, "Greska prilikom kreiranja citaoca");
        }
    }

    public Response pretraziCitaoca(Request request) throws Exception {
        try {
            Citalac citalac = (Citalac) request.getParam();
            List<Citalac> lista = new ArrayList<>();
            
            SOPretraziCitaoca pretraziCitaoca = new SOPretraziCitaoca();
            pretraziCitaoca.execute(citalac);
            lista = pretraziCitaoca.getListaCitaoca();
            
            Response response = new Response(lista, "Filtrirani citaoci");
            return response;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Response(null, "Greska prilikom pretrazivanja citaoca");
        }
    }

    public Response obrisiCitaoca(Request request) throws Exception {
        try {
            Citalac citalac = (Citalac) request.getParam();
            
            SOObrisiCitaoca obrisiCitaoca = new SOObrisiCitaoca();
            obrisiCitaoca.execute(citalac);
            int rowsAffected = obrisiCitaoca.getRowsAffected();
            
            Response response;
            if(rowsAffected != 0){
                return new Response(rowsAffected, "Citalac uspesno obrisan");
            } else {
                return new Response(rowsAffected, "Citalac nije uspesno obrisan");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Response(-1, "Greska prilikom brisanja citaoca");
        }
    }

    public Response promeniCitaoca(Request request) throws Exception {
        try {
            Citalac citalac = (Citalac) request.getParam();
            
            SOPromeniCitaoca promeniCitaoca = new SOPromeniCitaoca();
            promeniCitaoca.execute(citalac);
            int rowsAffected = promeniCitaoca.getRowsAffected();
            
            Response response;
            if(rowsAffected != 0){
                return new Response(rowsAffected, "Uspesno promenjen citalac");
            } else {
                return new Response(rowsAffected, "Neuspesno promenjen citalac");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Response(0, "Greska prilikom izmene citaoca");
        }
    }

    public Response pretraziKnjigu(Request request) throws Exception {
        try {
            Knjiga filter = (Knjiga) request.getParam();
            
            SOPretraziKnjigu pretraziKnjigu = new SOPretraziKnjigu();
            pretraziKnjigu.execute(filter);
            List<Knjiga> listaKnjiga = pretraziKnjigu.getListaKnjiga();
            
            return new Response(listaKnjiga, "Vracena lista knjiga");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Response(null, "Greska prilikom pretrage knjiga");
        }
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
