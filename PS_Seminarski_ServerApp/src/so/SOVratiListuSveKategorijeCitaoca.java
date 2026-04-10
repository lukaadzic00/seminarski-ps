/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.util.ArrayList;
import java.util.List;
import model.AbstractDomainObject;
import model.KategorijaCitaoca;

/**
 *
 * @author lukaa
 */
public class SOVratiListuSveKategorijeCitaoca extends AbstractSO{

    List<KategorijaCitaoca> listaKategorija;

    public List<KategorijaCitaoca> getListaKategorija() {
        return listaKategorija;
    }
    
    
    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        KategorijaCitaoca kc = new KategorijaCitaoca();
        List<AbstractDomainObject> lista = dbb.selectList(kc);
        listaKategorija = new ArrayList<>();
        for(AbstractDomainObject ado : lista){
            listaKategorija.add((KategorijaCitaoca) ado);
        }
    }
    
}
