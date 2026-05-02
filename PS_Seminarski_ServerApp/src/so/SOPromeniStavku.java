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
public class SOPromeniStavku extends AbstractSO{

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
        
        rowsAffected = dbb.update(stavka);
        
        double ukupanIznosBezStavke = iznajmljivanje.getUkupanIznos() - (stavka.getBrojDana()*stavka.getIznosPoDanu());
        stavka.setIznos(stavka.getNoviBrojDana() * stavka.getIznosPoDanu());
        double ukupanIznos = ukupanIznosBezStavke + stavka.getIznos();
        iznajmljivanje.setUkupanIznos(ukupanIznos);
        
        dbb.update(iznajmljivanje);
    }
}
