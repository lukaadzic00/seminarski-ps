package tests;

import static org.junit.Assert.assertThrows;
import org.junit.Test;
import so.SOObrisiCitaoca;

public class TestObrisiCitaocaNull {

    @Test
    public void testObrisiCitaocaNull() {

        SOObrisiCitaoca so = new SOObrisiCitaoca();

        assertThrows(Error.class, () -> {
            so.execute(null);
        });
    }
}