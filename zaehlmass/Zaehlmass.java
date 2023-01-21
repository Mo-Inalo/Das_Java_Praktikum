/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Dutzend, Schock, Gros
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package zaehlmass;

/**
 * Klassenrahmen für Zaehlmass.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class Zaehlmass {

    /**
     * Liest eine ganze Zahl n >= 0 von der Kommandozeile und gibt
     * vier Werte für die Anzahl Gros, Schock, Dutzend und Einzelne aus (alle >= 0).
     * @param args die zu zerlegende Zahl n
     */
    public static void main(final String[] args) {
        int n = Integer.parseInt(args[0]);
        final int gros = n/144;
        n = n%144;
        final int schock = n/60;
        n = n%60;
        final int dutzend = n/12;
        n = n%12;
        final int einzelne = n;
        System.out.printf("%d %d %d %d\n", gros, schock, dutzend, einzelne);
    }
}
