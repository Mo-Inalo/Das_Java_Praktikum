
package median;


class Median {


    public static void main(final String[] args) {
        final int a = Integer.parseInt(args[0]);
        final int b = Integer.parseInt(args[1]);
        final int c = Integer.parseInt(args[2]);
        if(a < b)
            if(b < c)
                System.out.println(b);
            else
                if(a < c)
                    System.out.println(c);
                else
                    System.out.println(a);
        else
            if(b < c)
                if(a < c)
                    System.out.println(a);
                else
                    System.out.println(c);
            else
                System.out.println(b);
    }
}
