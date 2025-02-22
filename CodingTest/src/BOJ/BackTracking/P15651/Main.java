package BOJ.BackTracking.P15651;

import java.util.*;
import java.io.*;

// N과 M (3)

//1부터 N까지 자연수 중에서 M개를 고른 수열
//같은 수를 여러 번 골라도 된다.

public class Main {

    static int N, M;
    static int[] nums, result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N+1];
        for(int n = 1; n <= N; n++) {
            nums[n] = n;
        }
        result = new int[M];

        BackTracking(0);

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // 같은 수를 여러번 골라도 된다.
    // 방문 했는지에 대한 visited가 없어도 된다.
    // 어디부터 시작할지에 대한 start가 없어도 된다.
    static void BackTracking(int depth) {
        if(depth == M) {
            for(int num : result) {
                sb.append(num + " ");
            }
            sb.append("\n");
            return;
        }

        for(int n = 1; n <= N; n++){
            result[depth] = nums[n];

            BackTracking(depth +1);
        }
    }
}
