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
class FlagSE {

    /**
     * Bestimmt Farbe zu gegebener Koordinate der schwedischen Flagge.
     * @param args Breite, Höhe, x-, y-Koordinate.
     */
    public static void main(final String[] args) {
        int a = 0;
        final int w = Integer.parseInt(args[a++]);
        final int h = Integer.parseInt(args[a++]);
        final int x = Integer.parseInt(args[a++]);
        final int y = Integer.parseInt(args[a++]);
        final int b = Math.min(w/10, h/10);
        if(Math.abs(y - h/2) <= b/2)
            System.out.println("yellow");
        else if(Math.abs(x - w/3) <= b/2)
            System.out.println("yellow");
        else
            System.out.println("blue");
    }
}
