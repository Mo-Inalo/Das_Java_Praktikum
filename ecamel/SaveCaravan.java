/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: E-Camel
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package ecamel;

import java.util.NoSuchElementException;

/**
 * Definiert eine Karawane aus Kamelen.
 * Eine Karawane ist eine einfach verkettete lineare Liste aus Kamelen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 14.06.2008
 */
public class SaveCaravan {

    /**
     * Geschachtelte Klasse definiert ein Kamel der Karawane.
     * Ein Kamel ist ein Knoten der linearen Liste Karawane.
     *
     * @author Klaus Köhler, koehler@hm.edu
     * @author Reinhard Schiedermeier, rs@cs.hm.edu
     * @version 15.06.2008
     */
    static class Camel {
        /**
         * Maximale Marschgeschwindigkeit unbeladen.
         */
        private final int maxPace;

        /**
         * Anzahl der Ballen Ladung.
         */
        private int load = 0;

        /**
         * Nachfolger in der Karawane.
         */
        private Camel next = null;

        /**
         * Karawane, zu der dieses Kamel gehört.
         */
        private SaveCaravan caravan = null;

        /**
         * Erzeugt ein isoliertes Kamel.
         * @param mp maximale Marschgeschwindigkeit unbeladen
         */
        public Camel(final int mp) {
            maxPace = mp;
        }

        /**
         * Liefert die tatsächliche Geschwindigkeit des Kamels.
         * @return tatsächliche Geschwindigkeit unter Berücksichtigung der Ladung
         */
        public int pace() {
            return Math.max(0, maxPace - load);
        }

        public int getLoad() {
            return load;
        }

        public int getMaxPace() {
            return maxPace;
        }

        public Camel getNext() {
            return next;
        }

        public void setLoad(final int load) {
            this.load = load;
        }

        private void setNext(final Camel next) {
            this.next = next;
        }

        public SaveCaravan getCaravan() {
            return caravan;
        }

        /**
         * Gliedert dieses Kamel in eine Karawane ein.
         * @param caravan die Karawane
         */
        private void setCaravan(final SaveCaravan caravan) {
            this.caravan = caravan;
        }

    }


    /**
     * Leitkamel = Startknoten.
     */
    private Camel first = null;

    /**
     * Liefert die Reisegeschwindigkeit dieser Karawane.
     * Wird vom langsamsten Kamel bestimmt.
     * @return Reisegeschwindigkeit
     */
    public int pace() {
        if(first == null)
            throw new NoSuchElementException("empty caravan");
        int pace = Integer.MAX_VALUE;
        for(Camel c = first; c != null; c = c.getNext())
            pace = Math.min(pace, c.pace());
        return pace;
    }

    /**
     * Fügt das Kamel in diese Karawane ein.
     * @param c Kamel
     */
    public void addCamel(final Camel c) {
        if(c == null)
            throw new NullPointerException("null camel");
        if(c.getCaravan() != null)
            throw new IllegalArgumentException("camel in caravan");
        c.setCaravan(this);
        c.setNext(first);
        first = c;
    }

    /**
     * Nimmt das Kamel c aus dieser Karawane heraus.
     * @param x
     */
    public void removeCamel(final Camel x) {
        if(x == null)
            throw new NullPointerException("null camel");
        if(x.getCaravan() != this)
            throw new NoSuchElementException("camel not in caravan");
        if(x == first)
            first = x.getNext();
        else {
            Camel c = first;
            while(c.getNext() != x)
                c = c.getNext();
            c.setNext(x.getNext());
        }
        x.setCaravan(null);
    }

    /**
     * Bestimmt das aktuell schnellste Kamel der Karawane.
     * @return schnellstes Kamel
     */
    private Camel findFastestCamel() {
        if(first == null)
            throw new NoSuchElementException("empty caravan");
        Camel fastest = first;
        for(Camel c = first; c != null; c = c.getNext())
            if(c.pace() > fastest.pace())
                fastest = c;
        return fastest;
    }

    /**
     * Verteilt zusätzliche Ladung auf die Kamele dieser Karawane,
     * sodass die Reisegeschwindigkeit möglichst hoch bleibt.
     * @param l  Anzahl Ballen Ladung
     */
    public void addLoad(final int l) {
        if(first == null)
            throw new NoSuchElementException("empty caravan");
        if(l < 0)
            throw new IllegalArgumentException("negative load");
        int remaining = l;
        while(remaining > 0) {
            final Camel fastest = findFastestCamel();
            fastest.setLoad(fastest.getLoad() + 1);
            remaining--;
        }
    }

    /**
     * Testprogramm für Karawane.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final Camel ataAllah = new Camel(8);
        final Camel desertWind = new Camel(7);

        final SaveCaravan saharaExpress = new SaveCaravan();
        saharaExpress.addCamel(ataAllah);
        saharaExpress.addCamel(desertWind);
        System.out.println(saharaExpress.pace());
        saharaExpress.addLoad(5);
        System.out.println(saharaExpress.pace());
    }

}
