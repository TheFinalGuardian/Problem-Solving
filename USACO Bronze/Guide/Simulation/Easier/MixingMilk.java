import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class MixingMilk {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("mixmilk.in"));
    BufferedWriter bw = new BufferedWriter(new FileWriter("mixmilk.out"));
    String line1 = br.readLine();
    String line2 = br.readLine();
    String line3 = br.readLine();

    int c1 = Integer.parseInt(line1.split(" ")[0]);
    int m1 = Integer.parseInt(line1.split(" ")[1]);

    int c2 = Integer.parseInt(line2.split(" ")[0]);
    int m2 = Integer.parseInt(line2.split(" ")[1]);

    int c3 = Integer.parseInt(line3.split(" ")[0]);
    int m3 = Integer.parseInt(line3.split(" ")[1]);

    br.close();

    int pourVal;

    for (int i = 1; i <= 100; i++) {
      switch (i % 3) {
        case 1:
          pourVal = pour(c1, m1, c2, m2);
          m2 += pourVal;
          m1 -= pourVal;
          break;

        case 2:
          pourVal = pour(c2, m2, c3, m3);
          m3 += pourVal;
          m2 -= pourVal;
          break;

        case 0:
          pourVal = pour(c3, m3, c1, m1);
          m1 += pourVal;
          m3 -= pourVal;
          break;
      }
    }

    bw.write(String.valueOf(m1));
    bw.write('\n');
    bw.write(String.valueOf(m2));
    bw.write('\n');
    bw.write(String.valueOf(m3));
    bw.write('\n');
    bw.close();
  }

  private static int pour(int c1, int m1, int c2, int m2) {
    int capacity = c2 - m2;
    int leftover = -(capacity - m1);
    return (leftover > 0) ? (m1 - leftover) : m1;
  }
}