/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Physikalische Größen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package quantity;

/**
 * Definiert eine Entfernung in einer beliebigen Längeneinheit.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 17.06.2008
 */
public class Distance {
    /**
     * Anzahl der Längeneinheiten.
     */
    private final double count;

    /**
     * Längeneinheit der Entfernung.
     */
    private final Length unit;

    /**
     * Erzeugt eine Entfernung aus Anzahl und Einheit.
     * Negative Anzahlen werden durch den Betrag ersetzt.
     * @param c die Anzahl
     * @param u die Einheit
     */
    public Distance(final double c, final Length u) {
        count = Math.abs(c);
        unit = u;
    }

    /**
     * Liefert die Anzahl Einheiten dieser Entfernung.
     * @return die Anzahl
     */
    public double count() {
        return count;
    }

    /**
     * Liefert die Einheit dieser Entfernung als Element des Aufzählungstyps Length.
     * @return die Einheit
     */
    public Length unit() {
        return unit;
    }

    /**
     * Liefert die Entfernung in der Grundeinheit Meter.
     * @return Entfernung in Metern.
     */
    private double baseUnits() {
        return count*unit.baseUnits();
    }

    /**
     * Liefert diese Entfernung in einer anderen, gegebenen Einheit.
     * @param u gegebene Einheit
     * @return die Entfernung in der gegebenen Einheit
     */
    public Distance as(final Length u) {
        return new Distance(count()*unit.baseUnits()/u.baseUnits(), u);
    }

    /**
     * Liefert die Summe aus dieser Entfernung und einer anderen Entfernung als neues Objekt.
     * Das Ergebnis hat die gleiche Einheit wie diese Entfernung.
     * @param other die andere Entfernung
     * @return die Summe der Entfernungen
     */
    public Distance add(final Distance other) {
        return new Distance(count + other.as(unit).count(), unit);
    }

    @Override
    public String toString() {
        return count + " " + unit;
    }

    @Override
    public boolean equals(final Object x) {
        if(x == null)
            return false;
        if(x.getClass() != getClass())
            return false;
        final Distance d = (Distance)x;
        return baseUnits() == d.baseUnits();
    }

    @Override
    public int hashCode() {
        return new Double(baseUnits()).hashCode();
    }

    /**
     * Testprogramm für Entfernungen.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final Distance d1 = new Distance(2, Length.km);
        final Distance d2 = new Distance(1, Length.mi);
        final Distance d12 = d1.add(d2);
        final Distance d21 = d2.add(d1);
        System.out.println(d12);
        System.out.println(d21);
        System.out.println(d12.equals(d21));
    }

}
