/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: 11. Reihen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package series;

/**
 * Klassenrahmen für reelle Funktionen, dargestellt durch Potenzreihen.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 25.05.2008
 */
class Series {
    /**
     * Rechengenauigkeit, relativer Fehler für Abbruch der Approximation.
     */
    public final static double EPSILON = 1E-16;

    /**
     * Exponentialfunktion.
     * @param x Argument
     * @return Funktionswert e^x
     */
    public static double exp(final double x) {
        int n = 0;
        double an = 1;
        double sn = 1;
        while(Math.abs(an/sn) > EPSILON) {
            an *= x/(n + 1);
            sn += an;
            n++;
        }
        return sn;
    }

    /**
     * Hyperbolischer Sinus.
     * @param x Argument
     * @return Funktionswert sinh(x)
     */
    public static double sinh(final double x) {
        int n = 1;
        double an = x;
        double sn = x;
        while(Math.abs(an) > EPSILON*Math.abs(sn)) {
            final int nn = 2*n;
            an *= x/nn*x/(nn + 1);
            sn += an;
            n++;
        }
        return sn;
    }

    /**
     * Area Sinus Hyperbolicus: Umkehrfunktion des hyperbolischen Sinus.
     * @param x Argument
     * @return arsinh(x)
     */
    public static double arsinh(final double x) {
        if(x >= 1  ||  x <= -1)
            throw new IllegalArgumentException("arsinh undefined for" + x);
        int n = 1;
        double an = x;
        double sn = x;
        while(Math.abs(an) > EPSILON*Math.abs(sn)) {
            final int nn = 2*n;
            an *= -x*x*(nn - 1)*(nn - 1)/nn/(nn + 1);
            sn += an;
            n++;
        }
        return sn;
    }

    /**
     * Testprogramm für Funktionen.
     * @param args
     */
    public static void main(final String[] args) {
        System.out.println(Math.exp(2));                    // 7.38905609893065
        System.out.println(Series.exp(2));                  // 7.389056098930649
        System.out.println(Math.sinh(0.5));                 // 0.5210953054937474
        System.out.println(Series.sinh(0.5));               // 0.5210953054937474
        System.out.println(Series.arsinh(Series.sinh(.5))); // 0.5000000000000001
        System.out.println(Series.sinh(Series.arsinh(.5))); // 0.49999999999999994
    }

}
