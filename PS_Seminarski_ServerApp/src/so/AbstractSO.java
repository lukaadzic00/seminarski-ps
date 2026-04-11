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
    
    public void execute(Object obj) throws Exception{
        try{
            precondition(obj);
            startTransaction();
            executeOperation(obj);
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

    protected abstract void precondition(Object obj) throws Exception;
    
    protected abstract void executeOperation(Object obj) throws Exception;

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
