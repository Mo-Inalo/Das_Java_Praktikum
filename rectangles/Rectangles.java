/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Rechtecke
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package rectangles;

/**
 * Klassenrahmen f�r Programm Rechtecke.
 *
 * @author Klaus K�hler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 27.06.2008
 */
class Rectangles {

    /**
     * Klassifiziert die gegenseitige Lage von zwei achsenparallelen Rechtecken in der Ebene.
     * Gibt eines der folgenden W�rter aus:
     * disjoint Der Durchschnitt der beiden Rechtecke ist leer.
     * same     Lage und Gr��e beider Rechtecke ist gleich.
     * contained Der Durchschnitt der beiden Rechtecke f�llt mit genau einem der Rechtecke zusammen.
     *          Alle Punkte eines der Rechtecke sind auch im anderen enthalten, aber nicht umgekehrt.
     * aligned  Der Durchschnitt der beiden Rechtecke ist eine Linie.
     *          Alle gemeinsamen Punkte liegen auf einer Linie mit L�nge > 0.
     * touching Der Durchschnitt der beiden Rechtecke ist ein Punkt.
     *          Es gibt genau einen gemeinsamen Punkt.
     * intersecting Der Durchschnitt der beiden Rechtecke ist ein anderes Rechteck mit einer Fl�che > 0.
     *
     * @param args gegen�ber liegende Ecken (px,py), (qx,qy) bzw. (sx,sy), (tx,ty) von zwei Rechtecken
     */
    public static void main(final String[] args) {
        int a = 0;
        final int px = Integer.parseInt(args[a++]);
        final int py = Integer.parseInt(args[a++]);
        final int qx = Integer.parseInt(args[a++]);
        final int qy = Integer.parseInt(args[a++]);
        final int sx = Integer.parseInt(args[a++]);
        final int sy = Integer.parseInt(args[a++]);
        final int tx = Integer.parseInt(args[a++]);
        final int ty = Integer.parseInt(args[a++]);

        final int r1left = Math.min(px, qx);
        final int r1right = Math.max(px, qx);
        final int r1bottom = Math.min(py, qy);
        final int r1top = Math.max(py, qy);
        final int r2left = Math.min(sx, tx);
        final int r2right = Math.max(sx, tx);
        final int r2bottom = Math.min(sy, ty);
        final int r2top = Math.max(sy, ty);

        if(r1right < r2left
                || r1left > r2right
                || r1top < r2bottom
                || r1bottom > r2top)
            System.out.println("disjoint");
        else if(r1right == r2left
                || r1left == r2right
                || r1top == r2bottom
                || r1bottom == r2top)
            if((r1right == r2left  || r1left == r2right)
                    && (r1top == r2bottom  || r1bottom == r2top))
                System.out.println("touching");
            else
                System.out.println("aligned");
        else if(r1left == r2left
                && r1right == r2right
                && r1top == r2top
                && r1bottom == r2bottom)
            System.out.println("same");
        else if(r1left >= r2left
                && r1right <= r2right
                && r1top <= r2top
                && r1bottom >= r2bottom)
            System.out.println("contained");
        else if(r2left >= r1left
                && r2right <= r1right
                && r2top <= r1top
                && r2bottom >= r1bottom)
            System.out.println("contained");
        else
            System.out.println("intersecting");
    }
}
