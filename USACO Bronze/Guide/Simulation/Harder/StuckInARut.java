// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.LinkedList;
import java.awt.Point;

public class StuckInARut {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		int n = io.nextInt();

		int highestEast = 0;
		int eastearnNorth = 0;
		LinkedList<Cow> cows = new LinkedList<Cow>();

		for (int i = 0; i < n; i++) {
			char direction = io.nextChar();
			int x = io.nextInt();
			int y = io.nextInt();
			Cow cow = new Cow(direction, x, y);

			if (direction == 'N')
				eastearnNorth = Math.max(eastearnNorth, x);
			else
				highestEast = Math.max(highestEast, y);
			
			cows.add(cow);
		}

		while (!finished(cows)) {
			forwardAll(highestEast, eastearnNorth, cows);
		}

		for (Cow cow : cows)
			io.println((cow.hasStopped()) ? String.valueOf(cow.getConsumed()) : "Infinity");
		io.close();
	}

	public static void forwardAll(int highestEast, int easternNorth, LinkedList<Cow> cows) {
		for (Cow cow : cows) {
			cow.check(highestEast, easternNorth);
		}
		Cow.ruttify();
	}

	public static boolean finished(LinkedList<Cow> cows) {
		for (Cow cow : cows) {
			if (!(cow.hasStopped() || cow.isInfinite()))
				return false;
		}

		return true;
	}
}

class Cow extends Point {
	boolean stopped;
	boolean infinite;
	int grassConsumed;
	boolean direction;

	public static HashSet<Point> rut = new HashSet<Point>();
	public static HashSet<Point> nextRut = new HashSet<Point>();

	public Cow(char direction, int x, int y) {
		super(x, y);
		this.direction = direction == 'N';
		this.stopped = this.infinite = false;
		grassConsumed = 0;
	}

	private void eat() {
		nextRut.add(super.getLocation());
		grassConsumed++;
	}

	private void move() {
		if (direction)
			super.translate(0, 1);
		else
			super.translate(1, 0);
	}

	private void checkRut() {
		if (rut.contains(super.getLocation()))
			stopped = true;
	}

	private void checkInfinite(int highestEast, int eastearnNorth) {
		if (direction)
			infinite = highestEast < super.getY();
		else
			infinite = eastearnNorth < super.getX();
	}

	public void check(int highestEast, int eastearnNorth) {
		this.checkRut();
		this.checkInfinite(highestEast, eastearnNorth);
		if (!(stopped || infinite)) {
			this.eat();
			this.move();
		}
	}

	public static void ruttify() {
		rut.addAll(nextRut);
		nextRut.clear();
	}

	public boolean hasStopped() { return stopped; }
	public boolean isInfinite() { return infinite; }
	public int getConsumed() { return grassConsumed; }
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
	public char nextChar() { return next().charAt(0); }
}
