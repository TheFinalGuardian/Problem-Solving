// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class LostCow {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("lostcow.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("lostcow.out"));

		String line = r.readLine();
		String[] lineArr = line.split(" ");
		int x = Integer.parseInt(lineArr[0]), y = Integer.parseInt(lineArr[1]);
		int boundDiffLast = 0;
		int boundDiff = 1;
		int distance = 0;

		while (!(y >= x && x + boundDiff >= y || y <= x && x + boundDiff <= y)) {
			distance += Math.abs(boundDiff - boundDiffLast);
			boundDiffLast = boundDiff;
			boundDiff = -2*boundDiff;
		}

		distance += Math.abs(x + boundDiffLast - y);
		pw.println(distance);
		pw.close();
	}
}
