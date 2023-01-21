/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Zahlenfolgen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package sequence;

/**
 * Klassenrahmen für das Sieb des Eratosthenes.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class Eratosthenes {
    
    /**
     * Bestimmt alle Primzahlen bis 100 und gibt sie aus.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        Sequence primes = new Range(2, 100);
        while(primes.hasNext()) {
            final int prime = primes.next();
            System.out.println(prime);
            primes = new ZapMultiples(primes, prime);
        }

    }

}
