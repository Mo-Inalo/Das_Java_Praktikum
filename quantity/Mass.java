/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Physikalische Größen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package quantity;

/**
 * Definiert Masseneinheiten mit ihren Umrechnungsfaktoren in die Basiseinheit kg.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 17.06.2008
 */
public enum Mass implements Unit {
    kg(1), g(1e-3), lb(.4536), Kt(2e-4);

    /**
     * Umrechnungsfaktor in Basiseinheiten.
     */
    private final double baseUnits;

    private Mass(final double b) {
        baseUnits = b;
    }

    /**
     * Liefert den Umrechnungsfaktor in Basiseinheiten.
     */
    public double baseUnits() {
        return baseUnits;
    }

}
