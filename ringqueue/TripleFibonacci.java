/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Ring-Queue
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package ringqueue;

/**
 * Rahmenklasse für die Triple-Fibonacci-Folge.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public class TripleFibonacci {

    /**
     * Bestimmt die ersten 20 Glieder der Triple-Fibonacci-Folge:
     * f(0) = f(1) = f(2) = 1
     * f(n) = f(n?-1) + f(n-2) + f(n-3) für n>=3
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final RingQueue<Integer> t = new RingQueue<Integer>(1, 1, 1);
        int count = 3;
        while(count++ < 20)
            t.push(t.get(0) + t.get(1) + t.get(2));
        System.out.println(t.get(0));
    }
}
