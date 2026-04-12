/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import model.Citalac;

/**
 *
 * @author lukaa
 */
public class SOObrisiCitaoca extends AbstractSO{

    private int rowsAffected;

    public int getRowsAffected() {
        return rowsAffected;
    }
    
    @Override
    protected void precondition(Object obj) throws Exception {
        Citalac citalac = (Citalac) obj;
        if(citalac == null){
            throw new Error("Prosledjeni objekat je null");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Citalac citalac = (Citalac) obj;
        rowsAffected = dbb.delete(citalac);
    }
    
}
