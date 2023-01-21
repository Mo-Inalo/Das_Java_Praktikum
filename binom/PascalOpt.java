/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Binomialkoeffizienten
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package binom;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Repräsentiert das Pascalsche Dreieck. Speicheroptimiert: speichert nur das
 * halbe Dreieck.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class PascalOpt {
    private final int[][] triangle;

    /**
     * Konstruiert das Pascalsche Dreieck der Höhe m.
     * @param m Höhe
     */
    public PascalOpt(final int m) {
        triangle = new int[m + 1][];
        for(int n = 0; n <= m; n++) {
            triangle[n] = new int[n / 2 + 1];
            triangle[n][0] = 1;
            for(int k = 1; k < (n + 1) / 2; k++)
                triangle[n][k] = triangle[n - 1][k - 1] + triangle[n - 1][k];
            if(n > 0 && n % 2 == 0)
                triangle[n][n / 2] = 2 * triangle[n - 1][n / 2 - 1];
        }
    }

    /**
     * Entnimmt dem optimierten Pascalschen Dreieck den Binomialkoeffizienten "n über k".
     * @param n
     * @param k
     * @return Binomialkoeffizient "n über k"
     */
    public int bin(final int n, int k) {
        if(k > n / 2)
            k = n - k;
        if(k < 0)
            return 0;
        return triangle[n][k];
    }

    @Override
    public String toString() {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        final int m = triangle.length - 1;
        for(int n = 0; n <= m; n++) {
            for(int l = 0; l < m - n; l++)
                printWriter.print("  ");
            for(int k = 0; k <= n; k++)
                printWriter.printf("%4d", bin(n, k));
            printWriter.println();
        }
        return stringWriter.toString();
    }

    /** Teszprogramm.
     * @param args wird ignoriert
     */
    public static void main(final String[] args) {
        final PascalOpt pascal = new PascalOpt(5);
        System.out.println(pascal.bin(3, 2));
        System.out.println(pascal.bin(5, 3));
        System.out.println(pascal.bin(2, 3));
        System.out.println(pascal);
    }

}
