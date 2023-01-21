/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Physikalische Größen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package quantity;

/**
 * Definiert Längeneinheiten mit ihren Umrechnungsfaktoren in die Basiseinheit Meter.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 17.06.2008
 */
public enum Length implements Unit {
    m(1), ft(0.3048), km(1000), mi(1632.9), LY(9.461E15), A(1E-10);

    /**
     * Umrechnungsfaktor.
     */
    private final double baseUnits;

    private Length(double b) {
        baseUnits = b;
    }

    /**
     * Liefert den Umrechnungsfaktor in Basiseinheiten.
     */
    public double baseUnits() {
        return baseUnits;
    }

}
