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
public class SOPretraziKnjigu extends AbstractSO{

    List<Knjiga> listaKnjiga = new ArrayList<>();

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }
    
    @Override
    protected void precondition(Object obj) throws Exception {
        Knjiga knjiga = (Knjiga) obj;
        if(knjiga == null){
            throw new Exception("Prosledjena knjiga je null");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Knjiga knjiga = (Knjiga) obj;
        List<AbstractDomainObject> lista = dbb.select(knjiga);
        for (AbstractDomainObject ado : lista) {
            listaKnjiga.add((Knjiga) ado);
        }
    }
}