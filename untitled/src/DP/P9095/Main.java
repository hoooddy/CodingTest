package DP.P9095;

import java.util.*;
import java.io.*;


// n을 1, 2, 3의 합으로 나타내는 방법의 가지 수
// 수를 1개 이상 사용해야 한다.
public class Main {

    static int[] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            // algo
            dp = new int[N + 1];

            if(N == 1){
                sb.append(1 + "\n");
                continue;
            }
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;

            if(N >= 3){
                for(int n = 3; n <= N; n++){
                    dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3];
                }
            }
            sb.append(dp[N]+"\n");
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
