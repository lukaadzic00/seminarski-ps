/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lukaa
 */
public class KategorijaCitaoca extends AbstractDomainObject{
    private int id;
    private Kategorija naziv;
    private double popust;

    public KategorijaCitaoca() {
    }

    public KategorijaCitaoca(int id, Kategorija naziv, double popust) {
        this.id = id;
        this.naziv = naziv;
        this.popust = popust;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kategorija getNaziv() {
        return naziv;
    }

    public void setNaziv(Kategorija naziv) {
        this.naziv = naziv;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    @Override
    public String toString() {
        return String.valueOf(naziv);
    }

    @Override
    public String tableName() {
        return "kategorija_citaoca";
    }

    @Override
    public String alias() {
        return "kc";
    }

    @Override
    public String insertColumns() {
        return "";
    }

    @Override
    public String insertValues() {
        return "";
    }

    @Override
    public String textJoin() {
        return "";
    }

    @Override
    public String getCondition() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            int id = rs.getInt("kc.id_kategorija");
            Kategorija k = Kategorija.valueOf(rs.getString("kc.naziv"));
            double popust = rs.getDouble("kc.popust");
            
            KategorijaCitaoca kc = new KategorijaCitaoca(id, k, popust);
            lista.add(kc);
        }
        
        rs.close();
        return lista;
    }

    @Override
    public String pkName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int id() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
