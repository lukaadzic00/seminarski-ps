/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeliTabele;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Citalac;

/**
 *
 * @author lukaa
 */
public class ModelTabeleCitalac extends AbstractTableModel {

    private List<Citalac> lista = new ArrayList<>();
    private final String[] kolone = {"Ime", "Prezime", "Email", "Telefon", "Kategorija"};

    public ModelTabeleCitalac(List<Citalac> listaCitalaca) {
        this.lista = listaCitalaca;
    }
    
    public ModelTabeleCitalac(){
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Citalac citalac = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return citalac.getIme();
            case 1:
                return citalac.getPrezime();
            case 2:
                return citalac.getEmail();
            case 3:
                return citalac.getTelefon();
            case 4:
                return citalac.getKategorija().getNaziv();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Ime";
            case 1:
                return "Prezime";
            case 2:
                return "Email";
            case 3:
                return "Telefon";
            case 4:
                return "Kategorija";
            default:
                return null;
        }
    }
    
    public Citalac getRow(int red){
        return lista.get(red);
    }

    public void setLista(List<Citalac> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
}
