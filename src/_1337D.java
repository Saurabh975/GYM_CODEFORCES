//                I know stuff but probably my rating tells otherwise...
//                  Kaafi din baad aaye hai... Swagat nhi kariyega???
//               Kya hua, code samajhne ki koshish kar rhe ho?? Mat karo,
//                      mujhe bhi samajh nhi aata kya likha hai


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class _1337D {

    static void Mangni_ke_bail_ke_dant_na_dekhal_jye() {
        t = ni();
        while (t-- > 0) {
            int x = ni(), y = ni(), z = ni();
            TreeSet<Long> sx = new TreeSet<>();
            for (int i = 0; i < x; i++) sx.add(nl());
            TreeSet<Long> sy = new TreeSet<>();
            for (int i = 0; i < y; i++) sy.add(nl());
            TreeSet<Long> sz = new TreeSet<>();
            for (int i = 0; i < z; i++) sz.add(nl());

            long ans1 = khojo_BSDK(sx, sy, sz);
            long ans2 = khojo_BSDK(sy, sz, sx);
            long ans3 = khojo_BSDK(sz, sx, sy);

            pl(min(min(ans1, ans2), ans3));
        }
    }
    static long ar[];
    static long khojo_BSDK(TreeSet<Long> x,TreeSet<Long> y, TreeSet<Long> z) {

        long yar[] = new long[y.size()];
        long zar[] = new long[z.size()];
        int i=0;
        for (long t : y) yar[i++] = t;
        i=0;
        for (long t : z) zar[i++] = t;
        long ans = Long.MAX_VALUE;
        for (long a : x) {
            ar = yar;
            long b = find(a);
            ar = zar;
            long c = find(a);
            //pl(a+" "+b+" "+c);
            ans = min(ans, formula(a, b, c));
        }
        return ans;
    }

    static long formula(long a, long b, long c) {
        return (a - b) * (a - b) + (b - c) * (b - c) + (c - a) * (c - a);
    }

    static long find(long x) {

        int l = 0, r = ar.length - 1;
        int temp = 0;
        while (l <= r) {
            int mid = l + r >> 1;
            if (abs(x - ar[mid]) < abs(x - ar[temp]))
                temp = mid;

            if (ar[mid] < x)
                l = mid + 1;
            else if (ar[mid] > x)
                r = mid - 1;
            else
                break;
        }

        return ar[temp];
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