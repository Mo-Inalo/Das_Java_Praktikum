/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Widerstandsnetzwerke
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package circuit;

/**
 * Schnittstelle f�r Widerstandsnetzwerke.
 * Component im Composite Pattern des Widerstandsnetzwerks.
 *
 * @author Klaus K�hler, koehler@hm.edu
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
     * Bestimmt die Gesamtzahl der enthaltenen Widerst�nde des Netzwerks.
     * @return Gesamtzahl der Widerst�nde
     */
    int numberOfResistors();
}

