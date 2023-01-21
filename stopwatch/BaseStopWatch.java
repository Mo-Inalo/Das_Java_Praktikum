/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Stoppuhren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package stopwatch;

/**
 * Legt die Basisfunktionalit�t einer Stoppuhr fest.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 28.06.2008
 */
public interface BaseStopWatch {

    /**
     * Liefert die abgelaufene Zeit seit Start der Stoppuhr in Millisekunden.
     * @return die Zeit
     */
    long read();

    /**
     * �bernimmt die Zeit einer anderen Stoppuhr sw.
     * sw wird dabei nicht ver�ndert.
     * @param sw die andere Stoppuhr
     */
    void syncTo(BaseStopWatch sw);
}

