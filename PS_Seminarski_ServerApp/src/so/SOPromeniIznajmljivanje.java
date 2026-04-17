/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import model.Iznajmljivanje;

/**
 *
 * @author lukaa
 */
public class SOPromeniIznajmljivanje extends AbstractSO {

    private int affectedRows;

    public int getAffectedRows() {
        return affectedRows;
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
        affectedRows = dbb.update(iznajmljivanje);
    }
}
