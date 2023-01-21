class Zaehlmass
{
    public static void main(String... args)
    {
        int n = Integer.parseInt(args[0]);
	
	final int gros = n/144;
	n %= 144;
	
	final int schock = n/60;
	n %= 60;
	
	final int dutzend = n/12;
	n %= 12;
	
	System.out.printf("%d %d %d %d%n", gros, schock, dutzend, n);
    }
}
