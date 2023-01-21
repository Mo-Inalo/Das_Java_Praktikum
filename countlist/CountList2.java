/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Zählerlisten
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package countlist;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definiert eine generische Liste, die zählt, wie oft ihre Elemente vorkommen.
 * Delegiert Aufgaben an eine eingebettete Liste.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
public class CountList2<E> extends AbstractList<E> {
    /**
     * Liste zur Delegation.
     */
    private final List<E> elements = new ArrayList<E>();

    /**
     * Tabelle zum Zählen der Vorkommen der Elemente der Liste.
     */
    private Map<E, Integer> counts = null;

    public CountList2() {}

    public CountList2(final Collection <? extends E> collection) {
        elements.addAll(collection);
    }

    @Override
    public E get(final int index) {
        return elements.get(index);
    }

    @Override
    public E set(final int index, final E element) {
        counts = null;
        return elements.set(index, element);
    }

    @Override
    public void add(final int index, final E element) {
        counts = null;
        elements.add(index, element);
    }

    @Override
    public E remove(final int index) {
        counts = null;
        return elements.remove(index);
    }

    @Override
    public int size() {
        return elements.size();
    }

    /**
     * Liefert die Anzahl der Vorkommen eines Elements in der Liste.
     * @param searched das gesuchte Element
     * @return Anzahl der Vorkommen
     */
    public int count(final E searched) {
        final Integer result = counts().get(searched);
        return result == null?  0:  result;
    }

    /**
     * Liefert die Anzahl der unterschiedlichen Elemente in der Liste.
     * @return die Anzahl der unterschiedlichen Elemente in der Liste
     */
    public int unique() {
        return counts().size();
    }

    /**
     * Zählt bei Bedarf die Anzahl der Vorkommen der Elemente der Liste.
     * @return Abbildung der Elemente der Liste auf die Anzahl der Vorkommen
     */
    public Map<E, Integer> counts() {
        if(counts == null) {
            counts = new HashMap<E, Integer>();
            for(final E element: elements) {
                final Integer count = counts.get(element);
                counts.put(element, count == null?  1:  count + 1);
            }
        }
        return Collections.unmodifiableMap(counts);
    }

    /**
     * Testprogramm für Zählerliste.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final CountList2<Integer> cl = new CountList2<Integer>();
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
