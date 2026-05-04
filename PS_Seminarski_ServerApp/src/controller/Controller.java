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
import model.RadnaSmena;
import model.StavkaIznajmljivanja;
import so.AbstractSO;
import so.SODodajStavke;
import so.SOKreirajCitaoca;
import so.SOKreirajIznajmljivanje;
import so.SOLogin;
import so.SOObrisiCitaoca;
import so.SOObrisiStavku;
import so.SOPretraziCitaoca;
import so.SOPretraziIznajmljivanje;
import so.SOPretraziKnjigu;
import so.SOPromeniCitaoca;
import so.SOPromeniIznajmljivanje;
import so.SOPromeniStavku;
import so.SOUbaciRadnuSmenu;
import so.SOVratiListuSveKategorijeCitaoca;
import so.SOVratiListuSveStavkeIznajmljivanja;
import so.SOVratiListuSviBibliotekari;
import so.SOVratiListuSviCitaoci;

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

    public Response kreirajIznajmljivanje(Request request) throws Exception {
        try {
            Iznajmljivanje iznajmljivanje = (Iznajmljivanje) request.getParam();
            
            SOKreirajIznajmljivanje kreirajIznajmljivanje = new SOKreirajIznajmljivanje();
            kreirajIznajmljivanje.execute(iznajmljivanje);
            int id = kreirajIznajmljivanje.getId();
            
            if(id != -1){
                return new Response(id, "Iznajmljivanje je uspesno kreirano");
            } else {
                return new Response(id, "Iznajmljivanje nije uspesno kreirano");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Response(-1, "Greska pri kreiranju iznajmljivanja");
        }
    }

    public Response vratiSveCitaoce(Request request) throws Exception {
        try {
            List<Citalac> listaCitalaca = new ArrayList<>();
            Citalac citalac = new Citalac();

            SOVratiListuSviCitaoci vratiListuSviCitaoci = new SOVratiListuSviCitaoci();
            vratiListuSviCitaoci.execute(citalac);
            listaCitalaca = vratiListuSviCitaoci.getListaCitalaca();

            return new Response(listaCitalaca, "Lista svih citalaca");
        } catch(SQLException ex) {
            ex.printStackTrace();
            return new Response(null, "Greska prilikom izvrsenja operacije vrati sve citaoce");
        }
    }

    public Response vratiSveBibliotekare(Request request) throws Exception {
        try {
            List<Bibliotekar> listaBibliotekara = new ArrayList<>();
            Bibliotekar bibliotekar = new Bibliotekar();

            SOVratiListuSviBibliotekari vratiListuSviBibliotekari = new SOVratiListuSviBibliotekari();
            vratiListuSviBibliotekari.execute(bibliotekar);
            listaBibliotekara = vratiListuSviBibliotekari.getListaBibliotekara();

            return new Response(listaBibliotekara, "Lista svih bibliotekara");
        } catch(SQLException ex) {
            ex.printStackTrace();
            return new Response(null, "Greska prilikom izvrsenja operacije vrati sve bibliotekare");
        }
    }

    public Response pretraziIznajmljivanje(Request request) throws Exception {
        try{
            List<Iznajmljivanje> listaIznajmljivanja = new ArrayList<>();
            Iznajmljivanje iznajmljivanje = (Iznajmljivanje) request.getParam();

            SOPretraziIznajmljivanje pretraziIznajmljivanje = new SOPretraziIznajmljivanje();
            pretraziIznajmljivanje.execute(iznajmljivanje);
            listaIznajmljivanja = pretraziIznajmljivanje.getListaIznajmljivanja();

            return new Response(listaIznajmljivanja, "Lista iznajmljivanja za izabrani filter");
        } catch(SQLException ex){
            ex.printStackTrace();
            return new Response(null, "Greska prilikom izvrsenja operacije pretrazi iznajmljivanje");
        }
    }

    public Response vratiSveStavkeIznajmljivanja(Request request) throws Exception {
        try{
            List<StavkaIznajmljivanja> listaStavki = new ArrayList<>();
            Iznajmljivanje iznajmljivanje = (Iznajmljivanje) request.getParam();
            
            SOVratiListuSveStavkeIznajmljivanja vratiSveStavke = new SOVratiListuSveStavkeIznajmljivanja();
            vratiSveStavke.execute(iznajmljivanje);
            listaStavki = vratiSveStavke.getListaStavki();
            
            return new Response(listaStavki, "Lista stavki za izabrano iznajmljivanje");
        } catch(SQLException ex){
            ex.printStackTrace();
            return new Response(null, "Greska prilikom izvrsenja operacije vrati sve stavke iznajmljivanja");
        }
    }

    public Response promeniIznajmljivanje(Request request) throws Exception {
        try{
            Iznajmljivanje iznajmljivanje = (Iznajmljivanje) request.getParam();
            
            SOPromeniIznajmljivanje promeniIznajmljivanje = new SOPromeniIznajmljivanje();
            promeniIznajmljivanje.execute(iznajmljivanje);
            int affectedRows = promeniIznajmljivanje.getAffectedRows();
            
            if(affectedRows != 0){
                return new Response(affectedRows, "Izmenjen je citalac za izabrano iznajmljivanje");
            } else{
                return new Response(affectedRows, "Nije izmenjen citalac za izabrano iznajmljivanje");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
            return new Response(null, "Greska prilikom izvrsenja operacije promeni iznajmljivanje");
        }
    }

    public Response obrisiStavku(Request request) {
        try {
            StavkaIznajmljivanja stavka = (StavkaIznajmljivanja) request.getParam();
            
            SOObrisiStavku obrisiStavku = new SOObrisiStavku();
            obrisiStavku.execute(stavka);
            int affectedRows = obrisiStavku.getRowsAffected();
            
            if(affectedRows != 0){
                return new Response(affectedRows, "Stavka je uspesno obrisana");
            } else {
                return new Response(affectedRows, "Stavka nije uspesno obrisana");
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Response promeniStavku(Request request) {
        try {
            StavkaIznajmljivanja stavka = (StavkaIznajmljivanja) request.getParam();
            
            SOPromeniStavku promeniStavku = new SOPromeniStavku();
            promeniStavku.execute(stavka);
            int affectedRows = promeniStavku.getRowsAffected();
            
            if(affectedRows != 0){
                return new Response(affectedRows, "Stavka iznajmljivanja je uspesno promenjena");
            } else {
                return new Response(affectedRows, "Stavka iznajmljivanja nije uspesno promenjena");
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Response dodajStavke(Request request) {
        try {
            Iznajmljivanje iznajmljivanje = (Iznajmljivanje) request.getParam();
            
            SODodajStavke dodajStavke = new SODodajStavke();
            dodajStavke.execute(iznajmljivanje);
            int rowsAffected = dodajStavke.getRowsAffected();
            
            if(rowsAffected == iznajmljivanje.getListaStavki().size()){
                return new Response(rowsAffected, "Uspesno su dodate izabrane stavke");
            } else {
                return new Response(rowsAffected, "Nisu uspesno dodate izabrane stavke");
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Response ubaciRadnuSmenu(Request request) {
        try {
            RadnaSmena radnaSmena = (RadnaSmena) request.getParam();
            
            SOUbaciRadnuSmenu ubaciRadnuSmenu = new SOUbaciRadnuSmenu();
            ubaciRadnuSmenu.execute(radnaSmena);
            int generatedKey = ubaciRadnuSmenu.getGeneratedKey();
            
            if(generatedKey != -1){
                return new Response(generatedKey, "Radna smena je uspesno ubacena");
            } else {
                return new Response(generatedKey, "Radna smena nije uspesno ubacena");
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}