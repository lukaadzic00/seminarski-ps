/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author lukaa
 */
public class StavkaIznajmljivanja extends AbstractDomainObject{
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

    @Override
    public String tableName() {
        return "stavka_iznajmljivanja";
    }

    @Override
    public String alias() {
        return "si";
    }

    @Override
    public String pkName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int id() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String insertColumns() {
        return "id_iznajmljivanje, rb, datum_vracanja, broj_dana, iznos_po_danu, iznos, valuta, id_knjiga";
    }

    @Override
    public String insertValues() {
        return iznajmljivanje.getId() + ", " + rb + ", '" + java.sql.Date.valueOf(datumVracanja) + "', " + brojDana + ", " + iznosPoDanu + ", " + iznos + ", '" + valuta + "', " + knjiga.getId();
    }

    @Override
    public String updateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String textJoin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}