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
    public String toString() {
        return "StavkaIznajmljivanja{" + "iznajmljivanje=" + iznajmljivanje + ", rb=" + rb + ", datumVracanja=" + datumVracanja + ", brojDana=" + brojDana + ", iznosPoDanu=" + iznosPoDanu + ", iznos=" + iznos + ", valuta=" + valuta + ", knjiga=" + knjiga + '}';
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
        return "";
    }

    @Override
    public int id() {
        return iznajmljivanje.getId();
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
        return " JOIN knjiga k ON si.id_knjiga=k.id_knjiga";
    }

    @Override
    public String getCondition() {
        return "si.id_iznajmljivanje=" + iznajmljivanje.getId();
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> listaStavki = new ArrayList<>();
        
        while(rs.next()){
            int idKnjiga = rs.getInt("k.id_knjiga");
            String naziv = rs.getString("k.naziv");
            String autor = rs.getString("k.autor");
            Zanr zanr = Zanr.valueOf(rs.getString("k.zanr"));
            double iznosPoDanu = rs.getDouble("k.iznos_po_danu");
            String valuta = rs.getString("k.valuta");
            Knjiga knjiga = new Knjiga(idKnjiga, naziv, autor, zanr, iznosPoDanu, valuta);
            System.out.println("KNJIGA : " + knjiga.toString());
            
            int idIzn = rs.getInt("si.id_iznajmljivanje");
            int rb = rs.getInt("si.rb");
            java.sql.Date datumSql = rs.getDate("si.datum_vracanja");
            LocalDate datum = datumSql.toLocalDate();
            int brojDana = rs.getInt("si.broj_dana");
            double iznosPoDanuStavka = rs.getDouble("si.iznos_po_danu");
            double iznos = rs.getDouble("si.iznos");
            StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(null, rb, datum, brojDana, iznosPoDanuStavka, iznos, "DIN", knjiga);
            System.out.println("STAVKA : " + stavka.getRb() + " " + stavka.getKnjiga().getNaziv());
            
            listaStavki.add(stavka);
        }
        return listaStavki;
    }
}