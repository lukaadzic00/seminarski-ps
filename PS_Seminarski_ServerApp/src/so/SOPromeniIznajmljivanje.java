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
            List<StavkaIznajmljivanja> listaStarihStavki = new ArrayList<>();
            for (AbstractDomainObject ado : lista) {
                listaStarihStavki.add((StavkaIznajmljivanja) ado);
            }

            List<StavkaIznajmljivanja> listaNovihStavki = iznajmljivanje.getListaStavki();
            
            // DELETE + UPDATE
            for (StavkaIznajmljivanja staraStavka : listaStarihStavki) {
                StavkaIznajmljivanja pronadjena = null;
                
                for (StavkaIznajmljivanja novaStavka : listaNovihStavki) {
                    if (staraStavka.getKnjiga().getId() == novaStavka.getKnjiga().getId()) {
                        pronadjena = novaStavka;
                        break;
                    }
                }

                if (pronadjena == null) {
                    // DELETE
                    dbb.delete(staraStavka);
                    
                } else {
                    //️ proveri UPDATE
                    if (staraStavka.getBrojDana() != pronadjena.getBrojDana()) {
                        pronadjena.setRb(staraStavka.getRb());
                        dbb.update(pronadjena);
                    }
                }
            }
            
            // renumeracija u bazi
            for (int i = 0; i < listaNovihStavki.size(); i++) {
                StavkaIznajmljivanja s = listaNovihStavki.get(i);
                s.setIznajmljivanje(iznajmljivanje);
                s.setRb(i + 1);
                dbb.update(s);
            }
            
            // vracamo novu izmenjenu listu stavki iz baze + renumerisanu
            listaStarihStavki.clear();
            lista = dbb.select(stavka);
            for (AbstractDomainObject ado : lista) {
                listaStarihStavki.add((StavkaIznajmljivanja) ado);
            }
            
            List<StavkaIznajmljivanja> stavkeZaUbaciti = getStavkeZaUbaciti(listaStarihStavki, listaNovihStavki);
            int rb = listaStarihStavki.size() + 1;
            for (StavkaIznajmljivanja s : stavkeZaUbaciti) {
                s.setIznajmljivanje(iznajmljivanje);
                s.setRb(rb);
                dbb.insert(s);
                rb++;
            }
            
            // update atributa iznajmljivanja
            iznajmljivanje.setBrojKnjiga(listaNovihStavki.size());
            dbb.update(iznajmljivanje);
            
            this.uspeh = true;
        }
    }
    
    public List<StavkaIznajmljivanja> getStavkeZaUbaciti(List<StavkaIznajmljivanja> stareStavke, List<StavkaIznajmljivanja> noveStavke) {
        List<StavkaIznajmljivanja> stavkeZaUbaciti = new ArrayList<>();

        for (StavkaIznajmljivanja nova : noveStavke) {
            boolean postoji = false;

            for (StavkaIznajmljivanja stara : stareStavke) {
                if (nova.getKnjiga().getId() == stara.getKnjiga().getId()) {
                    postoji = true;
                    break;
                }
            }

            if (!postoji) {
                stavkeZaUbaciti.add(nova);
            }
        }

        return stavkeZaUbaciti;
    }
}