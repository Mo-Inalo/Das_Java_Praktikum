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
class FlagFR {

    /**
     * Bestimmt Farbe zu gegebener Koordinate der französischen Flagge.
     * @param args Breite, Höhe, x-, y-Koordinate.
     */
    public static void main(final String[] args) {
        int a = 0;
        final int w = Integer.parseInt(args[a++]);
        @SuppressWarnings("unused")
        final int h = Integer.parseInt(args[a++]);
        final int x = Integer.parseInt(args[a++]);
        @SuppressWarnings("unused")
        final int y = Integer.parseInt(args[a++]);
        if(x < w/3)
            System.out.println("red");
        else if(x < 2*w/3)
            System.out.println("white");
        else
            System.out.println("blue");

    }
}
