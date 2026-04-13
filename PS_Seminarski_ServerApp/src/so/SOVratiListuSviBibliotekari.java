/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.util.ArrayList;
import java.util.List;
import model.AbstractDomainObject;
import model.Bibliotekar;

/**
 *
 * @author lukaa
 */
public class SOVratiListuSviBibliotekari extends AbstractSO{

    private List<Bibliotekar> listaBibliotekara = new ArrayList<>();

    public List<Bibliotekar> getListaBibliotekara() {
        return listaBibliotekara;
    }
    
    @Override
    protected void precondition(Object obj) throws Exception {
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Bibliotekar bibliotekar = (Bibliotekar) obj;
        List<AbstractDomainObject> lista = dbb.selectList(bibliotekar);
        for (AbstractDomainObject ado : lista) {
            listaBibliotekara.add((Bibliotekar) ado);
        }
    }
    
}
