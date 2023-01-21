/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Vorlesungsverzeichnis
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package lecture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Repräsentiert ein Vorlesungsverzeichnis mit einfachen Abfragemöglichkeiten.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class Lectures {
    /**
     * Datenbasis: Liste von Tripel (Studiengruppe, Vorlesung, Referent)
     */
    final private List<List<String>> db;

    /**
     * Lädt die Datenbasis aus einer Textdatei.
     * Zeilenformat der Textdatei: "Studiengruppe:Vorlesung:Referent"
     * @param filename Name der Textdatei
     * @return Datenbasis
     * @throws IOException bei Lesefehlern
     */
    public static List<List<String>> load(final String filename) throws IOException {
        final List<List<String>> result = new ArrayList<List<String>>();

        final BufferedReader br = new BufferedReader(new FileReader(filename));
        for(String line = br.readLine(); line != null; line = br.readLine())
            result.add(Arrays.asList(line.split(":")));
        br.close();
        return result;
    }

    /**
     * Lädt die Datenbasis von einer Textdatei.
     * @param filename Dateiname
     * @throws IOException
     */
    public Lectures(final String filename) throws IOException {
        db = load(filename);
    }

    /**
     * Liefert eine alphabetisch sortierte Liste mit den Titeln aller Vorlesungen.
     * @return Vorlesungsliste
     */
    public List<String> titles() {
        final Set<String> s = new TreeSet<String>();
        for(final List<String> f: db)
            s.add(f.get(1));
        return new ArrayList<String>(s);
    }

    /**
     * Liefert die Menge der Dozenten, die zwei oder mehr Vorlesungen halten.
     * @return die Menge dieser Dozenten
     */
    public Set<String> workaholics() {
        final Set<String> tmp = new HashSet<String>();
        final Set<String> set = new HashSet<String>();
        for(final List<String> entry: db) {
            final String lecturer = entry.get(2);
            if(tmp.contains(lecturer))
                set.add(lecturer);
            else
                tmp.add(lecturer);
        }
        return set;
    }

    /**
     * Liefert eine Map, die Studiengruppen auf Listen von Vorlesungstiteln abbildet.
     * @return die Map
     */
    public Map<String, List<String>> groupToTitles() {
        final Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(final List<String> entry: db) {
            final String group = entry.get(0);
            final String title = entry.get(1);
            List<String> subjects = map.get(group);
            if(subjects == null) {
                subjects = new ArrayList<String>();
                map.put(group, subjects);
            }
            subjects.add(title);
        }
        return map;
    }

    /**
     * Testprogramm für Vorlesungsverzeichnis.
     * @param args Name der Eingabedatei der Datenbasis (optional)
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        final String filename;
        if (args.length > 0)
            filename = args[0];
        else {
            filename = "db.txt";
            final PrintWriter pw = new PrintWriter(filename);
            pw.print(
                "IFB2:Softwareentwicklung II:Schiedermeier\n" +
                "IFS2:Softwareentwicklung II:Köhler\n" +
                "IFB4:Compilerbau:Schiedermeier\n" +
                "IFB4:Datenbanken:Bayer");
            pw.close();
        }
        final Lectures l = new Lectures(filename);
        System.out.println(l.titles());
        System.out.println(l.workaholics());
        System.out.println(l.groupToTitles());
    }

}
