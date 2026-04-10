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
public class RadnaSmena implements Serializable{
    private int id;
    private Smena naziv;
    private int trajanje;

    public RadnaSmena() {
    }

    public RadnaSmena(int id, Smena naziv, int trajanje) {
        this.id = id;
        this.naziv = naziv;
        this.trajanje = trajanje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Smena getNaziv() {
        return naziv;
    }

    public void setNaziv(Smena naziv) {
        this.naziv = naziv;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }
}
