/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeliTabele;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Citalac;
import model.Knjiga;

/**
 *
 * @author lukaa
 */
public class ModelTabeleKnjige extends AbstractTableModel {

    List<Knjiga> listaKnjiga = new ArrayList<>();
    private final String[] kolone = {"Naziv", "Autor", "Zanr", "Iznos po danu", "Valuta"};

    public ModelTabeleKnjige(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

    public ModelTabeleKnjige() {
    }
    
    @Override
    public int getRowCount() {
        return listaKnjiga.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Knjiga knjiga = listaKnjiga.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return knjiga.getNaziv();
            case 1:
                return knjiga.getAutor();
            case 2:
                return knjiga.getZanr();
            case 3:
                return knjiga.getIznosPoDanu();
            case 4:
                return knjiga.getValuta();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Naziv";
            case 1:
                return "Autor";
            case 2:
                return "Zanr";
            case 3:
                return "Iznos po danu";
            case 4:
                return "Valuta";
            default:
                return null;
        }
    }
    
    public void setLista(List<Knjiga> lista) {
        this.listaKnjiga = lista;
        fireTableDataChanged();
    }
    
    public Knjiga getRow(int red){
        return listaKnjiga.get(red);
    }
}
