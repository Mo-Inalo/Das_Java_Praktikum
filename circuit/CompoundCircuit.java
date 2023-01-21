/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Widerstandsnetzwerke
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package circuit;

/**
 * Absrakte Basisklasse für Komposition von 2 Widerstandsnetzwerken.
 * Composite im Composite Pattern des Widerstandsnetzwerks.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public abstract class CompoundCircuit implements Circuit {
    private final Circuit circuit1;
    private final Circuit circuit2;

    /**
     * Erzeugt eine noch undefinierte Schaltung von 2 Widerstandsnetzwerken.
     * @param c1 Widerstandsnetzwerk
     * @param c2 Widerstandsnetzwerk
     */
    protected CompoundCircuit(final Circuit c1, final Circuit c2) {
        circuit1 = c1;
        circuit2 = c2;
    }

    protected Circuit circuit1() {
        return circuit1;
    }

    protected Circuit circuit2() {
        return circuit2;
    }

    /**
     * Bestimmt die Gesamtzahl der enthaltenen Widerstände.
     * @return Summe der Anzahl Widerstände der beiden Widerstandsnetzwerke.
     */
    public int numberOfResistors() {
        return circuit1.numberOfResistors() + circuit2.numberOfResistors();
    }

}
