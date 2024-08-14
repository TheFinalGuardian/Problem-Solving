// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BucketList {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("blist.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("blist.out"));

		int n = Integer.parseInt(r.readLine());

		ArrayList<Integer> startTimes = new ArrayList<Integer>();
		ArrayList<Integer> endTimes = new ArrayList<Integer>();
		ArrayList<Integer> buckets = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(r.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			startTimes.add(s);
			endTimes.add(e);
			buckets.add(b);
		}

		int mostBuckets = 0;
		for (int i = 1; i <= 1000; i++) {
			int currentBuckets = 0;
			for (int j = 0; j < n; j++) {
				if (i >= startTimes.get(j) && i < endTimes.get(j)) {
					currentBuckets += buckets.get(j);
				}
			}

			if (currentBuckets > mostBuckets) {
				mostBuckets = currentBuckets;
			}
		}
		pw.print(String.valueOf(mostBuckets));
		r.close();
		pw.close();
	}
}
