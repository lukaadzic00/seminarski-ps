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
    
    public abstract String tableName(); // ime tabele
    public abstract String alias(); // alias
    public abstract String pkName();
    public abstract int id();
    public abstract String insertColumns(); // ime kolona za INSERT
    public abstract String insertValues(); // vrednosti kolona za INSERT
    public abstract String updateValues(); // vrednosti i imena kolona za UPDATE
    public abstract String updateCondition(); // uslov za UPDATE
    public abstract String textJoin(); // join za SELECT
    public abstract String getCondition(); // uslov za SELECT (i DELETE)
    public abstract ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException;
}
