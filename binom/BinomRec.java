/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Binomialkoeffizienten
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package binom;

/**
 * Klassenrahmen für statische Methode zur Bestimmung des Binomialkoeffizienten.
 * 
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class BinomRec {
    /**
     * Bestimmt rekursiv den Binomialkoeffizienten "n über k".
     * @param n
     * @param k
     * @return Binomialkoeffizient "n über k"
     */
    public static int bin(final int n, final int k) {
        if(k > n || k < 0)
            return 0;
        if(k == 0 || k == n)
            return 1;
        return bin(n, k - 1) * (n - k + 1) / k;
    }

    /**
     * Testprogramm.
     * @param args werden ignoriert
     */
    public static void main(final String[] args) {
        System.out.println(bin(3, 2));
        System.out.println(bin(17, 16));
        System.out.println(bin(2, 3));
    }

}
