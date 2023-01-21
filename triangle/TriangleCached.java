/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Dreiecke
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package triangle;

/**
 * TriangleCached repräsentiert eine beschleunigte Triangle-Klasse.
 * Unveränderliche Ergebnisse der Triangle-Methoden werden nur einmal
 * im Konstruktor berechnet und in zusätzlichen Instanzvariablen gespeichert.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class TriangleCached extends Triangle {
    private final double cachedPerimeter;
    private final double cachedArea;
    private final Point cachedLowerLeft;
    private final Point cachedUpperRight;

    /**
     * Erzeugt ein Dreieck mit gegebenen Eckpunkten p0, p1, p2.
     * Speichert unveränderliche Methodenergebnisse in Instanzvariablen.
     * @param p0
     * @param p1
     * @param p2
     */
    public TriangleCached(final Point p0, final Point p1, final Point p2) {
        super(p0, p1, p2);
        final double a = p0.distance(p1);
        final double b = p1.distance(p2);
        final double c = p2.distance(p0);
        cachedPerimeter = a + b + c;
        final double s = cachedPerimeter/2;
        cachedArea = Math.sqrt(s*(s - a)*(s - b)*(s - c));

        cachedLowerLeft = new Point(Math.min(p0.x(), Math.min(p1.x(), p2.x())),
                                    Math.min(p0.y(), Math.min(p1.y(), p2.y())));
        cachedUpperRight = new Point(Math.max(p0.x(), Math.max(p1.x(), p2.x())),
                                     Math.max(p0.y(), Math.max(p1.y(), p2.y())));
    }

    @Override
    public double perimeter() {
        return cachedPerimeter;
    }

    @Override
    public double area() {
        return cachedArea;
    }

    @Override
    public Point lowerLeft() {
        return cachedLowerLeft;
    }

    @Override
    public Point upperRight() {
        return cachedUpperRight;
    }

    /**
     * Testprogramm für Dreiecke.
     * @param args
     */
    public static void main(final String[] args) {
        final Triangle t1 = new TriangleCached(new Point(-1, 0),
                                         new Point(0, 2),
                                         new Point(1, 0));
        System.out.println(t1.perimeter());
        System.out.println(t1.area());
        Triangle t2 = t1;
        t2 = t2.zoomed(new Point(0, 1), -1);
        t2 = t2.moved(0, -1);
        System.out.println(t2.lowerLeft().x());
        System.out.println(t2.lowerLeft().y());
        System.out.println(t2.upperRight().x());
        System.out.println(t2.upperRight().y());
    }

}
