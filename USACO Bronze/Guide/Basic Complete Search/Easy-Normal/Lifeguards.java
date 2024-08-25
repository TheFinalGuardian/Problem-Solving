import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Lifeguards
 */
public class Lifeguards{

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("lifeguards");
        int n = io.nextInt();
        
        ArrayList<Integer> startTimes = new ArrayList<Integer>(n);
        ArrayList<Integer> endTimes = new ArrayList<Integer>(n);

        for (int i = 0; i < n; i++) {
            startTimes.add(io.nextInt());
            endTimes.add(io.nextInt());
        }

        int maxCovered = 0;

        for (int i = 0; i < n; i++) {
            int covered = 0;

            for (int j = 0; j < 1000; j++) {
                for (int k = 0; k < n; k++) {
                    if (k != i && startTimes.get(k) <= j && endTimes.get(k) > j) {
                        covered++;
                        break;
                    }
                }
            }

            maxCovered = Math.max(maxCovered, covered);
        }

        io.print(maxCovered);
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
