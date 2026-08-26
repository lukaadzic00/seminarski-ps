/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import java.util.List;
import static junit.framework.Assert.assertNotNull;
import model.Bibliotekar;
import org.junit.Test;
import so.SOVratiListuSviBibliotekari;

/**
 *
 * @author lukaa
 */
public class TestVratiListuSviBibliotekari {
    
    @Test
    public void testVratiSveBibliotekare() throws Exception {

        // Arrange
        SOVratiListuSviBibliotekari so = new SOVratiListuSviBibliotekari();
        Bibliotekar bibliotekar = new Bibliotekar();

        // Act
        so.execute(bibliotekar);
        List<Bibliotekar> rezultat = so.getListaBibliotekara();

        // Assert
        assertNotNull(rezultat);
        if(!rezultat.isEmpty()) {
            for(Bibliotekar b : rezultat) {
                assertNotNull(b);
                assertNotNull(b.getId());
                assertNotNull(b.getIme());
                assertNotNull(b.getPrezime());
                assertNotNull(b.getEmail());
                assertNotNull(b.getKorisnickoIme());
                assertNotNull(b.getSifra());
            }
        }
    }
}