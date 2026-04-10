/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author lukaa
 */
public class Citalac implements Serializable{
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private KategorijaCitaoca kategorija;

    public Citalac() {
    }

    public Citalac(int id, String ime, String prezime, String email, String telefon, KategorijaCitaoca kategorija) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.kategorija = kategorija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public KategorijaCitaoca getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijaCitaoca kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}
