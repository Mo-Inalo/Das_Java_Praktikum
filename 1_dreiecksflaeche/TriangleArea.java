import static java.lang.Math.*;

class TriangleArea
{
    public static void main(String... args)
    {
        final double ax = Double.parseDouble(args[0]);
        final double ay = Double.parseDouble(args[1]);
        final double bx = Double.parseDouble(args[2]);
        final double by = Double.parseDouble(args[3]);
        final double cx = Double.parseDouble(args[4]);
        final double cy = Double.parseDouble(args[5]);
	
	final double a = hypot(cx - bx, cy - by);
	final double b = hypot(ax - cx, ay - cy);
	final double c = hypot(bx - ax, by - ay);
	
	final double s = (a + b + c)/2;
	final double area = sqrt(s*(s - a)*(s - b)*(s - c));
	
	System.out.println(area);
    }
}
