/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import model.Citalac;
import model.Kategorija;
import model.KategorijaCitaoca;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import so.SOKreirajCitaoca;

/**
 *
 * @author lukaa
 */
public class TestKreirajCitaocaBezImena {
    
    @Test
    public void testKreirajCitaocaBezImena() throws Exception {
         // Arrange
        KategorijaCitaoca kategorija = new KategorijaCitaoca(1, Kategorija.OSNOVAC, 0);

        Citalac citalac = new Citalac(0, null, "Petrovic", "petar@gmail.com", "+381641234567", kategorija);
        SOKreirajCitaoca so = new SOKreirajCitaoca();

        // Act & Assert
        assertThrows(Exception.class, () -> {
            so.execute(citalac);
        });
    }
}
