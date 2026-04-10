/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import database.DatabaseBroker;
import java.sql.SQLException;
/**
 *
 * @author lukaa
 */
public abstract class AbstractSO {
    protected static DatabaseBroker dbb = new DatabaseBroker();
    
    public void execute(Object param) throws Exception{
        try{
            precondition(param);
            startTransaction();
            executeOperation(param);
            comitTransaction();
            System.out.println("Uspesno izvrsena operacija");
        } catch(Exception exception){
            exception.printStackTrace();
            System.out.println("Greska kod izvrsavanja operacije");
            rollbackTransaction();
        } finally{
            disconnect();
        }
    }

    protected abstract void precondition(Object param) throws Exception;
    
    protected abstract void executeOperation(Object param) throws Exception;

    private void startTransaction() throws Exception{
        dbb.connect();
    }

    private void disconnect() throws Exception {
        dbb.disconnect();
    }

    protected void comitTransaction() throws Exception {
        dbb.commit();
    }

    protected void rollbackTransaction() throws Exception {
        dbb.rollback();
    }
}
