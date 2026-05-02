/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lukaa
 */
public class Iznajmljivanje extends AbstractDomainObject{
    private int id;
    private int brojKnjiga;
    private LocalDate datumUzimanja;
    private double ukupanIznos;
    private String valuta;
    private Bibliotekar bibliotekar;
    private Citalac citalac;
    private List<StavkaIznajmljivanja> listaStavki = new ArrayList<>();

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int id, int brojKnjiga, LocalDate datumUzimanja, double ukupanIznos, String valuta, Bibliotekar bibliotekar, Citalac citalac) {
        this.id = id;
        this.brojKnjiga = brojKnjiga;
        this.datumUzimanja = datumUzimanja;
        this.ukupanIznos = ukupanIznos;
        this.valuta = valuta;
        this.bibliotekar = bibliotekar;
        this.citalac = citalac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrojKnjiga() {
        return brojKnjiga;
    }

    public void setBrojKnjiga(int brojKnjiga) {
        this.brojKnjiga = brojKnjiga;
    }

    public LocalDate getDatumUzimanja() {
        return datumUzimanja;
    }

    public void setDatumUzimanja(LocalDate datumUzimanja) {
        this.datumUzimanja = datumUzimanja;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    public Citalac getCitalac() {
        return citalac;
    }

    public void setCitalac(Citalac citalac) {
        this.citalac = citalac;
    }

    public List<StavkaIznajmljivanja> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaIznajmljivanja> listaStavki) {
        this.listaStavki = listaStavki;
    }
    
    public void addStavka(StavkaIznajmljivanja stavka){
        listaStavki.add(stavka);
    }
    
    // Inherited 

    @Override
    public String tableName() {
        return "iznajmljivanje";
    }

    @Override
    public String alias() {
        return "i";
    }

    @Override
    public String insertColumns() {
        return "broj_knjiga, datum_uzimanja, ukupan_iznos, valuta, id_bibliotekar, id_citalac";
    }

    @Override
    public String insertValues() {
        return brojKnjiga + ", '" + java.sql.Date.valueOf(datumUzimanja) + "', " + ukupanIznos + ", '" + valuta + "', " + bibliotekar.getId() + ", " + citalac.getId();
    }

    @Override
    public String updateValues() {
        String upit = "";
        if(citalac != null){
            upit += "id_citalac = " + citalac.getId() + ", ";
        }
        if(brojKnjiga != 0){
            upit += "broj_knjiga = " + brojKnjiga + ", ";
        }
        if(ukupanIznos != 0){
            upit += "ukupan_iznos = " + ukupanIznos + ", ";
        }
        
        if(upit.endsWith(", ")){
            upit = upit.substring(0, upit.length() - 2);
        }
        return upit;
    }

    @Override
    public String textJoin() {
        return " JOIN citalac c ON i.id_citalac=c.id_citalac JOIN bibliotekar b ON i.id_bibliotekar=b.id_bibliotekar";
    }

    @Override
    public String selectCondition() {
        String upit = "WHERE 1=1";
        if(citalac != null){
            upit += " AND i.id_citalac=" + citalac.getId();
        }
        if(bibliotekar != null){
            upit += " AND i.id_bibliotekar=" + bibliotekar.getId();
        }
        if(datumUzimanja != null){
            upit += " AND datum_uzimanja <='" + java.sql.Date.valueOf(datumUzimanja) + "'";
        }
        if(ukupanIznos != 0){
            upit += " AND ukupan_iznos <= " + ukupanIznos;
        }
        if(brojKnjiga != 0){
            upit += " AND broj_knjiga <= " + brojKnjiga;
        }
        
        return upit;
    }

    @Override
    public String updateCondition() {
        return "id_iznajmljivanje = " + id;
    }
    
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> listaIznajmljivanja = new ArrayList<>();
        while(rs.next()){
            int idCitlac = rs.getInt("c.id_citalac");
            String imeCitalac = rs.getString("c.ime");
            String prezimeCitalac = rs.getString("c.prezime");
            Citalac citalac = new Citalac(idCitlac, imeCitalac, prezimeCitalac, null, null, null);
            
            int idBib = rs.getInt("b.id_bibliotekar");
            String imeBib = rs.getString("b.ime");
            String prezimeBib = rs.getString("b.prezime");
            Bibliotekar bibliotekar = new Bibliotekar(idBib, imeBib, prezimeBib, null, null, null);
            
            int id = rs.getInt("i.id_iznajmljivanje");
            int brojKnjiga = rs.getInt("i.broj_knjiga");
            java.sql.Date datumSql = rs.getDate("datum_uzimanja");
            LocalDate datum = datumSql.toLocalDate();
            double ukupanIznos = rs.getDouble("i.ukupan_iznos");
            String valuta = rs.getString("i.valuta");
            Iznajmljivanje iznajmljivanje = new Iznajmljivanje(id, brojKnjiga, datum, ukupanIznos, valuta, bibliotekar, citalac);
            
            listaIznajmljivanja.add(iznajmljivanje);
        }
        
        return listaIznajmljivanja;
    }

    @Override
    public String deleteCondition() {
        return "id_iznajmljivanje = " + id;
    }

    @Override
    public String selectColumns() {
        return "*";
    }
}