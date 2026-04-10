/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

import javax.swing.JOptionPane;
import model.Bibliotekar;
import model.Knjiga;

/**
 *
 * @author lukaa
 */
public class Session {
    private static Session instance;
    private Bibliotekar ulogovaniBibliotekar;

    private Session() {}

    public static Session getInstance() {
        if(instance == null){
            instance = new Session();
        }
        return instance;
    }

    public Bibliotekar getUlogovaniKorisnik() {
        return ulogovaniBibliotekar;
    }

    public void setUlogovaniKorisnik(Bibliotekar bibliotekar) {
        this.ulogovaniBibliotekar = bibliotekar;
    }
}
