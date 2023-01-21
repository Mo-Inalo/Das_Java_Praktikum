/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Spielkarten
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package poker;

import java.util.EnumMap;

/**
 * Definiert ein Handblatt.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 17.06.2008
 */
public class Hand {
    /**
     * Anzahl der Karten.
     */
    public final static int HAND_CARDS = 5;

    /**
     * Abbildung des Kartenwerts auf die Anzahl der Karten mit diesem Wert.
     */
    private final EnumMap<Kind, Integer> byKind = new EnumMap<Kind, Integer>(Kind.class);

    /**
     * Abbildung des Kartenfarbe auf die Anzahl der Karten mit dieser Farbe.
     */
    private final EnumMap<Suit, Integer> bySuit = new EnumMap<Suit, Integer>(Suit.class);

    /**
     * Maximale Anzahl Karten mit aufeinander folgenden Werten.
     */
    private int longestRun = 0;

    /**
     * Rang dieses Handblatts.
     */
    private Rank rank;

    /**
     * Erzeugt ein Handblatt aus den übergebenen Karten und bestimmt den Rang.
     * @param cards die Karten
     */
    public Hand(final Card... cards) {
        if(cards.length != HAND_CARDS)
            throw new IllegalArgumentException("wrong number of cards");
        for(final Kind k: Kind.values())
            byKind.put(k, 0);
        for(final Card c: cards)
            byKind.put(c.kind(), byKind.get(c.kind()) + 1);
        for(final Suit s: Suit.values())
            bySuit.put(s, 0);
        for(final Card c: cards)
            bySuit.put(c.suit(), bySuit.get(c.suit()) + 1);
        int run = 0;
        for(final int i: byKind.values()) {
            if(i == 0)
                run = 0;
            else
                run++;
            if(run > longestRun)
                longestRun = run;
        }
        if(byKind.get(Kind.A) > 0)
            run++;
        if(run > longestRun)
            longestRun = run;
        for(final Rank r: Rank.values())
            if(r.matches(this))
                rank = r;
    }

    public Rank rank() {
        return rank;
    }

    /**
     * Liefert die Häufigkeitstabelle der Werte des Handblatts.
     * Hilfsmethode für Rangbestimmung
     * @return die Häufigkeitstabelle der Werte
     */
    EnumMap<Kind, Integer> byKind() {
        return byKind;
    }

    /**
     * Liefert die Häufigkeitstabelle der Farben des Handblatts.
     * Hilfsmethode für Rangbestimmung
     * @return die Häufigkeitstabelle der Farben
     */
    EnumMap<Suit, Integer> bySuit() {
        return bySuit;
    }

    /**
     * Liefert die maximale Anzahl Karten mit aufeinander folgenden Werten.
     * Hilfsmethode für Rangbestimmung
     * @return Länge der längsten Folge.
     */
    int longestRun() {
        return longestRun;
    }

    /**
     * Testprogramm für Handblatt.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final Hand h = new Hand(new Card(Suit.Spade, Kind.Q),
                                new Card(Suit.Heart, Kind.Q),
                                new Card(Suit.Heart, Kind.N8),
                                new Card(Suit.Spade, Kind.N8),
                                new Card(Suit.Club, Kind.N8));
        System.out.println(h.rank());
    }

}
