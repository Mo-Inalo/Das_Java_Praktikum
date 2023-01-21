/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Dreiecke
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package triangle;

/**
 * Punkt in der kartesischen Koordinatenebene.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class Point {
    private final double x;
    private final double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    /**
     * Liefert den euklidischen Abstand zwischen p und diesem Punkt.
     * @param p != null, der andere Punkt
     * @return den Abstand
     */
    public double distance(final Point p) {
        return Math.hypot(x - p.x(), y - p.y());
    }

    /**
     * Gibt Auskunft ob dieser Punkt und der Punkt p zusammenfallen.
     * Der Abstand der beiden Punkte darf dazu maximal within betragen.
     * @param p != null, der andere Punkt
     * @param within Genauigkeitsgrenze
     * @see equals
     * @return true, wenn der Abstand der Punkte höchstens within ist, sonst false
     */
    public boolean isSame(final Point p, final double within) {
        return distance(p) < within;
    }

    /**
     * Liefert einen neuen Punkt, der gegenüber diesem Punkt um (dy,dy) verschoben ist.
     * @param dx Delta-x
     * @param dy Delta-y
     * @return verschobener, neuer Punkt
     */
    public Point moved(final double dx, final double dy) {
        return new Point(x + dx, y + dy);
    }

    /**
     * Testprogramm für Punkte.
     * @param args
     */
    public static void main(final String[] args) {
        final Point p1 = new Point(1, 2);
        System.out.printf("p1(%f,%f)%n", p1.x(), p1.y());
        final Point p2 = p1.moved(-2, -3);
        System.out.printf("distance: %f%n", p1.distance(p2));
        System.out.printf("isSame: %b%n", p1.isSame(p2.moved(2, 3), 1e-6));
    }

}
