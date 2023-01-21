/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Verkehrsüberwachung
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package trafficcontrol;

import java.util.HashMap;
import java.util.Map;

/**
 * TrafficControl sammelt Verkehrsmeldungen von Detectoren und wertet sie aus.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class TrafficControl {

    /**
     * Prüft einmal pro Sekunde, welche Detektoren in der letzten Minute 10 oder
     * mehr Fahrzeuge registriert haben, und gibt diese auf der Konsole aus.
     * @param args IP-Adressen der Detectoren
     * @throws InterruptedException bei Unterbrechung des Tiefschlafs
     */
    public static void main(final String[] args) throws InterruptedException {
        // Tabelle mit Detector-IPs und zugehörigen Producer-Threads
        final Map<String, Producer> producers = new HashMap<String, Producer>();
        for(final String ip: args)
            producers.put(ip, new Producer(ip));
        for(final Thread th: producers.values())
            th.start();

        // Befragt einmal pro Sekunde die Producer, welche der Detektoren
        // in der letzten Minute 10 oder mehr Fahrzeuge registriert haben.
        while(true) {
            for(final String ip: producers.keySet())
                if(producers.get(ip).count() >= 10)
                    System.out.println(ip + ": heavy traffic");
            Thread.sleep(1000);
        }
    }

}
