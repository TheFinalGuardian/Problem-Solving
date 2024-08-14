// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class TeamTicTacToe {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("tttt.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("tttt.out"));

		char[][] board = new char[3][3];
		for (int i = 0; i < 3; i++) {
			board[i] = r.readLine().toCharArray();
		}
		r.close();

		ArrayList<Character> singles = new ArrayList<Character>();
		
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				if (!singles.contains(board[i][0])) {
					singles.add(board[i][0]);
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				if (!singles.contains(board[i][0])) {
					singles.add(board[i][0]);
				}
			}
		}

		if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			if (!singles.contains(board[0][0])) {
				singles.add(board[0][0]);
			}
		}

		if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			if (!singles.contains(board[0][2])) {
				singles.add(board[0][2]);
			}
		}
		pw.println(singles.size());

		ArrayList<Pair<Character, Character>> doubles = new ArrayList<Pair<Character, Character>>();
		
		for (int i = 0; i < 3; i++) {
			if (board[i][1] == board[i][0] || board[i][1] == board[i][2] || board[i][2] == board[i][0]) {
				char f = board[i][0];
				char s = (board[i][0] == board[i][1]) ? board[i][2] : board[i][1];
				if (f != s) {
					Pair<Character, Character> pair = new Pair<Character, Character>(f, s);
					if (!doubles.contains(pair)) {
						doubles.add(pair);
					}
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			if (board[1][i] == board[0][i] || board[1][i] == board[2][i] || board[2][i] == board[0][i]) {
				char f = board[0][i];
				char s = (board[0][i] == board[1][i]) ? board[2][i] : board[1][i];
				if (f != s) {
					Pair<Character, Character> pair = new Pair<Character, Character>(f, s);
					if (!doubles.contains(pair)) {
						doubles.add(pair);
					}
				}
			}
		}

		if (board[0][0] == board[1][1] || board[1][1] == board[2][2] || board[0][0] == board[2][2]) {
			char f = board[0][0];
			char s = (board[0][0] == board[1][1]) ? board[2][2] : board[1][1];
			if (f != s) {
				Pair<Character, Character> pair = new Pair<Character, Character>(f, s);
				if (!doubles.contains(pair)) {
					doubles.add(pair);
				}
			}
		}

		if (board[0][2] == board[1][1] || board[1][1] == board[2][0] || board[0][2] == board[2][0]) {
			char f = board[0][2];
			char s = (board[0][2] == board[1][1]) ? board[2][0] : board[1][1];
			if (f != s) {
				Pair<Character, Character> pair = new Pair<Character, Character>(f, s);
				if (!doubles.contains(pair)) {
					doubles.add(pair);
				}
			}
		}
		pw.println(doubles.size());
		pw.close();
	}
}

class Pair<K, V> {
	K first;
	V second;

	public Pair(K first, V second) {
		this.first = first;
		this.second = second;
	}
}
