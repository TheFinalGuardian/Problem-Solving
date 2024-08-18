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
 * CowGymnastics
 * USACO Bronze Guide, USACO - Basic Complete Search
 * https://usaco.org/index.php?page=viewproblem2&cpid=1060
 * 8/16/2024
 */
public class CowGymnastics {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("gymnastics");
        int numSessions = io.nextInt();
        int numCows = io.nextInt();
        
        int[] first = new int[numCows];
        for (int i = 0; i < numCows; i++) {
            first[i] = io.nextInt();
        }

        int[][] rankings = new int[numSessions - 1][numCows];
        for (int i = 0; i < numSessions - 1; i++) {
            for (int j = 0; j < numCows; j++) {
                rankings[i][j] = io.nextInt();
            }
        }

        HashSet<Pair<Integer, Integer>> pairs = new HashSet<Pair<Integer, Integer>>();
        for (int i = 0; i < numCows - 1; i++) {
            for (int j = i + 1; j < numCows; j++) {
                pairs.add(new Pair<Integer, Integer>(first[i], first[j]));
            }
        }

        HashSet<Pair<Integer, Integer>> toRemove = new HashSet<Pair<Integer, Integer>>();
        for (int[] ranking : rankings) {
            for (Pair<Integer,Integer> pair : pairs) {
                for (int rank : ranking) {
                    if (rank == pair.k) {
                        break;
                    }
                    if (rank == pair.v) {
                        toRemove.add(pair);
                        break;
                    }
                }
            }

            pairs.removeAll(toRemove);
        }

        io.print(pairs.size());
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

class Pair<K, V> {
    public K k;
    public V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }
}
