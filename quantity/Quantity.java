/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Physikalische Gr��en
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package quantity;

/**
 * Repr�sentiert eine physikalische Gr��e aus Anzahl und Einheit.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 17.06.2008
 */
public class Quantity<U extends Unit> {
    /**
     * Anzahl.
     */
    private final double count;

    /**
     * Einheit.
     */
    private final U unit;

    /**
     * Erzeugt physikalische Gr��e aus Anzahl und Einheit.
     * Negative Anzahlen werden durch den Betrag ersetzt.
     * @param n Anzahl
     * @param u Einheit
     */
    public Quantity(final double n, final U u) {
        count = n;
        unit = u;
    }

    /**
     * Liefert die Anzahl Einheiten dieser Gr��e.
     * @return die Anzahl
     */
    public double count() {
        return count;
    }

    /**
     * Liefert die Einheit dieser Gr��e.
     * @return
     */
    public U unit() {
        return unit;
    }

    /**
     * Liefert diese Gr��e in einer anderen, gegebenen Einheit der gleichen Dimension.
     * @param u die gegebene Einheit
     * @return die Gr��e in der gegebenen Einheit
     */
    public Quantity<U> as(final U u) {
        return new Quantity<U>(count()*unit.baseUnits()/u.baseUnits(), u);
    }

    /**
     * Liefert die Summe aus dieser Gr��e und einer anderen Gr��e
     * mit der gleichen physikalischen Dimension.
     * Das Ergebnis hat die gleiche Einheit wie diese Gr��e.
     * @param other die andere Gr��e
     * @return die Summe
     */
    public Quantity<U> add(final Quantity<U> other) {
        return new Quantity<U>(count + other.as(unit).count(), unit);
    }

    /**
     * Liefert das Produkt dieser Gr��e mit einer anderen Gr��e.
     * @param other die andere Gr��e
     * @return das Produkt
     */
    public <V extends Unit> Quantity<Prod<U, V>> by(final Quantity<V> other) {
        final double c = count*other.count();
        final Prod<U, V> u = new Prod<U, V>(unit, other.unit());
        return new Quantity<Prod<U, V>>(c, u);
    }

    @Override
    public String toString() {
        return count + " " + unit;
    }

    /**
     * Liefert den Quotienten dieser Gr��e mit einer anderen Gr��e.
     * @param other die andere Gr��e
     * @return der Quotient
     */
    public <V extends Unit> Quantity<Ratio<U, V>> per(final Quantity<V> other) {
        final double c = count/other.count();
        final Ratio<U, V> u = new Ratio<U, V>(unit, other.unit());
        return new Quantity<Ratio<U, V>>(c, u);
    }

    /**
     * Testprogramm f�r physikalische Gr��en.
     * @param args
     */
    public static void main(final String[] args) {
        final Quantity<Time> t1 = new Quantity<Time>(10000, Time.s);
        final Quantity<Time> t2 = new Quantity<Time>(1, Time.d);
        System.out.println(t1.add(t2));

        final Quantity<Length> b = new Quantity<Length>(2, Length.m);
        System.out.println(b.by(b).by(b));
        final Quantity<Length> d = new Quantity<Length>(2, Length.m);
        final Quantity<Time> t = new Quantity<Time>(0.5, Time.s);
        final Ratio<Length, Time> apd = new Ratio<Length, Time>(Length.A, Time.d);
        System.out.println(d.per(t).as(apd));
    }

}
