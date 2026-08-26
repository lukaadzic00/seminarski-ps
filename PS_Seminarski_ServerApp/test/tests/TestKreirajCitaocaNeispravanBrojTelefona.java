package tests;

import model.Citalac;
import model.Kategorija;
import model.KategorijaCitaoca;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import so.SOKreirajCitaoca;

public class TestKreirajCitaocaNeispravanBrojTelefona {

    @Test
    public void testKreirajCitaocaNeispravanBrojTelefona() throws Exception {

        KategorijaCitaoca kategorija =
                new KategorijaCitaoca(1, Kategorija.OSNOVAC, 0);

        Citalac citalac = new Citalac(
                0,
                "Petar",
                "Petrovic",
                "petar@gmail.com",
                "+382641234567",
                kategorija
        );

        SOKreirajCitaoca so = new SOKreirajCitaoca();

        assertThrows(Exception.class, () -> {
            so.execute(citalac);
        });
    }
}