/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Dreiecke
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package triangle;

/**
 * Triangle repräsentiert Dreiecke in der kartesischen Koordinatenebene.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class Triangle {

    /**
     * Drei Eckpunkte.
     */
    private final Point p0;
    private final Point p1;
    private final Point p2;

    public Point p0() {
        return p0;
    }

    public Point p1() {
        return p1;
    }

    public Point p2() {
        return p2;
    }

    public Triangle(final Point p0, final Point p1, final Point p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Liefert den Umfang dieses Dreiecks.
     * @return den Umfang
     */
    public double perimeter() {
        return p0.distance(p1) + p1.distance(p2) + p2.distance(p0);
    }

    /**
     * Liefert die Fläche dieses Dreiecks.
     * @return die Fläche
     */
    public double area() {
        final double a = p0.distance(p1);
        final double b = p1.distance(p2);
        final double c = p2.distance(p0);
        final double s = (a + b + c)/2;
        return Math.sqrt(s*(s - a)*(s - b)*(s - c));
    }

    /**
     * Liefert die linke untere Ecke des umschreibenden Rechtecks dieses Dreiecks.
     * @return die linke untere Ecke
     */
    public Point lowerLeft() {
        return new Point(Math.min(p0.x(), Math.min(p1.x(), p2.x())),
                         Math.min(p0.y(), Math.min(p1.y(), p2.y())));
    }

    /**
     * Liefert die rechte obere Ecke des umschreibenden Rechtecks dieses Dreiecks.
     * @return die rechte obere Ecke
     */
    public Point upperRight() {
        return new Point(Math.max(p0.x(), Math.max(p1.x(), p2.x())),
                         Math.max(p0.y(), Math.max(p1.y(), p2.y())));
    }

    /**
     * Vergleicht die drei Eckpunkte dieses Dreiecks mit drei anderen Punkten
     * in der gegebenen Reihenfolge.
     * @param q0 Vergleichspunkt
     * @param q1 Vergleichspunkt
     * @param q2 Vergleichspunkt
     * @param within Genauigkeitsschranke
     * @return true, wenn die korreapondierenden Punkte übereinstimmen, sonst false
     */
    private boolean match3Points(final Point q0, final Point q1, final Point q2, final double within) {
        return p0.isSame(q0, within)  &&  p1.isSame(q1, within)  &&  p2.isSame(q2, within);
    }

    /**
     * Prüft, ob dieses Dreieck und das Dreieck t zusammenfallen.
     * Der Abstand der drei entsprechenden Eckpunkte darf dabei
     * horizontal und vertikal um jeweils maximal within abweichen.
     * @param t != 0, das andere Dreieck
     * @param within Genauigkeitsschranke
     * @return true, wenn die Dreiecke übereinstimmen, sonst false
     */
    public boolean isSame(final Triangle t, final double within) {
        return match3Points(t.p0(), t.p1(), t.p2(), within)
            || match3Points(t.p0(), t.p2(), t.p1(), within)
            || match3Points(t.p1(), t.p0(), t.p2(), within)
            || match3Points(t.p1(), t.p2(), t.p0(), within)
            || match3Points(t.p2(), t.p0(), t.p1(), within)
            || match3Points(t.p2(), t.p1(), t.p0(), within);
    }

    /**
     * Erzeugt ein horizontal um dx und vertikal um dy verschobenes Dreieck.
     * @param dx delta-x
     * @param dy delta-y
     * @return das neue, verschobene Dreieck
     */
    public Triangle moved(final double dx, final double dy) {
        return new Triangle(p0.moved(dx, dy),
                            p1.moved(dx, dy),
                            p2.moved(dx, dy));
    }

    /**
     * Erzeugt ein um den Faktor f gestrecktes Dreieck.
     * Zentrum der Streckung ist der Koordinatenursprung.
     * @param f Streckungsfaktor
     * @return das neue, gestreckte Dreieck
     */
    public Triangle zoomed(final double f) {
        return new Triangle(new Point(p0.x()*f, p0.y()*f),
                            new Point(p1.x()*f, p1.y()*f),
                            new Point(p2.x()*f, p2.y()*f));
    }

    /**
     * Erzeugt ein um den Faktor f gestrecktes Dreieck.
     * Zentrum der Streckung ist der Punkt p.
     * @param p das Zentrum der Streckung
     * @param f Streckungsfaktor
     * @return das neue, gestreckte Dreieck
     */
    public Triangle zoomed(final Point p, final double f) {
        return moved(-p.x(), -p.y()).zoomed(f).moved(p.x(), p.y());
    }

    /**
     * Testprogramm für Dreiecke.
     * @param args
     */
    public static void main(final String[] args) {
        final Triangle t1 = new Triangle(new Point(-1, 0),
                                         new Point( 0, 2),
                                         new Point( 1, 0));
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


