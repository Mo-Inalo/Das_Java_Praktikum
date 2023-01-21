/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Potenzieren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package power;

/**
 * Klassenrahmen für Potenzalgorithmus.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 25.05.2008
 */
public class Power {

    /** Berechnet die Potenz a^k mod b mittels "Quadrieren und Multiplizieren".
     * @param a Basis
     * @param k Exponent, ganze Zahl >= 0
     * @param b Modulus
     * @return die Potenz
     */
    public static int pow(final int a, int k, final int b) {
        long x = a;
        long z = 1;
        while(k > 0)
            if(k%2 == 0) {
                k /= 2;
                x = x*x%b;
            } else {
                k--;
                z = z*x%b;
            }
        return (int) z;
    }

    /**
     * Testprogramm für Potenzalgorithmus.
     * @param args wird ignoriert
     */
    public static void main(final String[] args) {
        System.out.println(pow(3, 4, 5));           // 1
        System.out.println(pow(17, 23, 13));        // 10
        System.out.println(pow(3, 1733886101, 5));  // 3
    }

}
