//                I know stuff but probably my rating tells otherwise...
//                   Kaafi din baad aaye hai... Swagat nhi kariyega???
//               Kya hua, code samajhne ki koshish kar rhe ho?? Mat karo,
//                      mujhe bhi samajh nhi aata kya likha hai


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class tourists {
    static long n, n1, n2, c1, c2;
    static long xy[] = new long[2];

    static void Mangni_ke_bail_ke_dant_na_dekhal_jye() {
        try {
            while (true) {

                n = ni();
                c1 = ni();
                n1 = ni();
                c2 = ni();
                n2 = ni();
                xy = new long[2];

                long gcd[] = extended_gcd(n1, n2);

                xy[0] = gcd[1];
                xy[1] = gcd[2];
                long g = gcd[0];

                //pl(Arrays.toString(gcd));

                if (n % g != 0) {
                    pl("failed");
                    continue;
                }

                xy[0] = xy[0] * (n / g);

                long mod = n2 / g;

                xy[0] = (xy[0] % mod + mod) % mod;

                xy[1] = (n - n1 * xy[0]) / n2;

                if (xy[1] < 0) {
                    pl("failed");
                    continue;
                }

                long temp_ans = c1 * xy[0] + c2 * xy[1];
                long ansx = xy[0], ansy = xy[1];

                for (int i = 1; i < mod; i++) {
                    xy[0] += mod;
                    xy[1] -= n1 / g;

                    if (xy[1] < 0) break;

                    if (c1 * xy[0] + c2 * xy[1] < temp_ans) {
                        temp_ans = c1 * xy[0] + c2 * xy[1];
                        ansx = xy[0];
                        ansy = xy[1];
                    }
                }
                pl(ansx + " " + ansy);
            }
        } catch (Exception e) {
        }
    }

    static long[] extended_gcd(long p, long q) {
        if (q == 0)
            return new long[]{p, 1, 0};

        long[] vals = extended_gcd(q, p % q);
        long d = vals[0];
        long a = vals[2];
        long b = vals[1] - (p / q) * vals[2];
        //pl(a+" "+b + " "+p+" "+q);
        return new long[]{d, a, b};
    }


    //----------------------------------------The main code ends here------------------------------------------------------
    /*-------------------------------------------------------------------------------------------------------------------*/
    //-----------------------------------------Rest's all dust-------------------------------------------------------------


    static int mod9 = 1_000_000_007;
    //static int n, m, l, k, t;
    static AwesomeInput input;
    static PrintWriter pw;

    static long power(long a, long b) {
        long x = max(a, b);
        if (b == 0) return 1;
        if ((b & 1) == 1) return a * power(a * a, b >> 1);
        return power(a * a, b >> 1);
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