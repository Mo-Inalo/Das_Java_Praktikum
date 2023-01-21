/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Widerstandsnetzwerke
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package circuit;

/**
 * Klassenrahmen zum Testen von Widerstandsnetzwerken.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class Main {
    
    /**
     * Testprogramm für Widerstandsnetzwerk.
     * @param args nicht verwendet
     */
    public static void main(final String[] args) {
        final Circuit c1 = new Resistor(100);
        final Circuit c2 = new Resistor(200);
        final Circuit c3 = new Resistor(300);
        final Circuit c4 = new Resistor(400);
        final Circuit c5 = new Resistor(500);
        final Circuit c6 = new Resistor(600);
        final Circuit c13 = new Parallel(c1, c3);
        final Circuit c123 = new Serial(c13, c2);
        final Circuit c45 = new Serial(c4, c5);
        final Circuit c12345 = new Parallel(c123, c45);
        final Circuit c = new Parallel(c12345, c6);
        System.out.println(c.getOhm());                     
        System.out.println(c.numberOfResistors());       
        final Circuit d = new Parallel(new Resistor(600),
                                 new Parallel(new Serial(new Resistor(400),
                                                         new Resistor(500)),
                                              new Serial(new Resistor(200),
                                                         new Parallel(new Resistor(100),
                                                                      new Resistor(300)))));
        System.out.println(d.getOhm());                     
        System.out.println(d.numberOfResistors());       
    }

}
