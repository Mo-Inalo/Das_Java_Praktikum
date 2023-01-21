/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Mobiles
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package mobile;

/**
 * Definiert ein Stäbchen des Mobiles.
 * Composite im design pattern "composite".
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class Wire implements Mobile {
    /**
     * Erster Kindknoten.
     */
    private final Mobile first;

    /**
     * Zweiter Kindknoten.
     */
    private final Mobile second;

    /**
     * Armlänge des ersten Kindknotens.
     */
    private double distanceFirst;

    /**
     * Armlänge des zweiten Kindknotens.
     */
    private double distanceSecond;

    /**
     * Erzeugt ein Stäbchen mit zwei daran hängenden Mobiles.
     * @param fst erstes Mobile
     * @param snd zweites Mobile
     * @param ln Länge des Stäbchens
     */
    public Wire(final Mobile fst, final Mobile snd, final double ln) {
        if(fst == null  || snd == null)
            throw new NullPointerException("null mobile");
        first = fst;
        second = snd;
        distanceFirst = 0;
        distanceSecond = ln;
    }

    public double weight() {
        return first.weight() + second.weight();
    }

    public void balance() {
        first.balance();
        second.balance();
        final double w1 = first.weight();
        final double w2 = second.weight();
        final double length = distanceFirst + distanceSecond;
        distanceFirst = w2*length/(w1 + w2);
        distanceSecond = w1*length/(w1 + w2);
    }

    @Override
    public String toString() {
        return String.format("Mobile[%g:%s, %g:%s]",
                             distanceFirst,
                             first.toString(),
                             distanceSecond,
                             second.toString());
    }
}
