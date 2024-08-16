import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * DaisyChains
 * USACO Bronze Guide, USACO - Basic Complete Search
 * https://usaco.org/index.php?page=viewproblem2&cpid=1060
 * 8/15/2024
 */
public class DaisyChains {
    public static void main(String[] args) {
        Kattio io = new Kattio();
        int n = io.nextInt();

        int[] flowers = new int[n];
        for (int i = 0; i < n; i++) {
            flowers[i] = io.nextInt();
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n; j > i; j--) {
                if (containsAvg(flowers, i, j)) {
                    count++;
                }
            }
        }

        io.print(count);
        io.close();
    }

    static boolean containsAvg(int[] arr, int i, int j) {
        double avg = 0;
        for (int k = i; k < j; k++) {
            avg += arr[k];
        }

        avg /= (j - i);

        if (avg % 1 != 0) {
            return false;
        }

        for (int k = i; k < j; k++) {
            if (arr[k] == (int) avg) {
                return true;
            }
        }

        return false;
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
