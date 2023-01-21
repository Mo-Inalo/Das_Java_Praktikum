/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Zahlenfolgen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package sequence;

import java.util.NoSuchElementException;

/**
 * Filter l�sst die Elemente einer anderen Zahlenfolge durch oder absorbiert sie.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
public abstract class Filter extends Sequence {

    /**
     * Zu filternde Folge.
     */
    private final Sequence source;

    /**
     * Erzeugt ein Filterobjekt f�r eine gegebene Folge.
     * @param source die zu filternde Folge
     */
    protected Filter(final Sequence source) {
        this.source = source;
    }

    /**
     * �berpr�ft, ob eine Zahl durchgelassen wird.
     * @param number die Zahl
     * @return true, wenn number durchgelassen wird, sonst false
     */
    public abstract boolean pass(int number);

    /**
     * Filterzustand.
     *  Unknown: Keine Information �ber das n�chste Element verf�gbar
     *  Found: Beim Vorauslesen passendes Element gefunden
     *  None: Datenquelle ersch�pft, es gibt kein passendes Element mehr.
     *
     * @author Klaus K�hler, koehler@hm.edu
     * @author Reinhard Schiedermeier, rs@cs.hm.edu
     * @version 27.06.2008
     */
    private enum NextState {Unknown, Found, None}

    private NextState state = NextState.Unknown;

    private int next;

    /**
     * Liest voraus, bis klar ist, ob noch ein passendes Folgenglied existiert
     * @return Found, wenn Vorauslesen erfolgreich war,
     *         None, wenn kein passendes Folgenglied mehr existiert.
     */
    private NextState lookahead() {
        while(state == NextState.Unknown)
            if(source.hasNext()) {
                next = source.next();
                if(pass(next))
                    state = NextState.Found;
            }
            else
                state = NextState.None;
        return state;
    }

    /**
     * Geht die Folge weiter?
     * @return true, wenn noch eine weitere Zahl der zu filternden Folge durchgelassen wird
     */
    public boolean hasNext() {
        return lookahead() == NextState.Found;
    }

    /**
     * Liefert das n�chste Folgenglied.
     * @throws NoSuchElementException,
     *  wenn keine weitere Zahl der zu filternden Folge mehr durchgelassen wird
     */
    public Integer next() {
        if(lookahead() == NextState.None)
            throw new NoSuchElementException("no more elements");
        state = NextState.Unknown;
        return next;
    }

}
