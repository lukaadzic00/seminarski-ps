/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.util.ArrayList;
import java.util.List;
import model.AbstractDomainObject;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;

/**
 *
 * @author lukaa
 */
public class SOVratiListuSveStavkeIznajmljivanja extends AbstractSO {

    List<StavkaIznajmljivanja> listaStavki = new ArrayList<>();

    public List<StavkaIznajmljivanja> getListaStavki() {
        return listaStavki;
    }
    
    
    
    @Override
    protected void precondition(Object obj) throws Exception {
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) obj;
        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja();
        stavka.setIznajmljivanje(iznajmljivanje);
        
        List<AbstractDomainObject> lista = dbb.select(stavka);
        for (AbstractDomainObject ado : lista) {
            listaStavki.add((StavkaIznajmljivanja) ado);
        }
    }
}