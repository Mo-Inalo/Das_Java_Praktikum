/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: M�xchen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package maexchen;

class Maexchen {
    

    public static void main(final String[] args) {
        final int a = Integer.parseInt(args[0]);
        final int b = Integer.parseInt(args[1]);
        final int min = Math.min(a, b);
        final int max = Math.max(a, b);

        if(min == 1  &&  max == 2)      
            System.out.println(1000);
        else if(min == max)             
            System.out.println(100*min);
        else                            
            System.out.println(10*max + min);
    }
}
