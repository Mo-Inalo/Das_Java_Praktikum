/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Stoppuhren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package stopwatch;

/**
 * Stoppuhr, die sofort losl�uft, wenn das Objekt erzeugt wird.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 28.06.2008
 */
public class SwissTick implements BaseStopWatch {

    /**
     * Startzeit = Systemzeit in Millisekunden.
     */
    private long start = System.currentTimeMillis();

    public long read() {
        return System.currentTimeMillis() - start;
    }

    public void syncTo(final BaseStopWatch sw) {
        start = System.currentTimeMillis() - sw.read();
    }

    /**
     * Ver�ndert die Startzeit so, dass die gew�nschte Zielzeit s dargestellt wird.
     * @param s die Zielzeit
     */
    protected void setTo(final long s) {
        start = System.currentTimeMillis() - s;
    }

    @Override
    public String toString() {
        return Long.toString(read());
    }

    /**
     * Testprogramm f�r SwissTick.
     * @param args nicht verwendet
     * @throws InterruptedException, wenn im Schlaf gest�rt
     */
    public static void main(final String[] args) throws InterruptedException {
        final BaseStopWatch a = new SwissTick();
        Thread.sleep(100);
        System.out.println(a.read());
        final BaseStopWatch b = new SwissTick();
        Thread.sleep(100);
        System.out.println(a.read());
        System.out.println(b.read());
        b.syncTo(a);
        Thread.sleep(100);
        System.out.println(a.read());
        System.out.println(b.read());
    }
}
