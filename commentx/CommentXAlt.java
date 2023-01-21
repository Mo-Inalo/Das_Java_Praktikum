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
 * @version 14.06.2008
 */
public class CommentXAlt {

    /**
     * Definiert einen endlichen Automaten zu einer Zustandsübergangstabelle.
     * Zustandsübergangstabelle wird so gefüllt, dass
     * alle Blockkommentare und Zeilenkommentare aus einem Java-Quelltext entfernt werden.
     * Erhält die Zeilenstruktur des Quelltextes.
     * Eingabe: Quelltext von Standardeingabe
     * Ausgabe: Quelltext ohne Kommentare auf Standardausgabe
     * Quelltext darf keine Stringliterale, Zeichenkonstanten
     * oder Unicode-Ersatzdarstellungen enthalten.
     * @param args nicht verwendet
     * @throws IOException bei Lese- oder Schreibfehler
     */
    public static void main(final String[] args) throws IOException {
        final char OTHER = '\0';
        final char LAST = '\0';

        final Field<State, Character, Transition> auto = new Field<State, Character, Transition>();
        auto.put(Source, '/', new Transition(Into));
        auto.put(Source, OTHER, new Transition(Source, LAST));
        auto.put(Into, '/', new Transition(Line));
        auto.put(Into, '*', new Transition(Comment));
        auto.put(Into, OTHER, new Transition(Source, '/', LAST));
        auto.put(Comment, '*', new Transition(Outof));
        auto.put(Comment, '\n', new Transition(Comment, LAST));
        auto.put(Comment, '\r', new Transition(Comment, LAST));
        auto.put(Comment, OTHER, new Transition(Comment));
        auto.put(Outof, '/', new Transition(Source, ' '));
        auto.put(Outof, '*', new Transition(Outof));
        auto.put(Outof, '\n', new Transition(Comment, LAST));
        auto.put(Outof, '\r', new Transition(Comment, LAST));
        auto.put(Outof, OTHER, new Transition(Comment));
        auto.put(Line, '\n', new Transition(Source, LAST));
        auto.put(Line, '\r', new Transition(Source, LAST));
        auto.put(Line, OTHER, new Transition(Line));

        State state = Source;

        for(int c = System.in.read(); c >= 0; c = System.in.read()) {
            final char chr = (char)c;
            Transition transition = auto.get(state, chr);
            if(transition == null)
                transition = auto.get(state, OTHER);
            for(final char x: transition.output())
                System.out.print(x == LAST?  chr:  x);
            state = transition.next();
        }
    }
}
