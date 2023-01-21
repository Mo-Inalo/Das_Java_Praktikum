/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Polynom
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package polynomial;

import java.util.Arrays;

/**
 * Definiert Polynome mit reellen Koeffizienten.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 25.05.2008
 */
public class Polynomial {
    public static final Polynomial ZERO = new Polynomial();

    public static final Polynomial ONE = new Polynomial(1);

    /**
     * Rechengenauigkeit: akzeptierte Abweichung.
     * Achtung: Nicht zu klein machen, da sonst Polynomgrad falsch berechnet wird.
     */
    public static final double EPS = 1E-12;

    private final double[] a;

    /** Konstruktor erzeugt das Polynom c0 + c1*x + c2*x² + c3*x³ +... .
     * @param c Koeffizienten des Polynoms (in aufsteigender Reihenfolge)
     */
    public Polynomial(final double... c) {
        a = Arrays.copyOf(c, length(c));
    }

    /**
     * Bestimmt die "reduzierte" Länge eines Koeffizientenarrays a,
     * d.h. den um 1 erhöhten größten Index d, für den a[d]!=0 ist.
     * @param a Koeffizientenarray
     * @return
     */
    private static int length(final double[] a) {
        int d = a.length - 1;
        while(d >= 0  && Math.abs(a[d]) < EPS)
            d--;
        return d + 1;
    }

    /**
     * Bestimmt den Grad des Polynoms.
     * @return Grad des Polynoms
     */
    public int degree() {
        return Math.max(0, a.length - 1);
    }

    @Override
    public boolean equals(final Object x) {
        if(x == null)
            return false;
        if(getClass() != x.getClass())
            return false;

        final Polynomial p = (Polynomial) x;
        return Arrays.equals(a, p.a);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(a);
    }

    public boolean isZero() {
        return equals(ZERO);
    }

    public boolean isOne() {
        return equals(ONE);
    }

    /**
     * Multipliziert dieses Polynom mit einer Zahl r.
     * @param r die Zahl
     * @return das Produkt
     */
    public Polynomial mult(final double r) {
        if(isZero()  || r == 0)
            return ZERO;
        final double[] b = new double[degree() + 1];
        for(int i = 0; i <= degree(); i++)
            b[i] = a[i]*r;
        return new Polynomial(b);
    }

    /**
     * Multipliziert dieses Polynom mit einem Polynom p.
     * @param p das Polynom
     * @return das Produkt
     */
    public Polynomial mult(final Polynomial p) {
        if(isZero()  || p.isZero())
            return ZERO;
        if(isOne())
            return p;
        if(p.isOne())
            return this;
        final double[] c = new double[degree() + p.degree() + 1];
        for(int i = 0; i <= degree(); i++)
            for(int j = 0; j <= p.degree(); j++)
                c[i + j] += a[i]*p.a[j];
        return new Polynomial(c);
    }

    /**
     * Addiert dieses Polynom zu einem Polynom p.
     * @param p das Polynom
     * @return die Summe
     */
    public Polynomial add(final Polynomial p) {
        if(isZero())
            return p;
        if(p.isZero())
            return this;
        final double[] c = new double[Math.max(degree(), p.degree()) + 1];
        for(int i = 0; i < c.length; i++)
            c[i] = get(i) + p.get(i);
        return new Polynomial(c);
    }

    /**
     * Subtrahiert von diesem Polynom ein anderes Polynom p.
     * @param p das Polynom
     * @return die Differenz
     */
    public Polynomial sub(final Polynomial p) {
        return add(p.mult(-1));
    }

    /**
     * Selektiert den i-ten Koeffizienten.
     * @param i
     * @return den i-ten Koeffizienten
     */
    public double get(final int i) {
        if(i < 0)
            throw new IndexOutOfBoundsException();
        return isZero()  || i > degree()?  0:  a[i];
    }

    @Override
    public String toString() {
        if(isZero())
            return "0";
        String s = String.format("%+g", a[0]);
        for(int i = 1; i < a.length; i++)
            s = String.format("%+gx^%d", a[i], i) + s;
        return s;
    }

    /**
     * Bestimmt den Wert dieses Polynoms an der Stelle x mittels Horner-Schema.
     * @param x
     * @return
     */
    public double value(final double x) {
        double y = 0;
        for(int i = degree(); i >= 0; i--)
            y = a[i] + x*y;
        return y;
    }

    /**
     * Dividiert diese Polynom durch ein Polynom p mit Rest.
     * @param p das Divisorpolynom
     * @return das Paar [Quotient, Divisionsrest] als Array
     */
    public Polynomial[] div(final Polynomial p) {
        if(p.isZero())
            throw new ArithmeticException("division by zero");
        final int dq = degree() - p.degree() + 1;
        if(dq <= 0)
            return new Polynomial[] {ZERO, this};
        final double[] rest = Arrays.copyOf(a, length(a));
        final double[] quotient = new double[dq];
        final int dr = p.degree();
        final double c = p.a[dr];
        for(int i = dq - 1; i >= 0; i--) {
            final double q = rest[dr + i]/c;
            quotient[i] = q;
            for(int j = 0; j <= dr; j++)
                rest[i + j] -= q*p.a[j];
        }
        return new Polynomial[] {new Polynomial(quotient), new Polynomial(rest)};
    }

    /**
     * Bestimmt den größten gemeinsamen Teiler zweier Polynome g und q
     * mittels euklidischem Algorithmus.
     * @param g das 1. Polynom
     * @param q das 2. Polynom
     * @return der größte gemeinsame Teiler von g und q
     */
    public static Polynomial gcd(Polynomial g, Polynomial q) {
        Polynomial r;
        while(!q.isZero()) {
            r = g.div(q)[1];
            g = q;
            q = r;
        }
        return g;
    }

    /**
     * Testprogramm für Polynomklasse.
     * @param args wird ignoriert
     */
    public static void main(final String[] args) {
        final Polynomial p = new Polynomial(2, -1, 3);
        final Polynomial q = new Polynomial(0, 5, 0, -1, 0);
        System.out.println(q.degree());
        System.out.println(p.add(q));
        System.out.println(p.mult(q));
        System.out.println(p.sub(p).isZero());

        System.out.println(p.value(2));

        final Polynomial n = new Polynomial(1, 0, 1);
        final Polynomial d = new Polynomial(0, 1);
        System.out.println(Arrays.toString(n.div(d)));

        System.out.println("*** gcd");
        final Polynomial pq = p.mult(q);
        final Polynomial pqq = pq.mult(q);
        final Polynomial ppqp = p.mult(pq).mult(p);
        final Polynomial gcd = gcd(pqq, ppqp);
        System.out.println(gcd);
        System.out.println(pq);
        System.out.println(gcd.equals(pq));
        System.out.println(gcd.div(pq)[0].degree() == 0);
    }

}
