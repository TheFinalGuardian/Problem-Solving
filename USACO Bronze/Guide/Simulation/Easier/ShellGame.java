import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class ShellGame {
  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter("shell.out"));
    int[] points = new int[3];

    for (int i = 1; i <= 3; i++) {
      BufferedReader br = new BufferedReader(new FileReader("shell.in"));
      int lines = Integer.parseInt(br.readLine());
      int[] shells = { 1, 2, 3 };

      for (int j = 1; j <= lines; j++) {
        String line = br.readLine();
        int a = Integer.parseInt(line.substring(0, 1)) - 1;
        int b = Integer.parseInt(line.substring(2, 3)) - 1;
        int g = Integer.parseInt(line.substring(4, 5)) - 1;

        int x = shells[a];
        shells[a] = shells[b];
        shells[b] = x;

        if (shells[g] == i) {
          points[i - 1]++;
        }
      }

      br.close();
    }

    bw.write(String.valueOf(Math.max(Math.max(points[0], points[1]), points[2])));
    bw.close();
  }
}