/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Flaggen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package flag;

/**
 * Rahmenklasse für Flagge.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
class FlagJP {

    /**
     * Bestimmt Farbe zu gegebener Koordinate der japanischen Flagge.
     * @param args Breite, Höhe, x-, y-Koordinate.
     */
    public static void main(final String[] args) {
        int a = 0;
        final int w = Integer.parseInt(args[a++]);
        final int h = Integer.parseInt(args[a++]);
        final int x = Integer.parseInt(args[a++]);
        final int y = Integer.parseInt(args[a++]);
        final int r = Math.min(w/3, h/3);
        if(Math.hypot(x - w/2, y - h/2) < r)
            System.out.println("red");
        else
            System.out.println("white");

    }
}
