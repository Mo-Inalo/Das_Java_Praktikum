/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Mobiles
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package mobile;

/**
 * Repräsentiert ein Mobile.
 * Component im design pattern "composite".
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public interface Mobile {
    /**
     * Gesamtgewicht des Mobiles.
     * @return Gewicht.
     */
    double weight();

    /**
     * Balaciert das Mobile.
     */
    void balance();
}
