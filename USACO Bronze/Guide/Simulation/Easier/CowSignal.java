import java.io.*;

class CowSignal {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("cowsignal.in"));
    BufferedWriter bw = new BufferedWriter(new FileWriter("cowsignal.out"));

    String line = br.readLine();
    String[] split = line.split(" ");
    int k = Integer.parseInt(split[2]);
    int m = Integer.parseInt(split[0]);
    line = br.readLine();

    // iterate through each line
    for (int a = 0; a < m; a++) {
      StringBuilder sb = new StringBuilder();
      for (char j : line.toCharArray()) {
        // repeat each character k times
        for (int l = 0; l < k; l++) {
          sb.append(j);
        }
      }

      // add the enter
      sb.append('\n');
      
      // number of lines
      for (int i = 0; i < k; i++) {
        // write to file
        bw.write(sb.toString());

        // testing
        System.out.println(sb.toString());
      }

      line = br.readLine();
    }

    br.close();
    bw.close();
  }
}