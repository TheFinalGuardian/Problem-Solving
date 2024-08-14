// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class CircularBarn {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("cbarn.out"));
		int n = Integer.parseInt(r.readLine());
		ArrayList<Integer> barn = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			barn.add(Integer.parseInt(r.readLine()));
		}
		r.close();
		
		int minSteps = Integer.MAX_VALUE;
		for (int start = 0; start < n; start++) {
			int totalSteps = 0;
			int roomNum = 1;

			for (int i = start + 1; i < n; i++) {
				totalSteps += barn.get(i) * roomNum;
				roomNum++;
			}

			for (int i = 0; i < start; i++) {
				totalSteps += barn.get(i) * roomNum;
				roomNum++;
			}

			minSteps = Math.min(totalSteps, minSteps);
		}

		pw.print(String.valueOf(minSteps));
		pw.close();
	}
}
