package tests;

import static org.junit.Assert.assertThrows;
import org.junit.Test;
import so.SOPretraziIznajmljivanje;

public class TestPretraziIznajmljivanjeNull {

    @Test
    public void testPretraziIznajmljivanjeNull() {

        SOPretraziIznajmljivanje so = new SOPretraziIznajmljivanje();

        assertThrows(Exception.class, () -> {
            so.execute(null);
        });
    }
}