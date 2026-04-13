/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lukaa
 */
public class Citalac extends AbstractDomainObject{
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

    @Override
    public String tableName() {
        return "citalac";
    }

    @Override
    public String alias() {
        return "c";
    }

    @Override
    public String insertColumns() {
        return "ime, prezime, email, telefon, id_kategorija";
    }

    @Override
    public String insertValues() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', '" + telefon + "', " + kategorija.getId();
    }

    // "JOIN kategorija_citaoca k ON k.id_kategorija = c.id_kategorija"
    @Override
    public String textJoin() {
        return "JOIN kategorija_citaoca kc ON c.id_kategorija = kc.id_kategorija";
    }

    @Override
    public String getCondition() {
        String uslov = "1=1";

        if (ime != null && !ime.trim().isEmpty()) {
            uslov += " AND ime LIKE '%" + ime + "%'";
        }
        if (prezime != null && !prezime.trim().isEmpty()) {
            uslov += " AND prezime LIKE '%" + prezime + "%'";
        }
        if (email != null && !email.trim().isEmpty()) {
            uslov += " AND email LIKE '%" + email + "%'";
        }
        if (telefon != null && !telefon.trim().isEmpty()) {
            uslov += " AND telefon LIKE '%" + telefon + "%'";
        }
        if (kategorija != null) {
            uslov += " AND c.id_kategorija = " + kategorija.getId();
        }

        return uslov;
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            int id = rs.getInt("id_citalac");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String email = rs.getString("email");
            String telefon = rs.getString("telefon");
            KategorijaCitaoca kat = new KategorijaCitaoca(rs.getInt("id_kategorija"), Kategorija.valueOf(rs.getString("naziv")), rs.getDouble("popust"));
                
            Citalac c = new Citalac(id, ime, prezime, email, telefon, kat);
            lista.add(c);
        }
        
        return lista;
    }

    @Override
    public String pkName() {
        return "id_citalac";
    }

    @Override
    public int id() {
        return id; 
    }

    @Override
    public String updateValues() {
        return "ime='" + ime + "', prezime='" + prezime + "', email='" + email + "', telefon='" + telefon + "', id_kategorija=" + kategorija.getId();
    }
}