/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import model.RadnaSmena;

/**
 *
 * @author lukaa
 */
public class SOUbaciRadnuSmenu extends AbstractSO {

    private int generatedKey;

    public int getGeneratedKey() {
        return generatedKey;
    }
    
    @Override
    protected void precondition(Object obj) throws Exception {
        RadnaSmena radnaSmena = (RadnaSmena) obj;
        if(radnaSmena == null){
            throw new Error("Prosledjena radna smena je null");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {
        RadnaSmena radnaSmena = (RadnaSmena) obj;
        generatedKey = dbb.insert(radnaSmena);
    }
}