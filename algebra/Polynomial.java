/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Algebraische Strukturen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package algebra;

import static java.lang.Math.max;
import java.util.Arrays;

/**
 * Definiert den Ring der Polynome mit Koeffizienten aus einem Körper F.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
public class Polynomial<F extends Field<F>> implements Ring<Polynomial<F>> {
    private final F[] a;

    /** 
     * Konstruktor erzeugt das Polynom c0 + c1*x + c2*x² + c3*x³ +... .
     * @param c Koeffizienten des Polynoms (in aufsteigender Reihenfolge)
     */
    public Polynomial(final F... c) {
        a = Arrays.copyOf(c, length(c));
    }

    /**
     * Bestimmt die "reduzierte" Länge eines Koeffizientenarrays a,
     * d.h. den um 1 erhöhten größten Index d, für den a[d]!=0 ist.
     * @param a Koeffizientenarray
     * @return
     */
    private int length(final F[] a) {
        int d = a.length - 1;
        while(d >= 0  && a[d].isZero())
            d--;
        return d + 1;
    }

    public boolean isZero() {
        return a.length == 0;
    }

    public boolean isOne() {
        return a.length == 1  && a[0].isOne();
    }

    @SuppressWarnings("unchecked")
    public Polynomial<F> add(final Polynomial<F> p) {
        if(isZero())
            return p;
        if(p.isZero())
            return this;
        final int degree = Math.max(degree(), p.degree());
        final F[] b = (F[])new Field[degree + 1];
        for(int i = 0; i <= degree; i++)
            if(i > degree())
                b[i] = p.get(i);
            else if(i > p.degree())
                b[i] = get(i);
            else
                b[i] = get(i).add(p.get(i));
        return new Polynomial<F>(b);
    }

    @SuppressWarnings("unchecked")
    public Polynomial<F> mult(final Polynomial<F> p) {
        if(isZero()  || p.isZero())
            return new Polynomial<F>();
        if(isOne())
            return p;
        if(p.isOne())
            return this;
        final int d = degree() + p.degree();
        final F[] b = (F[])new Field[d + 1];
        for(int i = 0; i <= degree(); i++)
            for(int j = 0; j <= p.degree(); j++)
                if(b[i + j] == null)
                    b[i + j] = get(i).mult(p.get(j));
                else
                    b[i + j] = b[i + j].add(get(i).mult(p.get(j)));
        return new Polynomial<F>(b);
    }

    @SuppressWarnings("unchecked")
    public Polynomial<F> sub(final Polynomial<F> p) {
        if(p.isZero())
            return this;
        final int degree = Math.max(degree(), p.degree());
        final F[] b = (F[])new Field[degree + 1];
        for(int i = 0; i <= degree; i++)
            if(i > degree())
                b[i] = p.get(i);
            else if(i > p.degree())
                b[i] = get(i);
            else
                b[i] = get(i).sub(p.get(i));
        return new Polynomial<F>(b);
    }

    /**
     * Bestimmt den Grad des Polynoms.
     * @return Grad des Polynoms
     */
    public int degree() {
        return max(0, a.length - 1);
    }

    /**
     * Selektiert den i-ten Koeffizienten.
     * @param i
     * @return den i-ten Koeffizienten
     */
    public F get(int i) {
        if(i < 0)
            throw new IndexOutOfBoundsException("<0");
        return isZero()  || i > degree()?  null:  a[i];
    }

    @Override
    public String toString() {
        if(isZero())
            return "0";
        String s = get(0).toString();
        for(int i = 1; i <= degree(); i++)
            s += "+" + get(i) + "x^" + i;
        return s;
    }

    /**
     * Testprogramm.
     * @param args wird ignoriert
     */
    public static void main(final String[] args) {
        final int p = 5;
        final Zp z1 = new Zp(p, 2);
        final Zp z2 = new Zp(p, 1);
        final Polynomial<Zp> u = new Polynomial<Zp>(z1, z2, z1);
        final Polynomial<Zp> v = new Polynomial<Zp>(z2, z1, z2);
        System.out.printf("[%s]*[%s] = [%s]%n", u, v, u.mult(v));
    }

}
