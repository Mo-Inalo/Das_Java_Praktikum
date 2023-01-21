/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Zahlenfolgen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package sequence;

/**
 * Filter, der nur ungerade Zahlen durchlässt.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class Odd extends Filter {
    public Odd(final Sequence source) {
        super(source);
    }

    @Override
    public boolean pass(final int number) {
        return number%2 == 1;
    }

}
