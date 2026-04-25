/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import model.StavkaIznajmljivanja;

/**
 *
 * @author lukaa
 */
public class SOObrisiStavku extends AbstractSO{

    private int rowsAffected;

    public int getRowsAffected() {
        return rowsAffected;
    }
    
    
    
    @Override
    protected void precondition(Object obj) throws Exception {
        StavkaIznajmljivanja stavka = (StavkaIznajmljivanja) obj;
        
        if(stavka == null){
            throw new Error("Prosledjena stavka je null");
        }
        if(stavka.getIznajmljivanje() == null){
            throw new Error("Prosledjena stavka nije vezana ni za jedno iznajmljivanje");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        StavkaIznajmljivanja stavka = (StavkaIznajmljivanja) obj;
        rowsAffected = dbb.delete(stavka);
    }
}
