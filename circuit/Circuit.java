/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Widerstandsnetzwerke
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package circuit;

/**
 * Schnittstelle für Widerstandsnetzwerke.
 * Component im Composite Pattern des Widerstandsnetzwerks.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public interface Circuit {
    /**
     * Bestimmt den Gesamtwiderstand des Netzwerkes.
     * @return Gesamtwiderstand
     */
    double getOhm();

    /**
     * Bestimmt die Gesamtzahl der enthaltenen Widerstände des Netzwerks.
     * @return Gesamtzahl der Widerstände
     */
    int numberOfResistors();
}

