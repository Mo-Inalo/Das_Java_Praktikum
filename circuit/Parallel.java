/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Widerstandsnetzwerke
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package circuit;

/**
 * Parallelschaltung von Widerstandsnetzwerken.
 * Composite im Composite Pattern des Widerstandsnetzwerks.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class Parallel extends CompoundCircuit {
    /**
     * Erzeugt eine Parallelschaltung von 2 Widerstandsnetzwerken.
     * @param c1 Widerstandsnetzwerk
     * @param c2 Widerstandsnetzwerk
     */
    public Parallel(final Circuit c1, final Circuit c2) {
        super(c1, c2);
    }

    /**
     * Bestimmt den Gesamtwiderstand der Parallelschaltung.
     * Kirchhoffsche Regel: Kehrwerte der Widerstände addieren sich.
     * @return Gesamtwiderstand
     */
    public double getOhm() {
        return 1/(1/circuit1().getOhm() + 1/circuit2().getOhm());
    }

}
