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
        if (!c.getIme().matches("[a-zA-ZšđčćžŠĐČĆŽ]+")) {
            throw new Exception("Ime sadrzi neke karaktere koji nisu samo slova");
        }
        if (!c.getPrezime().matches("[a-zA-ZšđčćžŠĐČĆŽ]+")) {
            throw new Exception("Prezime sadrzi neke karaktere koji nisu samo slova");
        }
        if (!c.getEmail().contains("@")) {
            throw new Exception("Email ne sadrzi karakter '@'");
        }
        if (!c.getTelefon().matches("\\+3816\\d{4,8}")) {
            throw new Exception("Telefon nije u dobrom formatu");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        Citalac citalac = (Citalac) obj;
        id = dbb.insert(citalac);
    }
}