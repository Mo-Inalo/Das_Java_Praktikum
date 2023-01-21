/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Ring-Queue
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package ringqueue;

/**
 * Warteschlange mit fester Kapazit�t, die zyklisch �berschrieben wird.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class RingQueue<T> {
    private final T[] buffer;

    private final int capacity;

    /**
     * Pufferindex f�r das n�chste Element.
     */
    private int next = 0;

    /**
     * Anzahl der tats�chlich in buffer benutzten Elemente.
     */
    private int used;

    /**
     * Erzeugt neue RingQueue nit gegebener Kapazit�t.
     * @param c die Kapazit�t
     */
    @SuppressWarnings("unchecked")
    public RingQueue(final int c) {
        capacity = c;
        buffer = (T[])new Object[c];
        used = 0;
    }

    /**
     * Erzeugt eine RingQueue aus �bergebenen Elementen.
     * @param a die Elemente
     */
    public RingQueue(final T... a) {
        capacity = a.length;
        buffer = a;
        used = a.length;
    }

    /**
     * Liefert die Anzahl der momentan enthaltenen Elemente.
     * @return die Anzahl
     */
    public int size() {
        return used;
    }

    /**
     * Schiebt ein neues Element in diese Ring-Queue.
     * @param t
     */
    public void push(final T t) {
        buffer[next] = t;
        next++;
        if(next >= capacity)
            next = 0;
        if(used < capacity)
            used++;
    }

    /**
     * L�scht alle Elemente aus der Ring-Queue.
     */
    public void clear() {
        used = 0;
    }

    /**
     * Liefert das n.-j�ngste Element dieser RingQueue.
     * @param n aus [0, used)
     * @return das n.-j�ngste Element
     */
    public T get(int n) {
        if(n < 0)
            throw new IndexOutOfBoundsException();
        if(n >= used)
            throw new IndexOutOfBoundsException();
        n = next - (n + 1);
        if(n < 0)
            n += capacity;
        return buffer[n];
    }

    /**
     * Testprogramm f�r RingQueue.
     * @param args nicht verwendet.
     */
    public static void main(final String[] args) {
        RingQueue<Integer> queue = new RingQueue<Integer>(1, 2, 3);
        for(int i = 0; i < queue.size(); i++)
            System.out.printf("%d ", queue.get(i));
        System.out.println();

        queue = new RingQueue<Integer>(2);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        for(int i = 0; i < queue.size(); i++)
            System.out.printf("%d ", queue.get(i));
        System.out.println();
    }

}
