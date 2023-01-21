
package flag;

class FlagDE {


    public static void main(final String[] args) {
        int a = 0;
        @SuppressWarnings("unused")
        final int w = Integer.parseInt(args[a++]);
        final int h = Integer.parseInt(args[a++]);
        @SuppressWarnings("unused")
        final int x = Integer.parseInt(args[a++]);
        final int y = Integer.parseInt(args[a++]);
        if(y < h/3)
            System.out.println("gold");
        else if(y < 2*h/3)
            System.out.println("red");
        else
            System.out.println("black");

    }
}
