// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MowingTheField {
	public static void main(String[] args) throws IOException {
		Stepper stepper = new Stepper("mowing");
		Coordinate pos = new Coordinate(0, 0);
		ArrayList<Coordinate> trail = new ArrayList<Coordinate>();
		
		Coordinate next = stepper.nextStep();
		int minDist = Integer.MAX_VALUE;
		while (true) {
			trail.add(pos.copy());
			pos.add(next);
			int i = lastIndexOf(trail, pos);

			if (i != -1) {
				minDist = Math.min(minDist, trail.size() - i);
			}
			try { next = stepper.nextStep(); } 
			catch(Exception e) { break; }
		}

		stepper.println((minDist == Integer.MAX_VALUE) ? -1 : minDist);
		stepper.close();
	}

	private static int lastIndexOf(ArrayList<Coordinate> arr, Coordinate c) {
		for (int i = arr.size() - 1; i >= 0; i--) {
			if (arr.get(i).equals(c)) {
				return i;
			}
		}
		return -1;
	}
}

class Stepper {
	Kattio io;
	int n;
	int stepsLeft;
	Coordinate currDir;

	public Stepper(String name) throws IOException {
		io = new Kattio(name);
		n = io.nextInt();
		stepsLeft = 0;
		currDir = convert('N');
	}

	public int getN() {
		return n;
	}

	public Coordinate nextStep() throws IOException {
		if (stepsLeft == 0) {
			currDir = convert(io.next().charAt(0));
			stepsLeft = io.nextInt();
		}

		stepsLeft--;
		return currDir;
	}

	private Coordinate convert(char dir) {
		switch (dir) {
			case 'N': return new Coordinate(0, 1);
			case 'S': return new Coordinate(0, -1);
			case 'E': return new Coordinate(1, 0);
			case 'W': return new Coordinate(-1, 0);
		}
		return null;
	}

	public void println(int i) throws IOException {
		io.println(i);
	}

	public void println(String s) {
		io.println(s);
	}

	public void close() throws IOException {
		io.close();
	}
}

class Coordinate {
	private int x;
	private int y;
 
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinate copy() {
		return new Coordinate(this.getX(), this.getY());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void set(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public void set(Coordinate point) {
		this.set(point.getX(), point.getY());
	}

	public boolean equals(Coordinate point) {
		return this.x == point.getX() && this.y == point.getY();
	}

	public void add (int x, int y) {
		this.x += x;
		this.y += y;
	}

	public void add(Coordinate point) {
		this.add(point.getX(), point.getY());
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
