/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;

/**
 *
 * @author lukaa
 */
public class SODodajStavke extends AbstractSO{

    private int rowsAffected = 0;

    public int getRowsAffected() {
        return rowsAffected;
    }
    
    @Override
    protected void precondition(Object obj) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) obj;
        if(iznajmljivanje == null){
            throw new Error("Prosledjeno iznajmljivanje je null");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) obj;
        int brojKnjiga = iznajmljivanje.getBrojKnjiga();
        int rb = brojKnjiga + 1;
        
        for (StavkaIznajmljivanja stavka : iznajmljivanje.getListaStavki()) {
            stavka.setIznajmljivanje(iznajmljivanje);
            stavka.setRb(rb);
            int id = dbb.insert(stavka);
            if(id == 1){
                rowsAffected++;
            }
            
            rb++;
        }
    }
}