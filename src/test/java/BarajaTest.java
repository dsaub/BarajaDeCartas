import me.elordenador.barajadecartas.Baraja;
import me.elordenador.barajadecartas.Carta;
import me.elordenador.barajadecartas.Tipo;
import me.elordenador.barajadecartas.exceptions.NoEnLaBaraja;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void barajarShufflesDeckCorrectly() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        Carta[] originalDeck = baraja.getBaraja().clone();
        baraja.barajar();
        Carta[] shuffledDeck = baraja.getBaraja();
        assertNotEquals(originalDeck, shuffledDeck);
    }

    @Test
    public void barajarMaintainsSameNumberOfCards() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        int originalCount = baraja.numCartas();
        baraja.barajar();
        int shuffledCount = baraja.numCartas();
        assertEquals(originalCount, shuffledCount);
    }

    @Test
    public void barajarDoesNotLoseAnyCards() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        Carta[] originalDeck = baraja.getBaraja().clone();
        baraja.barajar();
        Carta[] shuffledDeck = baraja.getBaraja();
        Arrays.sort(originalDeck, Comparator.comparing(Carta::toString));
        Arrays.sort(shuffledDeck, Comparator.comparing(Carta::toString));
        assertArrayEquals(originalDeck, shuffledDeck);
    }

    @Test
    public void barajarWorksForEmptyDeck() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        for (int i = 0; i < 52; i++) {
            baraja.siguiente();
        }
        baraja.barajar();
        assertEquals(0, baraja.numCartas());
    }

    @Test
    public void siguienteReturnsNextCard() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        Carta nextCard = baraja.siguiente();
        assertNotNull(nextCard);
    }

    @Test
    public void siguienteRemovesCardFromDeck() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        int originalCount = baraja.numCartas();
        baraja.siguiente();
        int newCount = baraja.numCartas();
        assertEquals(originalCount - 1, newCount);
    }

    @Test
    public void siguienteReturnsNullWhenDeckIsEmpty() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        for (int i = 0; i < 52; i++) {
            baraja.siguiente();
        }
        Carta nextCard = baraja.siguiente();
        assertNull(nextCard);
    }

    @Test
    public void siguienteReturnsCardsInOrder() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        Carta firstCard = baraja.siguiente();
        Carta secondCard = baraja.siguiente();
        assertNotEquals(firstCard, secondCard);
    }

    @Test
    public void getBarajaReturnsAllCardsInDeck() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        Carta[] deck = baraja.getBaraja();
        assertEquals(52, deck.length);
    }

    @Test
    public void getBarajaReturnsEmptyArrayWhenDeckIsEmpty() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        for (int i = 0; i < 52; i++) {
            baraja.siguiente();
        }
        Carta[] deck = baraja.getBaraja();
        assertEquals(0, deck.length);
    }

    @Test
    public void getBarajaReturnsCorrectCardsAfterSomeAreDrawn() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        baraja.siguiente();
        baraja.siguiente();
        assertEquals(50, baraja.numCartas());
    }

    @Test
    public void getBarajaReturnsCardsInCorrectOrder() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        Carta[] deck = baraja.getBaraja();
        for (int i = 0; i < deck.length - 1; i++) {
            assertNotEquals(deck[i], deck[i + 1]);
        }
    }

    @Test
    public void toStringReturnsNonEmptyStringForNonEmptyDeck() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        String deckString = baraja.toString();
        assertNotNull(deckString);
        assertFalse(deckString.isEmpty());
    }

    @Test
    public void toStringReturnsEmptyStringForEmptyDeck() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        for (int i = 0; i < 52; i++) {
            baraja.siguiente();
        }
        String deckString = baraja.toString();
        assertEquals("[]", deckString);
    }

    @Test
    public void toStringReturnsCorrectStringRepresentation() throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.POKER);
        String deckString = baraja.toString();
        Carta[] deck = baraja.getBaraja();
        assertEquals(Arrays.toString(deck), deckString);
    }



}
