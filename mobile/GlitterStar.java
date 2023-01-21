/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Mobiles
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package mobile;

/**
 * Definiert einen Glitzerstern des Mobiles.
 * Ein Glitzerstern ist ein mit Perlen dekorierbarer Stern.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class GlitterStar extends Star {

    /**
     * Zusätzliches Gewicht der Perlen.
     */
    private double glitterWeight = 0;

    /**
     * Erzeugt einen dekorierbaren Stern.
     * @param w Gewicht des Sterns ohne Dekoration
     */
    public GlitterStar(final double w) {
        super(w);
    }

    public void decorate() {
        glitterWeight += 1;
    }

    @Override
    public double weight() {
        return super.weight() + glitterWeight;
    }

    @Override
    public String toString() {
        return String.format("GlitterStar[%f+%f]", super.weight(), glitterWeight);
    }
}
