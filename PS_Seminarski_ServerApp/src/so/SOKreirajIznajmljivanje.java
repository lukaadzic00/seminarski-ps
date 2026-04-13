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
public class SOKreirajIznajmljivanje extends AbstractSO{

    private int id;

    public int getId() {
        return id;
    }
    
    @Override
    protected void precondition(Object obj) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) obj;
        
        if(iznajmljivanje == null){
            throw new Exception("Prosledjeni objekat iznajmljivanja je null");
        }
        if(iznajmljivanje.getListaStavki().size() == 0){
            throw new Exception("Prosledjeno iznajmljivanje nema nijednu stavku");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) obj;
        id = dbb.insert(iznajmljivanje);
        iznajmljivanje.setId(id);
        
        int rb = 1;
        for (StavkaIznajmljivanja stavka : iznajmljivanje.getListaStavki()) {
            stavka.setIznajmljivanje(iznajmljivanje);
            stavka.setRb(rb);
            int id_stavke = dbb.insert(stavka);
            rb++;
        }
    }
}
