/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.util.List;
import model.AbstractDomainObject;
import model.Citalac;

/**
 *
 * @author lukaa
 */
public class SOKreirajCitaoca extends AbstractSO{

    private int id;

    public int getId() {
        return id;
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null){
            throw new Error("Prosledjeni parametar je null");
        }
        
        Citalac c = (Citalac) param;
        
        if(c.getIme() == null || c.getPrezime() == null || c.getEmail() == null || c.getTelefon() == null || c.getKategorija() == null){
            throw new Exception("Neki od parametara kod citaoca je null");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Citalac c = (Citalac) obj;
        id = dbb.insert(c);
    }
}