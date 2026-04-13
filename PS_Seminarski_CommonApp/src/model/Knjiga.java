/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author lukaa
 */
public class Knjiga extends AbstractDomainObject{
    private int id;
    private String naziv;
    private String autor;
    private Zanr zanr;
    private double iznosPoDanu;
    private String valuta;

    public Knjiga() {
    }

    public Knjiga(int id, String naziv, String autor, Zanr zanr, double iznosPoDanu, String valuta) {
        this.id = id;
        this.naziv = naziv;
        this.autor = autor;
        this.zanr = zanr;
        this.iznosPoDanu = iznosPoDanu;
        this.valuta = valuta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public double getIznosPoDanu() {
        return iznosPoDanu;
    }

    public void setIznosPoDanu(double iznosPoDanu) {
        this.iznosPoDanu = iznosPoDanu;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Knjiga other = (Knjiga) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return Objects.equals(this.autor, other.autor);
    }

    @Override
    public String tableName() {
        return "knjiga";
    }

    @Override
    public String alias() {
        return "k";
    }

    @Override
    public String pkName() {
        return "id_knjiga";
    }

    @Override
    public int id() {
        return id;
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
    public String updateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String textJoin() {
        return "";
    }

    @Override
    public String getCondition() {
        String uslov = "1=1";
        
        if(naziv != null && !naziv.trim().isEmpty()){
            uslov += " AND naziv LIKE '%" + naziv + "%'";
        }
        if(autor != null && !autor.trim().isEmpty()){
            uslov += " AND autor LIKE '%" + autor + "%'";
        }
        if(zanr != null){
            uslov += " AND zanr='" + zanr.toString() + "'";
        }
        
        return uslov;
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> listaKnjiga = new ArrayList<>();
        
        while(rs.next()){
            int id = rs.getInt("id_knjiga");
            String naziv = rs.getString("naziv");
            String autor = rs.getString("autor");
            Zanr zanr = Zanr.valueOf(rs.getString("zanr"));
            double iznosPoDanu = rs.getDouble("iznos_po_danu");
            String valuta = rs.getString("valuta");
                
            Knjiga k = new Knjiga(id, naziv, autor, zanr, iznosPoDanu, valuta);
            listaKnjiga.add(k);
        }
        
        return listaKnjiga;
    }

    
}
