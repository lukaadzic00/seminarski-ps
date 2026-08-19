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
public class Knjiga extends AbstractDomainObject{
    private int id;
    private String naziv;
    private Zanr zanr;
    private double iznosPoDanu;
    private String valuta;
    
    private List<Autor> autori;

    public Knjiga() {
    }

    public Knjiga(int id, String naziv, List<Autor> autori, Zanr zanr, double iznosPoDanu, String valuta) {
        this.id = id;
        this.naziv = naziv;
        this.autori = autori;
        this.zanr = zanr;
        this.iznosPoDanu = iznosPoDanu;
        this.valuta = valuta;
    }

    public Knjiga(int id, String naziv, Zanr zanr, double iznosPoDanu, String valuta) {
        this.id = id;
        this.naziv = naziv;
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

    public List<Autor> getAutori() {
        return autori;
    }

    public void setAutori(List<Autor> autori) {
        this.autori = autori;
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
        return this.id == other.id;
    }

    
    // Inherited methods

    @Override
    public String tableName() {
        return "knjiga";
    }

    @Override
    public String alias() {
        return "k";
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
        return "JOIN knjiga_autor ka ON k.id_knjiga = ka.id_knjiga JOIN autor a ON ka.id_autor = a.id_autor";
    }

    @Override
    public String selectCondition() {
        String uslov = "WHERE 1=1";
        
        if(naziv != null && !naziv.trim().isEmpty()){
            uslov += " AND k.naziv LIKE '%" + naziv + "%'";
        }
        
        if(autori != null && !autori.isEmpty()){
            String skup_autora_id = "(";
            for (Autor autor : autori) {
                skup_autora_id += autor.getId() + ", ";
            }
            skup_autora_id = skup_autora_id.substring(0, skup_autora_id.length() - 2) + ")";
            uslov += " AND ka.id_autor IN " + skup_autora_id;
        }
        
        if(zanr != null){
            uslov += " AND k.zanr='" + zanr.toString() + "'";
        }
        
        return uslov;
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            int id = rs.getInt("id_knjiga");
            String naziv = rs.getString("naziv");
            String zanr = rs.getString("zanr");
            double iznosPoDanu = rs.getDouble("iznos_po_danu");
            String valuta = rs.getString("valuta");
            
            Knjiga k = new Knjiga(id, naziv, Zanr.valueOf(zanr), iznosPoDanu, valuta);
            lista.add(k);
        }
        
        return lista;
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
        return "DISTINCT k.id_knjiga, k.naziv, k.zanr, k.iznos_po_danu, k.valuta";
    }
}
