class Zeller
{
    public static void main(String... args)
    {
        final int t = Integer.parseInt(args[0]);
        final int m = Integer.parseInt(args[1]);
        final int temp = Integer.parseInt(args[2]); // Jahreszahl
        final int j = temp%100;
        final int h = temp/100;
        final int w = (t + 26*(m + 1)/10 + 5*j/4 + h/4 - 1)%7;
        System.out.println(w);
    }
}
