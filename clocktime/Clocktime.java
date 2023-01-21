/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Uhrzeit
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package clocktime;

/**
 * Definiert die Uhrzeit in Stunden, Minuten und Sekunden.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class Clocktime {
    /**
     * Sekunden seit Mitternacht.
     */
    private int totalSeconds;

    private final static int SECONDS_A_DAY = 60*60*24;

    /**
     * Erzeugt Clocktime-Objekt 00:00:00 (Mitternacht).
     */
    public Clocktime() {
        this(0, 0, 0);
    }

    /**
     * Erzeugt Clocktime-Objekt h:00:00.
     * @param h Stunden
     */
    public Clocktime(final int h) {
        this(h, 0, 0);
    }

    /**
     * Erzeugt Clocktime-Objekt h:m:00.
     * @param h Stunden
     * @param m Minuten
     */
    public Clocktime(final int h, final int m) {
        this(h, m, 0);
    }

    /**
     * Erzeugt Clocktime-Objekt h:m:s.
     * @param h Stunden
     * @param m Minuten
     * @param s Sekunden
     */
    public Clocktime(final int h, final int m, final int s) {
        totalSeconds = normalizeTotalSeconds(60*(60*h + m) + s);
    }

    public Clocktime(final Clocktime ct) {
        totalSeconds = ct.totalSeconds();
    }

    /**
     * Normiert Sekunden auf den Bereich 0 - 60*60*24-1.
     * @param t unnormalisierte Sekunden
     * @return normalisierte Sekunden
     */
    private static int normalizeTotalSeconds(int t) {
        t %= SECONDS_A_DAY;
        if(t < 0)
            t += SECONDS_A_DAY;
        return t;
    }

   /**
     * Liefert die Uhrzeit in Sekunden.
     * @return Sekunden seit Mitternacht
     */
    private int totalSeconds() {
        return totalSeconds;
    }

    @Override
    public boolean equals(final Object x) {
        if(x == null)
            return false;
        if(x.getClass() != getClass())
            return false;
        final Clocktime c = (Clocktime)x;
        return c.totalSeconds() == totalSeconds;
    }

    @Override
    public int hashCode() {
        return totalSeconds;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours(), minutes(), seconds());
    }

    /**
     * Addiere Anzahl Sekunden zur Zeit.
     * @param s Sekunden
     * @return neue Zeit
     */
    public Clocktime add(final int s) {
        totalSeconds = normalizeTotalSeconds(totalSeconds + s);
        return this;
    }

    /**
     * Subtrahiere Uhrzeit von dieser Uhrzeit.
     * @param ct zu subtrahierende Uhrzeit
     * @return Zeitdifferenz in Sekunden
     */
    public int diff(final Clocktime ct) {
        final int d = ct.totalSeconds() - totalSeconds;
        return d < 0?  d + SECONDS_A_DAY:  d;
    }

    /**
     * Liefert die Anzahl Sekunden (0-59).
     * @return Sekunden
     */
    public int seconds() {
        return totalSeconds%60;
    }

    /**
     * Liefert die Anzahl Minuten (0-59).
     * @return Minuten
     */
    public int minutes() {
        return totalSeconds/60%60;
    }

    /**
     * Liefert die Anzahl Stunden (0-23).
     * @return Stunden
     */
    public int hours() {
        return totalSeconds/60/60;
    }

    /**
     * Testprogramm für Uhrzeit.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final Clocktime c1 = new Clocktime().add(5);
        System.out.println(c1);
        final Clocktime c2 = new Clocktime(1, 72, -101).add(-8000);
        System.out.println(c2);
        System.out.println(c1.diff(c2));
        System.out.println(c2.diff(c1));
    }

}

