// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class MeasuringTraffic {
  public static void main(String[] args) throws IOException {
    BufferedReader r = new BufferedReader(new FileReader("traffic.in"));
    PrintWriter pw = new PrintWriter(new FileWriter("traffic.out"));

    int n = Integer.parseInt(r.readLine());

    ArrayList<String> onoff = new ArrayList<String>();
    ArrayList<Integer> bottom = new ArrayList<Integer>();
    ArrayList<Integer> top = new ArrayList<Integer>();

    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(r.readLine());
      onoff.add(st.nextToken());
      bottom.add(Integer.parseInt(st.nextToken()));
      top.add(Integer.parseInt(st.nextToken()));
    }
    
    int si = onoff.indexOf("none");
    int lower = bottom.get(si);
    int upper = top.get(si);
    for (int i = si; i < n; i++) {
      if (onoff.get(i).equals("off")) {
        lower -= top.get(i);
        upper -= bottom.get(i);
      } else if (onoff.get(i).equals("on")) {
        lower += bottom.get(i);
        upper += top.get(i);
      } else {
        lower = Math.max(lower, bottom.get(i));
        upper = Math.min(upper, top.get(i));
      }
    }
    int[] after = {lower, upper};

    si = onoff.lastIndexOf("none");
    lower = bottom.get(si);
    upper = top.get(si);
    for (int i = si; i >= 0; i--) {
      if (onoff.get(i).equals("none")) {
        lower = Math.max(lower, bottom.get(i));
        upper = Math.min(upper, top.get(i));
      } else if (onoff.get(i).equals("on")) {
        lower -= top.get(i);
        upper -= bottom.get(i);
      } else {
        lower += bottom.get(i);
        upper += top.get(i);
      }
    }
    int[] before = {lower, upper};

    pw.println(String.valueOf(before[0]) + " " + String.valueOf(before[1]));
    pw.println(String.valueOf(after[0]) + " " + String.valueOf(after[1]));
    
    pw.close();
  }
}
