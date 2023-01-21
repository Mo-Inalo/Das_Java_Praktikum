class Rhombs
{
    public static void main(String... args)
    {
	final int rhombSize = Integer.parseInt(args[0]);
	final int rhombCount = Integer.parseInt(args[1]);

	for(int rhombLine = 0;	rhombLine < rhombCount;  rhombLine++)
	{
	    if(rhombLine > 0)
	    {
		for(int textColumn = 0;  textColumn < rhombSize*rhombCount + rhombCount - 1;  textColumn++)
		    System.out.print('.');
		System.out.println();
	    }

	    for(int textLine = 0;  textLine < rhombSize;  textLine++)
	    {
		for(int rhombColumn = 0;  rhombColumn < rhombCount;  rhombColumn++)
		{
		    if(rhombColumn> 0)
			System.out.print('.');

		    final int ohs = textLine <= rhombSize/2?  2*textLine + 1:  2*(rhombSize - textLine) - 1;
		    final int dots = (rhombSize - ohs)/2;

		    for(int i = 0;  i < dots;  i++)
			System.out.print('.');
		    for(int i = 0;  i < ohs;  i++)
			System.out.print('O');
		    for(int i = 0;  i < dots;  i++)
			System.out.print('.');
		}
		
		System.out.println();
	    }
	}
    }
}
