/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Physikalische Größen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package quantity;

/**
 * Definiert das Produkt von zwei physikalischen Größen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 17.06.2008
 */
public class Prod<U extends Unit, V extends Unit> implements Unit {

    private final U fst;

    private final V snd;

    public Prod(final U u, final V v) {
        fst = u;
        snd = v;
    }

    public double baseUnits() {
        return fst.baseUnits()*snd.baseUnits();
    }

    @Override
    public String toString() {
        return fst + "*" + snd;
    }

}
