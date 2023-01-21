/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Zahlenfolgen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package sequence;

import java.util.Iterator;

/**
 * Abstrakte Basisklasse modelliert allgemeine Folgen von ganzen Zahlen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public abstract class Sequence implements Iterator<Integer> {

    /**
     * Iterator-Methode remove wird nicht unterstützt.
     */
    public final void remove() {
        throw new UnsupportedOperationException("sequence is immutable");
    }

    /**
     * Gibt den Anfang der Folge auf der Konsole aus.
     * @param max Maximallänge des auszugebenden Anfangsstücks
     */
    public void dump(final int max) {
        for(int i = 0; hasNext()  && i < max; i++) {
            System.out.print(next());
            if(hasNext())
                System.out.print(", ");
        }
        System.out.println(hasNext()?  "...":  ";");
    }

}

