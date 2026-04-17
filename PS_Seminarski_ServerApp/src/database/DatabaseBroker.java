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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AbstractDomainObject;
import model.Bibliotekar;
import model.Citalac;
import model.Iznajmljivanje;
import model.Kategorija;
import model.KategorijaCitaoca;
import model.Knjiga;
import model.StavkaIznajmljivanja;
import model.Zanr;

/**
 *
 * @author lukaa
 */
public class DatabaseBroker {
    private Connection connection;

    public void connect() {
        String url = "jdbc:mysql://localhost:3306/ps_seminarski";
        String user = "root";
        String password = "root1234";
        
        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("Konekcija sa bazom uspesno uspostavljena");
        } catch(SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena");
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

    /*
    public Bibliotekar prijaviBibliotekara(Bibliotekar bibliotekar) {
        
        try {
            String query = "SELECT * FROM bibliotekar WHERE korisnicko_ime=? AND sifra=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, bibliotekar.getKorisnickoIme());
            ps.setString(2, bibliotekar.getSifra());
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                bibliotekar.setId(rs.getInt("id_bibliotekar"));
                bibliotekar.setIme(rs.getString("ime"));
                bibliotekar.setPrezime(rs.getString("prezime"));
                bibliotekar.setEmail(rs.getString("email"));
                bibliotekar.setKorisnickoIme(rs.getString("korisnicko_ime"));
                bibliotekar.setSifra(rs.getString("sifra"));
            } else {
                bibliotekar = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bibliotekar;
    }
    */

    
    /*
    public List<KategorijaCitaoca> vratiSveKategorije() {
        List<KategorijaCitaoca> listaKategorija = new ArrayList<>();
        try {
            String query = "SELECT * FROM kategorija_citaoca";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                int id = rs.getInt("id_kategorija");
                String naziv = rs.getString("naziv");
                double popust = rs.getDouble("popust");
                
                KategorijaCitaoca kc = new KategorijaCitaoca(id, Kategorija.valueOf(naziv), popust);
                listaKategorija.add(kc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaKategorija;
    }
    */

    /*
    public boolean kreirajCitaoca(Citalac citalac) {
        try {
            String query = "INSERT INTO citalac (ime, prezime, email, telefon, id_kategorija) VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            
            ps.setString(1, citalac.getIme());
            ps.setString(2, citalac.getPrezime());
            ps.setString(3, citalac.getEmail());
            ps.setString(4, citalac.getTelefon());
            ps.setInt(5, citalac.getKategorija().getId());
            
            int rows = ps.executeUpdate();
            System.out.println("Citalac ubacen u bazu: " + rows);
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    */
    
    /*
    public List<Citalac> pretraziCitaoca(Citalac citalac) {
        List<Citalac> listaCitalaca = new ArrayList<>();
        try {
            String query = "SELECT * FROM citalac c JOIN kategorija_citaoca k ON k.id_kategorija=c.id_kategorija WHERE 1=1";
            
            if(citalac.getIme() != null && !citalac.getIme().trim().isEmpty()){
                query = query + " AND ime LIKE '%" + citalac.getIme() + "%'";
            }
            if(citalac.getPrezime() != null && !citalac.getPrezime().trim().isEmpty()){
                query = query + " AND prezime LIKE '%" + citalac.getPrezime() + "%'";
            }
            if(citalac.getEmail() != null && !citalac.getEmail().trim().isEmpty()){
                query = query + " AND email LIKE '%" + citalac.getEmail() + "%'";
            }
            if(citalac.getTelefon() != null && !citalac.getTelefon().trim().isEmpty()){
                query = query + " AND telefon LIKE '%" + citalac.getTelefon() + "%'";
            }
            if(citalac.getKategorija() != null){
                query = query + " AND c.id_kategorija = " + citalac.getKategorija().getId();
            }
            System.out.println("QUERY: " + query);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                int id = rs.getInt("id_citalac");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String email = rs.getString("email");
                String telefon = rs.getString("telefon");
                KategorijaCitaoca kat = new KategorijaCitaoca(rs.getInt("id_kategorija"), Kategorija.valueOf(rs.getString("naziv")), rs.getDouble("popust"));
                
                Citalac c = new Citalac(id, ime, prezime, email, telefon, kat);
                listaCitalaca.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("GRESKA. Neuspesno vracena lista citalaca");
        }
        return listaCitalaca;
    }
    */

    /*
    public boolean obrisiCitaoca(Citalac citalac) {
        int uspeh = 0;
        try {
            int id = citalac.getId();
            String query = "DELETE FROM citalac WHERE id_citalac=" + id;
            
            Statement st = connection.createStatement();
            uspeh = st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspeh > 0;
    }
    */

    public boolean promeniCitaoca(Citalac citalac) {
        try {
            String query = "UPDATE citalac SET ime=?, prezime=?, email=?, telefon=?, id_kategorija=? WHERE id_citalac=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, citalac.getIme());
            ps.setString(2, citalac.getPrezime());
            ps.setString(3, citalac.getEmail());
            ps.setString(4, citalac.getTelefon());
            ps.setInt(5, citalac.getKategorija().getId());
            ps.setInt(6, citalac.getId());
            
            int row = ps.executeUpdate();
            return row > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Knjiga> pretraziKnjigu(Knjiga filter) {
        List<Knjiga> listaKnjiga = new ArrayList<>();
        try {
            String query = "SELECT * FROM knjiga WHERE 1=1";
            if(filter.getNaziv()!= null && !filter.getNaziv().trim().isEmpty()){
                query = query + " AND naziv LIKE '%" + filter.getNaziv() + "%'";
            }
            if(filter.getAutor()!= null && !filter.getAutor().trim().isEmpty()){
                query = query + " AND autor LIKE '%" + filter.getAutor() + "%'";
            }
            if(filter.getZanr()!= null){
                query = query + " AND zanr = '" + filter.getZanr().toString() + "'";
            }
            
            System.out.println("QUERY: " + query);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id_knjiga");
                String naziv = rs.getString("naziv");
                String autor = rs.getString("autor");
                Zanr zanr = Zanr.valueOf(rs.getString("zanr"));
                double iznosPoDanu = rs.getDouble("iznos_po_danu");
                String valuta = rs.getString("valuta");
                
                Knjiga k = new Knjiga(id, naziv, autor, zanr, iznosPoDanu, valuta);
                listaKnjiga.add(k);
            }
            
            System.out.println("Elementi liste knjiga: " + listaKnjiga.size());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaKnjiga;
    }

    public boolean kreirajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        try {
            String query = "INSERT INTO iznajmljivanje (broj_knjiga, datum_uzimanja, ukupan_iznos, valuta, id_bibliotekar, id_citalac) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, iznajmljivanje.getBrojKnjiga());
            ps.setDate(2, java.sql.Date.valueOf(iznajmljivanje.getDatumUzimanja()));
            ps.setDouble(3, iznajmljivanje.getUkupanIznos());
            ps.setString(4, "DIN");
            ps.setInt(5, iznajmljivanje.getBibliotekar().getId());
            ps.setInt(6, iznajmljivanje.getCitalac().getId());
            
            int affectedRows = ps.executeUpdate();
            System.out.println("Inserted iznajmljivanje rows: " + affectedRows);
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                System.out.println("GENERISAN ID: " + id);
                int rb = 1;
                for(StavkaIznajmljivanja stavka : iznajmljivanje.getListaStavki()){
                    String queryStavka = "INSERT INTO stavka_iznajmljivanja (id_iznajmljivanje, rb, datum_vracanja, broj_dana, iznos_po_danu, iznos, valuta, id_knjiga) VALUES (?,?,?,?,?,?,?,?)";
                    PreparedStatement psStavka = connection.prepareStatement(queryStavka);
                    psStavka.setInt(1, id);
                    psStavka.setInt(2, rb);
                    psStavka.setDate(3, java.sql.Date.valueOf(stavka.getDatumVracanja()));
                    psStavka.setInt(4, stavka.getBrojDana());
                    psStavka.setDouble(5, stavka.getIznosPoDanu());
                    psStavka.setDouble(6, stavka.getIznos());
                    psStavka.setString(7, "DIN");
                    psStavka.setInt(8, stavka.getKnjiga().getId());
                    psStavka.executeUpdate();
                    rb++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    /* -------------------------------------------------------------- */
    
    
    
    
    public AbstractDomainObject selectObject(AbstractDomainObject ado) throws Exception{
        String query = "SELECT * FROM " + ado.tableName() + " " + ado.alias() + " "
                + ado.textJoin() + " WHERE " + ado.getCondition();
        
        System.out.println(query);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<AbstractDomainObject> lista = ado.getList(rs);
        
        if(lista.isEmpty()){
            return null;
        } else {
            return lista.get(0);
        }
    }

    public List<AbstractDomainObject> selectList(AbstractDomainObject ado) throws Exception {
        String query = "SELECT * FROM " + ado.tableName() + " " + ado.alias() + " "
                + ado.textJoin() + " WHERE " + ado.getCondition();
        
        System.out.println(query);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        return ado.getList(rs);
    }
    
    // "INSERT INTO iznajmljivanje (broj_knjiga, datum_uzimanja, ukupan_iznos, valuta, id_bibliotekar, id_citalac) VALUES (?,?,?,?,?,?)"
    // "INSERT INTO stavka_iznajmljivanja (id_iznajmljivanje, rb, datum_vracanja, broj_dana, iznos_po_danu, iznos, valuta, id_knjiga) VALUES (?,?,?,?,?,?,?,?)"
    public int insert(AbstractDomainObject ado) throws Exception {
        int id = -1;
        String query = "INSERT INTO " + ado.tableName() + " (" + ado.insertColumns()
                + ") VALUES (" + ado.insertValues() + ")";
        
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
        String query = "DELETE FROM " + ado.tableName() + " WHERE " + ado.pkName() + "=" + ado.id();
        System.out.println(query);
        
        Statement st = connection.createStatement();
        int rowsAffected = st.executeUpdate(query);
        
        return rowsAffected;
    }
    
    // "UPDATE citalac SET ime=?, prezime=?, email=?, telefon=?, id_kategorija=? WHERE id_citalac=?"
    // MORAM DA IZMENIM UPDATE ZA CITALAC
    public int update(AbstractDomainObject ado) throws Exception{
        String query = "UPDATE " + ado.tableName() + " SET " + ado.updateValues() + " WHERE " + ado.updateCondition();
        System.out.println(query);
        
        Statement st = connection.createStatement();
        int rowsAffected = st.executeUpdate(query);
        
        return rowsAffected;
    }
}