/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.util.ArrayList;
import java.util.List;
import model.AbstractDomainObject;
import model.Autor;

/**
 *
 * @author lukaa
 */
public class SOVratiListuSviAutori extends AbstractSO{

    private List<Autor> listaAutora = new ArrayList<>();

    public List<Autor> getListaAutora() {
        return listaAutora;
    }
    
    @Override
    protected void precondition(Object obj) throws Exception {
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Autor autor = (Autor) obj;
        List<AbstractDomainObject> lista = dbb.select(autor);
        for (AbstractDomainObject ado : lista) {
            listaAutora.add((Autor) ado);
        }
    }
}
