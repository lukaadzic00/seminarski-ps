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
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        StavkaIznajmljivanja stavka = (StavkaIznajmljivanja) obj;
        Iznajmljivanje iznajmljivanje = stavka.getIznajmljivanje(); 
        
        int brojKnjiga = iznajmljivanje.getBrojKnjiga();
        double ukupanIznos = iznajmljivanje.getUkupanIznos();
        
        rowsAffected = dbb.delete(stavka);
        iznajmljivanje.getListaStavki().remove(stavka);
        
        for (StavkaIznajmljivanja s : iznajmljivanje.getListaStavki()){
            if(s.getRb() > stavka.getRb()){
                int stariRb = s.getRb();
                s.setRb(s.getRb() - 1);
                s.setStariRb(stariRb);
                dbb.update(s);
            }
        }
        
        brojKnjiga--;
        ukupanIznos -= stavka.getIznos();
        iznajmljivanje.setBrojKnjiga(brojKnjiga);
        iznajmljivanje.setUkupanIznos(ukupanIznos);
        
        dbb.update(iznajmljivanje);
        if(brojKnjiga == 0){
            dbb.delete(iznajmljivanje);
        }
    }
}