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
import static so.AbstractSO.dbb;

/**
 *
 * @author lukaa
 */
public class SOPromeniIznajmljivanje extends AbstractSO {

    private boolean uspeh = false;

    public boolean isUspeh() {
        return uspeh;
    }

    @Override
    protected void precondition(Object obj) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) obj;
        if(iznajmljivanje == null){
            throw new Exception("Prosledjeno iznajmljivanje je null");
        }
        if(iznajmljivanje.getCitalac() == null){
            throw new Exception("Prosledjeno iznajmljivanje nema izabranog citaoca");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) obj;
        
        // update atributa iznajmljivanja
        if(iznajmljivanje.isIzmenjenCitalac()){
            dbb.update(iznajmljivanje);
            this.uspeh = true;
        }
        
        if(iznajmljivanje.isIzmenjeneStavke()){
            // ucitavanje starih stavki
            StavkaIznajmljivanja stavka = new StavkaIznajmljivanja();
            stavka.setIznajmljivanje(iznajmljivanje);
            List<AbstractDomainObject> lista = dbb.select(stavka);
            List<StavkaIznajmljivanja> listaPrethodnihStavki = new ArrayList<>();
            for (AbstractDomainObject ado : lista) {
                listaPrethodnihStavki.add((StavkaIznajmljivanja) ado);
            }

            // brisanje starih stavki
            for (StavkaIznajmljivanja s : listaPrethodnihStavki) {
                dbb.delete(s);
            }

            // punjenje baze novim stavkama
            int rb = 1;
            for(StavkaIznajmljivanja s : iznajmljivanje.getListaStavki()){
                s.setIznajmljivanje(iznajmljivanje);
                s.setRb(rb);
                dbb.insert(s);
                rb++;
            }
            this.uspeh = true;
        }
    }
}