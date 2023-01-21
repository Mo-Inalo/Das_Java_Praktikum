
class Rectangles {


    public static void main(final String[] args) {
        int a = 0;
        final int px = Integer.parseInt(args[a++]);
        final int py = Integer.parseInt(args[a++]);
        final int qx = Integer.parseInt(args[a++]);
        final int qy = Integer.parseInt(args[a++]);
        final int sx = Integer.parseInt(args[a++]);
        final int sy = Integer.parseInt(args[a++]);
        final int tx = Integer.parseInt(args[a++]);
        final int ty = Integer.parseInt(args[a++]);

        final int r1left = Math.min(px, qx);
        final int r1right = Math.max(px, qx);
        final int r1bottom = Math.min(py, qy);
        final int r1top = Math.max(py, qy);
        final int r2left = Math.min(sx, tx);
        final int r2right = Math.max(sx, tx);
        final int r2bottom = Math.min(sy, ty);
        final int r2top = Math.max(sy, ty);

        if(r1right < r2left
                || r1left > r2right
                || r1top < r2bottom
                || r1bottom > r2top)
            System.out.println("disjoint");
        else if(r1right == r2left
                || r1left == r2right
                || r1top == r2bottom
                || r1bottom == r2top)
            if((r1right == r2left  || r1left == r2right)
                    && (r1top == r2bottom  || r1bottom == r2top))
                System.out.println("touching");
            else
                System.out.println("aligned");
        else if(r1left == r2left
                && r1right == r2right
                && r1top == r2top
                && r1bottom == r2bottom)
            System.out.println("same");
        else if(r1left >= r2left
                && r1right <= r2right
                && r1top <= r2top
                && r1bottom >= r2bottom)
            System.out.println("contained");
        else if(r2left >= r1left
                && r2right <= r1right
                && r2top <= r1top
                && r2bottom >= r1bottom)
            System.out.println("contained");
        else
            System.out.println("intersecting");
    }
}
