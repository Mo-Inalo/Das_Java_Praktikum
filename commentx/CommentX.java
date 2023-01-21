/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Kommentar-Zapper
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package commentx;

import static commentx.State.*;
import java.io.IOException;

/**
 * Klassenrahmen für Hauptprogramm Kommentar-Zapper.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class CommentX {

    /**
     * Entfernt alle Blockkommentare aus einem Java-Quelltext.
     * Eingabe: Quelltext von Standardeingabe
     * Ausgabe: Quelltext ohne Blockkommentare auf Standardausgabe
     * Quelltext darf keine Zeilenkommentare, Stringliterale, Zeichenkonstanten
     * oder Unicode-Ersatzdarstellungen enthalten.
     * @param args nicht verwendet
     * @throws IOException bei Lese- oder Schreibfehler
     */
    public static void main(final String[] args) throws IOException {
        State state = Source;
        for(int c = System.in.read(); c >= 0; c = System.in.read()) {
            final char chr = (char)c;

            switch(state) {
                case Source:
                    switch(chr) {
                        case '/':
                            state = Into;
                            break;
                        case '*':
                            System.out.print(chr);
                            break;
                        default:
                            System.out.print(chr);
                            break;
                    }
                    break;
                case Into:
                    switch(chr) {
                        case '/':
                            System.out.print(chr);
                            break;
                        case '*':
                            state = Comment;
                            break;
                        default:
                            System.out.print('/');
                            System.out.print(chr);
                            state = Source;
                            break;
                    }
                    break;
                case Comment:
                    switch(chr) {
                        case '/':
                            break;
                        case '*':
                            state = Outof;
                            break;
                        default:
                            break;
                    }
                    break;
                case Outof:
                    switch(chr) {
                        case '/':
                            System.out.print(' ');
                            state = Source;
                            break;
                        case '*':
                            break;
                        default:
                            state = Comment;
                            break;
                    }
                    break;
            }

        }

    }

}
