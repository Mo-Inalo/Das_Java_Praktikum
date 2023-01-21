/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Widerstandsnetzwerke
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package circuit;

/**
 * Einstellbarer Widerstand.
 * Leaf im Composite Pattern des Widerstandsnetzwerks.
 * 
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class Potentiometer implements Circuit {
    private double ohm;

    private final double maxOhm;

    /**
     * Erzeugt ein Potentiometer mit 0 bis o Ohm Widerstand.
     * @param o Maximalwiderstand und Anfangswiderstand.
     */
    public Potentiometer(final double o) {
        ohm = o;
        maxOhm = o;
    }

    public double getOhm() {
        return ohm;
    }

    /**
     * Stellt den Widerstand ein, begrenzt ihn auf den Bereich 0-maxOhm.
     * @param o
     */
    public void setOhm(final double o) {
        if(o < 0)
            ohm = 0;
        else if(o > maxOhm)
            ohm = maxOhm;
        else
            ohm = o;
    }

    public int numberOfResistors() {
        return 1;
    }

    /**
     * Testprogramm für Widerstandsnetzwerk mit Potentiometer. Gibt Liste mit
     * den Widerstandswerten des Gesamtnetzes für verschiedene
     * Potentiometereinstellungen aus.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final Circuit c1 = new Resistor(100);
        final Circuit c2 = new Resistor(200);
        final Circuit c3 = new Resistor(300);
        final Circuit c4 = new Resistor(400);
        final Potentiometer c5 = new Potentiometer(500);
        final Circuit c6 = new Resistor(600);
        final Circuit c13 = new Parallel(c1, c3);
        final Circuit c123 = new Serial(c13, c2);
        final Circuit c45 = new Serial(c4, c5);
        final Circuit c12345 = new Parallel(c123, c45);
        final Circuit c = new Parallel(c12345, c6);

        for(int ohm = 0; ohm <= 500; ohm += 100) {
            c5.setOhm(ohm);
            System.out.println(c.getOhm());
        }
    }

}
