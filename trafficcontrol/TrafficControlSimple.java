/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Verkehrsüberwachung
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package trafficcontrol;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TrafficControlSimple sammelt Verkehrsmeldungen von Detectoren und wertet sie aus.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class TrafficControlSimple {

    /**
     * Prüft einmal pro Sekunde, welche Detektoren in der letzten Minute 10 oder
     * mehr Fahrzeuge registriert haben, und gibt diese auf der Konsole aus.
     * @param args IP-Adressen der Detectoren
     * @throws InterruptedException bei Unterbrechung des Tiefschlafs
     */
    public static void main(final String[] args) throws InterruptedException {
        // Tabelle mit Detector-IPs und zugeordneter Anzahl der
        // innerhalb der letzten Minute gemeldeten Ereignisse
        final Map<String, Integer> counts =
            Collections.synchronizedMap(new HashMap<String, Integer>());
        for(final String ip: args)
            counts.put(ip, 0);
        for(final String ip: args)
            // anonymer Thread, der direkt beim Detector nachfragt
            new Thread() {
            @Override
            public void run() {
                try {
                    final String[] token = ip.split(":");
                    final Socket socket = new Socket(token[0], Integer.parseInt(token[1]));
                    final InputStream input = socket.getInputStream();
                    while(true) {
                        // wartet auf die nächste Detector-Meldung
                        input.read();
                        // erhöht den Ereigniszähler
                        synchronized(counts) {
                            counts.put(ip, counts.get(ip) + 1);
                        }
                        // startet einen neuen Thread,
                        // der den Ereigniszähler nach einer Minute wieder erniedrigt
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(60000);
                                    synchronized(counts) {
                                        counts.put(ip, counts.get(ip) - 1);
                                    }
                                }
                                catch (final InterruptedException ex) {
                                }
                            }
                        }.start();
                    }
                }
                catch (final IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
        .start();

        // wertet die Zählertabelle jede Sekunde aus und meldet hohes Verkehrsaufkommen
        while(true)
            for(final String ip: args) {
                synchronized(counts) {
                    if(counts.get(ip) >= 10)
                        System.out.println(ip + ": heavy traffic");
                }
                Thread.sleep(1000);
            }
    }

}
