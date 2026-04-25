/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.util.List;
import model.AbstractDomainObject;
import model.Bibliotekar;

/**
 *
 * @author lukaa
 */
public class SOLogin extends AbstractSO{

    private Bibliotekar ulogovani;
    
    public Bibliotekar getUlogovani() {
        return ulogovani;
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null){
            throw new Exception("Prosledjeni parametar je null");
        }
        
        Bibliotekar b = (Bibliotekar) param;
        if(b.getKorisnickoIme() == null || b.getSifra() == null){
            throw new Exception("Korisnicko ime ili sifra nisu popunjeni");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Bibliotekar b = (Bibliotekar) param;
        List<AbstractDomainObject> lista = dbb.select(b);
        ulogovani = (Bibliotekar) lista.get(0);
    }
}
