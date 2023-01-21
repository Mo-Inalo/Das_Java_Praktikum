/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Watchdog
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package watchdog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Watchdog überwacht andere Rechner im Netzwerk und wird von diesen selbst überwacht.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
class Watchdog extends Thread {
    /**
     * Anfragewert für überwachten Host.
     */
    private final static int REQUEST = 23;

    /**
     * Antwort des überwachten Host.
     */
    private final static int RESPONSE = 46;

    /**
     * Wartezeit zwischen zwei Anfragen.
     */
    private final static int DELAY_MILLIS = 10*1000;

    /**
     * Liste der überwachten Hosts.
     */
    private final String[] watchedHosts;

    /**
     * Erzeugt einen Watchdog.
     * @param args die Namen der überwachten Hosts
     */
    public Watchdog(final String... args) {
        watchedHosts = args;
    }

    /**
     * Beantwortet Anfragen anderer Watchdogs.
     * @throws IOException
     */
    public void respond() throws IOException {
        final ServerSocket serversocket = new ServerSocket(Integer.parseInt(watchedHosts[0]));
        while(true) {
            final Socket socket = serversocket.accept();
            final InputStream input = socket.getInputStream();
            final OutputStream output = socket.getOutputStream();
            if(input.read() == REQUEST)
                output.write(RESPONSE);
            socket.close();
        }
    }

    /**
     * Baut alle 10 Sekunden eine Verbindung zu den überwachten Hosts auf,
     * schickt ihnen aine Anfrage und überprüft ihre Antwort.
     * Protokolliert den Zustand der überwachten Hosts durch Konsolausgaben up/down.
     * @exception RuntimeException, wenn ein Kontaktversuch misslingt
     */
    @Override
    public void run() {
        try {
            while(true) {
                for(final String host: watchedHosts)
                    try {
                        final String[] tmp = host.split(":");
                        if(tmp.length < 2)
                            continue;
                        final Socket socket = new Socket(tmp[0], Integer.parseInt(tmp[1]));
                        final OutputStream output = socket.getOutputStream();
                        output.write(REQUEST);
                        output.flush();
                        final InputStream input = socket.getInputStream();
                        if(input.read() == RESPONSE)
                            System.out.println(host + " up");
                        else
                            System.out.println(host + " down");
                        socket.close();
                    }
                    catch (final IOException ex) {
                        System.out.println(host + " down");
                    }
                Thread.sleep(DELAY_MILLIS);
            }
        }
        catch (final InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Erzeugt und startet einen Watchdog-Thread zur Überwachunng anderer Hosts
     * und beantwortet dann Anfragen anderer Watchdogs.
     * @param args: ownport host1:port1    host2:port2 ...
     *         z.B. 2000    localhost:2001 localhost:2002
     * @throws IOException bei Kommunikationsfehlern
     */
    public static void main(final String[] args) throws IOException {
        final Watchdog watchdog = new Watchdog(args);
        watchdog.start();
        watchdog.respond();
    }

}
