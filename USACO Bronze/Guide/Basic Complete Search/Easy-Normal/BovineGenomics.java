import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * BovineGeonomics
 * USACO Bronze Guide, USACO - Basic Complete Search
 * https://usaco.org/index.php?page=viewproblem2&cpid=736
 * 8/17/2024
 */
public class BovineGenomics {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("cownomics");
        int n = io.nextInt();
        int m = io.nextInt();

        char[][] spottyGenomes = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = io.next();
            for (int j = 0; j < m; j++) {
                spottyGenomes[i][j] = line.charAt(j);
            }
        }

        char[][] plainGenomes = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = io.next();
            for (int j = 0; j < m; j++) {
                plainGenomes[i][j] = line.charAt(j);
            }
        }

        int numPossible = 0;

        for (int i = 0; i < m; i++) {
            HashSet<Character> spottyChars = new HashSet<Character>();
            HashSet<Character> plainChars = new HashSet<Character>();

            for (int j = 0; j < n; j++) {
                spottyChars.add(spottyGenomes[j][i]);
                plainChars.add(plainGenomes[j][i]);
            }

            spottyChars.retainAll(plainChars);

            if (spottyChars.size() == 0) {
                numPossible++;
            }
        }

        io.print(numPossible);
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
