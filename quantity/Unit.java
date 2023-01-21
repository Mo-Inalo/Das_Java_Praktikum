/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Physikalische Größen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package quantity;

/**
 * Gemeinsame Eigenschaften physikalischer Größen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 17.06.2008
 */
public interface Unit {
    /**
     * Liefert die Anzahl Basiseinheiten.
     * @return die Anzahl Basiseinheiten
     */
    double baseUnits();
}
