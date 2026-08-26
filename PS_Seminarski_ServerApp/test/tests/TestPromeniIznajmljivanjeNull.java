package tests;

import static org.junit.Assert.assertThrows;
import org.junit.Test;
import so.SOPromeniIznajmljivanje;

public class TestPromeniIznajmljivanjeNull {

    @Test
    public void testPromeniIznajmljivanjeNull() {

        SOPromeniIznajmljivanje so = new SOPromeniIznajmljivanje();

        assertThrows(Exception.class, () -> {
            so.execute(null);
        });
    }
}