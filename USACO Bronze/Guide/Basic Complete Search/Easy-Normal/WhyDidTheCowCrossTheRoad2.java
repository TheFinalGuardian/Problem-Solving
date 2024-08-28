import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class WhyDidTheCowCrossTheRoad2 {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("circlecross");
        char[] points = io.next().toCharArray();
        HashSet<Character> checked = new HashSet<Character>();
        int cross = 0;

        for (int i = 0; i < points.length - 1; i++) {
            if (!checked.contains(points[i])) {
                HashSet<Character> open = new HashSet<Character>();
                boolean done = false;

                for (int j = i + 1; j < points.length; j++) {
                    if (!checked.contains(points[j])) {
                        if (points[j] == points[i]) {
                            done = true;
                            break;
                        }
                        if (open.contains(points[j])) {
                            open.remove(points[j]);
                        } else {
                            open.add(points[j]);
                        }
                    }
                }

                if (!done) {
                    for (int j = 0; j < i; j++) {
                        if (points[j] == points[i]) {
                            done = true;
                            break;
                        }
                        if (open.contains(points[j])) {
                            open.remove(points[j]);
                        } else {
                            open.add(points[j]);
                        }
                    }
                }

                cross += open.size();
                checked.add(points[i]);
            }
        }

        io.print(cross);
        io.close();
    }
}

class Kattio extends PrintWriter {
    private BufferedReader r;
    private StringTokenizer st;

    // standard input
    public Kattio() {
        this(System.in, System.out);
    }

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
        } catch (Exception e) {
        }
        return null;
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
