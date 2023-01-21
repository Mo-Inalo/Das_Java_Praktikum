/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Primzahlen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package prime;

import java.util.Random;
import static power.Power.*;

/**
 * Klassenrahmen f�r den Fermat'schen Primzahltest.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 25.05.2008
 */
public class Fermat {
    final static Random rng = new Random();

    /**
     * Fermat-Test pr�ft, ob a^(n-1) mod n = 1 ist.
     * @param n Modulus
     * @param a Basis
     * @return true, wenn n den Fermat-Test �bersteht, sonst false
     */
    public static boolean fermatTest(final int n, final int a) {
        return pow(a, n - 1, n) == 1;
    }

    /**
     * Pr�ft, ob eine nat�rliche Zahl n eine Pseudo-Primzahl ist.
     * Wendet den Fermat-Test mit 100 Zufallszahlen a auf n an.
     * Die Wahrscheinlichkeit, dass eine zusammengesetzte Zahl n den Test �bersteht,
     * ist klein, meist kleiner als 2^-100.
     * Allerdings gibt es Ausnahmen, sog. Charmichael-Zahlen, z.B. n = 561 = 3*11*17.
     * @param n die zu testende Zahl
     * @return
     */
    public static boolean isPrime(final int n) {
        for(int i = 0; i < 100; i++)
            if(!fermatTest(n, rng.nextInt(n - 1) + 1))
                return false;
        return true;
    }

    /**
     * Erzeugt eine Pseudo-Primzahl.
     * @return die Pseudo-Primzahl
     */
    public static int randomPrime() {
        int n;
        do
            n = rng.nextInt(Integer.MAX_VALUE - 1) + 2;
        while(!isPrime(n));
        return n;
    }

    /**
     * Testprogramm f�r den Fermat-Test.
     * @param args wird ignoriert
     */
    public static void main(final String[] args) {
        System.out.println(fermatTest(999983, 17));             // true
        System.out.println(fermatTest(999984, 17));             // false
        System.out.println(fermatTest(Integer.MAX_VALUE, 17));  // true
        System.out.println(isPrime(Integer.MAX_VALUE));         // true
        // Bei Charmichael-Zahlen versagt der Fermat-Test:
        System.out.println(fermatTest(561, 13));                // true
        System.out.println(randomPrime());
    }

}
