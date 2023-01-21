/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Zahlenfolgen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package sequence;

import java.util.NoSuchElementException;

/**
 * Range repr�sentiert ganze Zahlen in einem gegebenen Intervall.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class Range extends Sequence {
    private int next;

    /**
     * Erster Wert nach dem Ende der Folge.
     */
    private final int stopper;

    /**
     * Erzeugt eine beschr�nkte Folge.
     * @param from untere Schranke der Folge, Startwert
     * @param to obere Schranke (ausschlie�lich), erster Wert nach dem Ende der Folge
     */
    public Range(final int from, final int to) {
        super();
        next = from;
        stopper = to;
    }

    /**
     * Geht die Folge weiter?
     */
    public boolean hasNext() {
        return next < stopper;
    }

    /**
     * Liefert das n�chste Folgenglied.
     * @throws NoSuchElementException am Ende der Folge
     */
    public Integer next() {
        if(!hasNext())
            throw new NoSuchElementException("no more elements");
        return next++;
    }

}
