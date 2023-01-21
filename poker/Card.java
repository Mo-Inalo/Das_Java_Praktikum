/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Spielkarten
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package poker;

/**
 * Definiert eine Spielkarte.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 17.06.2008
 */
public class Card {
    /**
     * Farbe.
     */
    private final Suit suit;

    /**
     * Wert.
     */
    private final Kind kind;

    public Card(final Suit s, final Kind k) {
        suit = s;
        kind = k;
    }

    public Kind kind() {
        return kind;
    }

    public Suit suit() {
        return suit;
    }

}
