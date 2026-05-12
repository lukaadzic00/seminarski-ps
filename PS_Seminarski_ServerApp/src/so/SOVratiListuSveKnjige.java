/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.util.ArrayList;
import java.util.List;
import model.AbstractDomainObject;
import model.Knjiga;

/**
 *
 * @author lukaa
 */
public class SOVratiListuSveKnjige extends AbstractSO{

    private List<Knjiga> listaKnjiga;

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }
    
    @Override
    protected void precondition(Object obj) throws Exception {
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Knjiga knjiga = (Knjiga) obj;
        listaKnjiga = new ArrayList<>();
        List<AbstractDomainObject> list = dbb.select(knjiga);
        for (AbstractDomainObject ado : list) {
            listaKnjiga.add((Knjiga) ado);
        }
    }    
}