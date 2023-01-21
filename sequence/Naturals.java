/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Zahlenfolgen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package sequence;

/**
 * Folge der nat�rlichen Zahlen.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class Naturals extends Sequence {
    private int next = 1;

    /**
     * Geht die Folge weiter?
     * @return true, da die Folge der unendlich vielen nat�rlichen Zahken nie abbricht
     */
    public boolean hasNext() {
        return true;
    }

    /**
     * Liefert die n�chste nat�rliche Zahl.
     * @throws IllegalStateException, wenn die Zahl zu gro� wird
     */
    public Integer next() {
        if(next < 0)
            throw new IllegalStateException("integer overflow");
        return next++;
    }

}
