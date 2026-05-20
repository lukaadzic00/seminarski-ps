/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.AbstractDomainObject;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author lukaa
 */
public class DatabaseBroker {
    private Connection connection;
    private static DatabaseBroker instance;

    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }

    public void connect() throws Exception{
        Properties properties = new Properties();
        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("database/DBBrokerConfig.properties")) {

            if (input == null) {
                throw new RuntimeException("Ne mogu da nadjem DBBrokerConfig.properties fajl");
            }

            properties.load(input);

            String url = properties.getProperty("url");
            String user = properties.getProperty("username");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);

            System.out.println("Konekcija sa bazom uspesno uspostavljena");
        } catch (Exception ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena");
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public void disconnect() throws SQLException {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Konekcija sa bazom uspesno raskinuta");
            }
        } catch (SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno raskinuta");
            ex.printStackTrace();
            throw ex;
        }
    }

    public void commit() throws SQLException {
        try{
            connection.commit();
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public void rollback() throws SQLException {
        try{
            connection.rollback();
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public List<AbstractDomainObject> select(AbstractDomainObject ado) throws Exception {
        String query = "SELECT " + ado.selectColumns()
                        + " FROM " + ado.tableName()
                        + " " + ado.alias()
                        + " " + ado.textJoin()
                        + " " + ado.selectCondition();
        
        System.out.println(query);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        return ado.getList(rs);
    }
    
    public int insert(AbstractDomainObject ado) throws Exception {
        int id = -1;
        String query = "INSERT INTO " + ado.tableName() + " (" + ado.insertColumns() + ") VALUES (" + ado.insertValues() + ")";
        
        
        System.out.println(query);
        Statement st = connection.createStatement();
        st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        
        if(rs.next()){
            id = rs.getInt(1);
        }
        rs.close();
        st.close();
        
        return id;
    }
    
    public int delete(AbstractDomainObject ado) throws Exception{
        
        String query = "DELETE FROM " + ado.tableName() + " WHERE " + ado.deleteCondition();
        System.out.println(query);
        
        Statement st = connection.createStatement();
        int rowsAffected = st.executeUpdate(query);
        
        return rowsAffected;
    }
    
    public int update(AbstractDomainObject ado) throws Exception{
        String query = "UPDATE " + ado.tableName() + " SET " + ado.updateValues() + " WHERE " + ado.updateCondition();
        System.out.println(query);
        
        Statement st = connection.createStatement();
        int rowsAffected = st.executeUpdate(query);
        
        return rowsAffected;
    }
}