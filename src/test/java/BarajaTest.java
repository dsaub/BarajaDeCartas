import me.elordenador.barajadecartas.Baraja;
import me.elordenador.barajadecartas.Carta;
import me.elordenador.barajadecartas.Tipo;
import me.elordenador.barajadecartas.exceptions.NoEnLaBaraja;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BarajaTest {
    @Test
    public void barajaInitializesCorrectlyForEspanola() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.ESPANOLA);
        assertNotNull(baraja);
    }

    @Test
    public void barajaInitializesCorrectlyForAlemana() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.ALEMANA);
        assertNotNull(baraja);
    }

    @Test
    public void barajaInitializesCorrectlyForPoker() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        assertNotNull(baraja);
    }

    @Test
    public void barajaInitializesCorrectlyForEspanolaExtendida() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.ESPANOLA_EXTENDIDA);
        assertNotNull(baraja);
    }

    @Test
    public void initializeCardsCreatesCorrectNumberOfCardsForEspanola() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.ESPANOLA);
        assertEquals(40, baraja.numCartas());
    }

    @Test
    public void initializeCardsCreatesCorrectNumberOfCardsForAlemana() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.ALEMANA);
        assertEquals(36, baraja.numCartas());
    }

    @Test
    public void initializeCardsCreatesCorrectNumberOfCardsForPoker() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        assertEquals(52, baraja.numCartas());
    }

    @Test
    public void initializeCardsCreatesCorrectNumberOfCardsForEspanolaExtendida() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.ESPANOLA_EXTENDIDA);
        assertEquals(48, baraja.numCartas());
    }




}
