//                I know stuff but probably my rating tells otherwise...

//                  Love is no stone, it's soft... more like snow
//                        beautifying everything it covers
//                        And when it falls... you listen

//               Kya hua, code samajhne ki koshish kar rhe ho?? Mat karo,
//                      mujhe bhi samajh nhi aata kya likha hai


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class _101635E {
      static int b;
      static LinkedList<data> adj[] = new LinkedList[10005];
      static void Mangni_ke_bail_ke_dant_na_dekhal_jye() {
            b = ni();
            m = ni();
            for (int i = 0; i < 10005; i++) adj[i] = new LinkedList<>();
            for (int i = 0; i < m; i++) {

                  int v = String_convert_karo(ns());
                  int u = String_convert_karo(ns());

                  ns();
                  adj[u].add(new data(v, ni(), ni()));
            }

            n = ind;

            minimize();

      }
      static int deg[], cost[];
      static int prestige[];

      static void minimize() {

            deg = new int[10005];
            cost = new int[10005];
            prestige = new int[10005];

            Arrays.fill(cost, mod9);

            for (int u = 1; u <= ind; u++) {
                  for (data v : adj[u])
                        deg[v.v]++;
            }

            Queue<Integer> start_from_these = new LinkedList<>();

            for (int u = 1; u <= ind; u++) {
                  if (deg[u] == 0) {
                        start_from_these.add(u);
                        cost[u] = 0;
                        prestige[u] = 0;
                  }
            }

            while (!start_from_these.isEmpty()) {

                  int u = start_from_these.poll();

                  for (data v : adj[u]) {
                        if (cost[v.v] > cost[u] + v.cost) {
                              cost[v.v] = cost[u] + v.cost;
                              prestige[v.v] = prestige[u] + v.pres;
                        } if (cost[v.v] == cost[u] + v.cost && prestige[v.v] < prestige[u] + v.pres)
                              prestige[v.v] = prestige[u] + v.pres;

                        deg[v.v]--;

                        if (deg[v.v] == 0) start_from_these.add(v.v);
                  }
            }

            knapsack_lagao_jake();
      }

      static void knapsack_lagao_jake() {

            int pre[][] = new int[2][10005];
            int cos[][] = new int[2][10005];

            for (int i = 0; i <= b; i++) cos[0][i] = i;

            for (int i = 1; i <= n; i++) {
                  for (int j = 0; j <= b; j++) {
                        int bit = i & 1;
                        pre[bit][j] = pre[bit ^ 1][j];
                        cos[bit][j] = cos[bit ^ 1][j];

                        if (j >= cost[i] && pre[bit][j] < prestige[i] + pre[bit ^ 1][j - cost[i]]) {
                              pre[bit][j] = prestige[i] + pre[bit ^ 1][j - cost[i]];
                              cos[bit][j] = cos[bit ^ 1][j - cost[i]];
                        }
                        if (j >= cost[i] && pre[bit][j] == prestige[i] + pre[bit ^ 1][j - cost[i]]
                                && cos[bit][j] < cos[bit ^ 1][j - cost[i]]) {
                              cos[bit][j] = cos[bit ^ 1][j - cost[i]];
                        }
                  }
            }

            pl(pre[n & 1][b]);
            pl(b - cos[n & 1][b]);

      }


      static HashMap<String, Integer> map = new HashMap<>();
      static int ind = 1;

      static int String_convert_karo(String s) {
            if (map.containsKey(s)) return map.get(s);
            map.put(s, ind++);
            return map.get(s);
      }

      static class data{
            int v, cost, pres;
            data(int a, int b, int c){
                  v = a;
                  cost = b;
                  pres = c;
            }
      }


      //----------------------------------------The main code ends here------------------------------------------------------
      /*-------------------------------------------------------------------------------------------------------------------*/
      //-----------------------------------------Rest's all dust-------------------------------------------------------------


      static int mod9 = 1_000_000_007;
      static int n, m, l, k, t;
      static AwesomeInput input;
      static PrintWriter pw;

      static long power(long a, long b) {
            long x = max(a, b);
            if (b == 0) return 1;
            if ((b & 1) == 1) return a * power(a * a, b >> 1);
            return power(a * a, b >> 1);
      }

      static long modpow(long a, long b, long m) {
            if (b == 0) return a % m;

            if ((b & 1) == 1) return a * modpow(a * a % m, b >> 1, m);
            return modpow(a * a % m, b >> 1, m);
      }

      // The Awesome Input Code is a fast IO method //
      static class AwesomeInput {
            private InputStream letsDoIT;
            private byte[] buf = new byte[1024];
            private int curChar;
            private int numChars;
            private SpaceCharFilter filter;

            private AwesomeInput(InputStream incoming) {
                  this.letsDoIT = incoming;
            }

            public int read() {
                  if (numChars == -1)
                        throw new InputMismatchException();
                  if (curChar >= numChars) {
                        curChar = 0;
                        try {
                              numChars = letsDoIT.read(buf);
                        } catch (IOException e) {
                              throw new InputMismatchException();
                        }
                        if (numChars <= 0)
                              return -1;
                  }
                  return buf[curChar++];
            }

            private long ForLong() {
                  int c = read();
                  while (isSpaceChar(c))
                        c = read();
                  int sgn = 1;
                  if (c == '-') {
                        sgn = -1;
                        c = read();
                  }
                  long res = 0;
                  do {
                        if (c < '0' || c > '9')
                              throw new InputMismatchException();
                        res *= 10;
                        res += c - '0';
                        c = read();
                  }
                  while (!isSpaceChar(c));
                  return res * sgn;
            }

            private String ForString() {
                  int c = read();
                  while (isSpaceChar(c))
                        c = read();
                  StringBuilder res = new StringBuilder();
                  do {
                        res.appendCodePoint(c);
                        c = read();
                  }
                  while (!isSpaceChar(c));

                  return res.toString();
            }

            public boolean isSpaceChar(int c) {
                  if (filter != null)
                        return filter.isSpaceChar(c);
                  return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
            }

            public interface SpaceCharFilter {
                  boolean isSpaceChar(int ch);
            }
      }

      // functions to take input//
      static int ni() {
            return (int) input.ForLong();
      }

      static String ns() {
            return input.ForString();
      }

      static long nl() {
            return input.ForLong();
      }

      //functions to give output
      static void pl() {
            pw.println();
      }

      static void p(Object o) {
            pw.print(o + " ");
      }

      static void pws(Object o) {
            pw.print(o + "");
      }

      static void pl(Object o) {
            pw.println(o);
      }

      // Fast Sort is Radix Sort
      public static int[] fastSort(int[] f) {
            int n = f.length;
            int[] to = new int[n];
            {
                  int[] b = new int[65537];
                  for (int i = 0; i < n; i++) b[1 + (f[i] & 0xffff)]++;
                  for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
                  for (int i = 0; i < n; i++) to[b[f[i] & 0xffff]++] = f[i];
                  int[] d = f;
                  f = to;
                  to = d;
            }
            {
                  int[] b = new int[65537];
                  for (int i = 0; i < n; i++) b[1 + (f[i] >>> 16)]++;
                  for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
                  for (int i = 0; i < n; i++) to[b[f[i] >>> 16]++] = f[i];
                  int[] d = f;
                  f = to;
                  to = d;
            }
            return f;
      }

      public static void main(String[] args) {      //threading has been used to increase the stack size.
            try {
                  input = new AwesomeInput(System.in);
                  pw = new PrintWriter(System.out, true);
                  input = new AwesomeInput(new FileInputStream("/home/saurabh/Desktop/input.txt"));
                  pw = new PrintWriter(new BufferedWriter(new FileWriter("/home/saurabh/Desktop/output.txt")), true);

            } catch (Exception e) {
            }

            new Thread(null, null, "AApan_gand_hawai_dusar_ke_kare_dawai", 1 << 25)  //the last parameter is stack size desired.
            {
                  public void run() {
                        try {
                              double s = System.currentTimeMillis();
                              Mangni_ke_bail_ke_dant_na_dekhal_jye();
                              //System.out.println(("\nExecution Time : " + ((double) System.currentTimeMillis() - s) / 1000) + " s");
                              pw.flush();
                              pw.close();
                        } catch (Exception e) {
                              e.printStackTrace();
                              System.exit(1);
                        }
                  }
            }.start();
      }
}