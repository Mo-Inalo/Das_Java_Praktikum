/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Stoppuhren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package stopwatch;

/**
 * Schweizer Uhr mit Reset-Knopf.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 28.06.2008
 */
public class SwissTickZero extends SwissTick implements ResettableStopWatch {
    public void reset() {
        setTo(0);
    }

    /**
     * Testprogramm für SwissTickZero.
     * @param args nicht verwendet
     * @throws InterruptedException, wenn im Schlaf gestört
     */
    public static void main(final String[] args) throws InterruptedException {
        final ResettableStopWatch a = new SwissTickZero();
        Thread.sleep(100);
        System.out.println(a.read());
        final ResettableStopWatch b = new SwissTickZero();
        Thread.sleep(100);
        System.out.println(a.read());
        System.out.println(b.read());
        a.reset();
        b.syncTo(a);
        Thread.sleep(100);
        System.out.println(a.read());
        System.out.println(b.read());
    }
}
