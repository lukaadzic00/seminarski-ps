/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeliTabele;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Iznajmljivanje;

/**
 *
 * @author lukaa
 */
public class ModelTabeleIznajmljivanje extends AbstractTableModel {

    private List<Iznajmljivanje> listaIznajmljivanja = new ArrayList<>();
    String[] kolone = {"ID", "Broj knjiga", "Datum", "Ukupan iznos", "Valuta", "Citalac", "Bibliotekar"};

    public void setListaIznajmljivanja(List<Iznajmljivanje> listaIznajmljivanja) {
        this.listaIznajmljivanja = listaIznajmljivanja;
        fireTableDataChanged();
    }
    
    
    
    @Override
    public int getRowCount() {
        return listaIznajmljivanja.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Iznajmljivanje izn = listaIznajmljivanja.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return izn.getId();
            case 1:
                return izn.getBrojKnjiga();
            case 2:
                return izn.getDatumUzimanja();
            case 3:
                return izn.getUkupanIznos();
            case 4:
                return izn.getValuta();
            case 5:
                return izn.getCitalac().toString();
            case 6:
                return izn.getBibliotekar().toString();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Broj knjiga";
            case 2:
                return "Datum";
            case 3:
                return "Ukupan iznos";
            case 4:
                return "Valuta";
            case 5:
                return "Citalac";
            case 6:
                return "Bibliotekar";
            default:
                return null;
        }
    }
}
