// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BlockGame {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("blocks.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("blocks.out"));

		int n = Integer.parseInt(r.readLine());
		int[] blocks = new int[26];
		Arrays.fill(blocks, 0);
		
		for (int boardNum = 0; boardNum < n; boardNum++) {
			StringTokenizer st = new StringTokenizer(r.readLine());
			char[] side1 = st.nextToken().toCharArray();
			char[] side2 = st.nextToken().toCharArray();
			int[] blocks1 = new int[26];
			Arrays.fill(blocks1, 0);
			int[] blocks2 = new int[26];
			Arrays.fill(blocks2, 0);
			
			for (char c : side1) {
				blocks1[charToIndex(c)]++;
			}
			for (char c : side2) {
				blocks2[charToIndex(c)]++;
			}
			for (int i = 0; i < 26; i++) {
				blocks[i] += Math.max(blocks1[i], blocks2[i]);
			}
		}
		r.close();

		for (int blockCount : blocks) {
			pw.println(String.valueOf(blockCount));
		}
		pw.close();
	}

	private static int charToIndex(char c) {
		if (c == 'a') {
			return 0;
		} if (c == 'b') {
			return 1;
		} if (c == 'c') {
			return 2;
		} if (c == 'd') {
			return 3;
		} if (c == 'e') {
			return 4;
		} if (c == 'f') {
			return 5;
		} if (c == 'g') {
			return 6;
		} if (c == 'h') {
			return 7;
		} if (c == 'i') {
			return 8;
		} if (c == 'j') {
			return 9;
		} if (c == 'k') {
			return 10;
		} if (c == 'l') {
			return 11;
		} if (c == 'm') {
			return 12;
		} if (c == 'n') {
			return 13;
		} if (c == 'o') {
			return 14;
		} if (c == 'p') {
			return 15;
		} if (c == 'q') {
			return 16;
		} if (c == 'r') {
			return 17;
		} if (c == 's') {
			return 18;
		} if (c == 't') {
			return 19;
		} if (c == 'u') {
			return 20;
		} if (c == 'v') {
			return 21;
		} if (c == 'w') {
			return 22;
		} if (c == 'x') {
			return 23;
		} if (c == 'y') {
			return 24;
		} if (c == 'z') {
			return 25;
		} 
		return -1;
	}
}
