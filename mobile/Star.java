/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Mobiles
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package mobile;

/**
 * Definiert einen Stern des Mobiles.
 * Leaf im design pattern "composite".
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class Star implements Mobile {
    private final double weight;

    /**
     * Erzeugt einen Stern mit Gewicht w.
     * @param w das Gewicht
     */
    public Star(final double w) {
        if(w <= 0)
            throw new IllegalArgumentException("non-positive weight: " + w);
        weight = w;
    }

    public double weight() {
        return weight;
    }

    public void balance() {}

    @Override
    public String toString() {
        return String.format("Star[%f]", weight);
    }
}
