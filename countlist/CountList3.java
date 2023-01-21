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
 * Merkt sich, wie oft ihre Elemente vorkommen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
public class CountList3<E> extends AbstractList<E> {
    /**
     * Liste zur Delegation.
     */
    private final List<E> elements = new ArrayList<E>();

    /**
     * Tabelle zum Zählen der Vorkommen der Elemente der Liste.
     */
    private final Map<E, Integer> counts = new HashMap<E, Integer>();

    public CountList3() {}

    public CountList3(final Collection <? extends E> collection) {
        addAll(collection);
    }

    @Override
    public E get(final int index) {
        return elements.get(index);
    }

    @Override
    public int size() {
        return elements.size();
    }

    /**
     * Erhöht den Zähler für das Vorkommen eines Elements.
     * Setzt Zähler für neues Element auf 1.
     * @param element das Element
     * @return das Element
     */
    private E incrementCount(final E element) {
        final Integer count = counts.get(element);
        counts.put(element, count == null?  1:  count + 1);
        return element;
    }

    /**
     * Erniedrigt den Zähler für das Vorkommen eines Elements.
     * Entfernt den Zähler aus der Zählertabelle, wenn er 0 wird.
     * @param element das Element
     * @return das Element
     */
    private E decrementCount(final E element) {
        final Integer count = counts.get(element);
        if(count == 1)
            counts.remove(element);
        else
            counts.put(element, count - 1);
        return element;
    }

    @Override
    public E set(final int index, final E element) {
        return decrementCount(elements.set(index, incrementCount(element)));
    }

    @Override
    public void add(final int index, final E element) {
        elements.add(index, incrementCount(element));
    }

    @Override
    public E remove(final int index) {
        return decrementCount(elements.remove(index));
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
     * Liefert einen View auf die Abbildung der Elemente auf die Anzahl der Vorkommen.
     * @return View auf die Abbildung der Elemente der Liste auf die Anzahl der Vorkommen
     */
    public Map<E, Integer> counts() {
        return Collections.unmodifiableMap(counts);
    }

    /**
     * Testprogramm für Zählerliste.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final CountList3<Integer> cl = new CountList3<Integer>();
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
