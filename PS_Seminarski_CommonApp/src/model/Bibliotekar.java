/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author lukaa
 */
public class Bibliotekar extends AbstractDomainObject{
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String korisnickoIme;
    private String sifra;

    public Bibliotekar() {
    }

    public Bibliotekar(int id, String ime, String prezime, String email, String korisnickoIme, String sifra) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bibliotekar other = (Bibliotekar) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    // Inherited methods
    
    @Override
    public String tableName() {
        return "bibliotekar";
    }

    @Override
    public String alias() {
        return "b";
    }

    @Override
    public String insertColumns() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String insertValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String textJoin() {
        return "";
    }

    @Override
    public String selectCondition() {
        String upit = "WHERE 1=1";
        if(korisnickoIme != null && sifra != null){
            upit += " AND b.korisnicko_ime='" + korisnickoIme + "' AND b.sifra='" + sifra + "'";
        }
        return upit;
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            int id = rs.getInt("b.id_bibliotekar");
            String ime = rs.getString("b.ime");
            String prezime = rs.getString("b.prezime");
            String email = rs.getString("b.email");
            String korisnickoIme = rs.getString("b.korisnicko_ime");
            String sifra = rs.getString("b.sifra");
            
            Bibliotekar b = new Bibliotekar(id, ime, prezime, email, korisnickoIme, sifra);
            lista.add(b);
        }
        
        rs.close();
        return lista;
    }

    @Override
    public String updateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String updateCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String deleteCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String selectColumns() {
        return "*";
    }
}
