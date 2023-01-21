/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Zählerlisten
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package countlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Definiert eine generische Liste, die zählt, wie oft ihre Elemente vorkommen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
public class CountList<E> extends ArrayList<E> {
    private static final long serialVersionUID = 1L;

    public CountList() {}

    public CountList(final Collection <? extends E> c) {
        super(c);
    }

    /**
     * Zählt, wie oft ein Element in der Liste vorkommt.
     * @param searched das gesuchte Element
     * @return Anzahl der Vorkommen
     */
    public int count(final E searched) {
        int result = 0;
        for(final E element: this)
            if(element == null) {
                if(searched == null)
                    result++;
            }
            else if(element.equals(searched))
                result++;
        return result;
    }

    /**
     * Liefert die Anzahl der unterschiedlichen Elemente in der Liste.
     * @return die Anzahl der unterschiedlichen Elemente in der Liste
     */
    public int unique() {
        return new HashSet<E>(this).size();
    }

    /**
     * Liefert eine Abbildung der Elemente der Liste auf die Anzahl der Vorkommen.
     * @return die Abbildung
     */
    public Map<E, Integer> counts() {
        final Map<E, Integer> counts = new HashMap<E, Integer>();
        for(final E element: this) {
            final Integer count = counts.get(element);
            counts.put(element, count == null?  1:  count + 1);
        }
        return counts;
    }

    /**
     * Testprogramm für Zählerliste.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final CountList<Integer> cl = new CountList<Integer>();
        cl.add(2);
        cl.add(5);
        cl.add(2);
        cl.add(5);
        cl.add(3);
        System.out.println(cl.count(2));
        System.out.println(cl.unique());
        System.out.println(cl.counts());
    }

}

