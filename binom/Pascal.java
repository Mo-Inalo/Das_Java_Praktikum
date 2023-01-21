/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Binomialkoeffizienten
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package binom;

/**
 * Repräsentiert das Pascalsche Dreieck der Binomialkoeffizienten.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class Pascal {
    private final int[][] triangle;

    /**
     * Konstruiert das Pascalsche Dreieck der Höhe m.
     * @param m Höhe
     */
    public Pascal(final int m) {
        triangle = new int[m + 1][];
        for(int n = 0; n <= m; n++) {
            triangle[n] = new int[n + 1];
            triangle[n][0] = 1;
            triangle[n][n] = 1;
            for(int k = 1; k < n; k++)
                triangle[n][k] = triangle[n - 1][k - 1] + triangle[n - 1][k];
        }
    }

    /**
     * Entnimmt dem Pascalschen Dreieck den Binomialkoeffizienten "n über k".
     * @param n
     * @param k
     * @return Binomialkoeffizient "n über k"
     */
    public int bin(final int n, final int k) {
        if(k < 0 || k > n)
            return 0;
        return triangle[n][k];
    }

    @Override
    public String toString() {
        final int m = triangle.length - 1;
        String s = "";
        for(int n = 0; n <= m; n++) {
            for(int l = 0; l < m - n; l++)
                s += "  ";
            for(int k = 0; k <= n; k++)
                s += String.format("%4d", bin(n, k));
            s += '\n';
        }
        return s;

    }

    /**
     * Testprogramm.
     * @param args wird ignoriert
     */
    public static void main(final String[] args) {
        final Pascal pascal = new Pascal(5);
        System.out.println(pascal.bin(3, 2));
        System.out.println(pascal.bin(5, 3));
        System.out.println(pascal.bin(2, 3));
        System.out.println(pascal);
    }

}
