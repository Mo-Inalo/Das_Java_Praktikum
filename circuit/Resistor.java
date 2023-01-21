/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Widerstandsnetzwerke
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package circuit;

/**
 * Einfacher Widerstand.
 * Leaf im Composite Pattern des Widerstandsnetzwerks.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class Resistor implements Circuit {
    private final double ohm;

    public Resistor(final double o) {
        ohm = o;
    }

    public double getOhm() {
        return ohm;
    }

    public int numberOfResistors() {
        return 1;
    }

}
