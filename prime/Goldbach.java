/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Primzahlen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package prime;

/**
 * Klassenrahmen für die Goldbach-Vermutung.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 25.05.2008
 */
public class Goldbach {

    /**
     * Testet die Goldbach-Vermutung bis zu einer Grenze n:
     * Jede gerade Zahl größer als 2 ist die Summe zweier Primzahlen.
     * @param n
     * @return
     */
    public static boolean testGoldbach(final int n) {
        for(int i = 4; i <= n; i += 2)
            if(!decomposable(i))
                return false;
        return true;
    }

    /**
     * Prüft, ob die natürliche Zahl n in die Summe zweier Primzahlen zerlegbar ist.
     * @param n > 2 die zu testende Zahl
     * @return true, wenn n zerlegbar ist, sonst false
     */
    public static boolean decomposable(final int n) {
        for(final int p : new Primes()) {
            if(Fermat.isPrime(n - p))
                return true;
            if(p > n/2)
                return false;
        }
        return false;
    }

    /**
     * Überprüft die Goldbach-Vermutung bis 1000.
     * @param args wird ignoriert
     */
    public static void main(final String[] args) {
        System.out.println(testGoldbach(1000));
    }

}
