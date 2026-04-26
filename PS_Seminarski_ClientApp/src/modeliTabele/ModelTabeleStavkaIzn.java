/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeliTabele;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Knjiga;
import model.StavkaIznajmljivanja;

/**
 *
 * @author lukaa
 */
public class ModelTabeleStavkaIzn extends AbstractTableModel{

    private List<StavkaIznajmljivanja> listaStavki = new ArrayList<>();
    private final String[] kolone = {"Naziv", "Autor", "Zanr", "Iznos po danu", "Broj dana", "Valuta"};

    public void setListaStavki(List<StavkaIznajmljivanja> listaStavki) {
        this.listaStavki = listaStavki;
        fireTableDataChanged();
    }
    
    public void deleteStavka(int index){
        listaStavki.remove(index);
        fireTableDataChanged();
    }
    
    
    
    @Override
    public int getRowCount() {
        return listaStavki.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaIznajmljivanja stavka = listaStavki.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getKnjiga().getNaziv();
            case 1:
                return stavka.getKnjiga().getAutor();
            case 2:
                return stavka.getKnjiga().getZanr();
            case 3:
                return stavka.getKnjiga().getIznosPoDanu();
            case 4:
                return stavka.getBrojDana();
            case 5:
                return stavka.getValuta();
            default:
                return null;
        }
    }
    
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
                return "Broj dana";
            case 5:
                return "Valuta";
            default:
                return null;
        }
    }

    public void addStavka(StavkaIznajmljivanja stavka) {
        listaStavki.add(stavka);
        fireTableDataChanged();
    }

    public List<StavkaIznajmljivanja> getListaStavki() {
        return listaStavki;
    }
}
