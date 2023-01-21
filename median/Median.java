/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Median
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package median;

/**
 * Rahmenklasse für Medianbestimmung.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
class Median {

    /**
     * Gibt den Median von drei ganzen Zahlen aus.
     * @param args die drei Zahlen
     */
    public static void main(final String[] args) {
        final int a = Integer.parseInt(args[0]);
        final int b = Integer.parseInt(args[1]);
        final int c = Integer.parseInt(args[2]);
        if(a < b)
            if(b < c)
                System.out.println(b);
            else
                if(a < c)
                    System.out.println(c);
                else
                    System.out.println(a);
        else
            if(b < c)
                if(a < c)
                    System.out.println(a);
                else
                    System.out.println(c);
            else
                System.out.println(b);
    }
}
