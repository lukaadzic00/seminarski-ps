/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lukaa
 */
public abstract class AbstractDomainObject implements Serializable {
    
    public abstract String tableName();
    public abstract String alias();
    public abstract String insertColumns();
    public abstract String insertValues();
    public abstract String textJoin();
    public abstract String getCondition();
    public abstract ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException;
}
