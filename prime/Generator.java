/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Primzahlen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package prime;

/**
 * Klassenrahmen für Methoden zur Bestimmung von Generatoren zu Primzahlen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 25.05.2008
 */
public class Generator {

    /**
     * Prüft, ob g ein Generator modulo der Primzahl p ist.
     * D.h., ob g^k mod p alle natürlichen Zahlen < p erzeugt.
     * @param g der Generator
     * @param p die Primzahl
     * @return true, wenn g ein Generator mod p ist, sonst false
     */
    public static boolean isGenerator(final int g, final int p) {
        if(g%p == 0)
            return false;
        int x = g;
        int k = 1;
        while(k <= (p - 1)/2  && x != 1) {
            k++;
            x = x*g%p;
        }
        return k > (p - 1)/2;
    }

    /**
     * Bestimmt den kleinsten Generator modulo p.
     * @param p die Primzahl
     * @return den Generator
     */
    public static int generator(final int p) {
        int g = 1;
        while(!isGenerator(g, p))
            g++;
        return g;
    }

    /**
     * Testprogramm für den Generator.
     * @param args
     */
    public static void main(final String[] args) {
        final int p = 7;
        System.out.println(generator(p));
    }

}
