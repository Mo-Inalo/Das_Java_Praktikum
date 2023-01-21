/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: S-Bahn in Byteburg
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package byteburg;

/**
 * Klassenrahmen für main-Methode zur S-Bahn-Tarifberechnung.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 24.05.2008
 */
class ByteburgTarif {
    /**
     * Tarifberechnung für ein regelmäßig aufgebautes S-Bahn-Netz.
     * @param args Nummern der Ein- und Ausstiegsstationen
     */
    public static void main(final String[] args) {
        final int lines = 5;
        final int code1 = Integer.parseInt(args[0]);
        final int code2 = Integer.parseInt(args[1]);
        final int codemin = Math.min(code1, code2);
        final int codemax = Math.max(code1, code2);
        final int lineMin = codemin/10;
        final int stationMin = codemin%10;
        final int lineMax = codemax/10;
        final int stationMax = codemax%10;
        int result;

        if(lineMin == lineMax  &&  stationMin + 1 == stationMax)
            result = 1;
        else if(stationMin == 3  && stationMax == 3  && lineMax + 1 == lineMax)
            result = 1;
        else if(stationMin == 3  && stationMax == 3  && lineMax == lines  && lineMin == 1)
            result = 1;
        else if(stationMin + stationMax == 1)
            result = 1;
        else {
            result = 2;    
            if(stationMin < 4  &&  stationMax >= 4)
                result++;
            else if(stationMin >= 4  &&  stationMax < 4)
                result++;
            else if(stationMin >= 4  &&  stationMax >= 4  &&  lineMax != lineMin)
                result += 2;
            if(stationMin == 6)
                result++;
            if(stationMax == 6)
                result++;
        }

        System.out.println(result);

    }
}
