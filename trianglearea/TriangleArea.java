/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Dreiecksfl�che
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package trianglearea;

import static java.lang.Double.*;
import static java.lang.Math.*;

/**
 * Klassenrahmen f�r Dreiecksfl�chenberechnung.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class TriangleArea {

    /**
     * Berechnet die Dreiecksfl�che mit der Heronischen Formel.
     * @param args Eckpunkte (ax,ay), (bx,by) und (cx,cy) des Dreiecks
     */
    public static void main(final String[] args) {
        int i = 0;

        final double ax = parseDouble(args[i++]);
        final double ay = parseDouble(args[i++]);
        final double bx = parseDouble(args[i++]);
        final double by = parseDouble(args[i++]);
        final double cx = parseDouble(args[i++]);
        final double cy = parseDouble(args[i++]);

        final double a = hypot(cx - bx, cy - by);
        final double b = hypot(ax - cx, ay - cy);
        final double c = hypot(bx - ax, by - ay);

        final double s = (a + b + c)/2;
        final double area = sqrt(s*(s - a)*(s - b)*(s - c));

        System.out.println(area);
    }
}

