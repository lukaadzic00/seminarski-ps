package tests;

import model.Citalac;
import model.Kategorija;
import model.KategorijaCitaoca;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import so.SOKreirajCitaoca;

public class TestKreirajCitaocaBezBrojaTelefona {

    @Test
    public void testKreirajCitaocaBezBrojaTelefona() throws Exception {

        KategorijaCitaoca kategorija =
                new KategorijaCitaoca(1, Kategorija.OSNOVAC, 0);

        Citalac citalac = new Citalac(
                0,
                "Petar",
                "Petrovic",
                "petar@gmail.com",
                null,
                kategorija
        );

        SOKreirajCitaoca so = new SOKreirajCitaoca();

        assertThrows(Exception.class, () -> {
            so.execute(citalac);
        });
    }
}