/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Zahlenfolgen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package sequence;

/**
 * Filter, der die Vielfachen einer gegebenen Zahl absorbiert.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class ZapMultiples extends Filter {

    /**
     * Basiszahl b, deren Vielfache ausgeblendet werden.
     */
    private final int base;

    /**
     * Erzeugt neues Filterobjekt, das die Vielfachen von b absorbiert.
     * @param source die zu filternde Folge
     * @param b die Basiszahl
     */
    public ZapMultiples(final Sequence source, final int b) {
        super(source);
        base = b;
    }

    @Override
    public boolean pass(final int number) {
        return number%base != 0;
    }

}
