import me.elordenador.barajadecartas.tipos.Espanola;
import me.elordenador.barajadecartas.tipos.Poker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantTest {
    @Test
    public void getNReturnsFourForOros() {
        assertEquals(4, Espanola.getN(Espanola.OROS));
    }

    @Test
    public void getNReturnsThreeForBastos() {
        assertEquals(3, Espanola.getN(Espanola.BASTOS));
    }

    @Test
    public void getNReturnsTwoForEspadas() {
        assertEquals(2, Espanola.getN(Espanola.ESPADAS));
    }

    @Test
    public void getNReturnsOneForCopas() {
        assertEquals(1, Espanola.getN(Espanola.COPAS));
    }

    @Test
    public void pgetNReturnsZeroForNull() {
        assertEquals(0, Espanola.getN(null));
    }

    @Test
    public void pgetNReturnsZeroForUnknownSuit() {
        assertEquals(0, Espanola.getN("Unknown"));
    }

    @Test
    public void pgetNReturnsFourForPicas() {
        assertEquals(4, Poker.getN(Poker.PICAS));
    }

    @Test
    public void pgetNReturnsThreeForCorazones() {
        assertEquals(3, Poker.getN(Poker.CORAZONES));
    }

    @Test
    public void pgetNReturnsTwoForTreboles() {
        assertEquals(2, Poker.getN(Poker.TREBOLES));
    }

    @Test
    public void pgetNReturnsOneForDiamantes() {
        assertEquals(1, Poker.getN(Poker.DIAMANTES));
    }

    @Test
    public void pogetNReturnsZeroForNull() {
        assertEquals(0, Poker.getN(null));
    }

    @Test
    public void pogetNReturnsZeroForUnknownSuit() {
        assertEquals(0, Poker.getN("Unknown"));
    }
}
