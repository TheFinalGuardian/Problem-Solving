import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.awt.Point;

/**
 * Triangles
 * USACO Bronze Guide, USACO - Basic Complete Search
 * 8/17/2024
 */
public class Triangles {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("triangles");
        int n = io.nextInt();

        LinkedList<Point> points = new LinkedList<Point>();
        for (int i = 0; i < n; i++) {
            points.add(new Point(io.nextInt(), io.nextInt()));
        }

        int maxAreaDoubled = 0;

        for (Point point : points) {
            maxAreaDoubled = Math.max(maxAreaDoubled, getArea(point, points));
        }

        io.print(maxAreaDoubled);
        io.close();
    }

    static int getArea(Point cornerPoint, LinkedList<Point> points) {
        int x = 0, y = 0;

        for (Point point : points) {
            if (!point.equals(cornerPoint)) {
                if (point.getX() == cornerPoint.getX()) {
                    y = Math.max(y, (int) Math.abs(point.getY() - cornerPoint.getY()));
                }

                if (point.getY() == cornerPoint.getY()) {
                    x = Math.max(x, (int) Math.abs(point.getX() - cornerPoint.getX()));
                }
            }
        }

        return x * y;
    }
}

class Kattio extends PrintWriter {
    private BufferedReader r;
    private StringTokenizer st;
    // standard input
    public Kattio() { this(System.in, System.out); }
    public Kattio(InputStream i, OutputStream o) {
        super(o);
        r = new BufferedReader(new InputStreamReader(i));
    }
    // USACO-style file input
    public Kattio(String problemName) throws IOException {
        super(problemName + ".out");
        r = new BufferedReader(new FileReader(problemName + ".in"));
    }
    // returns null if no more input
    public String next() {
        try {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(r.readLine());
            return st.nextToken();
        } catch (Exception e) {}
        return null;
    }
    public int nextInt() { return Integer.parseInt(next()); }
    public double nextDouble() { return Double.parseDouble(next()); }
    public long nextLong() { return Long.parseLong(next()); }
}
