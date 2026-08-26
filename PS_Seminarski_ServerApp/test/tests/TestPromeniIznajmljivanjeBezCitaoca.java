package tests;

import static org.junit.Assert.assertThrows;
import model.Iznajmljivanje;
import org.junit.Test;
import so.SOPromeniIznajmljivanje;

public class TestPromeniIznajmljivanjeBezCitaoca {

    @Test
    public void testPromeniIznajmljivanjeBezCitaoca() {

        Iznajmljivanje iznajmljivanje = new Iznajmljivanje();

        SOPromeniIznajmljivanje so = new SOPromeniIznajmljivanje();

        assertThrows(Exception.class, () -> {
            so.execute(iznajmljivanje);
        });
    }
}