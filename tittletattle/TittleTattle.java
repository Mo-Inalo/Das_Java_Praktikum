/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Tittle-Tattle
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package tittletattle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klassenrahmen für Peer-to-peer-Programm.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
public class TittleTattle {

    /**
     * Erzeugt und startet einen Server- und einen Client-Thread,
     * die mit demselben Programm auf anderen Rechnern Ereignisse austauschen.
     * @param hosts: ownport host1:port1    host2:port2 ...
     *          z.B. 2000    localhost:2001 localhost:2002
     */
    public static void main(final String[] hosts) {
        // Startet Client-Thread, der zufällige Ereignisse an die anderen Hosts schickt.
        new Thread() {
            @Override
            public void run() {
                final RandomTicker ticker = new RandomTicker();
                while(true) {
                   final  int number = ticker.tick();   // Ereignis
                    System.out.println("I got " + number);
                    // Schickt Ereignis an alle anderen Hosts
                    for(final String host: hosts)
                        try {
                            final String[] tmp = host.split(":");
                            if(tmp.length < 2)
                                continue;
                            final Socket socket = new Socket(tmp[0], Integer.parseInt(tmp[1]));
                            final OutputStream output = socket.getOutputStream();
                            output.write(number);
                            output.flush();
                            socket.close();
                        }
                        catch (final IOException ex) {
//                            throw new RuntimeException(ex);
                        }
                }
            }
        }
        .start();

        // Startet Server-Thread auf Port ownport=hosts[0]
        new Thread() {
            @Override
            public void run() {
                try {
                    final ServerSocket serversocket = new ServerSocket(Integer.parseInt(hosts[0]));
                    while(true) {
                        final Socket socket = serversocket.accept();
                        final InputStream input = socket.getInputStream();
                        final int number = input.read();
                        System.out.println(socket.getInetAddress() + " got " + number);
                        socket.close();
                    }
                }
                catch (final IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        .start();
    }

}
