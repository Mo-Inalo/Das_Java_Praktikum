/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Binomialkoeffizienten
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package binom;

/**
 * Klassenrahmen für Hauptprogramm zur Bestimmung von Binomialkoeffizienten.
 * 
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class Binom {
    /**
     * Bestimmt iterativ den Binomialkoeffizienten "n über k". 
     * Gibt ihn auf der Konsole aus.
     * @param args Kommandozeilenparameter n und k
     */
    public static void main(final String[] args) {
        final int n = Integer.parseInt(args[0]);
        final int k = Integer.parseInt(args[1]);
        int bin = 0;

        if(k <= n) {
            bin = 1;
            for(int i = 1; i <= k; i++)
                bin = bin * (n - i + 1) / i;
        }
        System.out.println(bin);
    }

}
