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
public class BibliotekarRS implements Serializable{
    private Bibliotekar bibliotekar;
    private LocalDate datumSmene;
    private RadnaSmena radnaSmena;

    public BibliotekarRS() {
    }

    public BibliotekarRS(Bibliotekar bibliotekar, LocalDate datumSmene, RadnaSmena radnaSmena) {
        this.bibliotekar = bibliotekar;
        this.datumSmene = datumSmene;
        this.radnaSmena = radnaSmena;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    public LocalDate getDatumSmene() {
        return datumSmene;
    }

    public void setDatumSmene(LocalDate datumSmene) {
        this.datumSmene = datumSmene;
    }

    public RadnaSmena getRadnaSmena() {
        return radnaSmena;
    }

    public void setRadnaSmena(RadnaSmena radnaSmena) {
        this.radnaSmena = radnaSmena;
    }
}
