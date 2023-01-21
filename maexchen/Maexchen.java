/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Mäxchen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package maexchen;

/**
 * Rahmenklasse für das Spiel "Mäxchen".
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
class Maexchen {
    
    /**
     * Berechnet die Punkte für das Würfelspiel "Mäxchen".
     * Ein Spieler wirft zwei Würfel. 
     * Der Wert des Wurfs ergibt sich aus den Augen der Würfel wie folgt:
     * 1. Der Wurf 1, 2 heißt "Mäxchen" und ist 1000 Punkte wert.
     * 2. Ein Wurf mit zwei gleichen Augenzahlen wird als "Pasch" bezeichnet 
     *    und ist 100xAugenzahl an Punkten wert. 
     * 3. Ansonsten ist der Wert 10 x(höhere Augenzahl) + (niedrigere Augenzahl).
     * @param args Augenzahlen der beiden Würfel
     */
    public static void main(final String[] args) {
        final int a = Integer.parseInt(args[0]);
        final int b = Integer.parseInt(args[1]);
        final int min = Math.min(a, b);
        final int max = Math.max(a, b);

        if(min == 1  &&  max == 2)      
            System.out.println(1000);
        else if(min == max)             
            System.out.println(100*min);
        else                            
            System.out.println(10*max + min);
    }
}
