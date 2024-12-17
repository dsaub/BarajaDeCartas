import me.elordenador.barajadecartas.Carta;
import me.elordenador.barajadecartas.Tipo;
import me.elordenador.barajadecartas.exceptions.NoEnLaBaraja;
import me.elordenador.barajadecartas.tipos.Alemana;
import me.elordenador.barajadecartas.tipos.Espanola;
import me.elordenador.barajadecartas.tipos.Poker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartaTest {

    @Test
    public void canCreateCard() throws NoEnLaBaraja {
        Carta carta1 = new Carta(1, Espanola.OROS, Tipo.ESPANOLA);
        Carta carta2 = new Carta(2, Espanola.ESPADAS, Tipo.ESPANOLA);
        Carta carta3 = new Carta(3, Espanola.BASTOS, Tipo.ESPANOLA);
        Carta carta4 = new Carta(4, Espanola.ESPADAS, Tipo.ESPANOLA);
        Carta carta5 = new Carta(5, Poker.CORAZONES, Tipo.POKER);
        Carta carta6 = new Carta(6, Poker.DIAMANTES, Tipo.POKER);
        Carta carta7 = new Carta(7, Poker.PICAS, Tipo.POKER);
        Carta carta8 = new Carta(8, Poker.PICAS, Tipo.POKER);
        Carta carta9 = new Carta(9, Espanola.OROS, Tipo.ESPANOLA_EXTENDIDA);
        Carta carta10 = new Carta(Espanola.SOTA, Espanola.COPAS, Tipo.ESPANOLA_EXTENDIDA);
        Carta carta11 = new Carta(Espanola.CABALLO, Espanola.ESPADAS, Tipo.ESPANOLA_EXTENDIDA);
        Carta carta12 = new Carta(Espanola.REY, Espanola.BASTOS, Tipo.ESPANOLA_EXTENDIDA);
        Carta carta13 = new Carta(6, Alemana.CAMPANAS, Tipo.ALEMANA);
        Carta carta14 = new Carta(7, Alemana.PASTOS, Tipo.ALEMANA);
        Carta carta15 = new Carta(8, Alemana.CORAZONES, Tipo.ALEMANA);
        Carta carta16 = new Carta(9, Alemana.BELLOTAS, Tipo.ALEMANA);
        System.out.println("Can Create Card -> SUCCESS");
    }

    @Test
    public void generalIllegal() throws Exception {
        try {
            Carta carta = new Carta(0, Espanola.OROS, Tipo.ESPANOLA);
            System.out.println("generalIllegal -> FAILURE (No Exception Detected)");
            throw new Exception("FAIL");
        } catch (NoEnLaBaraja e) {

        }
        System.out.println("generalIllegal -> SUCCESS");
    }

    @Test
    public void espanyaCreateIllegalCardLaunchException() throws Exception {
        try {
            Carta carta = new Carta(8, Espanola.OROS, Tipo.ESPANOLA);
            System.out.println("EspanyaCreateIllegalCardLaunchException -> FAILURE (No Exception Detected)");
            throw new Exception("FAIL");
        } catch (NoEnLaBaraja e) {

        }
        System.out.println("EspanyaCreateIllegalCardLaunchException -> SUCCESS");
    }

    @Test
    public void espanyaIllegalMaximumExceeded() throws Exception {
        try {
            Carta carta = new Carta(13, Espanola.OROS, Tipo.ESPANOLA);
            System.out.println("EspanyaIllegalMaximumExceeded -> FAILURE (No Exception Detected)");
            throw new Exception("FAIL");
        } catch (NoEnLaBaraja e) {

        }
        System.out.println("EspanyaIllegalMaximumExceeded -> SUCCESS");
    }

    @Test
    public void alemaniaIllegalRedux() throws Exception {
        try {
            Carta carta = new Carta(5, Alemana.BELLOTAS, Tipo.ALEMANA);
            System.out.println("alemaniaIllegalRedux -> FAILURE (No Exception Detected)");
            throw new Exception("FAIL");
        } catch (NoEnLaBaraja e) {

        }
        System.out.println("alemaniaIllegalRedux -> SUCCESS");
    }

    @Test
    public void alemaniaIllegalMax() throws Exception {
        try {
            Carta carta = new Carta(15, Alemana.BELLOTAS, Tipo.ALEMANA);
            System.out.println("alemaniaIllegalMax -> FAILURE (No Exception Detected)");
            throw new Exception("FAIL");
        } catch (NoEnLaBaraja e) {

        }
        System.out.println("alemaniaIllegalMax -> SUCCESS");
    }

    @Test
    public void pokerIllegalMax() throws Exception {
        try {
            Carta carta = new Carta(14, Poker.PICAS, Tipo.POKER);
            System.out.println("pokerIllegalMax -> FAILURE (No Exception Detected)");
            throw new Exception("FAIL");
        } catch (NoEnLaBaraja e) {

        }
        System.out.println("pokerIllegalMax -> SUCCESS");
    }

    @Test
    public void setterTest() throws NoEnLaBaraja {
        Carta carta = new Carta(6, Espanola.OROS, Tipo.ESPANOLA);

        carta.setTipo(Tipo.ALEMANA);
        carta.setValor(7);
        carta.setPalo(Alemana.BELLOTAS);
        System.out.println("setterTest -> SUCCESS");
    }

    @Test
    public void getterTest() throws NoEnLaBaraja {
        Carta carta = new Carta(6, Espanola.OROS, Tipo.ESPANOLA);

        assertEquals(6, carta.getValor());
        assertEquals(Espanola.OROS, carta.getPalo());
        assertEquals(Tipo.ESPANOLA, carta.getTipo());
        System.out.println("getterTest -> SUCCESS");
    }

    @Test
    public void toStringTest() throws NoEnLaBaraja {
        Carta carta = new Carta(6, Espanola.OROS, Tipo.ESPANOLA);
        Carta carta1 = new Carta(Poker.KING, Poker.DIAMANTES, Tipo.POKER);
        assertEquals("6 Oros", carta.toString());
        assertEquals("K Diamantes", carta1.toString());

    }

    @Test
    public void equalsTest() throws NoEnLaBaraja {
        Carta carta1 = new Carta(6, Espanola.OROS, Tipo.ESPANOLA);
        Carta carta2 = new Carta(6, Espanola.OROS, Tipo.ESPANOLA);
        assertEquals(true, carta1.equals(carta2));
    }

    @Test
    public void equalsNumberReturnsTrueForEqualValues() throws NoEnLaBaraja {
        Carta carta1 = new Carta(6, Espanola.OROS, Tipo.ESPANOLA);
        Carta carta2 = new Carta(6, Espanola.ESPADAS, Tipo.ESPANOLA);
        assertEquals(true, carta1.equalsNumber(carta2));
    }

    @Test
    public void equalsNumberReturnsFalseForDifferentValues() throws NoEnLaBaraja {
        Carta carta1 = new Carta(6, Espanola.OROS, Tipo.ESPANOLA);
        Carta carta2 = new Carta(7, Espanola.ESPADAS, Tipo.ESPANOLA);
        assertEquals(false, carta1.equalsNumber(carta2));
    }

    @Test
    public void equalsNumberReturnsFalseForNullCard() throws NoEnLaBaraja {
        Carta carta1 = new Carta(6, Espanola.OROS, Tipo.ESPANOLA);
        assertEquals(false, carta1.equalsNumber(null));
    }

    @Test
    public void equalsPaloReturnsTrueForEqualSuits() throws NoEnLaBaraja {
        Carta carta1 = new Carta(6, Espanola.OROS, Tipo.ESPANOLA);
        Carta carta2 = new Carta(7, Espanola.OROS, Tipo.ESPANOLA);
        assertEquals(true, carta1.equalsPalo(carta2));
    }

    @Test
    public void equalsPaloReturnsFalseForDifferentSuits() throws NoEnLaBaraja {
        Carta carta1 = new Carta(6, Espanola.OROS, Tipo.ESPANOLA);
        Carta carta2 = new Carta(7, Espanola.ESPADAS, Tipo.ESPANOLA);
        assertEquals(false, carta1.equalsPalo(carta2));
    }

    @Test
    public void equalsPaloReturnsFalseForNullCard() throws NoEnLaBaraja {
        Carta carta1 = new Carta(6, Espanola.OROS, Tipo.ESPANOLA);
        assertEquals(false, carta1.equalsPalo(null));
    }
}