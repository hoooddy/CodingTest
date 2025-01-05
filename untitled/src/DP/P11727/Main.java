package DP.P11727;

import java.util.*;
import java.io.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        // n = 1 -> 1
        // n = 2 -> 3
        // n = 3 -> 5
        // n = 4 -> 11
        // n = 5 -> 21
        // n = (n - 1) + 2*(n - 2)

        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        dp[1] = 1;

        for(int n = 2; n <= N; n++) {
            dp[n] = (dp[n - 1] + 2 * dp[n - 2]) % 10007;

        }

        sb.append(dp[N]);
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
