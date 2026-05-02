/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.util.ArrayList;
import java.util.List;
import model.AbstractDomainObject;
import model.Iznajmljivanje;

/**
 *
 * @author lukaa
 */
public class SOPretraziIznajmljivanje extends AbstractSO{

    List<Iznajmljivanje> listaIznajmljivanja = new ArrayList<>();

    public List<Iznajmljivanje> getListaIznajmljivanja() {
        return listaIznajmljivanja;
    }

    @Override
    protected void precondition(Object obj) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) obj;
        if(iznajmljivanje == null){
            throw new Exception("Prosledjeno iznajmljivanje je null");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) obj;
        List<AbstractDomainObject> lista = dbb.select(iznajmljivanje);
        for (AbstractDomainObject ado : lista) {
            listaIznajmljivanja.add((Iznajmljivanje) ado);
        }
    }
}