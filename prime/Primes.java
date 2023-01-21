/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Primzahlen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package prime;

import java.util.Iterator;

/**
 * Definiert einen iterierbaren Primzahlgenerator.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 25.05.2008
 */
public class Primes implements Iterable<Integer> {

    public Iterator<Integer> iterator() {
        return new PrimeIterator();
    }

    /**
     * Definiert einen Iterator über die Primzahlen.
     *
     * @author Klaus Köhler, koehler@hm.edu
     * @author Reinhard Schiedermeier, rs@cs.hm.edu
     * @version 25.05.2008
     */
    private static class PrimeIterator implements Iterator<Integer> {
        /**
         * Erste Primzahl
         */
        private int nextPrime = 2;

        /**
         * Bestimmt die nächste Pseudo-Primzahl.
         */
        public Integer next() {
            final int result = nextPrime;
            if(nextPrime == 2)
                nextPrime = 3;
            else
                do
                    nextPrime += 2;
                while(nextPrime > 0  && !Fermat.isPrime(nextPrime));
            return result;
        }

        /**
         * Überprüft, ob noch eine Pseudo-Primzahl existiert.
         * @return true, wenn noch eine Primzahl < 2^31-1 existiert
         * Achtung: Die Primzahl Integer.MAX_VALUE = 2^31-1 wird nicht mehr gefunden.
         */
        public boolean hasNext() {
            return nextPrime > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("primes are immutable");
        }
    }

    /**
     * Bestimmt die Primzahlen bis 100 und gibt sie aus.
     * @param args
     */
    public static void main(final String[] args) {
        final int max = 100;
        for(final int p: new Primes())
            if(p <= max)
                System.out.println(p);
            else
                break;
    }
}
