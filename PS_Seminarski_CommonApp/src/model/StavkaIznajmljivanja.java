/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author lukaa
 */
public class StavkaIznajmljivanja implements Serializable{
    private Iznajmljivanje iznajmljivanje;
    private int rb;
    private LocalDate datumVracanja;
    private int brojDana;
    private double iznosPoDanu;
    private double iznos;
    private String valuta;
    private Knjiga knjiga;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(Iznajmljivanje iznajmljivanje, int rb, LocalDate datumVracanja, int brojDana, double iznosPoDanu, double iznos, String valuta, Knjiga knjiga) {
        this.iznajmljivanje = iznajmljivanje;
        this.rb = rb;
        this.datumVracanja = datumVracanja;
        this.brojDana = brojDana;
        this.iznosPoDanu = iznosPoDanu;
        this.iznos = iznos;
        this.valuta = valuta;
        this.knjiga = knjiga;
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public LocalDate getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(LocalDate datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public double getIznosPoDanu() {
        return iznosPoDanu;
    }

    public void setIznosPoDanu(double iznosPoDanu) {
        this.iznosPoDanu = iznosPoDanu;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }
}