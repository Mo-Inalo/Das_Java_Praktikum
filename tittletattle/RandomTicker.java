/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Tittle-Tattle
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package tittletattle;

import java.util.Random;

/**
 * RandomTicker tickt in zufälligen Abständen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class RandomTicker implements Ticker {
    private final static Random rng = new Random();

    /**
     * Tickt in zufälligen Abständen von einer bis zehn Sekunden.
     * @return Zufallszahl im Bereich von 0 bis 99 (Ereignis)
     */
    public int tick() {
        try {
            Thread.sleep(1000 + rng.nextInt(9000));
        } catch(final InterruptedException ex) {
        }
        return rng.nextInt(100);
    }

}