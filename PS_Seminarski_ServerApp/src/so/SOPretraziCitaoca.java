/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.util.ArrayList;
import java.util.List;
import model.AbstractDomainObject;
import model.Citalac;

/**
 *
 * @author lukaa
 */
public class SOPretraziCitaoca extends AbstractSO{

    private List<Citalac> listaCitaoca = new ArrayList<>();

    public List<Citalac> getListaCitaoca() {
        return listaCitaoca;
    }
    
    @Override
    protected void precondition(Object obj) throws Exception {
        Citalac citalac = (Citalac) obj;
        if(citalac == null){
            throw new Error("Prosledjeni objekat je null");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Citalac citalac = (Citalac) obj;
        List<AbstractDomainObject> lista = dbb.select(citalac);
        for (AbstractDomainObject ado : lista) {
            listaCitaoca.add((Citalac) ado);
        }
    }
}
