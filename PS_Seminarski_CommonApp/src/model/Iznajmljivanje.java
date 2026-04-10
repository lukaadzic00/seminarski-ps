/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lukaa
 */
public class Iznajmljivanje implements Serializable{
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
}
