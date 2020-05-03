//                I know stuff but probably my rating tells otherwise...

//                  Love is no stone, it's soft... more like snow
//                        beautifying everything it covers
//                        And when it falls... you listen

//               Kya hua, code samajhne ki koshish kar rhe ho?? Mat karo,
//                      mujhe bhi samajh nhi aata kya likha hai


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class _101972G {

      static void Mangni_ke_bail_ke_dant_na_dekhal_jye() {
            t = ni();
            while (t-- > 0) {
                  n = ni();
                  m = ni();
//                  pl(n);
//                  pl(m);
                  int ar[][] = new int[n + 2][m + 2];
                  int up_l[][] = new int[n + 2][m + 2];
                  int up_r[][] = new int[n + 2][m + 2];
                  int dw_l[][] = new int[n + 2][m + 2];
                  int dw_r[][] = new int[n + 2][m + 2];

                  //pl("HERE1");
                  for (int i = 1; i <= n; i++) {
                        for (int j = 1; j <= m; j++) {
                              up_l[i][j] = up_r[i][j] = ar[i][j] = ni();
                              dw_l[i][j] = dw_r[i][j] = ar[i][j];
                        }
                  }

                  //pl("HERE2");

                  int ans = mod9;
                  for (int i = 1; i <= n; i++) {
                        for (int j = 1; j <= m; j++) {

                              up_l[i][j] = max(up_l[i][j], max(up_l[i][j - 1], up_l[i - 1][j]));
                              up_l[i][j] = max(up_l[i][j], up_l[i - 1][j - 1]);
                        }
                  }

                  //pl("HERE3");

                  for (int i = 1; i <= n; i++) {
                        for (int j = m; j >= 1; j--) {
                              up_r[i][j] = max(up_r[i][j], max(up_r[i][j + 1], up_r[i - 1][j + 1]));
                              up_r[i][j] = max(up_r[i][j], up_r[i - 1][j]);
                        }
                  }
                  //pl("HERE4");


                  for (int i = n; i >= 1; i--) {
                        for (int j = 1; j <= m; j++) {
                              dw_l[i][j] = max(dw_l[i][j], max(dw_l[i][j - 1], dw_l[i + 1][j]));
                              dw_l[i][j] = max(dw_l[i][j], dw_l[i + 1][j - 1]);
                        }
                  }

                  //pl("HERE5");

                  for (int i = n; i >= 1; i--) {
                        for (int j = m; j >= 1; j--) {
                              dw_r[i][j] = max(dw_r[i][j], max(dw_r[i + 1][j + 1], dw_r[i][j + 1]));
                              dw_r[i][j] = max(dw_r[i][j], dw_r[i + 1][j]);
                        }
                  }

                  //pl("HERE6");


                  for(int i = 2; i < n; i++){
                        for(int j = 2; j < m; j++){
                              int max = 0, min = mod9;
                              //pl(i+" "+j);
                              max = max(max, max(up_l[i - 1][j - 1], up_r[i - 1][j + 1]));
                              min = min(min, min(up_l[i - 1][j - 1], up_r[i - 1][j + 1]));

                              max = max(max, max(dw_l[i + 1][j - 1], dw_r[i + 1][j + 1]));
                              min = min(min, min(dw_l[i + 1][j - 1], dw_r[i + 1][j + 1]));

                              ans = min(ans, max - min);
                        }
                  }
                  pl(ans);
                  //pl(max);pl(min);
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