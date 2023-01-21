/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Stoppuhren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package stopwatch;

/**
 * Luxus-Stoppuhr, die bei dem Zeitpunkt weiterlaufen kann, an dem sie angehalten wurde.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 28.06.2008
 */
public class SwissTickDeluxe extends SwissTickZero implements SuspendableStopWatch {
    private boolean suspended = false;

    /**
     * Haltezeit.
     * Nur gültig, wenn suspended = true.
     */
    private long suspendedAt;

    /**
     * Hält die Stoppuhr vorübergehend an.
     * Wenn die Stoppuhr schon steht, geschieht nichts.
     */
    public void suspend() {
        if(!suspended) {
            suspendedAt = read();
            suspended = true;
        }
    }

    /**
     * Lässt die Stoppuhr weiterlaufen.
     * Wenn die Stoppuhr gerade läuft, bleibt die Methode ohne Wirkung.
     */
    public void resume() {
        if(suspended) {
            setTo(suspendedAt);
            suspended = false;
        }
    }

    @Override
    public long read() {
        if(suspended)
            return suspendedAt;
        return super.read();
    }

    @Override
    public void syncTo(final BaseStopWatch sw) {
        if(suspended)
            suspendedAt = sw.read();
        super.syncTo(sw);
    }

    @Override
    public void reset() {
        if(suspended)
            suspendedAt = 0;
        super.reset();
    }

    /**
     * Testprogramm für Luxus-Stoppuhr.
     * @param args nicht verwendet
     * @throws InterruptedException, wenn im Schlaf gestört
     */
    public static void main(final String[] args) throws InterruptedException {
        final SuspendableStopWatch a = new SwissTickDeluxe();
        Thread.sleep(100);

        System.out.println(a.read());
        a.suspend();
        Thread.sleep(100);

        System.out.println(a.read());
        a.resume();
        Thread.sleep(100);

        System.out.println(a.read());

        final SuspendableStopWatch b = new SwissTickDeluxe();
        Thread.sleep(100);

        a.suspend();
        a.syncTo(b);
        Thread.sleep(100);

        System.out.println(a.read());
        a.reset();
        System.out.println(a.read());
        a.resume();
        Thread.sleep(100);

        System.out.println(a.read());
    }
}
