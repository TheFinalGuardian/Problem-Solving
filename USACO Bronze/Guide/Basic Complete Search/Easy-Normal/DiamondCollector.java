import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * DiamondCollector
 * USACO Bronze Guide, USACO - Basic Complete Search
 * https://usaco.org/index.php?page=viewproblem2&cpid=639
 * 8/14/2024
 */
public class DiamondCollector {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("diamond");
        int n = io.nextInt();
        int k = io.nextInt();
        
        int[] diamonds = new int[n];
        for (int i = 0; i < n; i++) {
            diamonds[i] = io.nextInt();
        }

        Arrays.sort(diamonds);

        int lower = 0;
        int upper = 0;

        while (upper < diamonds.length) {
            upper++;

            try {
                if (diamonds[upper] - diamonds[lower] > k) {
                    lower++;
                }
            } catch (Exception e) {
                break;
            }
        }

        io.print(upper - lower);
        io.close();
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
