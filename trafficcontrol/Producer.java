/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Verkehrsüberwachung
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package trafficcontrol;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Producer-Thread zählt die Ereignisse eines Detectors.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class Producer extends Thread {
    /**
     * Host-Adresse des zugeordneten Detectors.
     */
    private final String address;

    /**
     * Port des zugeordneten Detectors.
     */
    private final int port;

    /**
     * Puffer für die Zeitstempel der vom Detector gemeldeten Ereignisse.
     */
    private final Deque<Long> timestamps = new ArrayDeque<Long>();

    /**
     * Erzeugt einen Producer, der einem Detector zugeordnet ist.
     * @param ip IP-Adresse des Detectors "adress:port"
     */
    public Producer(final String ip) {
        final String[] token = ip.split(":");
        address = token[0];
        port = Integer.parseInt(token[1]);
    }

    /**
     * Holt fortlaufend die Ereignisse vom Detector ab und speichert sie.
     */
    @Override
    public void run() {
        try {
            final Socket socket = new Socket(address, port);
            final InputStream input = socket.getInputStream();
            while(true) {
                input.read();
                synchronized (address) {
                    timestamps.add(System.currentTimeMillis());
                }
            }
        }
        catch (final IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Zählt die vom Detector in der letzten Minute gemeldeten Ereignisse.
     * @return die Anzahl der Ereignisse
     */
    public int count() {
        synchronized (address) {
            while(timestamps.size() > 0
                    && timestamps.peek() < System.currentTimeMillis() - 60000)
                timestamps.remove();
            return timestamps.size();
        }
    }

}
