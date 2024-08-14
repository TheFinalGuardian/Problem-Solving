import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * MaximumDistance
 * USACO Bronze Guide, Codeforces - Basic Complete Search
 * https://codeforces.com/gym/102951/problem/A
 * 8/14/2024
 */
public class MaximumDistance {
    public static void main(String[] args) {
        int[] xCoords;
        int[] yCoords;
        Kattio io = new Kattio();
        
        int n = io.nextInt();
        
        // populate xCoords
        xCoords = new int[n];
        for (int i = 0; i < n; i++) {
            xCoords[i] = io.nextInt();
        }
        
        // populate yCoords
        yCoords = new int[n];
        for (int i = 0; i < n; i++) {
            yCoords[i] = io.nextInt();
        }

        int highestSqDist = 0;

        // i being the first coordinate index
        for (int i = 0; i < n; i++) {
            // j being the second coordinate index
            for (int j = 0; j < n; j++) {
                highestSqDist = Math.max(highestSqDist, distSq(xCoords[i], yCoords[i], xCoords[j], yCoords[j]));
            }
        }

        io.print(String.valueOf(highestSqDist));
        io.close();
    }

    static int distSq(int x1, int y1, int x2, int y2) { return (int) (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); } 
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
