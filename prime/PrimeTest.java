/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Primzahlen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package prime;

/**
 * Klassenrahmen f�r Primzahltest.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 25.05.2008
 */
public class PrimeTest {

    /**
     * Testet, ob n eine Primzahl ist.
     * �berpr�ft alle m�glichen Teiler: aufw�ndig!
     * @param n zu testende nat�rliche Zahl
     * @return true, wenn n eine Primzahl ist
     */
    public static boolean isPrime(final int n) {
        if(n == 2)
            return true;
        if(n%2 == 0)
            return false;
        final int nroot = (int)Math.sqrt(n);
        for(int t = 3; t <= nroot; t += 2)
            if(n%t == 0)
                return false;
        return true;
    }

    /**
     * Bestimmt alle Primzahlen bis 100.
     * @param args wird ignoriert
     */
    public static void main(final String[] args) {
        final int max = 100;
        for(int n = 2; n < max; n++)
            if(isPrime(n))
                System.out.println(n);
    }
}
