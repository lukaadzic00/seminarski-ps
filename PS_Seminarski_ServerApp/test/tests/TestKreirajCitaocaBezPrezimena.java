package tests;

import model.Citalac;
import model.Kategorija;
import model.KategorijaCitaoca;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import so.SOKreirajCitaoca;

public class TestKreirajCitaocaBezPrezimena {

    @Test
    public void testKreirajCitaocaBezPrezimenа() throws Exception {

        // Arrange
        KategorijaCitaoca kategorija =
                new KategorijaCitaoca(1, Kategorija.OSNOVAC, 0);

        Citalac citalac = new Citalac(
                0,
                "Petar",
                null,
                "petar@gmail.com",
                "+381641234567",
                kategorija
        );

        SOKreirajCitaoca so = new SOKreirajCitaoca();

        // Act & Assert
        assertThrows(Exception.class, () -> {
            so.execute(citalac);
        });
    }
}