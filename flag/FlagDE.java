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
class FlagDE {

    /**
     * Bestimmt Farbe zu gegebener Koordinate der deutschen Flagge.
     * @param args Breite, Höhe, x-, y-Koordinate.
     */
    public static void main(final String[] args) {
        int a = 0;
        @SuppressWarnings("unused")
        final int w = Integer.parseInt(args[a++]);
        final int h = Integer.parseInt(args[a++]);
        @SuppressWarnings("unused")
        final int x = Integer.parseInt(args[a++]);
        final int y = Integer.parseInt(args[a++]);
        if(y < h/3)
            System.out.println("gold");
        else if(y < 2*h/3)
            System.out.println("red");
        else
            System.out.println("black");

    }
}
