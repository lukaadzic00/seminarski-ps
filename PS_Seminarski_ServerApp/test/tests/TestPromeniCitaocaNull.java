package tests;

import static org.junit.Assert.assertThrows;
import org.junit.Test;
import so.SOPromeniCitaoca;

public class TestPromeniCitaocaNull {

    @Test
    public void testPromeniCitaocaNull() throws Exception {

        SOPromeniCitaoca so = new SOPromeniCitaoca();

        assertThrows(Exception.class, () -> {
            so.execute(null);
        });
    }
}