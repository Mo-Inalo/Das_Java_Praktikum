/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Verkehrsüberwachung
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package trafficcontrol;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Detector für manuelle Eingabe.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 29.06.2008
 */
class Detector {

    /**
     * Erzeugt einen Server, der eine 1 an den Client schickt,
     * sobald an der Konsole eine Eingabe erfolgt.
     * @param args Port, an dem der Server lauscht
     * @throws Exception bei Socketfehlern
     */
    public static void main(final String[] args) throws Exception {
        System.out.println("Please hit [enter] when you see a car ...");

        final ServerSocket serversocket = new ServerSocket(Integer.parseInt(args[0]));
        final Socket socket = serversocket.accept();
        final OutputStream output = socket.getOutputStream();
        while(true) {
            while(System.in.read() != '\n')
                ;
            output.write(1);
            output.flush();
        }
    }
}
