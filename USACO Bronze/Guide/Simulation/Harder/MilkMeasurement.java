// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;

public class MilkMeasurement {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("measurement");
		HashMap<Integer, Pair> measurements = new HashMap<Integer, Pair>();
		
		int n = io.nextInt();
		for (int i = 0; i < n; i++) {
			measurements.put(io.nextInt(), new Pair(io.next(), io.nextInt()));
		}

		int bessie = 7;
		int elsie = 7;
		int mildred = 7;
		int numChanges = 0;
		boolean[] currLead = new boolean[] {true, true, true};

		for (int i = 1; i <= 100; i++) {
			if (measurements.containsKey(i)) {
				switch (measurements.get(i).name) {
					case "Bessie":
						bessie += measurements.get(i).change;
						break;
					case "Elsie":
						elsie += measurements.get(i).change;
						break;
					case "Mildred":
						mildred += measurements.get(i).change;
						break;
				}

				boolean[] lead;
				if (bessie == elsie && elsie == mildred) {
					lead = new boolean[] {true, true, true};
				} else if (bessie == elsie && bessie == Math.max(bessie, mildred)) {
					lead = new boolean[] {true, true, false};
				} else if (bessie == mildred && bessie == Math.max(bessie, elsie)) {
					lead = new boolean[] {true, false, true};
				} else if (elsie == mildred && elsie == Math.max(bessie, elsie)) {
					lead = new boolean[] {false, true, true};
				} else if (bessie == Math.max(Math.max(bessie, elsie), mildred)) {
					lead = new boolean[] {true, false, false};
				} else if (elsie == Math.max(Math.max(bessie, elsie), mildred)) {
					lead = new boolean[] {false, true, false};
				} else {
					lead = new boolean[] {false, false, true};
				} 

				if (lead[0] != currLead[0] || lead[1] != currLead[1] || lead[2] != currLead[2]) {
					currLead = lead;
					numChanges++;
				}
			}
		}

		io.println(numChanges);
		io.close();
	}
}

class Pair {
	public String name;
	public int change;

	public Pair(String name, int change) {
		this.name = name;
		this.change = change;
	}

	public Pair(String s) {
		StringTokenizer st = new StringTokenizer(s);
		name = st.nextToken();
		change = Integer.parseInt(st.nextToken());
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
		} catch (Exception e) { }
		return null;
	}
	public int nextInt() { return Integer.parseInt(next()); }
	public double nextDouble() { return Double.parseDouble(next()); }
	public long nextLong() { return Long.parseLong(next()); }
}
