import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * CountingLiars
 * USACO Bronze Guide, USACO - Basic Complete Search
 * https://usaco.org/index.php?page=viewproblem2&cpid=1228
 * 8/16/2024
 */
public class CountingLiars {
    public static void main(String[] args) {
        Kattio io = new Kattio();
        int n = io.nextInt();

        boolean[] greater = new boolean[n];
        int[] vals = new int[n];

        for (int i = 0; i < n; i++) {
            greater[i] = io.next().equals("G");
            vals[i] = io.nextInt();
        }

        int minLying = Integer.MAX_VALUE;
        int currLying = 0;
        for (int val : vals) {
            for (int j = 0; j < n; j++) {
                if (greater[j] && val < vals[j]) {
                    currLying++;
                } else if (!greater[j] && val > vals[j]) {
                    currLying++;
                }
            }

            minLying = Math.min(minLying, currLying);
            currLying = 0;
        }

        io.print(minLying);
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
