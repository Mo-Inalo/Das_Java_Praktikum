/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Spielkarten
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package poker;

/**
 * Definiert den Rang eines Handblatts.
 * Aufsteigend geordnet.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 17.06.2008
 */
public enum Rank {
    HighCard(new CardMatcher() {
        public boolean matches(final Hand hand) {
            return true;
        }
    }),
    OnePair(new CardMatcher() {
        public boolean matches(final Hand hand) {
            for(final int i: hand.byKind().values())
                if(i == 2)
                    return true;
            return false;
        }
    }),
    ThreeOfAKind(new CardMatcher() {
        public boolean matches(final Hand hand) {
            for(final int i: hand.byKind().values())
                if(i == 3)
                    return true;
            return false;
        }
    }),
    TwoPair(new CardMatcher() {
        public boolean matches(final Hand hand) {
            int pairs = 0;
            for(final int i: hand.byKind().values())
                if(i == 2)
                    pairs++;
            return pairs == 2;
        }
    }),
    Straight(new CardMatcher() {
        public boolean matches(final Hand hand) {
            return hand.longestRun() == 5;
        }
    }),
    Flush(new CardMatcher() {
        public boolean matches(final Hand hand) {
            for(final int i: hand.bySuit().values())
                if(i == 5)
                    return true;
            return false;
        }
    }),
    FullHouse(new CardMatcher() {
        public boolean matches(final Hand hand) {
            return OnePair.cardMatcher.matches(hand)  && ThreeOfAKind.cardMatcher.matches(hand);
        }
    }),
    StraightFlush(new CardMatcher() {
        public boolean matches(final Hand hand) {
            return Straight.cardMatcher.matches(hand)  && Flush.cardMatcher.matches(hand);
        }
    }),
    RoyalFlush(new CardMatcher() {
        public boolean matches(final Hand hand) {
            return StraightFlush.cardMatcher.matches(hand)  && hand.byKind().get(Kind.A) > 0;
        }
    });

    /**
     * Vergleicher für den Rang.
     */
    private final CardMatcher cardMatcher;

    /**
     * Vergleicht Rang mit Handblatt.
     * @param hand Handblatt.
     * @return
     */
    boolean matches(final Hand hand) {
        return cardMatcher.matches(hand);
    }

    /**
     * Erzeugt einen Wert des Aufzählungstyps.
     * @param cm Vergleicher für den Wert
     */
    Rank(final CardMatcher cm) {
        cardMatcher = cm;
    }

    ///////////////////////////////////////////////////
    /**
     * Repräsentiert Vergleich von Rang mit Handblatt.
     * Geschachteltes Interface.
     *
     * @author Klaus Köhler, koehler@hm.edu
     * @author Reinhard Schiedermeier, rs@cs.hm.edu
     * @version 17.06.2008
     */
    private interface CardMatcher {
        boolean matches(final Hand hand);
    }
    ///////////////////////////////////////////////////
}
