/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Stoppuhren
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package stopwatch;

/**
 * Stoppuhr mit Pauseknopf.
 * Kann vorübergehend angehalten werden.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 28.06.2008
 */
public interface SuspendableStopWatch extends ResettableStopWatch {

    /**
     * Hält die Stoppuhr vorübergehend an.
     * Wenn die Stoppuhr schon steht, geschieht nichts.
     */
    void suspend();

    /**
     * Lässt die Stoppuhr weiterlaufen.
     * Wenn die Stoppuhr gerade läuft, bleibt die Methode ohne Wirkung.
     */
    void resume();
}

