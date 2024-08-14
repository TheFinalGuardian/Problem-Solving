// Source: https://usaco.guide/general/io

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class BovineShuffle {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
		
		int n = Integer.parseInt(br.readLine());

		String line = br.readLine();
		String[] l = line.split(" ");
		int[] instructions = new int[n];
		for (int i = 0; i < n; i++) {
			instructions[i] = Integer.parseInt(l[i]) - 1;
		}

		line = br.readLine();
		l = line.split(" ");
		int[] ids = new int[n];
		for (int i = 0; i < n; i++) {
			ids[i] = Integer.parseInt(l[i]);
		}

		br.close();

		for (int i = 0; i < 3; i++) {
			int[] arr = new int[n];
			for (int j = 0; j < n; j++) {
				int from = instructions[j];
				arr[j] = ids[from];
			}
			ids = arr;
		}

		BufferedWriter bw = new BufferedWriter(new FileWriter("shuffle.out"));
		for (int i : ids) {
			bw.write(String.valueOf(i));
			bw.newLine();
		}
		bw.close();
	}
}
