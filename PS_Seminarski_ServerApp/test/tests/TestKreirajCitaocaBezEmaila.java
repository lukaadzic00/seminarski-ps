package tests;

import model.Citalac;
import model.Kategorija;
import model.KategorijaCitaoca;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import so.SOKreirajCitaoca;

public class TestKreirajCitaocaBezEmaila {

    @Test
    public void testKreirajCitaocaBezEmaila() throws Exception {

        KategorijaCitaoca kategorija =
                new KategorijaCitaoca(1, Kategorija.OSNOVAC, 0);

        Citalac citalac = new Citalac(
                0,
                "Petar",
                "Petrovic",
                null,
                "+381641234567",
                kategorija
        );

        SOKreirajCitaoca so = new SOKreirajCitaoca();

        assertThrows(Exception.class, () -> {
            so.execute(citalac);
        });
    }
}