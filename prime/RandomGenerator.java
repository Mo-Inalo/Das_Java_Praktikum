/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Primzahlen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package prime;

import java.util.Iterator;
import static prime.Generator.*;

/**
 * Definiert einen Pseudo-Zufallszahlengenerator.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 25.05.2008
 */
public class RandomGenerator implements Iterable<Integer> {
    /**
     * Große Primzahl.
     */
    private final int prime;

    /**
     * Generator modulo prime.
     */
    private final int generator;

    /**
     * Initialisierungszahl für den Zufallszahlengenerator.
     */
    private final int seed;

    /**
     * Erzeugt einen neuen Zufallszahlengenerator.
     * @param p Primzahl, erzeugte Zufallszahlen sind kleiner als p
     * @param s Seed
     */
    public RandomGenerator(final int p, final int s) {
        prime = p;
        generator = generator(p);
        seed = Math.abs(s)%(p - 1) + 1;
    }

    /**
     * Gibt einen neuen Iterator über Pseudo-Zufallszahlen zurück.
     */
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int random = seed;

            public Integer next() {
                return random = generator*random%prime;
            }

            public boolean hasNext() {
                return true;
            }

            public void remove() {
                throw new UnsupportedOperationException(
                    "cannot remove a number");
            }
        };
    }

    /**
     * Gibt alle natürlichen Zahlen < p in einer zufälligen Reihenfolge aus.
     * @param args Primzahl p und Seed
     */
    public static void main(final String[] args) {
        final int prime = Integer.parseInt(args[0]);
        final int seed = Integer.parseInt(args[1]);
        final Iterator<Integer> rng = new RandomGenerator(prime, seed).iterator();
        for(int i = 1; i < prime; i++)
            System.out.print(rng.next() + " ");
    }

}
