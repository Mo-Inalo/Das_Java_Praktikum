/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Physikalische Größen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package quantity;

/**
 * Definiert Zeiteinheiten mit ihren Umrechnungsfaktoren in die Basiseinheit Sekunde.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 17.06.2008
 */
public enum Time implements Unit {
    s(1), d(3600*24), yr(365*d.baseUnits), ns(1e-9);

    /**
     * Umrechnungsfaktor in Basiseinheiten.
     */
    private final double baseUnits;

    private Time(final double b) {
        baseUnits = b;
    }

    /**
     * Liefert den Umrechnungsfaktor in Basiseinheiten.
     */
    public double baseUnits() {
        return baseUnits;
    }

}
