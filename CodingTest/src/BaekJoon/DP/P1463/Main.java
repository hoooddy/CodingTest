package BaekJoon.DP.P1463;

import java.io.*;
import java.util.*;


// 1. 3으로 나누어 떨어지면 3으로 나눈다.
// 2. 2로 나누어 떨어지면 2로 나눈다.
// 3. 1을 뺀다.
public class Main {

    static int dp[];
    static int answer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];

        Arrays.fill(dp, -1);
        dp[0] = dp[1] = 0;

        answer = Recur(N);

        sb.append(answer);

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static int Recur(int N) {

        if(dp[N] != -1){
            return dp[N];
        }

        // 1. 6으로 나누어 떨어질 경우
        // 1-1. 3으로 나눈다.
        // 1-2. 2로 나눈다.
        // 1-3. 1을 뺸다.
        if(N % 6 == 0) {
            dp[N] = Math.min(Recur(N / 3), Math.min(Recur(N / 2), Recur(N - 1))) + 1;
        }

        // 2. 3으로 나누어 떨어질 경우
        // 2-1. 3으로 나눈다.
        // 2-2. 1을 뺀다.
        else if(N % 3 == 0) {
            dp[N] = Math.min(Recur(N / 3), Recur(N - 1)) + 1;
        }

        // 3. 2로 나누어 떨어질 경우
        // 3-1. 2로 나눈다.
        // 3-2. 1을 뺀다.
        else if(N % 2 == 0) {
            dp[N] = Math.min(Recur(N / 2), Recur(N - 1)) + 1;

        }

        // 4. 1을 뺀다.
        else {
            dp[N] = Recur(N - 1) + 1;
        }

        return dp[N];

    }
}
