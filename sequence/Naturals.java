/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Zahlenfolgen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package sequence;

/**
 * Folge der natürlichen Zahlen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class Naturals extends Sequence {
    private int next = 1;

    /**
     * Geht die Folge weiter?
     * @return true, da die Folge der unendlich vielen natürlichen Zahken nie abbricht
     */
    public boolean hasNext() {
        return true;
    }

    /**
     * Liefert die nächste natürliche Zahl.
     * @throws IllegalStateException, wenn die Zahl zu groß wird
     */
    public Integer next() {
        if(next < 0)
            throw new IllegalStateException("integer overflow");
        return next++;
    }

}
